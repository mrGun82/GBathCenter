package com.g.bathcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g.bathcenter.bean.Expense;
import com.g.bathcenter.bean.Recharge;
import com.g.bathcenter.bean.User;
import com.g.bathcenter.mapper.ExpenseMapper;
import com.g.bathcenter.mapper.RechargeMapper;

@Service
public class ExpenseRechargeService implements ExpenseRechargeIService {
	@Autowired
	ExpenseMapper expenseMapper;

	@Autowired
	RechargeMapper rechargeMapper;

	@Override
	public void recharge(Recharge recharge) {
		rechargeMapper.insert(recharge);
	}

	@Override
	public List<Recharge> findRecharge(int customerId) {
		return rechargeMapper.find(customerId);
	}

	@Override
	public void expense(Expense expense) {
		expenseMapper.insert(expense);
	}

	@Override
	public List<Expense> findExpense(int customerId) {
		return expenseMapper.find(customerId);
	}

}
