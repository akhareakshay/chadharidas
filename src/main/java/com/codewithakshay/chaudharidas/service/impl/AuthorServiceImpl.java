package com.codewithakshay.chaudharidas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithakshay.chaudharidas.entity.Author;
import com.codewithakshay.chaudharidas.repository.AuthorRepository;
import com.codewithakshay.chaudharidas.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Author> searchAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAuthor(long id) {
		authorRepository.deleteById(id);
	}

}
