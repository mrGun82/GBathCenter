package com.g.bathcenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.g.bathcenter.bean.Customer;

@Mapper
public interface CustomerMapper {

	public void insert(Customer customer);

	public void update(Customer customer);

	public void delete(int id);

	public List<Customer> find(@Param("customer")Customer customer);

	public Customer findById(int id);
}
