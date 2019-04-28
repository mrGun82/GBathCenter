package com.g.bathcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.g.bathcenter.bean.Customer;
import com.g.bathcenter.bean.Expense;
import com.g.bathcenter.bean.Recharge;
import com.g.bathcenter.bean.User;
import com.g.bathcenter.mapper.CustomerMapper;

@Service("customerService")
public class CustomerService implements CustomerIService {
	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	ExpenseRechargeIService expenseRechargeService;

	@Override
	@Transactional
	public void save(Customer customer, User user, Expense expense, Recharge recharge) {
		customer.setRegUsername(user.getName());
		customer.setRegUserId(user.getId());
		int balance = recharge.getRecharge() - expense.getExpense();
		customer.setBalance(balance);
		customerMapper.insert(customer);
		if (customer.getExpense() != 0) {
			expense.setCustomerId(customer.getId());
			expenseRechargeService.expense(expense);
		}
		if (customer.getRecharge() != 0) {
			recharge.setCustomerId(customer.getId());
			expenseRechargeService.recharge(recharge);
		}
	}

	@Override
	@Transactional
	public void update(Customer customer, User user, Expense expense, Recharge recharge) {
		customer.setModUserId(user.getId());
		customer.setModUsername(user.getName());
		Customer sCustomer = customerMapper.findById(customer.getId());
		int balance = sCustomer.getBalance() - expense.getExpense() + recharge.getRecharge();
		customer.setBalance(balance);
		customerMapper.update(customer);
		if (customer.getExpense() != 0) {
			expenseRechargeService.expense(expense);
		}
		if (customer.getRecharge() != 0) {
			expenseRechargeService.recharge(recharge);
		}
	}

	@Override
	public Customer findById(int id) {
		return customerMapper.findById(id);
	}

	@Override
	public List<Customer> find(Customer customer) {
		return customerMapper.find(customer);
	}

	@Override
	public void delete(int id) {
		customerMapper.delete(id);
	}

	@Override
	public List<Recharge> findRecharge(int customerId) {
		return expenseRechargeService.findRecharge(customerId);
	}
	
	@Override
	public List<Expense> findExpense(int customerId) {
		return expenseRechargeService.findExpense(customerId);
	}
	
}
