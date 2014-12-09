package com.shopOL.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserBean {
	private int id;
	private String username;
	private String password;
	private String address;
	private String phone;
	private String rdate;
	private String enable;
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		String fmt = "yyyy-MM-dd HH:mm:SS";
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		Date date;
		try {
			date = sdf.parse(rdate);
			this.rdate = sdf.format(date);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
		if (enable.equals("0")) {
			type = "冻结";
		} else {
			type = "正常";
		}
	}

	public String getType() {
		return type;
	}
}
