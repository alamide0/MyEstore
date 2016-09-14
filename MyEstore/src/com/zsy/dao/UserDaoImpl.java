package com.zsy.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.zsy.domain.User;
import com.zsy.util.DaoUtils;

public class UserDaoImpl implements UserDao {

	public User find(User user) {
		QueryRunner runner = new QueryRunner(DaoUtils.getDataSourse());
		String sql = "select * from users where username = ?";
		try {
			User u = null;
			u = runner.query(sql,new BeanHandler<User>(User.class), user.getUsername());
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addUser(User user) {
		QueryRunner runner = new QueryRunner(DaoUtils.getDataSourse());
		String sql = "insert into users values(null,?,?,?,?,?,?,?,null)";
		try {
			runner.update(sql, user.getUsername(),user.getPassword()
								,user.getNickname(),user.getEmail()
								,user.getRole(),user.getState()
								,user.getActivecode()
			);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public User findUserByActiveCode(String activecode) {
		String sql = "select * from users where activecode = ?";
		QueryRunner runner = new QueryRunner(DaoUtils.getDataSourse());
		try {
			return runner.query(sql, new BeanHandler<User>(User.class), activecode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void delete(User user) {
		String sql = "delete from users where id = ?";
		QueryRunner runner = new QueryRunner(DaoUtils.getDataSourse());
		try {
			runner.update(sql, user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateState(User user, int state) {
		String sql = "update users set state=? where id=?";
		QueryRunner runner = new QueryRunner(DaoUtils.getDataSourse());
		try {
			runner.update(sql,state,user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User findUserByNameAndPwd(String username, String password) {
		String sql = "select * from users where username=? and password=?";
		QueryRunner runner = new QueryRunner(DaoUtils.getDataSourse());
		try {
			return runner.query(sql, new BeanHandler<User>(User.class), username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		//return null;
	}

}
