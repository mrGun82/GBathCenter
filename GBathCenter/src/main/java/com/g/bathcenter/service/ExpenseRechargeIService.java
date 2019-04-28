package com.g.bathcenter.service;

import java.util.List;

import com.g.bathcenter.bean.Expense;
import com.g.bathcenter.bean.Recharge;
import com.g.bathcenter.bean.User;

interface ExpenseRechargeIService {

	void recharge(Recharge recharge);

	void expense(Expense expense);

	List<Recharge> findRecharge(int customerId);
	
	List<Expense> findExpense(int customerId);
}
