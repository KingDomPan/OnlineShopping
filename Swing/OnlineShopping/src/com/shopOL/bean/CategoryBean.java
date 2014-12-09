package com.shopOL.bean;

import com.shopOL.dao.CategoryDao;

public class CategoryBean {
	private int id;
	private String description;
	private int grade;
	private String name;
	private int csuperId;
	private String csuperName;
	private String gradeNane;

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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
		if (grade==0) {
			gradeNane="根类别";
		} else {
			gradeNane="子类别";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCsuperId() {
		return csuperId;
	}

	public void setCsuperId(int csuperId) {
		this.csuperId = csuperId;
		if (csuperId==0) {
			csuperName="无";
		} else {
			csuperName=CategoryDao.getCategoryBean(csuperId).getName();
		}
		
	}

	public String getCsuperName() {
		return csuperName;
	}

	public String getGradeNane() {
		return gradeNane;
	}
	
	public static void main(String[] args) {
		CategoryBean a=CategoryDao.getCategoryBean(2);
		System.out.println(a.getId());
		System.out.println(a.getName());
		System.out.println(a.getGrade());
		System.out.println(a.getDescription());
		System.out.println(a.getCsuperId());
	}
}
