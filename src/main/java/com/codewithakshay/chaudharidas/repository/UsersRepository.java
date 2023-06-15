package com.codewithakshay.chaudharidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithakshay.chaudharidas.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
