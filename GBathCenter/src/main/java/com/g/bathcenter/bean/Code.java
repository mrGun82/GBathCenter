package com.g.bathcenter.bean;

import java.util.List;

public class Code {
	private String id;
	private String name;
	private String value;
	private List<CodeSet> codeSets;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public List<CodeSet> getCodeSets() {
		return codeSets;
	}
	public void setCodeSets(List<CodeSet> codeSets) {
		this.codeSets = codeSets;
	}
	@Override
	public String toString() {
		return "Code [id=" + id + ", name=" + name + ", value=" + value + ", codeSets=" + codeSets + "]";
	}
	
	
}
