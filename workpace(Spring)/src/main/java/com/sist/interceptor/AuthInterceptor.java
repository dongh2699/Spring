package com.sist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sist.model.MemberDTO;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	//로그인이 안되었을때 밴드로 들어가지 못하게 설정하는 인터셉터
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		    throws Exception {
		
		HttpSession httpSession = request.getSession();
		
		if(httpSession.getAttribute("login")==null){  
			logger.info("current user is not logged");
			response.sendRedirect("/BOND/login");
			return false;
		}
			return true;
		}
	

	
}
