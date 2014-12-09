package com.us.os.qd.action;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.us.os.qd.model.Product;
import com.us.os.qd.services.ProductService;
import com.us.os.qd.template.ActionTemplate;
import com.us.os.qd.tools.Container;

@SuppressWarnings("serial")
@Component("productAction")
@Scope("prototype")
public class ProductAction extends ActionTemplate {
	private ProductService productService;
	private int cid;
	private int pageNo = 1;
	private int totalPage;
	private String pname;
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public ProductService getProductService() {
		return productService;
	}

	@Resource(name = "productServiceImpl")
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 展示最新商品
	public String findNewers() {
		Container<Product> products = this.productService.findNewers(pageNo, 8);
		totalPage = products.getTotalPages();
		this.request.setAttribute("products", products.getDataContainer());
		this.request.setAttribute("totalPage", totalPage);
		this.request.setAttribute("currentPage", pageNo);
		return SUCCESS;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	// 根据商品类别查找商品
	public String findProductsByCategiryId() {
		Container<Product> products = null;
		products = this.productService.findProductsByCategiryId(cid, pageNo, 8);
		totalPage = products.getTotalPages();
		this.request.setAttribute("products", products.getDataContainer());
		this.request.setAttribute("totalPage", totalPage);
		this.request.setAttribute("currentPage", pageNo);
		return SUCCESS;
	}

	public String searchProductByName(){
		Container<Product> products = null;
		products = this.productService.searchProductByName(pname, pageNo, 8);
		totalPage = products.getTotalPages();
		this.request.setAttribute("products", products.getDataContainer());
		this.request.setAttribute("totalPage", totalPage);
		this.request.setAttribute("currentPage", pageNo);
		return SUCCESS;
	}
}
