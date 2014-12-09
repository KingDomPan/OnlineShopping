package com.shopOL.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class JDBCUtils {
	/**
	 * 通用更新数据库操作,适用于单表(一次只进行一次更新操作)操作
	 * 
	 * @param sql
	 *            更新SQL语句 insert,update delete
	 * @param obj
	 *            语句中"?"对应的值,要求类型必须匹配
	 */
	public static int update(String sql, Object... obj) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = JDBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					pst.setObject(i + 1, obj[i]);
				}
			}
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBConnection.closeConnection(conn, pst, null);
		}
		return 0;
	}

	/**
	 * 
	 * @param sql
	 * @param obj
	 */
	public static int[] updateAll(String[] sql, Object... obj) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = JDBConnection.getConnection();
			// 设置手动提交事务
			conn.setAutoCommit(false);
			if (sql != null) {
				int[] result = new int[sql.length];
				for (int i = 0; i < sql.length; i++) {
					pst = conn.prepareStatement(sql[i]);
					if (obj != null) {
						Object[] param = (Object[]) obj[i];
						for (int j = 0; j < param.length; j++) {
							pst.setObject(j + 1, param[j]);
						}
					}
					int x = pst.executeUpdate();
					result[i] = x;
				}
				conn.commit();
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			JDBConnection.closeConnection(conn, pst, null);
		}
		return null;
	}

	/**
	 * 单纯执行语句，不控制事务以及连接,由使用者来控制事务的提交或者回滚
	 * 
	 * @param conn
	 * @param sql
	 * @param obj
	 * @return
	 */
	public static int[] updateAll(Connection conn, String[] sql, Object... obj) {

		PreparedStatement pst = null;

		try {
			if (sql != null) {
				int[] result = new int[sql.length];
				for (int i = 0; i < sql.length; i++) {
					pst = conn.prepareStatement(sql[i]);
					if (obj != null) {
						Object[] param = (Object[]) obj[i];
						for (int j = 0; j < param.length; j++) {
							pst.setObject(j + 1, param[j]);
						}
					}
					int x = pst.executeUpdate();
					result[i] = x;
				}
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBConnection.closeConnection(null, pst, null);
		}
		return null;
	}

	/*
	 * 查询
	 */
	public static Map<Object, Object> executeQuery(String sql, Object... obj) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			con = JDBConnection.getConnection();
			ps = con.prepareStatement(sql);
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i + 1, obj[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					result.put(metaData.getColumnName(i), rs.getString(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBConnection.closeConnection(con, ps, rs);
		}
		return result;
	}

	public static Vector<Vector<String>> getTableData(String sql, Object... obj) {
		Vector<Vector<String>> result = new Vector<Vector<String>>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i + 1, obj[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				for (int i = 1; i <= count; i++) {
					row.add(rs.getString(i));
				}
				result.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBConnection.closeConnection(conn, ps, rs);
		}
		return result;
	}
}
