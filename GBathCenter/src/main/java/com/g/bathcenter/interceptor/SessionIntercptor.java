package com.g.bathcenter.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionIntercptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		System.out.println(request.getRequestURI());
		Object user = request.getSession().getAttribute("user");
        if (user == null) {
            try {
                response.sendRedirect(request.getContextPath()+"/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
	}
}
