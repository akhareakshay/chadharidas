package com.codewithakshay.chaudharidas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codewithakshay.chaudharidas.entity.BlogPosts;
import com.codewithakshay.chaudharidas.entity.PostCategory;

@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPosts, Long> {

	public List<BlogPosts> findByCategory(PostCategory category);

//	@Query("SELECT bp FROM BlogPosts bp where bp.postCategoryId = :postCategoryId")
//	public List<BlogPosts> getBlogsByPostCategoryId(long postCategoryId);

}
