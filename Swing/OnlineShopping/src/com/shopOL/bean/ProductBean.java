package com.shopOL.bean;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.shopOL.dao.CategoryDao;
import com.shopOL.dao.ProductDao;

public class ProductBean {
	private int id;
	private String description;
	private String memberPrice;
	private String normalPrice;
	private String name;
	private String newer;
	private String path;
	private String pdate="";
	private int categoryId;
	private String categoryName;
	private String md5 = "";
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(String memberPrice) {
		DecimalFormat df = new DecimalFormat("#0.00");
		this.memberPrice = df.format(Double.parseDouble(memberPrice));
	}

	public String getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(String normalPrice) {
		DecimalFormat df = new DecimalFormat("#0.00");
		this.normalPrice = df.format(Double.parseDouble(normalPrice));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNewer() {
		return newer;
	}

	public void setNewer(String newer) {
		this.newer = newer;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		if (this.path == "none") {
			this.path = "";
		}
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		if (pdate.equals("") || pdate.equals(null)) {
			this.path = "";
		} else {
			String fmt = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			Date date;
			try {
				date = sdf.parse(pdate);
				this.pdate = sdf.format(date);
			} catch (ParseException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
		CategoryBean categoryBean = CategoryDao.getCategoryBean(categoryId);
		categoryName = categoryBean.getName();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getMd5() {
		if (!getPath().equals("")) {
			String[] ary = getPath().split("/");
			md5 = ary[ary.length - 1];
			int i = md5.lastIndexOf(".");
			md5 = md5.substring(0, i);
			return md5;
		}
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getType() {
		if (newer.equals("1")) {
			type = "最新上架";
		} else {
			type = "其他";
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProductBean [id=" + id + ", description=" + description
				+ ", memberPrice=" + memberPrice + ", normalPrice="
				+ normalPrice + ", name=" + name + ", newer=" + newer
				+ ", path=" + path + ", pdate=" + pdate + ", categoryId="
				+ categoryId + ", categoryName=" + categoryName + ", md5="
				+ md5 + ", type=" + type + "]";
	}
	
	public static void main(String[] args) {
		ProductBean a = ProductDao.getProductBean(4);
		System.out.println(a);
//		System.out.println(a.getPdate());
//		System.out.println(a.getMemberPrice());
//		System.out.println(a.normalPrice);
//		System.out.println(a.getCategoryId());
//		System.out.println(a.getCategoryName());
	}
}
