package com.zsy.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zsy.factory.BasicFactory;
import com.zsy.service.UserService;

public class ActiveCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String activecode = request.getParameter("activecode");
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		service.activeUser(activecode);
		response.getWriter().write("��ϲ������ɹ�,3���ص���ҳ");
		response.setHeader("Refresh", "3;url=/MyEstore/index.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
