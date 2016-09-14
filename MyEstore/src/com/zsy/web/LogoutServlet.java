package com.zsy.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null){
			Cookie cookie = new Cookie("autologin","");
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
			session.invalidate();
		}
		
		response.getWriter().write("注销成功，3秒后回到主页！！！");
		response.setHeader("Refresh", "3;url=/MyEstore/index.jsp");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

}
