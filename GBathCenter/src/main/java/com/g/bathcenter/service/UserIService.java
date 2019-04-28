package com.g.bathcenter.service;

import com.g.bathcenter.bean.User;

public interface UserIService {
	void insert(User user);

	void update(User user);

	User login(String username,String password);

	void delete(int id);
}
