package com.g.bathcenter.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.g.bathcenter.bean.User;
import com.g.bathcenter.service.UserIService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserIService userService;
	
	@RequestMapping("/login")
	public ModelAndView login(String username,String password) {
		String returnPage = "main";
		ModelAndView mav = new ModelAndView();
		
		User user = userService.login(username, password);
		if(user==null) {
			returnPage="index";
			mav.addObject("errMsg", "账号或密码错误");
		}else 
			session.setAttribute("user", user);
		mav.setViewName(returnPage);
		return mav;
	}
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private HttpSession session;
	
//	@RequestMapping("/login")
//	public String say() {
//		User user = userService.find(username,password);
//		session.setAttribute("user", user);
//		return "index";
//	}

//	@RequestMapping("/doLogin")
//	public String find(String username,String password) {
//		return null;
//	}

//	@RequestMapping("/ajax")
//	public String find1() {
//		return "[''message':'123dfx']";
//	}
}
