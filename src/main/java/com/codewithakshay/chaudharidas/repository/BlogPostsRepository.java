package com.codewithakshay.chaudharidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithakshay.chaudharidas.entity.BlogPosts;

@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPosts, Long> {

}
