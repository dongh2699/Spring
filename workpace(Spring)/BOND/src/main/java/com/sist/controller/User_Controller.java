package com.sist.controller;


import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import javax.mail.internet.MimeMessage;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.sist.fileupload.Upload;



import com.sist.model.MemberDAO;
import com.sist.model.MemberDTO;


// 세션 유지를 위한 장치
@SessionAttributes("login")
@Controller
public class User_Controller {

	@Autowired
	MemberDAO m_Dao;

	@Inject // 서비스를 호출하기 위해서 의존성을 주입
	JavaMailSender mailSender; // 메일 서비스를 사용하기 위해 의존성을 주입함
	// 회원가입 페이지 이동
	@Autowired
	Upload upload;
	

	
	
	
	//회원가입 페이지로 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinForm() {

		return "join";
	}

	// 회원가입
	@RequestMapping(value = "/join_Ok", method = RequestMethod.POST)
	public void joinOk(MultipartHttpServletRequest mRequest, MemberDTO dto, HttpServletResponse response)
			throws IOException {
		int res = 0;
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ArrayList<String> upload_Paths = this.upload.fileUpload(mRequest);
		for (String path : upload_Paths) {
			dto.setM_Picture(path);
		}
		dto.setM_Pw(BCrypt.hashpw(dto.getM_Pw(), BCrypt.gensalt())); // 암호화
		dto.setM_Birth(dto.getM_Birth().replace("-", "")); // 생년월일 - 뺴는 과정
		res = this.m_Dao.createMember(dto);
		if (res == 0) { // 실패

			out.println("<script>");
			out.println("alert('회원가입에 실패했습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else { // 성공

			out.println("<script>");
			out.println("alert('회원가입에 성공했습니다!')");
			out.println("location.href='/BOND/login'");
			out.println("</script>");
		}

	}

	// 유저 마이페이지로 이동
	@RequestMapping(value = "/userDetail", method = RequestMethod.POST)
	public String userDtail(HttpSession session, Model model, @RequestParam("m_Pw") String m_Pw,
			HttpServletResponse response) throws IOException {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
	
		if (BCrypt.checkpw(m_Pw, dto.getM_Pw())) { // 비밀번화 확인 성공
			model.addAttribute("Login_Member", dto);
		
		} else { // 실패
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.!')");
			out.println("history.back()");
			out.println("</script>");
		}
		return "userDetail";
	}

	// 유저 정보 수정
	@RequestMapping(value = "/modify_Member", method = RequestMethod.POST)
	public void modify_Member(MultipartHttpServletRequest mRequest, MemberDTO dto, HttpServletResponse response)
			throws IOException {
		String hashedpw = BCrypt.hashpw(dto.getM_Pw(), BCrypt.gensalt());
		
		response.setContentType("text/html; charset=UTF-8");
		ArrayList<String> upload_Paths = this.upload.fileUpload(mRequest);
		dto.setM_Picture(upload_Paths.get(0));
		dto.setM_Pw(hashedpw);
		dto.setM_Birth(dto.getM_Birth().replace("-", ""));
		PrintWriter out = response.getWriter();
		int res = this.m_Dao.modify_Member(dto);
		if (res == 1) { // 성공
			out.println("<script>");
			out.println("alert('회원정보가 수정되었습니다!')");
			out.println("location.href='/BOND/log_out'");
			out.println("</script>");
		} else { // 실패
			out.println("<script>");
			out.println("alert('회원정보가 수정되지 않았습니다!')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	// 유저 정보 삭제
	@RequestMapping(value = "/delete_Member", method = RequestMethod.POST)
	public void delete_Member(HttpSession session, HttpServletResponse response ,@RequestParam("m_Email") String m_Email) throws IOException {
		
		int res = this.m_Dao.delete_Member(m_Email.trim());
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (res == 1) { // 성공
			out.println("<script>");
			out.println("alert('회원정보가 삭제되었습니다!')");
			out.println("location.href='/BOND/log_out'");
			out.println("</script>");
		} else { // 실패
			out.println("<script>");
			out.println("alert('회원정보가 삭제되지 않았습니다!')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	// 로그인화면 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {

		return "login";
	}
	
	// 로그인 확인
	@RequestMapping(value = "/login_Ok", method = RequestMethod.POST)
	public void loginCheck(MemberDTO dto, HttpSession session, Model model) {
		MemberDTO memberDTO = null;
		boolean login = true;
		try {
			memberDTO = m_Dao.loginCheck(dto.getM_Email());
			login = !BCrypt.checkpw(dto.getM_Pw(), memberDTO.getM_Pw()); // 비밀번호가 틀리면 false 맞으면 true
		} catch (Exception e) {
			login = false;
		}
		if (login) { // false일떄
			return;
		}
		;
		model.addAttribute("member", memberDTO);

	}

	// 로그아웃
	@RequestMapping(value = "/log_out", method = RequestMethod.GET)
	public String login_Out() {
		return "login";
	}

	// AJAX 이메일 확인 처리
	@RequestMapping(value = "/EmailCheck", method = RequestMethod.POST)
	@ResponseBody
	String EmailCheck(@RequestParam("m_Email") String m_email) {
		int res = 0;
		String pass = "false";
		res = this.m_Dao.EmailCheck(m_email);

		if (res > 0) {
			pass = "true";
		} else {
			pass = "false";
		}

		return pass;
	}

	// 마이페이지 전 비밀번호 확인창으로 이동
	@RequestMapping(value = "/check_Pw", method = RequestMethod.GET)
	public String pw_Check() {

		return "check_Pw";
	}
	//이메일 찾기 이동
		@RequestMapping("find_email")
		public String find_email() {
			return "find_email";
		}
		//비번 찾기 페이지 이동
		@RequestMapping("find_pwd")
		public String find_pwd() {
			return "find_pwd";
		}
		
		//비밀번호 수정 페이지 이동
		@RequestMapping(value = "/moidfy_pwd", method = RequestMethod.POST)
		public String modifypwd(Model model, @RequestParam("m_Email") String m_Email) {
			model.addAttribute("m_Email", m_Email);
			return "modify_pwd";
		}
		
		//비밀번호 수정하기
		@RequestMapping(value = "/modify_pwd_ok", method = RequestMethod.POST)
		public void modifypwdOk(MemberDTO dto, HttpServletResponse response) throws IOException {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			String hashedpw = BCrypt.hashpw(dto.getM_Pw(), BCrypt.gensalt());
			dto.setM_Pw(hashedpw);
			int res = this.m_Dao.modifypwd(dto);
			if (res > 0) {
				out.println("<script>");
				out.println("alert('비밀번호 수정 완료')");
				out.println("location.href='/BOND/login'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('비밀번호 수정 실패')");
				out.println("history.back()");
				out.println("</script>");
			}

		}
		
		//이메일 찾기 
		@RequestMapping(value = "/find_email_ok", method = RequestMethod.POST)
		public void find_email_OK(MemberDTO dto, HttpServletResponse response) throws IOException {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			List<MemberDTO>dto2 = this.m_Dao.findEmail(dto);

		

			if (dto2 != null) {
				out.println("<script>");
				if(dto2.size()>1){
				out.println("alert('해당하는 정보에 이메일이 총"+ dto2.size()+ "개 존재합니다.')");
					for(int i=0; i<dto2.size();i++){
				out.println("alert('이메일은" + dto2.get(i).getM_Email() + "입니다')");
					}
				}else{
				out.println("alert('이메일은" + dto2.get(0).getM_Email() + "입니다')");				
				}
				out.println("location.href='/BOND/login'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('이름과 연락처를 다시 확인해주세요')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
		
		//이메일 보내기
		@RequestMapping(value = "/mailsend", method = RequestMethod.POST)
		@ResponseBody
		public String mailsending(@RequestParam("m_Email") String m_Email) throws IOException {

			Random r = new Random();
			String dice = Integer.toString((r.nextInt(4589362) + 49311));

			String content = System.getProperty("line.separator") + // 한줄씩 줄간격을 두기위해
																	// 작성
					System.getProperty("line.separator") +

					"안녕하세요 회원님 저희 홈페이지를 찾아주셔서 감사합니다"

					+ System.getProperty("line.separator") +

					System.getProperty("line.separator") +

					" 인증번호는 " + dice + " 입니다. "

					+ System.getProperty("line.separator") +

					System.getProperty("line.separator") +

					"받으신 인증번호를 홈페이지에 입력해 주시면 다음으로 넘어갑니다."; // 내용

			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

				messageHelper.setFrom("rkddlsejr1@gamil.com"); // 보내는사람 생략하면 정상작동을
																// 안함
				messageHelper.setTo(m_Email); // 받는사람 이메일
				messageHelper.setSubject("BOND 회원 인증 이메일 입니다."); // 메일제목은 생략이 가능하다
				messageHelper.setText(content); // 메일 내용

				mailSender.send(message);
			} catch (Exception e) {
			
			}
			return dice;
		}
		
}