package com.ssm.service;

import com.ssm.model.User;

import java.util.List;

public interface UserService {

	void saveUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);

    User findUserById(int id);

    List<User> findAll();

}

