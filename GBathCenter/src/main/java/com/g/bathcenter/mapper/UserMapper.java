package com.g.bathcenter.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.g.bathcenter.bean.User;

@Mapper
public interface UserMapper {
	
	public void insert(User user);

	public void update(User user);

	public void delete(int id);

	public User login(String username,String password);
	
	public User findByUsername(String name);
}
