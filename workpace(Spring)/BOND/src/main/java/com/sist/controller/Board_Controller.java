package com.sist.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;


import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;



import com.sist.fileupload.Upload;

import com.sist.model.BlogDAO;

import com.sist.model.BlogMemberDAO;

import com.sist.model.Blog_contentDTO;

import com.sist.model.MemberDTO;
import com.sist.service.B_ContentFileService;
import com.sist.service.B_ContentService;

// 세션 유지를 위한 장치
@SessionAttributes("login")
@Controller
public class Board_Controller {

	
	@Autowired
	BlogDAO b_Dao;
	
	private final B_ContentFileService b_ContentFileService;
	private final B_ContentService b_ContentService;

	@Inject
	public Board_Controller(B_ContentService b_ContentService, B_ContentFileService b_ContentFileService) {
		this.b_ContentService = b_ContentService;
		this.b_ContentFileService = b_ContentFileService;
	}

	   @RequestMapping(value = "/getboard_List", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	   public ResponseEntity getboard_List(Blog_contentDTO dto, HttpServletRequest request,
	         @RequestParam("blog_no") int blog_no, HttpSession session) {
	      HttpHeaders responseHeaders = new HttpHeaders();
	      dto.setB_No(blog_no);
	      ArrayList<HashMap> hmlist = new ArrayList<HashMap>();
	      // 필요한 것 : 닉네임 시간 , 내용 ,프로필 사진 member 랑 게시판 조인
	      List<MemberDTO> boardList = this.b_ContentService.getBoardList(dto);
	      // 세션에서 회원 등급 가져오기
	      MemberDTO login_member = (MemberDTO) session.getAttribute("login");
	      // 회원 등급 가져오기
	      HashMap<String, Object> getMemeber_Grade = new HashMap<String, Object>();
	      getMemeber_Grade.put("m_Email", login_member.getM_Email());
	      getMemeber_Grade.put("b_No", blog_no);
	      String grade = b_Dao.getMember_Grade(getMemeber_Grade);
	      if (boardList.size() > 0) {
	         for (int i = 0; i < boardList.size(); i++) {
	            for (int j = 0; j < boardList.get(i).getBlog_contentDTO().size(); j++) {	        
	               HashMap hm = new HashMap();
	               hm.put("no", boardList.get(i).getBlog_contentDTO().get(j).getBc_No());
	               hm.put("writer", boardList.get(i).getM_Nickname());
	               hm.put("date", boardList.get(i).getBlog_contentDTO().get(j).getBc_Date().substring(0, 11));
	               hm.put("content", boardList.get(i).getBlog_contentDTO().get(j).getBc_Cont());
	               hm.put("writer_email", boardList.get(i).getM_Email());
	               hm.put("b_no", boardList.get(i).getBlog_contentDTO().get(j).getB_No());
	               hm.put("login_email", login_member.getM_Email());
	               hm.put("goods_count", boardList.get(i).getBlog_contentDTO().get(j).getBc_Goods());
	               hm.put("grade", grade);
	               if (boardList.get(i).getBlog_contentDTO().get(j).getBc_filecount() > 0) {
	                  List<String> files = this.b_ContentFileService
	                        .getFiles(boardList.get(i).getBlog_contentDTO().get(j).getBc_No());
	                  hm.put("uploadfiles", files);
	               }
	               hm.put("picture", boardList.get(i).getM_Picture());

	               hmlist.add(hm);
	            }
	         }
	      }
	      JSONArray json = new JSONArray(hmlist);

	      return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
	   }
		// 일반 회원 글쓰기
		@RequestMapping(value = "/create_board", method = RequestMethod.POST)
		@ResponseBody
		public String create_board(Blog_contentDTO dto, @RequestParam("blog_no") int b_no, HttpSession session)
				throws Exception {
			String resultStr = "";
			MemberDTO mdto = (MemberDTO) session.getAttribute("login");
			HashMap<String, Object> gmp = new HashMap<String, Object>();
			gmp.put("m_Email", mdto.getM_Email());
			gmp.put("b_No", b_no);
			dto.setB_No(b_no);
			int gradeChk = this.b_Dao.checkGrade(gmp);
			if (gradeChk > 0) {
				int res = b_ContentService.create_board(dto);
				if (res > 0) {
					resultStr = "success";
				}
			} else {
				resultStr = "notPermission";
			}
			return resultStr;
		}
		
		@RequestMapping(value = "/update_board", method = RequestMethod.POST)
		@ResponseBody
		public String update_board(Blog_contentDTO dto, @RequestParam("board_no") int board_no) throws Exception {
			String resultStr = "";
			dto.setBc_No(board_no);

			int res = b_ContentService.update_board(dto);
			if (res > 0) {
				resultStr = "success";
			} else {
				resultStr = "notPermission";
			}
			return resultStr;
		}

		@RequestMapping(value = "/delete_board", method = RequestMethod.POST)
		@ResponseBody
		public String delete_board(Blog_contentDTO dto, @RequestParam("board_no") int board_no) throws Exception {
			String resultStr = "";
			dto.setBc_No(board_no);

			int res = b_ContentService.delete_board(dto);
			if (res > 0) {
				resultStr = "success";
			} else {
				resultStr = "notPermission";
			}
			return resultStr;
		}

		// 공지사항 쓰기
		@RequestMapping(value = "/create_Notice", method = RequestMethod.POST)
		@ResponseBody
		public String create_notice(Blog_contentDTO dto, @RequestParam("blog_no") int b_no, HttpSession session)
				throws Exception {
			String resultStr = "";

			MemberDTO mdto = (MemberDTO) session.getAttribute("login");
			HashMap<String, Object> ngm = new HashMap<String, Object>();
			ngm.put("m_Email", mdto.getM_Email());
			ngm.put("b_No", b_no);
			dto.setB_No(b_no);
			int gradeChk = this.b_Dao.checkNotice(ngm);
			if (gradeChk > 0) {
				int res = b_ContentService.create_notice(dto);
				if (res > 0) {
					resultStr = "success";
				}
			} else {
				resultStr = "notPermission";
			}

			return resultStr;
		}
}