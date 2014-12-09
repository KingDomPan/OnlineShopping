package com.us.os.qd.services;

import com.us.os.qd.model.User;
import com.us.os.qd.template.ServiceTemplate;

public interface UserService extends ServiceTemplate<User> {
	boolean exists(User user);
	User login(User u);
}
