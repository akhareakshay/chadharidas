package com.codewithakshay.chaudharidas.service;

import java.util.List;

import com.codewithakshay.chaudharidas.entity.PostCategory;

public interface PostCategoryService {

	public List<PostCategory> searchPostCategory(PostCategory postCategory);

	public void deleteCategory(long id);

}
