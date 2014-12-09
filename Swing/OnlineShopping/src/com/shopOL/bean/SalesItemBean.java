package com.shopOL.bean;

import java.text.DecimalFormat;

import com.shopOL.dao.ProductDao;

public class SalesItemBean {
	@Override
	public String toString() {
		return "SalesItemBean [id=" + id + ", productId=" + productId
				+ ", productName=" + productName + ", count=" + count
				+ ", order=" + order + ", unitPrice=" + unitPrice + "]";
	}

	private Integer id;
	private int productId;
	private String productName;
	private Double count;
	private SalesOrderBean order;
	private String unitPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
		productName = ProductDao.getProductBean(productId).getName();
	}

	public String getProductName() {
		return productName;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public SalesOrderBean getOrder() {
		return order;
	}

	public void setOrder(SalesOrderBean order) {
		this.order = order;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		DecimalFormat df = new DecimalFormat("#0.00");
		this.unitPrice = df.format(Double.parseDouble(unitPrice));
	}

	public String getAllPrice() {
		double allPrice = count * Double.parseDouble(unitPrice);
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(allPrice);
	}
}
