package com.zsy.service;

import java.util.List;
import java.util.UUID;

import com.zsy.dao.ProductDao;
import com.zsy.domain.Product;
import com.zsy.factory.BasicFactory;

public class ProductServiceImpl implements ProductService {
	ProductDao dao = BasicFactory.getFactory().getInstance(ProductDao.class);
	
	public void addProduct(Product prod) {
		prod.setId(UUID.randomUUID().toString());
		dao.addProduct(prod);
	}

	public List<Product> findAllProd() {
		return dao.findAllProd();
	}

	public Product findProdById(String id) {
		return dao.findProdById(id);
	}
}
