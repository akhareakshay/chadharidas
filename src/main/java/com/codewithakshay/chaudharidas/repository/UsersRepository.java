package com.codewithakshay.chaudharidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithakshay.chaudharidas.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	public String findPasswordByEmail(String email);

	public Users findByEmail(String emails);
}
