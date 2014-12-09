package com.us.os.qd.action;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.us.os.qd.model.Category;
import com.us.os.qd.services.CategoryService;
import com.us.os.qd.template.ActionTemplate;

@SuppressWarnings("serial")
@Component("categoryAction")
@Scope("prototype")
public class CategoryAction extends ActionTemplate {
	private CategoryService categoryService;
	
	public CategoryService getCategoryService() {
		return categoryService;
	}

	@Resource(name="categoryServiceImpl")
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public String findCategories(){
		List<Category> categories=this.categoryService.findSuperCategories();
		this.request.setAttribute("categories", categories);
		return SUCCESS;
	}
}
