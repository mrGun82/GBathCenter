package com.g.bathcenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.g.bathcenter.bean.Expense;

@Mapper
public interface ExpenseMapper {
	void insert(Expense expense);

	List<Expense> find(int customerId);
}
