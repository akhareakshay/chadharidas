package com.codewithakshay.chaudharidas.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(schema = "chaudharidas", name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;
//	@NotBlank(message = "User Name is Mandatory")
//	@Column(name = "user_name")
//	private String userName;
	@NotBlank(message = "Email is Mandatory")
	private String email;
	@NotBlank(message = "Password is Mandatory")
	private String password;
//	@NotBlank(message = "Role is Mandatory")
//	private String role;
	@UpdateTimestamp
	private Timestamp createdAt;

}
