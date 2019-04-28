package com.g.bathcenter.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.g.bathcenter.bean.User;
import com.g.bathcenter.mapper.UserMapper;
import com.g.bathcenter.util.SHA256Utils;

@Service
public class UserService implements UserIService {
	@Resource
	private UserMapper userMapper;

	@Override
	public void insert(User user) {
		userMapper.insert(user);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public User login(String username, String password) {
		User user = userMapper.findByUsername(username);
		if (user == null)
			return null;
		String sk = user.getSk();
		String spassword = SHA256Utils.SHA(password + sk);
		return userMapper.login(username, spassword);
	}

	@Override
	public void delete(int id) {
		userMapper.delete(id);
	}
}
