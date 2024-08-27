package com.mimi.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mimi.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
	
	public List<PostEntity> findByOrderByIdDesc(); // 기본(최신)
	
	 public List<PostEntity> findByOrderByPointDesc(); // 별점순
	 
	 // 검색 조건 두 개 다 있으면서 별점순 
	 public List<PostEntity> findByAddressContainingAndCategoryOrderByPointDesc(String address, String category);
	    
	 // 위치검색만 있으면서 별점순
	 public List<PostEntity> findByAddressContainingOrderByPointDesc(String address);
	    
	    //카테고리만 선택하면서 별점순
	 public List<PostEntity> findByCategoryOrderByPointDesc(String category);
	    
	    // 검색 조건 두 개 다 있으면서 최신순
	 public List<PostEntity> findByAddressContainingAndCategoryOrderByIdDesc(String address, String category);
	    
	    // 위치검색만 있으면서 최신순
	 public List<PostEntity> findByAddressContainingOrderByIdDesc(String address);
	    
	    
	    // 카테고리만 선택하면서 최신순
	 public List<PostEntity> findByCategoryOrderByIdDesc(String category);
	}