package com.g.bathcenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.g.bathcenter.bean.Code;
import com.g.bathcenter.bean.CodeSet;

@Mapper
public interface CodeMapper {
	public List<Code> find(Code code);
	public void insertCodeSet(CodeSet codeSet);
	public void updateCodeSet(CodeSet codeSet);
}
