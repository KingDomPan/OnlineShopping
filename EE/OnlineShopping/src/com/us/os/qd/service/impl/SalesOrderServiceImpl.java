package com.us.os.qd.service.impl;

import org.springframework.stereotype.Component;
import com.us.os.qd.model.SalesOrder;
import com.us.os.qd.model.User;
import com.us.os.qd.services.SalesOrderService;
import com.us.os.qd.template.Services;
import com.us.os.qd.tools.Container;
import com.us.os.qd.tools.DateStringHelper;

@Component("salesOrderServiceImpl")
public class SalesOrderServiceImpl extends Services implements
		SalesOrderService {
	public Container<SalesOrder> findFinishOrders(User user, int pageNo, int pageSize) {
		// return this.crudHandler.loadById(SalesOrder.class,
		// "where obj.status='FIN' and obj.buyer.id="+user.getId());

		return this.pageSpliter.getListByPage(SalesOrder.class, pageNo,
				pageSize,
				"where obj.status='FIN' and obj.buyer.id=" + user.getId());
	}

	public SalesOrder findWdOrder(User user) {
		SalesOrder order = this.crudHandler.loadById(SalesOrder.class,
				"where obj.status='WD' and obj.buyer.id=" + user.getId());
		if (order == null) {
			order = new SalesOrder();
			order.setBuyer(user);
			order.setStatus("WD");
			order.setAddress(user.getAddress());
			this.save(order);
		}
		order.setOid(DateStringHelper.getNowDateString());
		return order;
	}

	public void save(SalesOrder model) {
		this.crudHandler.save(model);
	}

	public SalesOrder findById(Integer id) {
		return this.crudHandler.loadById(SalesOrder.class, id);
	}

	public void update(SalesOrder model) {
		this.crudHandler.update(model);
	}
}
