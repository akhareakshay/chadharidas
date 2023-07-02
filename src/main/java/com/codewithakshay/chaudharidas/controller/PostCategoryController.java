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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithakshay.chaudharidas.entity.PostCategory;
import com.codewithakshay.chaudharidas.entity.ValidList;
import com.codewithakshay.chaudharidas.repository.PostCategoryRepository;
import com.codewithakshay.chaudharidas.service.PostCategoryService;
import com.codewithakshay.chaudharidas.util.ChaudharidasErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/postcategory")
@CrossOrigin(origins = "*")
public class PostCategoryController {

	@Autowired
	private ChaudharidasErrorResponse chaudharidasErrorResponse;

	@Autowired
	private PostCategoryRepository postCategoryRepository;

	@Autowired
	private PostCategoryService postCategoryService;

	@PostMapping(value = "/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdatePostCategory(@Valid @RequestBody PostCategory postCategory,
			BindingResult bindingResult) {
		PostCategory postCategoryData;
		try {
			if (bindingResult.hasErrors())
				return chaudharidasErrorResponse.setValidationErrorResponse(bindingResult);
			postCategoryData = postCategoryRepository.save(postCategory);
			if (postCategoryData != null)
				return new ResponseEntity<>(postCategoryData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			log.error("Exception while saving or updating post category ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping(value = "/list/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateListOfPostCategory(
			@Valid @RequestBody ValidList<PostCategory> postCategoryList, BindingResult bindingResult) {
		List<PostCategory> postCategoryDataList;
		try {
			if (bindingResult.hasErrors())
				return chaudharidasErrorResponse.setValidationErrorResponse(bindingResult);
			postCategoryDataList = postCategoryRepository.saveAll(postCategoryList.getList());
			if (!postCategoryDataList.isEmpty())
				return new ResponseEntity<>(postCategoryDataList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exception while saving or updating list of post category lists ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPostCategoryList() {
		List<PostCategory> postCategoryList;
		try {
			postCategoryList = postCategoryRepository.findAll();
			if (!postCategoryList.isEmpty())
				return new ResponseEntity<>(postCategoryList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exception while getting list of post category ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> searchPostCategory(@RequestBody PostCategory postCategory) {
		List<PostCategory> postCategoryDataList;
		try {
			postCategoryDataList = postCategoryService.searchPostCategory(postCategory);
			if (!postCategoryDataList.isEmpty())
				return new ResponseEntity<>(postCategoryDataList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exception while searching post category ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<Object> deletePostCategoryById(@RequestBody PostCategory postCategory) {
		try {
			postCategoryRepository.deleteById(postCategory.getId());
			return new ResponseEntity<>("post category Deleted Successfully", HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception while deleting post categorys ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

}
