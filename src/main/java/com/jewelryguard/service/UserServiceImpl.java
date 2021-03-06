package com.jewelryguard.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jewelryguard.model.Role;
import com.jewelryguard.model.User;
import com.jewelryguard.repository.RoleRepository;
import com.jewelryguard.repository.UserRepository;

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Override
	public List<User> findAll() {
		return Lists.newArrayList( userRepository.findAll());
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

}
