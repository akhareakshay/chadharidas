package com.codewithakshay.chaudharidas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithakshay.chaudharidas.entity.BlogPosts;
import com.codewithakshay.chaudharidas.entity.ValidList;
import com.codewithakshay.chaudharidas.repository.BlogPostsRepository;
import com.codewithakshay.chaudharidas.service.BlogPostsService;
import com.codewithakshay.chaudharidas.util.ChaudharidasErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/blogposts")
@Slf4j
@CrossOrigin(origins = "*")
public class BlogPostsController {

	@Autowired
	private ChaudharidasErrorResponse chaudharidasErrorResponse;

	@Autowired
	private BlogPostsRepository blogPostsRepository;

	@Autowired
	private BlogPostsService blogPostsService;

	@PostMapping(value = "/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateBlogPosts(@Valid @RequestBody BlogPosts blogPosts,
			BindingResult bindingResult) {
		BlogPosts blogPostsData;
		try {
			if (bindingResult.hasErrors())
				return chaudharidasErrorResponse.setValidationErrorResponse(bindingResult);
			blogPostsData = blogPostsService.saveBlogs(blogPosts);
//			blogPostsData = blogPostsRepository.save(blogPosts);
			if (blogPostsData != null)
				return new ResponseEntity<>(blogPostsData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			log.error("Exception while saving or updating blog posts ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping(value = "/list/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateListOfBlogPosts(@Valid @RequestBody ValidList<BlogPosts> blogPostsList,
			BindingResult bindingResult) {
		List<BlogPosts> blogPostsDataList;
		try {
			if (bindingResult.hasErrors())
				return chaudharidasErrorResponse.setValidationErrorResponse(bindingResult);
			blogPostsDataList = blogPostsRepository.saveAll(blogPostsList.getList());
			if (!blogPostsDataList.isEmpty())
				return new ResponseEntity<>(blogPostsDataList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exception while saving or updating list of blog posts lists ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBlogPostsList() {
		List<BlogPosts> blogPostsList;
		try {
			blogPostsList = blogPostsRepository.findAll();
			if (!blogPostsList.isEmpty())
				return new ResponseEntity<>(blogPostsList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exception while getting list of blog posts ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> searchBlogPosts(@RequestBody BlogPosts blogPosts) {
		List<BlogPosts> blogPostDataList;
		try {
			blogPostDataList = blogPostsService.searchBlogPosts(blogPosts);
			if (!blogPostDataList.isEmpty())
				return new ResponseEntity<>(blogPostDataList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exceptio while searching blog posts ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

//	@GetMapping(value = "/getbycategory/{postCategoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> searchBlogPosts(@PathVariable Long postCategoryId) {
//		List<BlogPosts> blogPostDataList;
//		try {
//			blogPostDataList = blogPostsRepository.getBlogsByPostCategoryId(postCategoryId);
//			if (!blogPostDataList.isEmpty())
//				return new ResponseEntity<>(blogPostDataList, HttpStatus.OK);
//			else
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			log.error("Exceptio while searching blog posts ", e);
//			return chaudharidasErrorResponse.setExceptionResponse(e);
//		}
//	}

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteBlogPostById(@RequestBody BlogPosts blogPosts) {
		try {
			blogPostsRepository.deleteById(blogPosts.getBlogPostsId());
			return new ResponseEntity<>("blog post Deleted Successfully", HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception while deleting blog posts ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

}
