package com.sist.mybatis;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.model.userDAO;
import com.sist.model.userDTO;

@Controller
public class UserController {
	
	
	@Autowired
	private userDAO dao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		
		return "main";
	}
	
	@RequestMapping("/user_main.do")
	String goMain() {
		return "main";
	}
	
	
	@RequestMapping("/user_login.do")
	String userLogin() {
		
		return "userLoginForm";
	}
	
	@RequestMapping(value="/user_loginOk.do" , method = RequestMethod.POST)
	void userLoginOk(
			@RequestParam("inputId") String inputId,
			@RequestParam("inputPwd") String inputPwd,
			Model model,HttpServletResponse response, HttpSession session
			) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		userDTO dto = new userDTO();
		dto.setUserId(inputId);
		dto.setUserPwd(inputPwd);
		
		//여기서 회원인지 체크함과 동시에 userDTO를 받아옴
		dto = this.dao.UserLogin(dto);
		
		PrintWriter out = response.getWriter();
		
		
		session.setAttribute("LoginUser", dto);
		
		if(dto != null) {
			out.println("<script>");
			out.println("alert('로그인 성공!!!')");
			out.println("location.href='user_list.do'"); 
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호가 잘못되었습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping(value = "/user_IdCheck.do", method = RequestMethod.POST)
	@ResponseBody
	String UserIdCheck(@RequestParam ("user_id") String userId, HttpServletResponse response ) throws IOException {
		int res = 0;
		String pass="false";
		response.setContentType("text/html; charset=UTF-8");
		
		res = this.dao.userIdCheck(userId);
		if(res>0) {
			pass = "true";
		} else {
			pass = "false";
		}
		
		return pass;
	}
	
	@RequestMapping("/user_join.do")
	String ShowJoinPage() {
		
		return "userJoin";
	}
	
	@RequestMapping("/user_LogOut.do")
	String LogOut(HttpSession session) {
		session.removeAttribute("LoginUser");
		
		return "userJoin";
	}
	
	@RequestMapping(value = "/user_insert.do", method = RequestMethod.POST)
	void joinUser(userDTO dto, HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		
		int res = 0;
		
		
		PrintWriter out = response.getWriter();
	
			
		res = this.dao.insertUser(dto);
		session.setAttribute("LoginUser", dto);
		
		if(res >0) {
			out.println("<script>");
			out.println("alert('가입 성공!!!')");		
			out.println("location.href='user_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('가입 실패')");
			out.println("history.back()");
			out.println("</script>");
			}
		
	}
	
	@RequestMapping("/user_list.do")
	String joinUser(Model model){
		
		
		model.addAttribute("UserList", dao.showList());
		
		return "userList";
	}
	
	@RequestMapping("/userListInfo.do")
	String userListInfo(@RequestParam("ListUserId") String userId, Model model) {
	
	model.addAttribute("ListUserInfo", this.dao.UserInfo(userId));

		return "userListInfo";
	}
	
	@RequestMapping("/user_LogInfo.do")
	String userContent(HttpSession session, Model model) {
		userDTO dto =  (userDTO) session.getAttribute("LoginUser");
		model.addAttribute("userLogInfo", this.dao.UserInfo(dto.getUserId()));
			
		return "userLogInfo";
	}
	
	@RequestMapping("/userInfo_edit.do")
	void userModify(userDTO dto, HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		int res = 0;
		
		res = this.dao.modifyUser(dto);
		PrintWriter out = response.getWriter();
		
		if(res >0) {
			out.println("<script>");
			out.println("alert('수정완료!! 수정된 정보로 다시 로그인 해주세요!')");
			session.removeAttribute("LoginUser");
			out.println("location.href='user_login.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("/user_delete.do")
	String userDelete(){
		
		return "userDeleteForm";
	}
	
	@RequestMapping(value = "/user_deleteOk.do" , method = RequestMethod.POST)
	
	void userDeleteOk(userDTO dto, Model model,HttpServletResponse response, HttpSession session
			) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		System.out.println(dto.getUserId());
		int res = 0;
		
		userDTO DBdto = this.dao.UserInfo(dto.getUserId());
		PrintWriter out = response.getWriter();
		if(DBdto.getUserPwd().equals(dto.getUserPwd())) {
			res = this.dao.deleteUser(dto);
		
			if(res > 0) {
				out.println("<script>");
				out.println("alert('회원탈퇴 성공!!!')");
				session.removeAttribute("LoginUser");
				out.println("location.href='user_join.do'"); 
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원탈퇴 실패!')");
				out.println("history.back()");
				out.println("</script>");
			}
		
		
		} else {
			out.println("<script>");
			out.println("alert('비밀번호가 잘못되었습니다!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		
		
	
		
		
		
		
		
	}
	
	
}
