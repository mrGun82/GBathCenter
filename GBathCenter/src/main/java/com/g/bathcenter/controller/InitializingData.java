package com.g.bathcenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.g.bathcenter.bean.Code;
import com.g.bathcenter.service.CodeIService;

@Component
public class InitializingData implements InitializingBean {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private CodeIService codeService;

	@Override
	public void afterPropertiesSet() throws Exception {
		resetContext(ContextDataType.APP_CODES);
	}

	public void resetContext(ContextDataType cdt) {
		switch (cdt) {
		case APP_CODES:
			resetAppCodes();
			break;
		default:
			break;
		}
	}

	private void resetAppCodes() {
		List<Code> codes = codeService.find(null);
		Map<String, Code> appCodes = new HashMap<>();
		codes.forEach(code -> {
			appCodes.put(code.getId(), code);
		});
		servletContext.setAttribute(ContextDataType.APP_CODES.getName(), appCodes);
	}

	public enum ContextDataType {
		ALL("all"), APP_CODES("appCodes");
		private ContextDataType(String name) {
			this.name = name;
		}
		String name;
		public String getName() {
			return name;
		}
	}
}
