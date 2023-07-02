package com.codewithakshay.chaudharidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithakshay.chaudharidas.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	public Author findByName(String name);

	public Author findById(long id);

}
