package com.codewithakshay.chaudharidas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithakshay.chaudharidas.entity.Users;
import com.codewithakshay.chaudharidas.repository.UsersRepository;
import com.codewithakshay.chaudharidas.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<Users> searchUser(Users users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String adminLogin(Users users) {
//		String password = usersRepository.findPasswordByEmail(users.getEmail());
		Users userData = usersRepository.findByEmail(users.getEmail());
		if(userData == null) {
			return "email & password is incorrect";
		}
		String password = userData.getPassword();
		if (users.getPassword().equalsIgnoreCase(password))
			return "email & password is correct";
		else
			return "email & password is incorrect";
	}

}
