package com.jewelryguard.service;

import java.util.List;

import com.jewelryguard.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<User> findAll();
}
