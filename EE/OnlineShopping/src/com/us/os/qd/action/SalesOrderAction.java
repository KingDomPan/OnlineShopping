package com.us.os.qd.action;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.us.os.qd.model.SalesItem;
import com.us.os.qd.model.SalesOrder;
import com.us.os.qd.model.User;
import com.us.os.qd.services.ProductService;
import com.us.os.qd.services.SalesOrderService;
import com.us.os.qd.template.ActionTemplate;
import com.us.os.qd.tools.Container;

@SuppressWarnings("serial")
@Component("salesOrderAction")
@Scope("prototype")
public class SalesOrderAction extends ActionTemplate {
	private SalesOrderService salesOrderService;
	private ProductService productService;
	private SalesOrder order;
	private int pageNo=1;
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public SalesOrder getOrder() {
		return order;
	}

	public void setOrder(SalesOrder order) {
		this.order = order;
	}

	public ProductService getProductService() {
		return productService;
	}

	@Resource(name = "productServiceImpl")
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private Integer pid;
	private Double unitPrice;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public SalesOrderService getSalesOrderService() {
		return salesOrderService;
	}

	@Resource(name = "salesOrderServiceImpl")
	public void setSalesOrderService(SalesOrderService salesOrderService) {
		this.salesOrderService = salesOrderService;
	}

	public String findFinishOrder() {
		Container<SalesOrder> forders = this.salesOrderService
				.findFinishOrders((User) this.session.getAttribute("user"),pageNo,5);
		this.request.setAttribute("forders", forders.getDataContainer());
		return SUCCESS;
	}

	public void addItemToOrder() {
		try {
			this.response.setContentType("text/json;charset=UTF-8");
			SalesOrder order = (SalesOrder) this.session.getAttribute("order");
			SalesItem titem = null;
			boolean flag = false;
			for (SalesItem item : order.getItems()) {
				if (item.getProduct().getId() == pid) {
					flag = true;
					titem = item;
					break;
				}
			}
			if (!flag) {
				SalesItem item = new SalesItem();
				item.setCount(1.0D);
				item.setOrder(order);
				item.setUnitPrice(unitPrice);
				item.setProduct(this.productService.findById(pid));
				order.getItems().add(item);
			} else {
				titem.setCount(titem.getCount() + 1D);
			}
			System.out.println("2   " + order);
			this.session.setAttribute("order", order);
			response.getWriter().write(
					"{\"message\":\"订单添加成功\",\"success\":true}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void submitOrder() {
		try {
			this.response.setContentType("text/json;charset=UTF-8");
			// 订单提交处理
			SalesOrder _order = (SalesOrder) this.session.getAttribute("order");
			_order.setAddress(order.getAddress());
			_order.setPhone(order.getPhone());
			_order.setRemark(order.getRemark());
			_order.setOdate(new Date());
			_order.setStatus("FIN");
			_order.setTotalPrice(order.getTotalPrice());
			this.salesOrderService.update(_order);
			this.session.setAttribute("order", this.salesOrderService
					.findWdOrder((User) this.session.getAttribute("user")));
			response.getWriter()
			.write("{\"message\":\"订单提交成功..系统配货中!!!\",\"success\":true,\"redirectURL\":\"salesorder/findFinishOrder\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
