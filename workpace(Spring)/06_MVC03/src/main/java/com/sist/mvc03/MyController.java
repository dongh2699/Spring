package com.sist.mvc03;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	
		@RequestMapping("/input")
		public String go() {
			
			return "insertForm";
		}
		
		@RequestMapping("/inputOk")
		public ModelAndView getPerson
		(@RequestParam("name") String name,
		@RequestParam("id") String id	) {
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("req_name", name);
			mav.addObject("req_id", id);
			mav.setViewName("person/personInfo");
			return mav;
		}
		
		@RequestMapping(value="/inputOk", method=RequestMethod.POST)
		public ModelAndView getPerson(HttpServletRequest request) {
			String name = request.getParameter("name").replace(" ","");
			String id = request.getParameter("id").replace(" ","");
			
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("req_name", name);
			mav.addObject("req_id", id);
			mav.setViewName("person/personInfo");
			return mav;
		}
		
}
