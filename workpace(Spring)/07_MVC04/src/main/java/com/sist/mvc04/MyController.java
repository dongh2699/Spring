package com.sist.mvc04;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sist.model.PersonDTO;

@Controller
public class MyController {

	
		@RequestMapping("/insert.do")
		public String insert() {
			
			return "insertForm";
		}
		
		@RequestMapping(value="/insert_ok1.do", method=RequestMethod.POST)
		
		public ModelAndView insertRes (HttpServletRequest request) {
			String name = request.getParameter("name").replace(" ", "");
			String age = request.getParameter("age").replace(" ", "");
			String phone = request.getParameter("phone").replace(" ", "");
			
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("info_name", name);
			mav.addObject("info_age", age);
			mav.addObject("info_phone", phone);
			
			mav.setViewName("insertResult1");
			return mav;
		}
		
		@RequestMapping(value="/insert_ok2.do", method=RequestMethod.POST)
		public String insertPro(PersonDTO dto, Model model) {
				// 파라미터로 넘어온 값을 dto의 속성에 일치하는 변수에 자동으로 추가해 줌
			
			model.addAttribute("dto", dto);
			
			return "insertResult2";
		}
		
}
