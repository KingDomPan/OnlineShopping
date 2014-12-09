package com.us.os.qd.listeners;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import com.us.os.qd.model.Product;

public class ProductPathHelper extends TimerTask {
	private static Integer version = 0;
	private WebApplicationContext appctx;
	private ServletContext app;
	private String imgRoot;
	private String basePath = "/pictures/products/";

	public ProductPathHelper(WebApplicationContext appctx) {
		this.appctx = appctx;
		app = appctx.getServletContext();
		imgRoot = app.getRealPath("/") + File.separator + "pictures"
				+ File.separator + "products" + File.separator;
		
		this.hibernateTemplate = (HibernateTemplate) appctx
				.getBean("hibernateTemplate");
	}

	private HibernateTemplate hibernateTemplate;

	public Integer getProductTimestamp() {
		String sql = "select cast(@@dbts as bigint)";
		return Integer.parseInt(this.hibernateTemplate.getSessionFactory()
				.openSession().createSQLQuery(sql).uniqueResult().toString());
	}

	public List<Product> getUpdateProducts() throws SQLException, IOException {
		Connection connection = SessionFactoryUtils.getDataSource(
				this.hibernateTemplate.getSessionFactory()).getConnection();

		String sql = "select [path],imagesData from t_product where cast(tversion as bigint)>"
				+ version;

		ResultSet rs = connection.prepareStatement(sql).executeQuery();
		Product product = null;
		List<Product> products = new ArrayList<Product>();
		while (rs.next()) {
			product = new Product();
			product.setPath(rs.getString(1));
			InputStream is = rs.getBinaryStream(2);
			byte[] bytes = new byte[1024*512];
			is.read(bytes);
			product.setImagesData(bytes);
			products.add(product);
		}
		return products;
	}

	@Override
	public void run() {
		try {
			File[] imgs = new File(imgRoot).listFiles();
			List<String> paths=new ArrayList<String>();//存放单独文件名
			for(File f:imgs){
				paths.add(basePath+f.getName());
			}
			
			List<Product> products = getUpdateProducts();
			
			for (Product product : products) {
				String path=product.getPath();
				if(paths.contains(path)){
					continue;
				}else{
					path=path.substring(path.lastIndexOf("/")+1);
					FileOutputStream fos=new FileOutputStream(imgRoot+path);
					byte[] bytes=product.getImagesData();
					fos.write(bytes,0,bytes.length);
					fos.flush();
					fos.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		version = getProductTimestamp();
	}

	public WebApplicationContext getAppctx() {
		return appctx;
	}

	public void setAppctx(WebApplicationContext appctx) {
		this.appctx = appctx;
	}
}