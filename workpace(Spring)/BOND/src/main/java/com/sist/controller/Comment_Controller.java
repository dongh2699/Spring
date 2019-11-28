package com.sist.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;



import com.sist.model.Content_CommentDTO;

import com.sist.model.MemberDTO;
import com.sist.service.B_ContentService;

// 세션 유지를 위한 장치
@SessionAttributes("login")
@Controller
public class Comment_Controller {

	private final B_ContentService b_ContentService;

	@Inject
	public Comment_Controller(B_ContentService b_ContentService) {
		this.b_ContentService = b_ContentService;
		
	}


	
	
	//댓글 생성하기
	@RequestMapping(value = "/create_Comment", method = RequestMethod.POST)
	@ResponseBody
	public String create_Comment(Content_CommentDTO dto, @RequestParam("bc_No") int bc_No, HttpSession session) {
		dto.setBc_No(bc_No);
		MemberDTO login_Member = (MemberDTO) session.getAttribute("login");

		dto.setM_Email(login_Member.getM_Email());
		// bc _ no m _meil cc_cont 가들어가있음
		int res = b_ContentService.create_Comment(dto);

		if (res == 1) {
			return "success";
		} else {
			return "false";
		}

	}

	//댓글 수정하기
	@RequestMapping(value = "/update_Comment", method = RequestMethod.POST)
	@ResponseBody
	public String update_Comment(Content_CommentDTO dto) {
	
		// bc _ no m _meil cc_cont 가들어가있음
		int res = b_ContentService.update_Comment(dto);

		if (res == 1) {
			return "success";
		} else {
			return "false";
		}

	}

	//대댓글 생성하기
	@RequestMapping(value = "/create_Reply_Comment", method = RequestMethod.POST)
	@ResponseBody
	public String create_Reply_Comment(Content_CommentDTO dto, HttpSession session) {
		MemberDTO login_Member = (MemberDTO) session.getAttribute("login");
		b_ContentService.update_Step(dto);

		dto.setM_Email(login_Member.getM_Email());
		int res = b_ContentService.create_Reply_Comment(dto);
		
		if (res >0) {
			return "success";
		} else {
			return "false";
		}

	}

	//댓글 삭제하기
	@RequestMapping(value = "/delete_Comment", method = RequestMethod.POST)
	@ResponseBody
	public String delete_Comment(Content_CommentDTO dto) {

		int res = b_ContentService.delete_Comment(dto);
	
		if (res > 0) {
			return "success";
		} else {
			return "false";
		}

	}

	//댓글 리스트 가져오기
	@RequestMapping(value = "/getComment_List", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public ResponseEntity getboard_List(Content_CommentDTO dto, HttpServletRequest request,
			@RequestParam("bc_No") int bc_No) {
		HttpHeaders responseHeaders = new HttpHeaders();
		dto.setBc_No(bc_No);
		ArrayList<HashMap> hmlist = new ArrayList<HashMap>();
		// 필요한 것 : 닉네임 시간 , 내용 ,프로필 사진 member 랑 게시판 조인
		List<HashMap<String, Object>> comment_list = this.b_ContentService.getcomment_list(dto);

		if (comment_list.size() > 0) {
			for (int i = 0; i < comment_list.size(); i++) {
					HashMap hm = new HashMap();
					hm.put("content", comment_list.get(i).get("CC_CONT"));
					hm.put("nickname", comment_list.get(i).get("M_NICKNAME"));
					hm.put("cc_no", comment_list.get(i).get("CC_NO"));
					hm.put("cc_Indent", comment_list.get(i).get("CC_INDENT"));
					hm.put("cc_step", comment_list.get(i).get("CC_STEP"));
					hm.put("cc_group", comment_list.get(i).get("CC_GROUP"));
					hm.put("cc_Date", comment_list.get(i).get("CC_DATE"));
					hm.put("bc_no", comment_list.get(i).get("BC_NO"));
					hm.put("picture", comment_list.get(i).get("M_PICTURE"));

					hmlist.add(hm);
				}
			}
		
		JSONArray json = new JSONArray(hmlist);
		return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
	}

}