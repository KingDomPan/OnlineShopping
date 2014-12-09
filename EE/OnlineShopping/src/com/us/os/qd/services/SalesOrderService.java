package com.us.os.qd.services;

import com.us.os.qd.model.SalesOrder;
import com.us.os.qd.model.User;
import com.us.os.qd.template.ServiceTemplate;
import com.us.os.qd.tools.Container;

public interface SalesOrderService extends ServiceTemplate<SalesOrder>{
	public SalesOrder findWdOrder(User user);
	public Container<SalesOrder> findFinishOrders(User user,int pageNo,int pageSize);
}
