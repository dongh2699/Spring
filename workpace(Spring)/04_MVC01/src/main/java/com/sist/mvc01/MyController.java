package com.sist.mvc01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
		
	/*
	 * ModelAndView 객체는 컨트롤러에 의해서 비지느스 로직이 수행되고 나면
	 * 대체로 사용자에게 반환되어 브라우저에 표시될 정보가 만들어진다.
	 * 이 때 만들어진 정보를 View에 넘겨줄 때 응답할 View Page와 전달할 값을
	 * 한꺼번에 같이 저장하여 넘겨줄 때 사용함.
	 */
	
		@RequestMapping("/mav")
		public ModelAndView mav() {
			ModelAndView mav = new ModelAndView();
			mav.addObject("email","hong@naver.com"); // 정보 키와 Value로 저장
			mav.setViewName("member/email"); // 이동할 페이지 View Page
			
			return mav;
		}
}
