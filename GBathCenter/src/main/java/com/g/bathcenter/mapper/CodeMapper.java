package com.g.bathcenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.g.bathcenter.bean.Code;

@Mapper
public interface CodeMapper {
	public List<Code> find(Code code);
}
