package com.g.bathcenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.g.bathcenter.interceptor.SessionIntercptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Autowired
	SessionIntercptor sessionIntercptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration ir = registry.addInterceptor(sessionIntercptor);
		ir.excludePathPatterns("/error");
		ir.excludePathPatterns("/static/**");
		ir.excludePathPatterns("/index");
		ir.excludePathPatterns("/login");
		ir.excludePathPatterns("/css/**");
		ir.excludePathPatterns("/js/**");
		ir.excludePathPatterns("/user/login");
		ir.addPathPatterns("/**");
		
	}
}
