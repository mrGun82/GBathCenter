package com.g.bathcenter.bean;

public abstract class CommonBean {
	
	public static final int PAGE_SIZE=10;
	public static final int PAGE_SIZE_MODAL=8;
	
	private int pageNum;
	private int expense;//消费金额
	private int recharge;//充值金额
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

	public int getRecharge() {
		return recharge;
	}

	public void setRecharge(int recharge) {
		this.recharge = recharge;
	}
	
}
