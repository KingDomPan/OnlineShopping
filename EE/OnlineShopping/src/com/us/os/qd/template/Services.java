package com.us.os.qd.template;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import com.us.os.qd.tools.CrudHandler;
import com.us.os.qd.tools.PageSpliter;

public class Services {
	protected CrudHandler crudHandler;
	protected PageSpliter pageSpliter;
	protected HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public CrudHandler getCrudHandler() {
		return crudHandler;
	}

	public PageSpliter getPageSpliter() {
		return pageSpliter;
	}

	@Resource(name="crudHandler")
	public void setCrudHandler(CrudHandler crudHandler) {
		this.crudHandler = crudHandler;
	}

	@Resource(name="pageSpliter")
	public void setPageSpliter(PageSpliter pageSpliter) {
		this.pageSpliter = pageSpliter;
	}
}
