package com.g.bathcenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("customer/list")
	public ModelAndView customerList() {
		ModelAndView mav = new ModelAndView("customer/list");
		return mav;
	}
	@RequestMapping("customer/historyExpense")
	public ModelAndView historyExpense(int customerId) {
		ModelAndView mav = new ModelAndView("customer/historyExpense");
		mav.addObject("customerId", customerId);
		return mav;
	}
	
	@RequestMapping("customer/historyRecharge")
	public ModelAndView historyRecharge(int customerId) {
		ModelAndView mav = new ModelAndView("customer/historyRecharge");
		mav.addObject("customerId", customerId);
		return mav;
	}
	
	@RequestMapping("code/list")
	public ModelAndView codeList() {
		ModelAndView mav = new ModelAndView("code/list");
		return mav;
	}
	@RequestMapping("statistics")
	public ModelAndView statistics() {
		ModelAndView mav = new ModelAndView("statistics/statistic");
		return mav;
	}
	@RequestMapping("user/list")
	public ModelAndView userList() {
		ModelAndView mav = new ModelAndView("user/list");
		return mav;
	}
}
