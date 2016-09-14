package com.zsy.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zsy.domain.Product;
import com.zsy.util.DaoUtils;

public class ProductDaoImpl implements ProductDao {

	public void addProduct(Product prod) {
		String sql = "insert into products values (?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DaoUtils.getDataSourse());
		try {
			runner.update(sql, prod.getId(), prod.getName(), prod.getPrice(),
					prod.getCategory(), prod.getPnum(), prod.getImgurl(), prod
							.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Product> findAllProd() {
		try {
			String sql = "select * from products";
			QueryRunner runner = new QueryRunner(DaoUtils.getDataSourse());
			return runner.query(sql,
					new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	public Product findProdById(String id) {
		try {
			String sql = "select * from products where id=?";
			QueryRunner runner = new QueryRunner(DaoUtils.getDataSourse());
			return runner.query(sql, new BeanHandler<Product>(Product.class),
					id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
