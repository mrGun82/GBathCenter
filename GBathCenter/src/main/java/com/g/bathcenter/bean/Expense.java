package com.g.bathcenter.bean;

public class Expense extends CommonBean {
	private int id;
	private int customerId;
	private String customerName;
	private String customerPhoneNumber;
	private int expense;
	private String expenseTypeIds;
	private String expenseTypeNames;
	private int regUserId;
	private String regUsername;
	private String regdt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

	public String getExpenseTypeIds() {
		return expenseTypeIds;
	}

	public void setExpenseTypeIds(String expenseTypeIds) {
		this.expenseTypeIds = expenseTypeIds;
	}

	public String getExpenseTypeNames() {
		return expenseTypeNames;
	}

	public void setExpenseTypeNames(String expenseTypeNames) {
		this.expenseTypeNames = expenseTypeNames;
	}

	public int getRegUserId() {
		return regUserId;
	}

	public void setRegUserId(int regUserId) {
		this.regUserId = regUserId;
	}

	public String getRegUsername() {
		return regUsername;
	}

	public void setRegUsername(String regUsername) {
		this.regUsername = regUsername;
	}

	public String getRegdt() {
		return regdt;
	}

	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
}
