package com.us.os.qd.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="T_USER")
public class User {
	private Integer id;
	private String username;
	private String password;
	private char enable;
	private String phone;
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", enable=" + enable + ", phone=" + phone
				+ ", address=" + address + "]";
	}
	private String address;
	private Date rdate;
	private Set<SalesOrder> orders=new HashSet<SalesOrder>(); 
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer")
	public Set<SalesOrder> getOrders() {
		return orders;
	}
	public void setOrders(Set<SalesOrder> orders) {
		this.orders = orders;
	}
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(updatable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getEnable() {
		return enable;
	}
	public void setEnable(char enable) {
		this.enable = enable;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
}
