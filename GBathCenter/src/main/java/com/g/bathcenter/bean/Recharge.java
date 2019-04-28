package com.g.bathcenter.bean;

public class Recharge extends CommonBean {
	private int id;
	private int customerId;
	private String customerName;
	private String customerPhoneNumber;
	private int recharge;
	private int regUserId;
	private String regUsername;
	private String regdt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getRecharge() {
		return recharge;
	}
	public void setRecharge(int recharge) {
		this.recharge = recharge;
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
	
}
