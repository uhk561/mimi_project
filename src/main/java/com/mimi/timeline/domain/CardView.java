package com.mimi.timeline.domain;

import java.util.List;

import com.mimi.comment.domain.CommentView;
import com.mimi.post.entity.PostEntity;
import com.mimi.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CardView {
	// 글 1개
	private PostEntity post;
	
	// 글쓴이 정보
	private UserEntity user;
	
	// 댓글 N개
	private List<CommentView> commentList;
	
}
