package com.shopOL.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.shopOL.bean.SalesOrderBean;
import com.shopOL.jdbc.JDBCUtils;

public class SalesOrderDao {
	/*
	 * ��ȡ��������
	 */
	public static SalesOrderBean getSalesOrderBean(String oid) {
		SalesOrderBean salesOrderBean = new SalesOrderBean();
		String sql = "select * from T_SALESORDER where oid=?";
		Map<Object, Object> salesOrder = JDBCUtils.executeQuery(sql, oid);
		try {
			BeanUtils.populate(salesOrderBean, salesOrder);
		} catch (IllegalAccessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return salesOrderBean;
	}

	public static SalesOrderBean getSalesOrderBean(int id) {
		SalesOrderBean salesOrderBean = new SalesOrderBean();
		String sql = "select * from T_SALESORDER where id=?";
		Map<Object, Object> salesOrder = JDBCUtils.executeQuery(sql, id);
		try {
			BeanUtils.populate(salesOrderBean, salesOrder);
		} catch (IllegalAccessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return salesOrderBean;
	}

	/*
	 * ��������
	 */
	public static boolean closing(String oid, int adminId) {
		String sql = "update T_SALESORDER set status=?,adminId=? where oid=?";
		int index = JDBCUtils.update(sql, "end", adminId, oid);
		if (index == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		System.out.println(SalesOrderDao.getSalesOrderBean(1));
	}
}
