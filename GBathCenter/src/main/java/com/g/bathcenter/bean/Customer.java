package com.g.bathcenter.bean;

public class Customer extends CommonBean {
	private int id;
	private int regUserId;
	private String regUsername;
	private String name;
	private String phoneNumber;
	private int balance;
	private int gender;
	private int agerange;
	private int level;
	private String birthday;
	private String modUsername;
	private int modUserId;
	private int active;
	
	private String expenseTypeIds;
	private String expenseTypeNames;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRegUserId() {
		return regUserId;
	}

	public void setRegUserId(int regUserId) {
		this.regUserId = regUserId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAgerange() {
		return agerange;
	}

	public void setAgerange(int agerange) {
		this.agerange = agerange;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRegUsername() {
		return regUsername;
	}

	public void setRegUsername(String regUsername) {
		this.regUsername = regUsername;
	}

	public String getModUsername() {
		return modUsername;
	}

	public void setModUsername(String modUsername) {
		this.modUsername = modUsername;
	}

	public int getModUserId() {
		return modUserId;
	}

	public void setModUserId(int modUserId) {
		this.modUserId = modUserId;
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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", regUserId=" + regUserId + ", regUsername=" + regUsername + ", name=" + name
				+ ", phoneNumber=" + phoneNumber + ", balance=" + balance + ", gender=" + gender + ", agerange="
				+ agerange + ", level=" + level + ", birthday=" + birthday + ", modUsername=" + modUsername
				+ ", modUserId=" + modUserId + ", active=" + active + ", expenseTypeIds=" + expenseTypeIds
				+ ", expenseTypeNames=" + expenseTypeNames + "]";
	}

}
