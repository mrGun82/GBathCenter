package com.g.bathcenter.bean;

public class CodeSet {
	private int id;
	private String codeId;
	private String name;
	private String value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "CodeSet [id=" + id + ", codeId=" + codeId + ", name=" + name + ", value=" + value + "]";
	}
	
}
