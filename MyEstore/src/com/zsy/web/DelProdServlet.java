package com.zsy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zsy.domain.Product;
import com.zsy.factory.BasicFactory;
import com.zsy.service.ProductService;

public class DelProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		Product prod = service.findProdById(id);
		
		Map<Product, Integer> map = (Map<Product, Integer>) request.getSession().getAttribute("map");
		map.remove(prod);
		response.sendRedirect("/MyEstore/cart.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
