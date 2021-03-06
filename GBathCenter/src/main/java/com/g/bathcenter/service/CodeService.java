package com.g.bathcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g.bathcenter.bean.Code;
import com.g.bathcenter.bean.CodeSet;
import com.g.bathcenter.mapper.CodeMapper;

@Service
public class CodeService implements CodeIService {

	@Autowired
	CodeMapper codeMapper;
	
	@Override
	public List<Code> find(Code code) {
		return codeMapper.find(code);
	}

	@Override
	public CodeSet saveCodeSet(CodeSet codeSet) {
		codeMapper.insertCodeSet(codeSet);
		return codeSet;
	}

	@Override
	public void removeCodeSet(int id) {
		CodeSet codeSet = new CodeSet();
		codeSet.setId(id);
		codeSet.setActive(0);
		codeMapper.updateCodeSet(codeSet);
	}
}
