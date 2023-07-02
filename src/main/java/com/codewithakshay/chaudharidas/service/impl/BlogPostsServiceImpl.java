package com.codewithakshay.chaudharidas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithakshay.chaudharidas.entity.Author;
import com.codewithakshay.chaudharidas.entity.BlogPosts;
import com.codewithakshay.chaudharidas.entity.PostCategory;
import com.codewithakshay.chaudharidas.repository.AuthorRepository;
import com.codewithakshay.chaudharidas.repository.BlogPostsRepository;
import com.codewithakshay.chaudharidas.repository.PostCategoryRepository;
import com.codewithakshay.chaudharidas.service.BlogPostsService;

@Service
public class BlogPostsServiceImpl implements BlogPostsService {

	@Autowired
	private BlogPostsRepository blogPostsRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private PostCategoryRepository postcategoryRepository;

	@Override
	public List<BlogPosts> searchBlogPosts(BlogPosts blogPosts) {

//		long postCategoryId = postcategoryRepository
//				.findByPostCategoryId(blogPosts.getCategory().getPostCategoryId());
		// TODO Auto-generated method stub
		List<BlogPosts> blogLists = blogPostsRepository.findByCategory(blogPosts.getCategory());
		return blogLists;
	}

	@Override
	public BlogPosts saveBlogs(BlogPosts blogPosts) {
		Author author;
		PostCategory postCategory;

		String authorName = blogPosts.getAuthor().getName();
		author = authorRepository.findByName(authorName);
		if (author == null) {
			author = new Author();
			author.setName(authorName);
		}
//		long authorId = blogPosts.getAuthorId().getId();
//		Author author = authorRepository.findById(authorId);

		String categoryName = blogPosts.getCategory().getName();
		postCategory = postcategoryRepository.findByName(categoryName);
		if (postCategory == null) {
			postCategory = new PostCategory();
			postCategory.setName(categoryName);
		}
//		long categoryId = blogPosts.getCategoryId().getId();
//		PostCategory category = postcategoryRepository.findById(categoryId);

		blogPosts.setAuthor(author);
		blogPosts.setCategory(postCategory);

		BlogPosts savedData = blogPostsRepository.save(blogPosts);
//		System.out.println("saved data : " + save);

		return savedData;
	}

}
