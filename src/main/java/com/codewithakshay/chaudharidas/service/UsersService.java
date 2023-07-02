package com.codewithakshay.chaudharidas.service;

import java.util.List;

import com.codewithakshay.chaudharidas.entity.Users;

public interface UsersService {

	public List<Users> searchUser(Users users);

	public String adminLogin(Users users);

}
