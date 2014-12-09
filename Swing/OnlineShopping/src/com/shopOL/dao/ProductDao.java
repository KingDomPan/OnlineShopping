package com.shopOL.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.shopOL.bean.ProductBean;
import com.shopOL.jdbc.JDBCUtils;
import com.shopOL.util.FileUtil;
import com.shopOL.util.ImageOperate;
import com.shopOL.util.ImgSql;
import com.shopOL.util.MD5FileUtil;

public class ProductDao {
	private final static String PRODUCT_FOLDER = "pictures/products/";// 商品文件夹

	/*
	 * 获取单个管理员
	 */
	public static ProductBean getProductBean(int id) {
		ProductBean productBean = new ProductBean();
		String sql = "select * from T_PRODUCT where id=?";
		Map<Object, Object> admin = JDBCUtils.executeQuery(sql, id);
		try {
			BeanUtils.populate(productBean, admin);
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return productBean;
	}

	public static ProductBean getProductBean(String product) {
		ProductBean productBean = new ProductBean();
		String sql = "select * from T_PRODUCT where name=?";
		Map<Object, Object> admin = JDBCUtils.executeQuery(sql, product);
		try {
			BeanUtils.populate(productBean, admin);
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return productBean;
	}

	/*
	 * 添加产品
	 */
	public static boolean addProduct(ProductBean product, String suffixe) {
		String sql = "select * from T_PRODUCT where name=?";
		Map<Object, Object> admin = JDBCUtils.executeQuery(sql,
				product.getName());
		if (admin.size() == 0) {
			String md5 = "";
			sql = "insert into T_PRODUCT(name,categoryId,normalPrice,memberPrice,pdate,description,path,newer) values(?,?,?,?,?,?,?,1)";
			int index = JDBCUtils.update(sql, product.getName(),
					product.getCategoryId(), product.getNormalPrice(),
					product.getMemberPrice(), product.getPdate(),
					product.getDescription(), md5);
			if (index == 0) {
				return false;
			}
			if (!product.getPath().equals("")) {
				File big = new File(product.getPath());
				try {
					md5 = MD5FileUtil.getFileMD5String(big);
					md5 = PRODUCT_FOLDER + md5 + suffixe;
					ImageOperate.copyImg(md5, product.getPath());
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				sql = "update T_PRODUCT set imagesData=?,path='" + md5
						+ "' where name='" + product.getName() + "'";
				index = ImgSql.setImg(big, sql);
				if (index == 0) {
					return false;
				}
				return true;
			}
			return true;
		}
		return false;
	}

	/*
	 * 修改产品
	 */

	public static boolean updateProduct(ProductBean product, String suffixe) {
		String path=ProductDao.getProductBean(product.getName()).getPath();
		String md5Old = ProductDao.getProductBean(product.getName()).getMd5();
		String md5New = "";
		String sql = "update T_PRODUCT set categoryId=?,normalPrice=?,memberPrice=?,pdate=?,description=? where name=?";
		int index = JDBCUtils.update(sql, product.getCategoryId(),
				product.getNormalPrice(), product.getMemberPrice(),
				product.getPdate(), product.getDescription(),product.getName());
		if (index == 0) {
			return false;
		}
		if (!product.getPath().equals("")) {
			File big = new File(product.getPath());
			try {
				md5New = MD5FileUtil.getFileMD5String(big);
				if (!md5Old.equals(md5New)) {
					md5New = PRODUCT_FOLDER + md5New + suffixe;
					sql = "update T_PRODUCT set imagesData=?,path='" + md5New
							+ "' where name='" + product.getName() + "'";
					index = ImgSql.setImg(big, sql);
					if (index == 0) {
						return false;
					}
					ImageOperate.copyImg(md5New, product.getPath());
					sql = "select * from T_PRODUCT where path=?";
					Map<Object, Object> map = JDBCUtils.executeQuery(sql,path);
					if (map.size() == 0) {
						FileUtil.deleteFile(path);
					}
				}

			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return true;
	}
}
