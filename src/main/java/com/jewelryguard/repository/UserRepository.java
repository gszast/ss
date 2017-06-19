package com.jewelryguard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jewelryguard.model.User;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository("UserRepository")
@RequestMapping(value = "/user")
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	 User findByEmail(String email);
}
