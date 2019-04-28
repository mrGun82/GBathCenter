package com.g.bathcenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g.bathcenter.bean.Code;
import com.g.bathcenter.bean.CommonBean;
import com.g.bathcenter.bean.Customer;
import com.g.bathcenter.controller.InitializingData.ContextDataType;
import com.g.bathcenter.service.CodeIService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/code")
public class CodeController {
	
	@Autowired
	private CodeIService codeService;
	@Autowired
	InitializingData initializingData;
	@RequestMapping("/find")
	public PageInfo<Code> find(Code code) {
		PageHelper.startPage(1, CommonBean.PAGE_SIZE);
		List<Code> list = codeService.find(code);
		PageInfo<Code> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@RequestMapping("/findById")
	public Code findById(int id) {
		return null;
	}
	
	@RequestMapping("/save")
	public void save(Code code) {
		initializingData.resetContext(InitializingData.ContextDataType.APP_CODES);
	}

	@RequestMapping("/update")
	public void update(Code code) {
	}
}
