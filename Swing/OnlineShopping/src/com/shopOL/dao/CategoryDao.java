package com.shopOL.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.shopOL.bean.CategoryBean;
import com.shopOL.jdbc.JDBCUtils;

public class CategoryDao {
	/*
	 * ��ȡ�������
	 */
	public static CategoryBean getCategoryBean(int id) {
		CategoryBean categoryBean = new CategoryBean();
		String sql = "  select * from T_CATEGORY where id=?";
		Map<Object, Object> category = JDBCUtils.executeQuery(sql, id);
		try {
			BeanUtils.populate(categoryBean, category);
		} catch (IllegalAccessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return categoryBean;
	}

	/*
	 * �޸ĵ������
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
