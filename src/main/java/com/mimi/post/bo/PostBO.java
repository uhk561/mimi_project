package com.mimi.post.bo;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mimi.common.FileManagerService;
import com.mimi.post.entity.PostEntity;
import com.mimi.post.repository.PostRepository;

@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	
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
	
}
