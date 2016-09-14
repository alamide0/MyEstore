package com.zsy.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.zsy.domain.Product;

public class MySessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		Map<Product, Integer> map = new LinkedHashMap<Product, Integer>();
		se.getSession().setAttribute("map", map);
		System.out.println("map创建成功");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

}
