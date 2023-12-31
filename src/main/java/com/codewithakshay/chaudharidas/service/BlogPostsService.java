package com.codewithakshay.chaudharidas.service;

import java.util.List;

import com.codewithakshay.chaudharidas.entity.BlogPosts;

public interface BlogPostsService {

	public List<BlogPosts> searchBlogPosts(BlogPosts blogPosts);

	public BlogPosts saveBlogs(BlogPosts blogPosts);

	public void deleteBlog(BlogPosts blogPosts);
}
