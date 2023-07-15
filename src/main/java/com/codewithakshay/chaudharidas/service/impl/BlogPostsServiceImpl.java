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
import com.codewithakshay.chaudharidas.service.AuthorService;
import com.codewithakshay.chaudharidas.service.BlogPostsService;
import com.codewithakshay.chaudharidas.service.PostCategoryService;

@Service
public class BlogPostsServiceImpl implements BlogPostsService {

	@Autowired
	private BlogPostsRepository blogPostsRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private PostCategoryService postCategoryService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private PostCategoryRepository postcategoryRepository;

	@Override
	public List<BlogPosts> searchBlogPosts(BlogPosts blogPosts) {

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

		String categoryName = blogPosts.getCategory().getName();
		postCategory = postcategoryRepository.findByName(categoryName);
		if (postCategory == null) {
			postCategory = new PostCategory();
			postCategory.setName(categoryName);
		}

		blogPosts.setAuthor(author);
		blogPosts.setCategory(postCategory);

		BlogPosts savedData = blogPostsRepository.save(blogPosts);

		return savedData;
	}

	@Override
	public void deleteBlog(BlogPosts blogPosts) {

		Optional<BlogPosts> blogData = blogPostsRepository.findById(blogPosts.getBlogPostsId());

		List<BlogPosts> blogByCategory = blogPostsRepository.findByCategory(blogData.get().getCategory());
		if (blogByCategory.size() == 1) {
			blogPostsRepository.deleteById(blogPosts.getBlogPostsId());
			postCategoryService.deleteCategory(blogData.get().getCategory().getId());
			authorService.deleteAuthor(blogData.get().getAuthor().getId());
		} else {
			blogPostsRepository.deleteById(blogPosts.getBlogPostsId());
		}

	}

}
