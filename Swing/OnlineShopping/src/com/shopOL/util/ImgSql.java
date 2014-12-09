package com.shopOL.util;

import java.io.*;
import java.sql.*;

import com.shopOL.jdbc.JDBConnection;

public class ImgSql {
	public static int setImg(File file, String sql) {
		InputStream photoStream = null;
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			photoStream = new FileInputStream(file);
			conn = JDBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setBinaryStream(1, photoStream,(int)file.length());
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBConnection.closeConnection(conn, pst, null);
		}
		return 0;
	}
	public static void getImg() throws Exception{
		String sql="select imagesData from T_PRODUCT where id=11";
		Connection conn = JDBConnection.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				InputStream is=rs.getBinaryStream(1);
				byte[] bytes=new byte[1024*20];
				is.read(bytes);
				FileOutputStream fos=new FileOutputStream(new File("F:"+File.separator+"1.jpg"));
				fos.write(bytes);
				fos.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		getImg();
	}
}
