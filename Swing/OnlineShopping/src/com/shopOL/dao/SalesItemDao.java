package com.shopOL.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

import com.shopOL.bean.SalesItemBean;
import com.shopOL.jdbc.JDBCUtils;

public class SalesItemDao {
	/*
	 * ��ȡ��������Ա
	 */
	public static SalesItemBean getSalesItemBean(int id) {
		SalesItemBean salesItemBean = new SalesItemBean();
		String sql = "select * from T_SALESITEM  where id=?";
		Map<Object, Object> salesItem = JDBCUtils.executeQuery(sql, id);
		try {
			BeanUtils.populate(salesItemBean, salesItem);
		} catch (IllegalAccessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return salesItemBean;
	}
}
