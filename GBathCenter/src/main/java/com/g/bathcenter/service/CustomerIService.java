package com.g.bathcenter.service;

import java.util.List;

import com.g.bathcenter.bean.Customer;
import com.g.bathcenter.bean.Expense;
import com.g.bathcenter.bean.Recharge;
import com.g.bathcenter.bean.User;

public interface CustomerIService {
	void save(Customer customer, User user, Expense expense, Recharge recharge);

	void update(Customer customer, User user, Expense expense, Recharge recharge);

	void delete(int id);

	List<Customer> find(Customer customer);

	Customer findById(int id);

	List<Recharge> findRecharge(int customerId);

	List<Expense> findExpense(int customerId);
}
