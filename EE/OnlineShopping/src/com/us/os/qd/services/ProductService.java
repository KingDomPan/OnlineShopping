package com.us.os.qd.services;

import com.us.os.qd.model.Product;
import com.us.os.qd.template.ServiceTemplate;
import com.us.os.qd.tools.Container;

public interface ProductService extends ServiceTemplate<Product> {
	public Container<Product> findNewers(int pageNo,int pageSize);
	public Container<Product> findProductsByCategiryId(int cid,int pageNo,int pageSize);
	public Container<Product> searchProductByName(String pname,int pageNo,int pageSize);
}
