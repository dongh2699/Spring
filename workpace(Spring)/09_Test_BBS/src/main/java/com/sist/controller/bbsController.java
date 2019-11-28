package com.sist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.model.bbsDAO;
import com.sist.model.bbsDTO;

@Controller
public class bbsController {
	
	@Autowired
	private bbsDAO dao;
	
	@RequestMapping("/board_list.do")
	public String getList(Model model){
		List<bbsDTO> list = this.dao.getBoardList();
		model.addAttribute("List", list);
		return "board_list";
	}

	@RequestMapping("/board_write.do")
	public String write(Model model) {
		
		
		return "board_write";
	}
	
	@RequestMapping(value="/board_write_ok.do", method=RequestMethod.POST)
	public void writeOk(bbsDTO dto, HttpServletResponse response) throws IOException {
		int res = this.dao.insertBoard(dto);
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(res >0 ) {
			out.println("<script>");
			out.println("alert('글쓰기 성공!!!')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('글쓰기 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	
	}
	
	@RequestMapping("/board_cont.do")
	public String showCont(@RequestParam("no") int no,Model model ) {
		this.dao.readCount(no);
		bbsDTO dto = this.dao.boardCont(no);
		model.addAttribute("cont", dto);
		
		return "board_cont";
	}
	
	@RequestMapping("/board_update.do")
	public String update(@RequestParam("no") int no,Model model) {
		bbsDTO dto = this.dao.boardCont(no);
		model.addAttribute("update", dto);
		return "board_updateForm";
	}

	@RequestMapping(value="/board_update_ok.do", method=RequestMethod.POST)
	public void updateOk(bbsDTO dto, HttpServletResponse response) throws IOException {
		int res=0;
		
		res = this.dao.updateBoard(dto);
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		if(res >0 ) {
			out.println("<script>");
			out.println("alert('게시글 수정 성공!!!')");
			out.println("location.href='board_cont.do?no="+dto.getBoard_no()+"'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("/board_delete.do")
	public void delete(@RequestParam("no")int no, HttpServletResponse response ) throws IOException {
		int res = 0;
		res = this.dao.deleteBoard(no);
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		if(res >0 ) {
			out.println("<script>");
			out.println("alert('게시글 삭제 성공!!!')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	@RequestMapping("/board_reply.do")
	public String reply(@RequestParam("no")int no,Model model){
		bbsDTO dto = this.dao.boardCont(no);
		model.addAttribute("reply", dto);
		return "board_replyForm";
	}
	
	
	@RequestMapping(value="/board_reply_ok.do", method=RequestMethod.POST)
	public void replyOk(bbsDTO dto,HttpServletResponse response) throws IOException{
		int res = 0;
		
		
		
		int board_group = dto.getBoard_group();
		int board_step = dto.getBoard_step();
		this.dao.replyUpdate(board_group, board_step);
		res = this.dao.replyBoard(dto);
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		if(res >0 ) {
			out.println("<script>");
			out.println("alert('게시글 답변 성공!!!')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 답변 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
}
