package com.codewithakshay.chaudharidas.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(schema = "chaudharidas", name = "blog_posts")
@Data
public class BlogPosts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blog_post_id")
	private long blogPostsId;
	@NotBlank(message = "Title is Mandatory")
	private String title;
	@NotBlank(message = "Content is Mandatory")
	private String content;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "author_id")
	@NotBlank(message = "Author ID is Mandatory")
	private Author authorId;
	@NotBlank(message = "Category ID is Mandatory")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_category_id")
	private PostCategory postCategoryId;
	@UpdateTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "updated_at")
	private Timestamp updatedAt;

}
