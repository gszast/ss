package com.jeweleryguard.service;

import java.util.List;

import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<User> findAll();
}
