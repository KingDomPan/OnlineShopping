package com.shopOL.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.shopOL.bean.CategoryBean;
import com.shopOL.jdbc.JDBCUtils;

public class CategoryDao {
	/*
	 * 获取单个类别
	 */
	public static CategoryBean getCategoryBean(int id) {
		CategoryBean categoryBean = new CategoryBean();
		String sql = "  select * from T_CATEGORY where id=?";
		Map<Object, Object> category = JDBCUtils.executeQuery(sql, id);
		try {
			BeanUtils.populate(categoryBean, category);
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return categoryBean;
	}

	public static CategoryBean getCategoryBean(String name) {
		CategoryBean categoryBean = new CategoryBean();
		String sql = "  select * from T_CATEGORY where name=?";
		Map<Object, Object> category = JDBCUtils.executeQuery(sql, name);
		try {
			BeanUtils.populate(categoryBean, category);
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return categoryBean;
	}

	/*
	 * 修改单个类别
	 */
	public static boolean updateCategoryBean(String name, String description,
			String old) {
		String sql = "update T_CATEGORY set name=?,description=? where name=?";
		int index = JDBCUtils.update(sql, name, description, old);
		if (index != 0) {
			return true;
		} else {
			return false;
		}

	}
}
