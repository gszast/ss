package com.jeweleryguard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jeweleryguard.model.User;

@Repository("UserRepository")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	 User findByEmail(String email);
}
