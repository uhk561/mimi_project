package com.mimi.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mimi.user.entity.UserEntity;

public interface UserRepository  extends JpaRepository<UserEntity, Integer> {

	public UserEntity findByLoginId(String loginId);
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
}