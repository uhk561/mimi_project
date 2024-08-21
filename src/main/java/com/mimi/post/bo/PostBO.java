package com.mimi.post.bo;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mimi.comment.bo.CommentBO;
import com.mimi.common.FileManagerService;
import com.mimi.post.entity.PostEntity;
import com.mimi.post.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentBO commentBO;	
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public List<PostEntity> getPostEntityList() {
		return postRepository.findByOrderByIdDesc();
	}
	
	public List<PostEntity> getPostEntitySortList() {
		return postRepository.findByOrderByPointDesc();
	}
	
	
	public PostEntity addPost(int userId, String userLoginId, String content, String restaurantName,
			String address, Date visitDate, String category, int point, MultipartFile file) {

		// 업로드 후 imagePath를 받아옴
		String imagePath = fileManagerService.uploadFile(file, userLoginId);

		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.restaurantName(restaurantName)
				.address(address)
				.visitDate(visitDate)
				.category(category)
				.point(point)
				.imagePath(imagePath)
				.build());
	}
	
	
	@Transactional
	public void deletePostByPostIdUserId(int postId, int userId) {
		// 기존 글 가져오기
		PostEntity post = postRepository.findById(postId).orElse(null);
		if (post == null) {
			log.error("[delete post] postId:{}, userId:{}", postId, userId);
			return;
		}

		// 글 삭제
		postRepository.delete(post);

		// 이미지 있으면 삭제
		fileManagerService.deleteFile(post.getImagePath());

		// 댓글들 삭제
		commentBO.deleteCommentsByPostId(postId);

	}
}
