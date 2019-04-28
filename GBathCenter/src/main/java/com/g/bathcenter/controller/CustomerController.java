package com.g.bathcenter.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.g.bathcenter.bean.CommonBean;
import com.g.bathcenter.bean.Customer;
import com.g.bathcenter.bean.Expense;
import com.g.bathcenter.bean.Recharge;
import com.g.bathcenter.bean.User;
import com.g.bathcenter.service.CustomerIService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	
	@Autowired
	private CustomerIService customerService;
	
	@Autowired  
	private HttpSession session;
	
	@RequestMapping("/find")
	public PageInfo<Customer> find(Customer customer) {
		PageHelper.startPage(customer.getPageNum(), CommonBean.PAGE_SIZE);
        List<Customer> list= customerService.find(customer);
        PageInfo<Customer> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@RequestMapping("/findById")
	public Customer findById(int id) {
		Customer customer = customerService.findById(id);
		return customer;
	}
	
	@RequestMapping("/save")
	public void save(@RequestBody Customer customer) {
		User user = (User)session.getAttribute("user");
		Expense expense = new Expense();
		Recharge recharge = new Recharge();
		setER(expense, recharge, user, customer);		
		customerService.save(customer, user, expense, recharge);
	}

	@RequestMapping("/update")
	public void update(Customer customer) {
		User user = (User)session.getAttribute("user");
		Expense expense = new Expense();
		Recharge recharge = new Recharge();
		setER(expense, recharge, user, customer);
		customerService.update(customer, user, expense, recharge);
	}
	
	private void setER(Expense expense,Recharge recharge,User user,Customer customer) {
		if (customer.getExpense() != 0) {
			expense.setRegUserId(user.getId());
			expense.setRegUsername(user.getName());
			expense.setExpense(customer.getExpense());
			expense.setCustomerId(customer.getId());
			expense.setCustomerName(customer.getName());
			expense.setCustomerPhoneNumber(customer.getPhoneNumber());
			expense.setExpenseTypeIds(customer.getExpenseTypeIds());
			expense.setExpenseTypeNames(customer.getExpenseTypeNames());
		}
		if (customer.getRecharge() != 0) {
			recharge.setRegUserId(user.getId());
			recharge.setRegUsername(user.getName());
			recharge.setRecharge(customer.getRecharge());
			recharge.setCustomerId(customer.getId());
			recharge.setCustomerName(customer.getName());
			recharge.setCustomerPhoneNumber(customer.getPhoneNumber());
		}
	}
	
	
	@RequestMapping("/delete")
	public void delete(int id) {
		customerService.delete(id);
	}
	
	@RequestMapping(path="/findRecharge" ,method=RequestMethod.POST)
	public PageInfo<Recharge> findRecharge(Customer customer) {
		PageHelper.startPage(customer.getPageNum(), CommonBean.PAGE_SIZE_MODAL);
		List<Recharge> list = customerService.findRecharge(customer.getId());
		PageInfo<Recharge> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	@RequestMapping(path="/findExpense" ,method=RequestMethod.POST)
	public PageInfo<Expense> findExpense(Customer customer) {
		PageHelper.startPage(customer.getPageNum(), CommonBean.PAGE_SIZE_MODAL);
		List<Expense> list = customerService.findExpense(customer.getId());
		PageInfo<Expense> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
}
