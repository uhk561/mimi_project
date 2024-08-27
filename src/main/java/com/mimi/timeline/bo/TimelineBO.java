package com.mimi.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mimi.comment.bo.CommentBO;
import com.mimi.comment.domain.CommentView;
import com.mimi.post.bo.PostBO;
import com.mimi.post.entity.PostEntity;
import com.mimi.timeline.domain.CardView;
import com.mimi.user.bo.UserBO;
import com.mimi.user.entity.UserEntity;

@Service
public class TimelineBO {

	@Autowired
	private PostBO postBO;

	@Autowired
	private UserBO userBO;

	@Autowired
	private CommentBO commentBO;
	
	// input: userId(로그인 된 사람 번호)   output: List<CardView>
		public List<CardView> generateCardViewList(Integer userId, String sort,  String addressSearch,
				 String range) {
			List<CardView> cardViewList = new ArrayList<>();

			// 글목록을 가져온다. List<PostEntity>
			List<PostEntity> postList = postBO.getPostEntityList(sort, addressSearch, range);

			// 글목록 반복문 순회
			// PostEntity => CardView     -> cardViewList에 넣기
			for (PostEntity post : postList) {
				CardView card = new CardView();

				// 글
				card.setPost(post);

				// 글쓴이
				UserEntity user = userBO.getUserEntityById(post.getUserId());
				card.setUser(user);

				// 댓글 N개
				List<CommentView> commentViewList = commentBO.generateCommentViewListByPostId(post.getId());
				// 댓글을 카드에 넣는다.
				card.setCommentList(commentViewList);
				
				
				//!!!!!!!!! 반드시 리스트에 넣는다.
				cardViewList.add(card);
			}

			return cardViewList;
		}
		
		
		
		
	}
