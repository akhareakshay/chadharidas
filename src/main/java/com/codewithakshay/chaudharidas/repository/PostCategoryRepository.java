package com.codewithakshay.chaudharidas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithakshay.chaudharidas.entity.PostCategory;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {

	public PostCategory findById(long id);

	public PostCategory findByName(String name);

}
