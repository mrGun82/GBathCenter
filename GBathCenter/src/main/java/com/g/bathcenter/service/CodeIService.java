package com.g.bathcenter.service;

import java.util.List;

import com.g.bathcenter.bean.Code;
import com.g.bathcenter.bean.CodeSet;

public interface CodeIService {
	List<Code> find(Code code);
	public CodeSet saveCodeSet(CodeSet codeSet);
	public void removeCodeSet(int id);
}
