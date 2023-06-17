package com.codewithakshay.chaudharidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithakshay.chaudharidas.entity.PostCategory;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {

}
