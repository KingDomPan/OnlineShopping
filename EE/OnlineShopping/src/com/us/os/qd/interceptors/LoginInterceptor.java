package com.us.os.qd.interceptors;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		HttpServletResponse response = (HttpServletResponse) ServletActionContext
				.getResponse();
		Map<?, ?> session = invocation.getInvocationContext().getSession();
		if (null != session.get("user")) {
			return invocation.invoke();
		}

		if (isAjaxRequest(request)) {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().write("{\"message\":\"还未登录\",\"success\":false,\"redirectURL\":\"login.html\"}");
			return null;
		} else {
			System.out.println(request.getRequestURI());
			System.out.println(request.getHeader("Referer"));
			System.out.println("AAAA");
			return Action.LOGIN;
		}
		
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
}
