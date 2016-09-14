package com.zsy.dao;

import com.zsy.domain.User;

public interface UserDao {

	User find(User user);

	void addUser(User user);

	User findUserByActiveCode(String activecode);

	void delete(User user);

	void updateState(User user, int state);

	User findUserByNameAndPwd(String username, String password);

}
