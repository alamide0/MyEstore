package com.zsy.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zsy.domain.Product;
import com.zsy.factory.BasicFactory;
import com.zsy.service.ProductService;

public class ProdInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		Product prod = service.findProdById(id);
		if(prod==null){
			throw new RuntimeException("未找到该商品");
		}
		request.setAttribute("prod", prod);
		request.getRequestDispatcher("/prodInfo.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
