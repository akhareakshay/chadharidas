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
@Table(schema = "chaudharidas", name = "post_category")
@Data
public class PostCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_category_id")
	private long id;
	@NotBlank(message = "Category Name is Mandatory")
	private String name;

}
