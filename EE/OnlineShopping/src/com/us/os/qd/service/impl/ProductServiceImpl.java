package com.us.os.qd.service.impl;

import org.springframework.stereotype.Component;
import com.us.os.qd.model.Product;
import com.us.os.qd.services.ProductService;
import com.us.os.qd.template.Services;
import com.us.os.qd.tools.Container;

@Component("productServiceImpl")
public class ProductServiceImpl extends Services implements ProductService {
	public Container<Product> searchProductByName(String pname, int pageNo,
			int pageSize) {
		return this.pageSpliter.getListByPage(Product.class, pageNo, pageSize,
				"where obj.name like '%" + pname + "%'");
	}

	public void save(Product model) {

	}

	public Product findById(Integer id) {
		return this.crudHandler.loadById(Product.class, id);
	}

	public void update(Product model) {

	}

	public Container<Product> findNewers(int pageNo, int pageSize) {
		Container<Product> products = this.pageSpliter.getListByPage(
				Product.class, pageNo, pageSize, "where obj.newer='1'");
		System.out.println(products.getTotalPages());
		return products;
	}

	public Container<Product> findProductsByCategiryId(int cid, int pageNo,
			int pageSize) {
		return this.pageSpliter.getListByPage(Product.class, pageNo, pageSize,
				"where obj.category.id=" + cid);
	}

}
