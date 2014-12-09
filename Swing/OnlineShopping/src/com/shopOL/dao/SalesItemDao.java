package com.shopOL.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

import com.shopOL.bean.SalesItemBean;
import com.shopOL.jdbc.JDBCUtils;

public class SalesItemDao {
	/*
	 * 获取单个管理员
	 */
	public static SalesItemBean getSalesItemBean(int id) {
		SalesItemBean salesItemBean = new SalesItemBean();
		String sql = "select * from T_SALESITEM  where id=?";
		Map<Object, Object> salesItem = JDBCUtils.executeQuery(sql, id);
		try {
			BeanUtils.populate(salesItemBean, salesItem);
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return salesItemBean;
	}
}
