package com.zsy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zsy.domain.Product;
import com.zsy.factory.BasicFactory;
import com.zsy.service.ProductService;

public class ProductListServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		List<Product> list = service.findAllProd();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/prodlist.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
