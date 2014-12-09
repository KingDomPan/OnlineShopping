package com.shopOL.bean;

public class AdminBean {
	private int id;
	private String username;
	private String password;
	private String name;
	private int grade = 0;
	private String path;
	private String type;
	private String md5="";

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getType() {
		if (grade == 2) {
			type = "超级管理员(2)";
		} else {
			type = "普通管理员(1)";
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		if (path.equals("none")) {
			this.path="";
		}
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
}
