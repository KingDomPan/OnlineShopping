package com.shopOL.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.shopOL.bean.UserBean;
import com.shopOL.jdbc.JDBCUtils;

public class UserDao {
	/*
	 * 获取单个用户
	 */
	public static UserBean getUserBean(int id) {
		UserBean userBean = new UserBean();
		String sql = "select * from T_USER where id=?";
		Map<Object, Object> user = JDBCUtils.executeQuery(sql, id);
		try {
			BeanUtils.populate(userBean, user);
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return userBean;
	}
	
	public static UserBean getUserBean(String name) {
		UserBean userBean = new UserBean();
		String sql = "select * from T_USER where username=?";
		Map<Object, Object> user = JDBCUtils.executeQuery(sql, name);
		try {
			BeanUtils.populate(userBean, user);
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return userBean;
	}
	/*
	 * 重置密码
	 */
	public static boolean resetPassword(int id) {
		UserBean userBean = UserDao.getUserBean(id);
		String sql = "update T_USER set password=? where id=?";
		int index = JDBCUtils.update(sql, userBean.getUsername(), id);
		if (index > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 冻结用户
	 */
	public static boolean blocked(int id) {
		String sql="select * from T_USER where enable=? and id=?";
		if (JDBCUtils.executeQuery(sql, "1",id).size()==0) {
			return false;
		}
		sql = "update T_USER set enable=? where id=?";
		int index = JDBCUtils.update(sql, "0", id);
		if (index > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 解冻
	 */
	public static boolean thaw(int id) {
		String sql="select * from T_USER where enable=? and id=?";
		if (JDBCUtils.executeQuery(sql, "0",id).size()==0) {
			return false;
		}
		sql = "update T_USER set enable=? where id=?";
		int index = JDBCUtils.update(sql, "1", id);
		if (index > 0) {
			return true;
		} else {
			return false;
		}
	}
}
