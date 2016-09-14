package com.zsy.service;

import java.util.List;

import com.zsy.domain.Product;

public interface ProductService {

	void addProduct(Product prod);

	List<Product> findAllProd();

	Product findProdById(String id);

}
