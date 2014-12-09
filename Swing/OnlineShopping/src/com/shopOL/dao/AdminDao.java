package com.shopOL.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

import com.shopOL.bean.AdminBean;
import com.shopOL.jdbc.JDBCUtils;
import com.shopOL.util.FileUtil;
import com.shopOL.util.ImageOperate;
import com.shopOL.util.MD5FileUtil;

public class AdminDao {
	private final static String ADMIN_FOLDER = "pictures/admins/";// 管理员文件夹

	/*
	 * 获取单个管理员
	 */
	public static AdminBean getAdminBean(int id) {
		AdminBean adminBean = new AdminBean();
		String sql = "select * from T_ADMIN where id=?";
		Map<Object, Object> admin = JDBCUtils.executeQuery(sql, id);
		try {
			BeanUtils.populate(adminBean, admin);
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return adminBean;
	}

	public static AdminBean getAdminBean(String username) {
		AdminBean adminBean = new AdminBean();
		String sql = "select * from T_ADMIN where username=?";
		Map<Object, Object> admin = JDBCUtils.executeQuery(sql, username);
		try {
			BeanUtils.populate(adminBean, admin);
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return adminBean;
	}

	public static AdminBean getAdminBean2(String name) {
		AdminBean adminBean = new AdminBean();
		String sql = "select * from T_ADMIN where name=?";
		Map<Object, Object> admin = JDBCUtils.executeQuery(sql, name);
		try {
			BeanUtils.populate(adminBean, admin);
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return adminBean;
	}

	/*
	 * 添加管理员
	 */
	public static boolean addAdmin(String username, String password,
			String name, String path, String suffixe) {
		String sql = "select * from T_ADMIN where username=?";
		Map<Object, Object> admin = JDBCUtils.executeQuery(sql, username);
		if (admin.size() == 0) {
			String md5 = "";
			if (!path.equals("")) {
				File big = new File(path);
				try {
					md5 = MD5FileUtil.getFileMD5String(big);
					md5 = ADMIN_FOLDER + md5 + suffixe;
					ImageOperate.copyImg(md5, path);
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			sql = "insert into T_ADMIN(name,password,username,grade,path) values(?,?,?,1,?)";
			int index = JDBCUtils.update(sql, name, password, username, md5);
			if (index == 0) {
				return false;
			}
			return true;
		}
		return false;
	}

	/*
	 * 修改管理员
	 */

	public static boolean updateAdmin(String username, String password,
			String name, String path, String suffixe) {
		AdminBean adminBean = AdminDao.getAdminBean(username);
		String sql = "update T_ADMIN set name=?,password=? where username=?";
		int index = JDBCUtils.update(sql, name, password, username);
		if (index == 0) {
			return false;
		}
		String md5Old = adminBean.getMd5();
		String md5New = "";
		if (!path.equals("")) {
			File big = new File(path);
			try {
				md5New = MD5FileUtil.getFileMD5String(big);
				if (!md5Old.equals(md5New)) {
					md5New = ADMIN_FOLDER + md5New + suffixe;
					sql = "update T_ADMIN set path=? where username=?";
					index = JDBCUtils.update(sql, md5New, username);
					if (index == 0) {
						return false;
					}
					ImageOperate.copyImg(md5New, path);
					sql = "select * from T_ADMIN where path=?";
					Map<Object, Object> map = JDBCUtils.executeQuery(sql,
							adminBean.getPath());
					if (map.size() == 0) {
						FileUtil.deleteFile(adminBean.getPath());
					}
				}

			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return true;
	}

	/*
	 * 删除管理员
	 */
	public static boolean deleteAdmin(String username) {
		AdminBean adminBean = AdminDao.getAdminBean(username);
		String sql = "delete from T_ADMIN where username=?";
		int index = JDBCUtils.update(sql, username);
		if (index == 0) {
			return false;
		}
		sql = "select * from T_ADMIN where path=?";
		Map<Object, Object> map = JDBCUtils.executeQuery(sql,
				adminBean.getPath());
		if (map.size() == 0) {
			FileUtil.deleteFile(adminBean.getPath());
		}
		return true;
	}

}
