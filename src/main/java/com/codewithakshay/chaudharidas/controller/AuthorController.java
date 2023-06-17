package com.codewithakshay.chaudharidas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithakshay.chaudharidas.entity.Author;
import com.codewithakshay.chaudharidas.entity.ValidList;
import com.codewithakshay.chaudharidas.repository.AuthorRepository;
import com.codewithakshay.chaudharidas.service.AuthorService;
import com.codewithakshay.chaudharidas.util.ChaudharidasErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("author")
public class AuthorController {

	@Autowired
	private ChaudharidasErrorResponse chaudharidasErrorResponse;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AuthorService authorService;

	@PostMapping(value = "/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateAuthor(@Valid @RequestBody Author author, BindingResult bindingResult) {
		Author authorData;
		try {
			if (bindingResult.hasErrors())
				return chaudharidasErrorResponse.setValidationErrorResponse(bindingResult);
			authorData = authorRepository.save(author);
			if (authorData != null)
				return new ResponseEntity<>(authorData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			log.error("Exception while saving or updating author ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping(value = "/list/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateListOfAuthor(@Valid @RequestBody ValidList<Author> authorList,
			BindingResult bindingResult) {
		List<Author> authorDataList;
		try {
			if (bindingResult.hasErrors())
				return chaudharidasErrorResponse.setValidationErrorResponse(bindingResult);
			authorDataList = authorRepository.saveAll(authorList.getList());
			if (!authorDataList.isEmpty())
				return new ResponseEntity<>(authorDataList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exception while saving or updating list of author lists ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAuthorList() {
		List<Author> authorList;
		try {
			authorList = authorRepository.findAll();
			if (!authorList.isEmpty())
				return new ResponseEntity<>(authorList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exception while getting list of author ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> searchAuthor(@RequestBody Author author) {
		List<Author> authorDataList;
		try {
			authorDataList = authorService.searchAuthor(author);
			if (!authorDataList.isEmpty())
				return new ResponseEntity<>(authorDataList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exceptio while searching author ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteAuthorById(@RequestBody Author author) {
		try {
			authorRepository.deleteById(author.getAuthorId());
			return new ResponseEntity<>("author Deleted Successfully", HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception while deleting author ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

}
