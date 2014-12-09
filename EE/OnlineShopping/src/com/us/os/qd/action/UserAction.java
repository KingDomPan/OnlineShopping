package com.us.os.qd.action;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.opensymphony.xwork2.ModelDriven;
import com.us.os.qd.dto.UserDto;
import com.us.os.qd.model.SalesOrder;
import com.us.os.qd.model.User;
import com.us.os.qd.services.SalesOrderService;
import com.us.os.qd.services.UserService;
import com.us.os.qd.template.ActionTemplate;

@SuppressWarnings("serial")
@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionTemplate implements ModelDriven<User> {
	private UserDto udto;
	private User user = new User();
	private UserService userService;
	private SalesOrderService salesOrderService;

	public SalesOrderService getSalesOrderService() {
		return salesOrderService;
	}

	@Resource(name = "salesOrderServiceImpl")
	public void setSalesOrderService(SalesOrderService salesOrderService) {
		this.salesOrderService = salesOrderService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource(name = "userServiceImpl")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getModel() {
		return user;
	}

	public UserDto getUdto() {
		return udto;
	}

	public void login() throws IOException {
		try {
			this.response.setContentType("text/json;charset=UTF-8");
			User u = this.userService.login(user);
			if (u == null) {
				response.getWriter()
						.write("{\"message\":\"用户名或密码错误!!!\",\"success\":false,\"redirectURL\":\"login.html\"}");
			} else {
				if(u.getEnable()=='0'){
					response.getWriter()
					.write("{\"message\":\"亲爱的你被冻结了,请等待系统解冻!!!\",\"success\":false,\"redirectURL\":\"login.html\"}");
				}
				else{
					this.session.setAttribute("user", u);
					this.session.setAttribute("order", this.salesOrderService
							.findWdOrder((User) this.session.getAttribute("user")));
					response.getWriter()
							.write("{\"message\":\"用户登录成功\",\"success\":true,\"redirectURL\":\"index.jsp\"}");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void signup() throws IOException {
		this.response.setContentType("text/json;charset=UTF-8");
		if (this.userService.exists(udto.getUser())) {
			response.getWriter()
					.write("{\"message\":\"该用户名已经存在!!!\",\"success\":false,\"redirectURL\":\"signup.html\"}");
		} else {
			udto.getUser().setRdate(new Date());
			udto.getUser().setEnable('1');
			this.userService.save(udto.getUser());
			response.getWriter()
					.write("{\"message\":\"用户注册成功\",\"success\":true,\"redirectURL\":\"index.jsp\"}");
		}
	}

	public void setUdto(UserDto udto) {
		this.udto = udto;
	}

	public void loginOut() throws IOException {
		try {
			this.response.setContentType("text/json;charset=UTF-8");
			SalesOrder order = (SalesOrder) this.session.getAttribute("order");
			if (order.getItems().size() > 0) {
				// ///询问订单情况
				response.getWriter()
				.write("{\"message\":\"您的购物车还未处理?是否放弃退出\",\"success\":false,\"redirectURL\":\"order.jsp\"}");
			} else {
				// //确实退出操作
				this.session.removeAttribute("user");
				this.session.removeAttribute("order");
				response.getWriter()
						.write("{\"message\":\"退出成功\",\"success\":true,\"redirectURL\":\"index.jsp\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void realLoginOut() throws IOException{
		// //确实退出操作
		try {
			this.response.setContentType("text/json;charset=UTF-8");
			this.session.removeAttribute("user");
			this.session.removeAttribute("order");
			response.getWriter()
					.write("{\"message\":\"退出成功\",\"success\":true,\"redirectURL\":\"index.jsp\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		try {
			this.response.setContentType("text/json;charset=UTF-8");
			User u=(User)this.session.getAttribute("user");
			u.setAddress(udto.getUser().getAddress());
			u.setPhone(udto.getUser().getPhone());
			u.setPassword(udto.getUser().getPassword());
			this.userService.update(u);
			this.session.setAttribute("user", u);
			response.getWriter().write("{\"message\":\"修改成功\",\"success\":true,\"redirectURL\":\"index.jsp\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
