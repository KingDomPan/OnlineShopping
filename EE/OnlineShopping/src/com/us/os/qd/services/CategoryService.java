package com.us.os.qd.services;

import java.util.List;

import com.us.os.qd.model.Category;
import com.us.os.qd.template.ServiceTemplate;

public interface CategoryService extends ServiceTemplate<Category> {
		List<Category> findSuperCategories();
}
