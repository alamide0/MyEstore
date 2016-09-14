package com.zsy.service;

import com.zsy.domain.User;

public interface UserService {

	void addUser(User user);

	User findUser(User user);

	void activeUser(String activecode);

	User findUserByNameAndPwd(String username, String password);

}
