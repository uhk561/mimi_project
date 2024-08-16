package com.mimi.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mimi.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

}
