package com.us.os.qd.action;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.us.os.qd.services.SalesItemService;
import com.us.os.qd.template.ActionTemplate;

@SuppressWarnings("serial")
@Component("salesItemAction")
@Scope("prototype")
public class SalesItemAction extends ActionTemplate {
	private String pid;
	private SalesItemService salesItemService;
	
	public SalesItemService getSalesItemService() {
		return salesItemService;
	}

	@Resource(name="salesItemServiceImpl")
	public void setSalesItemService(SalesItemService salesItemService) {
		this.salesItemService = salesItemService;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
