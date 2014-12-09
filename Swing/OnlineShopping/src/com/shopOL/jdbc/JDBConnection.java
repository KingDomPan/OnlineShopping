/*
 * 数据库操作
 */

package com.shopOL.jdbc;

import java.sql.*;
import java.util.Map;

import com.shopOL.util.FileUtil;

public class JDBConnection {

	// 数据库配置
	private static String DB_FOLDER = "dataBase.ini";
	/*
	 * 加载驱动
	 */
	private static String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	/*
	 * 创建数据库连接
	 */
	public static boolean creatConnection(String hostName, String port,
			String dbName,String userName,String password) {
		Connection con = null;
		try {
			Class.forName(dbDriver);// .newInstance();
		} catch (Exception e) {
			System.out.println("数据库加载失败");
		}
		String url = "jdbc:sqlserver://" + hostName + ":" + port
				+ ";databaseName=" + dbName;
		try {
			con = DriverManager.getConnection(url, userName, password);
			// con.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			closeConnection(con, null, null);
		}
	}

	/*
	 * 返回Connection
	 */
	public static Connection getConnection() {
		try {
			Class.forName(dbDriver);// .newInstance();
		} catch (Exception e) {
			System.out.println("数据库加载失败");
		}
		Connection con = null;
		@SuppressWarnings("rawtypes")
		Map map = FileUtil.getProperties(DB_FOLDER);
		String hostName = map.get("hostName").toString();// 主机名;
		String port = map.get("port").toString();// 端口
		String dbName = map.get("dbName").toString();// 数据库名
		String userName=map.get("userName").toString();
		String password=map.get("password").toString();
		String url = "jdbc:sqlserver://" + hostName + ":" + port
				+ ";databaseName=" + dbName;
		try {
			con = DriverManager.getConnection(url, userName, password);
			// con.setAutoCommit(true);
			return con;
		} catch (SQLException e) {
			return null;
		}
	}

	/*
	 * 关闭数据库连接
	 */
	public static void closeConnection(Connection conn, PreparedStatement pst,
			ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}