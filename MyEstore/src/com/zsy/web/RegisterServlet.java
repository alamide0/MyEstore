package com.zsy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.zsy.domain.User;
import com.zsy.factory.BasicFactory;
import com.zsy.service.UserService;
import com.zsy.util.MD5Utils;

public class RegisterServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(!request.getParameter("valistr").equalsIgnoreCase(request.getSession().getAttribute("valistr").toString())){
			request.setAttribute("valierror", "��֤�����!");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}

		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());	
			user.setPassword(MD5Utils.md5(user.getPassword()));
			UserService service = BasicFactory.getFactory().getInstance(UserService.class);
			if(service.findUser(user)!=null){
				request.setAttribute("valierror", "");
				request.setAttribute("rename", "�û����Ѿ�����!!");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
				return;
			}			
			service.addUser(user);
			response.getWriter().write("ע��ɹ����뵽���������м��3���ص���ҳ��");
			response.setHeader("Refresh", "3;url=/MyEstore/index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
