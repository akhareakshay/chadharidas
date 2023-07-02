package com.codewithakshay.chaudharidas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(schema = "chaudharidas", name = "author")
@Data
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private long id;
	@NotBlank(message = "Author Name is Mandatory")
	private String name;
//	@NotBlank(message = "Author Email is Mandatory")
	private String email;
//	@NotBlank(message = "Author Bio is Mandatory")
	private String bio;

}
