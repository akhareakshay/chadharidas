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

import com.codewithakshay.chaudharidas.entity.Users;
import com.codewithakshay.chaudharidas.entity.ValidList;
import com.codewithakshay.chaudharidas.repository.UsersRepository;
import com.codewithakshay.chaudharidas.service.UsersService;
import com.codewithakshay.chaudharidas.util.ChaudharidasErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
@CrossOrigin(origins = "*")
public class UsersController {

	@Autowired
	private ChaudharidasErrorResponse chaudharidasErrorResponse;

	@Autowired
	private UsersService usersService;

	@Autowired
	private UsersRepository usersRepository;

	@PostMapping(value = "/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateUser(@Valid @RequestBody Users users, BindingResult bindingResult) {
		Users usersData;
		try {
			if (bindingResult.hasErrors())
				return chaudharidasErrorResponse.setValidationErrorResponse(bindingResult);
			usersData = usersRepository.save(users);
			if (usersData != null)
				return new ResponseEntity<>(usersData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			log.error("Exception while saving or updating user ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping(value = "/list/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateListOfUsers(@Valid @RequestBody ValidList<Users> usersList,
			BindingResult bindingResult) {
		List<Users> usersDataList;
		try {
			if (bindingResult.hasErrors())
				return chaudharidasErrorResponse.setValidationErrorResponse(bindingResult);
			usersDataList = usersRepository.saveAll(usersList.getList());
			if (!usersDataList.isEmpty())
				return new ResponseEntity<>(usersDataList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exception while saving or updating list of users ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUsersList() {
		List<Users> usersList;
		try {
			usersList = usersRepository.findAll();
			if (!usersList.isEmpty())
				return new ResponseEntity<>(usersList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exception while getting list of users ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> searchUsers(@RequestBody Users users) {
		List<Users> usersDataList;
		try {
			usersDataList = usersService.searchUser(users);
			if (!usersDataList.isEmpty())
				return new ResponseEntity<>(usersDataList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Exceptio while searching users ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteUserById(@RequestBody Users users) {
		try {
			usersRepository.deleteById(users.getUserId());
			return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception while deleting user ", e);
			return chaudharidasErrorResponse.setExceptionResponse(e);
		}
	}

	@PostMapping(value = "/admin/login")
	public ResponseEntity<Object> loginUser(@RequestBody Users users) {
		try {
			String loginStatus = usersService.adminLogin(users);
			if (loginStatus != null)
				return new ResponseEntity<>(loginStatus, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception er) {
			System.out.println("exception : " + er);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
