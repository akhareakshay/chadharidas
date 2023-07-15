package com.codewithakshay.chaudharidas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithakshay.chaudharidas.entity.PostCategory;
import com.codewithakshay.chaudharidas.repository.PostCategoryRepository;
import com.codewithakshay.chaudharidas.service.PostCategoryService;

@Service
public class PostCategoryServiceImpl implements PostCategoryService {

	@Autowired
	private PostCategoryRepository postCategoryRepository;

	@Override
	public List<PostCategory> searchPostCategory(PostCategory postCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategory(long id) {
		postCategoryRepository.deleteById(id);
	}

}
