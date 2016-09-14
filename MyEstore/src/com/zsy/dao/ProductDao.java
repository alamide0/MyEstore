package com.zsy.dao;

import java.util.List;

import com.zsy.domain.Product;

public interface ProductDao {

	void addProduct(Product prod);

	List<Product> findAllProd();

	Product findProdById(String id);

}
