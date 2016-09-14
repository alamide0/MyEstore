package com.zsy.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zsy.domain.User;
import com.zsy.factory.BasicFactory;
import com.zsy.service.UserService;
import com.zsy.util.MD5Utils;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = BasicFactory.getFactory().getInstance(
				UserService.class);
		// service.findState();
		String username = request.getParameter("username");
		String password = MD5Utils.md5(request.getParameter("password"));
		User user = service.findUserByNameAndPwd(username, password);
		if (user == null) {
			request.setAttribute("log_msg", "用户名或密码错误！！");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
			return;
		}
		if (user.getState() != 1) {
			request.setAttribute("log_msg", "用户没激活");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
			return;
		}
		request.getSession().setAttribute("user", user);
		if ("true".equals(request.getParameter("remname"))) {
			Cookie cookie = new Cookie("remname", user.getUsername());
			cookie.setMaxAge(3600 * 24 * 30);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}else{
			Cookie cookie = new Cookie("remname", user.getUsername());
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
			
		if("true".equals(request.getParameter("autologin"))){
			Cookie cookie = new Cookie("autologin",user.getUsername()+":"+user.getPassword());
			cookie.setMaxAge(3600*24*30);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
		response.getWriter().write("登陆成功，3秒后回到主页。。。");
		response.setHeader("Refresh", "3;url=/MyEstore/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
