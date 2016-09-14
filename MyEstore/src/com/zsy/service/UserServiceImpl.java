package com.zsy.service;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.dbutils.DbUtils;

import com.zsy.dao.UserDao;
import com.zsy.domain.User;
import com.zsy.factory.BasicFactory;

public class UserServiceImpl implements UserService {
	UserDao dao = BasicFactory.getFactory().getInstance(UserDao.class);

	public void addUser(User user) {
		if(dao.find(user)!=null){
			throw new RuntimeException("用户名已经存在");
		}else{
		try{
			user.setRole("user");
			user.setState(0);
			user.setActivecode(UUID.randomUUID().toString());
			
			Properties prop = new Properties();
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.host", "localhost");
			prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.debug", "true");
			Session session = Session.getInstance(prop);
			
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("aa@zsy.com"));
			msg.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			msg.setSubject(user.getUsername()+",来自estore的激活邮件");
			msg.setText(user.getUsername()+",点击如下连接激活账户,如果不能点击请复制到浏览器地址栏访问:http://localhost/MyEstore/servlet/ActiveCodeServlet?activecode="+user.getActivecode());
		
			Transport trans = session.getTransport();
			trans.connect("aa", "123");
			trans.sendMessage(msg, msg.getAllRecipients());
			

			dao.addUser(user);
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		
	}

	public User findUser(User user) {
		
		return dao.find(user);
	}

	public void activeUser(String activecode) {
		User user = dao.findUserByActiveCode(activecode);
		if(user==null){
			throw new RuntimeException("该激活码无效!!");
		}
		if(user.getState()==1){
			throw new RuntimeException("该用户已激活!!");
		}
		
		if((System.currentTimeMillis()-user.getUpdatetime().getTime())>24*3600*1000){
			dao.delete(user);
			throw new RuntimeException("超过激活时间!!");
		}
		dao.updateState(user,1);
	}

	public User findUserByNameAndPwd(String username, String password) {
		
		return dao.findUserByNameAndPwd(username,password);
	}
	

}
