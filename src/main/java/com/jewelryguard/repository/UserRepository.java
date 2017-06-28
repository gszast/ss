package com.jewelryguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewelryguard.model.User;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository("UserRepository")
@RequestMapping(value = "/user")
public interface UserRepository extends JpaRepository<User, Integer> {
	 User findByEmail(String email);
}
