package com.spring.jdbc01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.model.BoardDAO;
import com.spring.model.BoardDTO;



@Controller
public class BoardController {

	@Autowired
	private BoardDAO dao;
	
	@RequestMapping("/board_list.do")
	public String list(Model model){
		
		List<BoardDTO> list=this.dao.getBoardList();
		model.addAttribute("List",list);
		return "board_list";
	}
	@RequestMapping("/board_writer.do")
	public String writeForm(Model model) {
		return "board_writeForm";
	}
	
	@RequestMapping("/board_write_ok.do")
	public String writeOk(BoardDTO dto, Model model){
		
		this.dao.insertBoard(dto);
		return "redirect:board_list.do";
	}
	
	@RequestMapping("/board_cont.do")
	public String cont(@RequestParam("no") int no,Model model){
		this.dao.readCount(no);    // 조회수 증가 메서드 호출
		BoardDTO dto=this.dao.boardCont(no);	// 게시글 상세내역 조회하는 메서드 호출
		model.addAttribute("cont",dto);
		return "board_cont";
		
	}
	
	@RequestMapping("/board_edit.do")
	public String modify(@RequestParam("no") int no,Model model) {
		BoardDTO dto=this.dao.boardCont(no);
		model.addAttribute("modify", dto);
		
		return "board_editForm";
	}
	
	@RequestMapping(value="/board_edit_ok.do", method=RequestMethod.POST)
	public void modifyOk(BoardDTO dto, HttpServletResponse response) throws IOException {
		BoardDTO dto2 = this.dao.boardCont(dto.getBoard_no());
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(dto2.getBoard_pwd().equals(dto.getBoard_pwd())) {
		this.dao.updateBoard(dto);
	
		
		
		out.println("<script>");
		out.println("alert('게시물 수정 성공!!!')");
		out.println("location.href='board_cont.do?no="+dto.getBoard_no()+"'");
		out.println("</script>");
		} else {
		out.println("<script>");
		out.println("alert('게시물 수정 실패')");
		out.println("history.back()");
		out.println("</script>");
		}
	

	}
	
	@RequestMapping("/board_delete.do")
	public String delete(@RequestParam("no") int no, Model model) {
		BoardDTO dto=this.dao.boardCont(no);
		model.addAttribute("delete", dto);
		
		return "board_deleteForm";
	}
	
	@RequestMapping(value="/board_delete_ok.do", method=RequestMethod.POST)
	public void deleteOk(BoardDTO dto,HttpServletResponse response) throws IOException {
		BoardDTO dto2 = this.dao.boardCont(dto.getBoard_no());	
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		
		if(dto2.getBoard_pwd().equals(dto.getBoard_pwd())) {
		this.dao.deleteBoard(dto.getBoard_no());			
		
		out.println("<script>");
		out.println("alert('게시물 삭제 성공!!!')");
		out.println("location.href='board_list.do'");
		out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시물 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
			
		}
	
		
	}
	@RequestMapping(value="/board_search.do", method=RequestMethod.POST)
	public String search(
			@RequestParam("search_type") String search_type,
			@RequestParam("seach_name") String seach_name,
			BoardDTO dto)
	{
		
		return "board_searchList";
	}
	
}
