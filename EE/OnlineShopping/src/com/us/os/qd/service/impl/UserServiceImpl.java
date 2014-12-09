package com.us.os.qd.service.impl;

import org.springframework.stereotype.Component;
import com.us.os.qd.model.User;
import com.us.os.qd.services.UserService;
import com.us.os.qd.template.Services;

@Component("userServiceImpl")
public class UserServiceImpl extends Services implements UserService{

	public User login(User user) {
		String hql="from User u where u.username='"+user.getUsername()+"' and u.password='"+user.getPassword()+"'";
		User u=(User) this.crudHandler.sclar(hql);
		return u;
	}

	public boolean exists(User user) {
		String hql="from User u where u.username='"+user.getUsername()+"'";
		User u=(User) this.crudHandler.sclar(hql);
		return u==null?false:true;
	}

	public void save(User model) {
		this.crudHandler.save(model);
	}

	public User findById(Integer id) {
		return this.crudHandler.loadById(User.class, id);
	}

	public void update(User model) {
		this.crudHandler.update(model);
	}
}
