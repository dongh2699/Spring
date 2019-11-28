package com.sist.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	//로그인 , 로그아웃시 세션을 관리하는 인터셉터
	
	private static final String LOGIN= "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public void postHandle(  //로그인 세션 설정
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		HttpSession httpSession =request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object memberDTO = modelMap.get("member");
		if(memberDTO != null) {
			logger.info("new Login succes");
			httpSession.setAttribute(LOGIN, memberDTO);
			response.sendRedirect("bond");  //성공시 메인화면 이동
		}
	}
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  //로그아웃 세션 설정
		    throws Exception {
		
		HttpSession httpSession = request.getSession();
		//기존의 로그인 정보 제거
		
		if(httpSession.getAttribute(LOGIN)!=null){
			logger.info("clear login data before");
			httpSession.removeAttribute(LOGIN);
		}
		return true;
	}
}
