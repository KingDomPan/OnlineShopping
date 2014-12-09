package com.us.os.qd.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import com.us.os.qd.model.Category;
import com.us.os.qd.services.CategoryService;
import com.us.os.qd.template.Services;

@Component("categoryServiceImpl")
public class CategoryServiceImpl extends Services implements CategoryService{

	public List<Category> findSuperCategories() {
		return this.crudHandler.getList(Category.class, "where obj.grade=0");
	}

	public void save(Category model) {
		
	}

	public Category findById(Integer id) {
		return null;
	}

	public void update(Category model) {
		
	}

	
}
