package com.spring.mybatis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
	public String list(Model model) {
		List<BoardDTO> list = this.dao.getList();
		model.addAttribute("List", list);
		return "board_list";
	}
	
	@RequestMapping("/board_writer.do")
	public String write() {
		
		return "board_write";
	}
	
	@RequestMapping(value="/board_write_ok", method=RequestMethod.POST)
	public void wirteOk(BoardDTO dto, Model model,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		int res=0;
		res = this.dao.insertBoard(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res >0) {
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
	public String cont(@RequestParam("no")int no,Model model) {
		
		this.dao.readCount(no);
		BoardDTO dto = this.dao.content(no);
		model.addAttribute("cont", dto);
		
		return "board_cont";
	}

	@RequestMapping("/board_edit.do")
	public String modify(@RequestParam("no")int no,Model model){
		BoardDTO dto = this.dao.content(no);
		model.addAttribute("modify", dto);
		
		return "board_editForm";
		
	}
	
	@RequestMapping(value="/board_edit_ok", method=RequestMethod.POST)
	public void modifyOk(BoardDTO dto, HttpServletResponse response) throws IOException{
		int res=0;
		BoardDTO dto2 = this.dao.content(dto.getBoard_no());
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(dto2.getBoard_pwd().equals(dto.getBoard_pwd())) {
		
		res = this.dao.updateBoard(dto);
	
		if(res>0) {
		
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
		} else {
			out.println("<script>");
			out.println("alert('비밀 번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}
	
	@RequestMapping("/board_delete.do")
	public String delete(@RequestParam("no")int no, Model model) {
		
		BoardDTO dto=this.dao.content(no);
		model.addAttribute("delete", dto);
		
		return "board_pwdForm";
	}

	@RequestMapping(value="/board_delete_ok.do", method=RequestMethod.POST)
	public void deleteOk(BoardDTO dto, HttpServletResponse response) throws IOException {
		int res=0;
		response.setContentType("text/html; charset=UTF-8");		
		PrintWriter out = response.getWriter();
		
		BoardDTO dto2 = this.dao.content(dto.getBoard_no());
		if(dto2.getBoard_pwd().equals(dto.getBoard_pwd())) {
			res = this.dao.deleteBoard(dto.getBoard_no());
			if(res >0) {
				out.println("<script>");
				out.println("alert('글 삭제 성공!!!')");
				out.println("location.href='board_list.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('글 삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		} else {
			out.println("<script>");
			out.println("alert('비밀 번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
	}
	
	
	@RequestMapping(value="/board_search.do", method=RequestMethod.POST)
		public String search(
				@RequestParam("find_field") String find_field,
				@RequestParam("find_name")String find_name,
				Model model) {
		
		
		
		model.addAttribute("Sc_List", this.dao.search(find_field, find_name));
		
		return "board_searchList";
	}
	
	
	
	
	
	
	
	
	

}


