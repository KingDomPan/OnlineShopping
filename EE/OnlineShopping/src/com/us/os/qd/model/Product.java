package com.us.os.qd.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_PRODUCT")
public class Product {
	private Category category;
	private String description;
	private Integer id;
	private byte[] imagesData;
	private Double memberPrice;
	private String name;
	private char newer;
	private Double normalPrice;
	private String path;
	private Date pdate;

	public Product(String path, byte[] imagesData) {
		this.imagesData = imagesData;
		this.path = path;
	}

	@ManyToOne
	@JoinColumn(name = "categoryId")
	public Category getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	@Column(columnDefinition = "image")
	public byte[] getImagesData() {
		return imagesData;
	}

	public Double getMemberPrice() {
		return memberPrice;
	}

	public String getName() {
		return name;
	}

	public Product() {
	}

	@Column(columnDefinition = "char(1) default '1'")
	public char getNewer() {
		return newer;
	}

	public Double getNormalPrice() {
		return normalPrice;
	}

	@Column(columnDefinition = "varchar(255) default 'none'")
	public String getPath() {
		return path;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImagesData(byte[] imagesData) {
		this.imagesData = imagesData;
	}

	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNewer(char newer) {
		this.newer = newer;
	}

	public void setNormalPrice(Double normalPrice) {
		this.normalPrice = normalPrice;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description="
				+ description + ", normalPrice=" + normalPrice
				+ ", memberPrice=" + memberPrice + ", category=" + category
				+ ", path=" + path + ", pdate=" + pdate + "]";
	}
}
