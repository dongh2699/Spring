package com.sist.controller;


import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.sist.fileupload.Upload;

import com.sist.model.BlogDAO;
import com.sist.model.BlogDTO;
import com.sist.model.BlogMemberDAO;
import com.sist.model.BlogMemberDTO;
import com.sist.model.Blog_Content_GoodDTO;
import com.sist.model.Blog_contentDTO;
import com.sist.model.CategoryDAO;
import com.sist.model.CategoryDTO;

import com.sist.model.MemberDTO;
import com.sist.service.B_ContentFileService;
import com.sist.service.B_ContentService;

// 세션 유지를 위한 장치
@SessionAttributes("login")
@Controller
public class Blog_Controller {


	@Autowired
	BlogDAO b_Dao;
	@Autowired
	CategoryDAO c_Dao;
	// 회원가입 페이지 이동
	@Autowired
	Upload upload;
	@Autowired
	BlogMemberDAO bm_Dao;


	private final B_ContentFileService b_ContentFileService;
	private final B_ContentService b_ContentService;

	@Inject
	public Blog_Controller(B_ContentService b_ContentService, B_ContentFileService b_ContentFileService) {
		this.b_ContentService = b_ContentService;
		this.b_ContentFileService = b_ContentFileService;
	}

	//bond
	
	
	// 본드 메인 페이지 이동
	@RequestMapping(value = "/bond", method = RequestMethod.GET)
	public String mainPage(HttpSession session, Model model, HttpServletRequest request) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		// 페이징 처리
		int rowsize = 3; // 한 페이지당 보여질 게시물의 수
		int block = 3; // 아래에 보여질 페이지 최대 수 - 예)[1][2][3] / [4][5][6]
		int totalRecord = 0; // DB상의 레코드 전체 수(게시글의 수)
		int allPage = 0; // 전체 페이지 수
		int page = 0;
		if (request.getParameter("page") != null) {

			page = Integer.parseInt(request.getParameter("page"));

		} else {
			// 처음으로 리스트 페이지에 들어온 경우
			page = 1;
		}
		// 해당 페이지에서의 시작번호
		int startNo = (page * rowsize) - (rowsize - 1);
		// 해당 페이지에서의 끝번호
		int endNo = (page * rowsize);
		// 해당 페이지의 시작 블럭
		int startBlock = (((page - 1) / block) * block) + 1;
		// 해당 페이지의 끝 블럭
		int endBlock = (((page - 1) / block) * block) + block;
		totalRecord = this.b_Dao.countMyBlogList(dto.getM_Email());

		// 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 준다.
		// 이러한 작업을 거치면 전체 페이지가 나온다.
		// 전체 페이지가 나올 때 나머지가 있으면 무조건 올려주어야 한다.
		allPage = (int) Math.ceil(totalRecord / (double) rowsize);

		if (endBlock > allPage) {
			endBlock = allPage;
		}
		HashMap<String, Object> mbp = new HashMap<String, Object>(); // 2개이상 인자
																		// 넣기 위한
																		// 해쉬맵
		mbp.put("StartNo", startNo);
		mbp.put("EndNo", endNo);
		mbp.put("MyEmail", dto.getM_Email());
		List<BlogDTO> myBlogList = this.b_Dao.myBlogList(mbp); // 나의 블로그 리스트
																// 가져오기

		model.addAttribute("page", page);
		model.addAttribute("rowsize", rowsize);
		model.addAttribute("block", block);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("allPage", allPage);
		model.addAttribute("startNo", startNo);
		model.addAttribute("endNo", endNo);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("endBlock", endBlock);

	
		model.addAttribute("List", myBlogList);

		ArrayList<Blog_contentDTO> contList = (ArrayList<Blog_contentDTO>) this.b_Dao.gethotcontent_total();
		model.addAttribute("contList", contList);
		/*
		 * model.addAttribute("R_List",recommendation_Bond); //나중에 사용
		 */ return "bond";
	}
	

	// search 페이지 이동
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("search_content") String search_content, @RequestParam("page") int page,
			Model model, HttpServletRequest request) {
		// 페이징 처리
		int rowsize = 3; // 한 페이지당 보여질 게시물의 수
		int block = 3; // 아래에 보여질 페이지 최대 수 - 예)[1][2][3] / [4][5][6]
		int totalRecord = 0; // DB상의 레코드 전체 수(게시글의 수)
		int allPage = 0; // 전체 페이지 수

		if (page != 0) {

			page = Integer.parseInt(request.getParameter("page"));

		} else {
			// 처음으로 리스트 페이지에 들어온 경우
			page = 1;
		}
		// 해당 페이지에서의 시작번호
		int startNo = (page * rowsize) - (rowsize - 1);
		// 해당 페이지에서의 끝번호
		int endNo = (page * rowsize);
		// 해당 페이지의 시작 블럭
		int startBlock = (((page - 1) / block) * block) + 1;
		// 해당 페이지의 끝 블럭
		int endBlock = (((page - 1) / block) * block) + block;
		totalRecord = this.b_Dao.countSearchBlogList(search_content);

		// 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 준다.
		// 이러한 작업을 거치면 전체 페이지가 나온다.
		// 전체 페이지가 나올 때 나머지가 있으면 무조건 올려주어야 한다.
		allPage = (int) Math.ceil(totalRecord / (double) rowsize);

		if (endBlock > allPage) {
			endBlock = allPage;
		}
		
		HashMap<String, Object> sbp = new HashMap<String, Object>();
		sbp.put("StartNo", startNo);
		sbp.put("EndNo", endNo);
		sbp.put("Search_Content", search_content);
		List<HashMap> blogList = this.b_Dao.searchBlogList(sbp);
		ArrayList<BlogDTO> blog_name = new ArrayList<BlogDTO>(); // 검색어가 이름에 포함되어 있는 블로그 리스트																
		ArrayList<BlogDTO> category_name = new ArrayList<BlogDTO>(); // 검색어가  카테고리에 포함되어 있는 카테고리 리스트

		for (int i = 0; i < blogList.size(); i++) {
			if (blogList.get(i).get("B_NAME").toString().contains(search_content)) { // 검색어가 블로그 이름에 포함되어있을   때 저장																			
				BlogDTO blog = new BlogDTO();
				blog.setB_No(Integer.parseInt((blogList.get(i).get("B_NO").toString())));
				blog.setB_Name(blogList.get(i).get("B_NAME").toString());
				blog.setB_Intro(blogList.get(i).get("B_INTRO").toString());
				blog.setB_Picture(blogList.get(i).get("B_PICTURE").toString());
				blog_name.add(blog);
			} else if (blogList.get(i).get("CT_NAME").toString().equals(search_content)) { // 검색어가 카테고리에 포함되어있을  때 저장
	
				BlogDTO blog = new BlogDTO();
				blog.setB_No(Integer.parseInt((blogList.get(i).get("B_NO").toString())));
				blog.setB_Name(blogList.get(i).get("B_NAME").toString());
				blog.setB_Intro(blogList.get(i).get("B_INTRO").toString());
				blog.setB_Picture(blogList.get(i).get("B_PICTURE").toString());
				category_name.add(blog);
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("rowsize", rowsize);
		model.addAttribute("block", block);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("allPage", allPage);
		model.addAttribute("startNo", startNo);
		model.addAttribute("endNo", endNo);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("endBlock", endBlock);
		model.addAttribute("seach_BlogList", blog_name);
		model.addAttribute("category_BlogList", category_name);
		model.addAttribute("Search_content", search_content);

		return "search";
	}



	// 본드 생성 페이지 이동
	@RequestMapping(value = "/bond_Create", method = RequestMethod.GET)
	public String bond_Create(Model model) {
		// 카테고리 리스트 불러오기
		List<CategoryDTO> categorylist = this.c_Dao.getCategory();
		model.addAttribute("List", categorylist);
		return "bond_Create";
	}

	// 본드 생성 완료
	@RequestMapping(value = "/bond_Create_Ok", method = RequestMethod.POST)
	public void bond_Create_OK(MultipartHttpServletRequest mRequest, BlogDTO dto,
			@RequestParam("b_category") int b_category, HttpServletResponse response, HttpSession session)
			throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		ArrayList<String> upload_Paths = this.upload.fileUpload(mRequest);
		for (String path : upload_Paths) {
			dto.setB_Picture(path);
		}
		// 0일떄 블로그 생성 성공 아닐때는 실패
		int res = this.b_Dao.createBlog(dto);
		if (res != 0) { // 실패
			out.println("<script>");
			out.println("alert('BOND생성에 실패했습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else { // 성공
			int blogNo = this.b_Dao.selectOneBlog(dto); // blog No불러오기
			HashMap<String, Object> req = new HashMap<String, Object>(); // 2개이상 인자  넣기 위한 해쉬맵

			req.put("Category_No", b_category);
			req.put("Blog_No", blogNo);
			this.c_Dao.create_B_Category(req);

			MemberDTO Connect_Member = (MemberDTO) session.getAttribute("login"); // 블로그 관리자 지정
	
			req.put("M_Email", Connect_Member.getM_Email());
			this.b_Dao.createBlog_Member(req);

			out.println("<script>");
			out.println("alert('BOND생성에 성공했습니다!')");
			out.println("location.href='/BOND/bond'");
			out.println("</script>");
		}

	}
	
	
	


	@RequestMapping(value = "/bond_modify", method = RequestMethod.GET)
	public String bond_modify(@RequestParam("bond_no") int b_No, Model model, HttpSession session) {
		// 블로그 객체 가져오기
		BlogDTO dto = this.b_Dao.getBlog_Detail(b_No);

		List<CategoryDTO> categorylist = this.c_Dao.getCategory();
		int ct_no = c_Dao.blog_Category(b_No);
		int member_Count = this.b_Dao.Member_Count(b_No);
	
		model.addAttribute("Bond", dto);
		model.addAttribute("Member_Count", member_Count);
		model.addAttribute("List", categorylist);
		model.addAttribute("Ct_no", ct_no);
		return "bond_Modify";
	}

	@RequestMapping(value = "/bond_Modify_Ok", method = RequestMethod.POST)
	public void bond_Modify_OK(MultipartHttpServletRequest mRequest, BlogDTO dto, @RequestParam("bond_No") int b_No,
			@RequestParam("b_category") int b_category, HttpServletResponse response) throws IOException {
		
		dto.setB_No(b_No);
		ArrayList<String> upload_Paths = this.upload.fileUpload(mRequest);
		dto.setB_Picture(upload_Paths.get(0));
		int res = this.b_Dao.modifyBlog(dto);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (res > 0) {
			HashMap<String, Object> req = new HashMap<String, Object>(); // 2개이상
																			// 인자
																			// 넣기
																			// 위한
																			// 해쉬맵
			req.put("Category_No", b_category);
			req.put("Blog_No", b_No);
			this.c_Dao.modify_B_Category(req);

			out.println("<script>");
			out.println("alert('블로그 수정 완료')");
			out.println("location.href='/BOND/bond_Detail?bond_no=" + b_No + "'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('블로그 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	@RequestMapping(value = "/bond_Delete_OK", method = RequestMethod.GET)
	public void bond_Delete_OK(@RequestParam("bond_No") int b_No, HttpServletResponse response) throws IOException {

		int res = this.b_Dao.deleteBlog(b_No);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (res > 0) {
			out.println("<script>");
			out.println("alert('블로그 삭제 완료')");
			out.println("location.href='/BOND/bond'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('블로그 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}

	}
	
	// 좋아요 등록하기
		@RequestMapping(value = "/Goods_Regist", method = RequestMethod.POST)
		@ResponseBody
		public int addGoods(@RequestParam("bc_No") int blogcont_No, HttpSession session) {
			
			int res = 0;
			Blog_Content_GoodDTO Gdto = new Blog_Content_GoodDTO();
			Blog_contentDTO Bdto = new Blog_contentDTO();
			MemberDTO Mdto = (MemberDTO) session.getAttribute("login");
			Gdto.setBc_No(blogcont_No);
			Gdto.setM_Email(Mdto.getM_Email());
			res = this.b_ContentService.addGoods(Gdto);
			Bdto.setBc_Goods(this.b_ContentService.getGoodsCount(blogcont_No));
		
			Bdto.setBc_No(blogcont_No);
			this.b_ContentService.updateGoods(Bdto);
			return res;
		}

		// 좋아요 취소하기
		@RequestMapping(value = "/Goods_Cancel", method = RequestMethod.POST)
		@ResponseBody
		public int CancelGoods(@RequestParam("bc_No") int blogcont_No, HttpSession session) {

			int res = 0;

			Blog_Content_GoodDTO Gdto = new Blog_Content_GoodDTO();
			Blog_contentDTO Bdto = new Blog_contentDTO();
			MemberDTO Mdto;
			Mdto = (MemberDTO) session.getAttribute("login");
			Gdto.setBc_No(blogcont_No);
			Gdto.setM_Email(Mdto.getM_Email());
			res = this.b_ContentService.CancelGoods(Gdto);
			Bdto.setBc_Goods(this.b_ContentService.getGoodsCount(blogcont_No));
			Bdto.setBc_No(blogcont_No);
			this.b_ContentService.updateGoods(Bdto);
			return res;
		}

		// 좋아요 수 보여주기
		@RequestMapping(value = "/content_goods", method = RequestMethod.POST)
		@ResponseBody
		public int addGoods(@RequestParam("bc_No") String blogcont_No) {
			int bc_no = Integer.parseInt(blogcont_No.trim());

			int res = this.b_ContentService.getGoodsCount(bc_no);

			return res;
		}
		// 좋아요, 좋아요 취소 체크
		@RequestMapping(value = "/Check_Goods", method = RequestMethod.POST)
		@ResponseBody
		public String CheckGoods(@RequestParam("bc_No") String blogcont_No, HttpSession session) {
			int bc_no = Integer.parseInt(blogcont_No.trim());

			String checkStr = "";
			Blog_Content_GoodDTO Gdto = new Blog_Content_GoodDTO();
			MemberDTO Mdto;
			Mdto = (MemberDTO) session.getAttribute("login");
			Gdto.setBc_No(bc_no);
			Gdto.setM_Email(Mdto.getM_Email());

			checkStr = this.b_ContentService.CheckGoods(Gdto);

			return checkStr;
		}

		

		// 본드 디테일 nav에서 회원관리로 이동
		   @RequestMapping(value = "/bond_Member", method = RequestMethod.GET)
		   public String manage_Member(@RequestParam("bond_no") int b_No, Model model, HttpSession session) {
		      MemberDTO member = (MemberDTO) session.getAttribute("login");
		      HashMap<String, Object> ngm = new HashMap<String, Object>();
		      ngm.put("m_Email", member.getM_Email());
		      ngm.put("b_No", b_No);
		      // 블로그 객체 가져오기
		      List<HashMap> listDto = this.bm_Dao.blogMemberList(b_No);
		      // 블로그 객체 가져오기
		      BlogDTO dto = this.b_Dao.getBlog_Detail(b_No);
		      // 블로그 멤버수 가져오기
		      int member_Count = this.b_Dao.Member_Count(b_No);
		      // 관리자인지 확인
		      int res = this.b_Dao.checkNotice(ngm);
		      // 가입된 멤버인지 확인
		      int gradeChk = this.b_Dao.checkGrade(ngm);
		   
		      if (res > 0) {
		         model.addAttribute("Manager", res);
		      }
		      model.addAttribute("manageCK", res);
		      model.addAttribute("joinCK", gradeChk);
		      model.addAttribute("Bond", dto);
		      model.addAttribute("listDto", listDto);
		      model.addAttribute("Member_Count", member_Count);
		      model.addAttribute("Member", member);
		      model.addAttribute("b_No", b_No);
		      return "bond_Member";
		   }

		// 본드 회원관리에서 멤버 검색
		   @RequestMapping(value = "/searchMember", method = RequestMethod.GET)
		   public String searchMember(@RequestParam("m_nickname") String nName, @RequestParam("bond_no") String no,
		         Model model, HttpSession session) {
		      MemberDTO member = (MemberDTO) session.getAttribute("login");
		      String m_nickName = nName;
		      int b_No = Integer.parseInt(no);
		      HashMap<String, Object> ngm = new HashMap<String, Object>();
		      ngm.put("m_Email", member.getM_Email());
		      ngm.put("b_No", b_No);
		      ngm.put("M_NICKNAME", nName);
		      // 블로그 객체 가져오기
		      List<HashMap> listDto;
		      if (nName.isEmpty()) {
		         // 빈칸으로 검색할때
		         listDto = this.bm_Dao.blogMemberList(b_No);
		      } else {
		         // 검색할 멤버가 있을때
		         listDto = this.bm_Dao.searchMember(ngm);
		      
		      }
		      // 블로그 객체 가져오기
		      BlogDTO dto = this.b_Dao.getBlog_Detail(b_No);
		      // 블로그 멤버수 가져오기
		      int member_Count = this.b_Dao.Member_Count(b_No);
		      // 관리자인지 확인
		      int res = this.b_Dao.checkNotice(ngm);
		      // 가입된 멤버인지 확인
		      int gradeChk = this.b_Dao.checkGrade(ngm);
		    
		      if (res > 0) {
		         model.addAttribute("Manager", res);
		      }
		      model.addAttribute("manageCK", res);
		      model.addAttribute("joinCK", gradeChk);
		      model.addAttribute("Bond", dto);
		      model.addAttribute("listDto", listDto);
		      model.addAttribute("Member_Count", member_Count);
		      model.addAttribute("Member", member);
		      model.addAttribute("b_No", b_No);
		      return "bond_MemberSearch";
		   }

		// 본드 디테일 nav에서 갤러리로 이동

		   @RequestMapping(value = "/bond_gallary", method = RequestMethod.GET)
		      public String bond_gallary(@RequestParam("bond_no") int b_No, Model model, HttpSession session) {
			   		MemberDTO member = (MemberDTO) session.getAttribute("login");
			      HashMap<String, Object> ngm = new HashMap<String, Object>();
			      ngm.put("m_Email", member.getM_Email());
			      ngm.put("b_No", b_No);
		         // 블로그 객체 가져오기
		         BlogDTO dto = this.b_Dao.getBlog_Detail(b_No);
		         // 블로그 멤버수 가져오기
		         int member_Count = this.b_Dao.Member_Count(b_No);
		         // 블로그 사진첩 가져오기
		         List<HashMap<String, Object>> PL = this.b_ContentService.getPictureList(b_No);		  
		         for(int j=0; j<PL.size()-1; ) {
		            if(String.valueOf(PL.get(j).get("BC_NO")).equals(String.valueOf(PL.get(j+1).get("BC_NO")))){
		            String filename =String.valueOf(PL.get(j).get("FILE_NAME")).concat(",").concat(String.valueOf(PL.get(j+1).get("FILE_NAME")));
		            PL.get(j).put("FILE_NAME",filename);
		            PL.remove(j+1);
		          
		            }else{
		               j++;
		            }
		         }
		         // 관리자인지 확인
			      int res = this.b_Dao.checkNotice(ngm);
			      // 가입된 멤버인지 확인
			      int gradeChk = this.b_Dao.checkGrade(ngm);
			   
			      if (res > 0) {
			         model.addAttribute("Manager", res);
			      }
			      model.addAttribute("manageCK", res);
			      model.addAttribute("joinCK", gradeChk);
		      
		         model.addAttribute("Bond", dto);
		         model.addAttribute("Member_Count", member_Count);
		         model.addAttribute("Member", member);
		         model.addAttribute("b_No", b_No);
		         model.addAttribute("PictureList", PL);
		         
		         return "bond_gallary";
		      }

		// 인기 사진첩 Ajax
		@RequestMapping(value = "/getHotPicture", method = RequestMethod.POST, produces = "application/json; charset=utf8")
		public ResponseEntity getHotPicture(@RequestParam("b_No") int b_No) {
			HttpHeaders responseHeaders = new HttpHeaders();
			List<HashMap<String, Object>> HotPictureList = (List<HashMap<String, Object>>) this.b_ContentService
					.getHotpictureList(b_No);
			ArrayList<HashMap> hmlist = new ArrayList<HashMap>();

			if (HotPictureList.size() > 0) {
				for (int i = 0; i < HotPictureList.size(); i++) {
					HashMap hm = new HashMap();

					hm.put("bc_no", HotPictureList.get(i).get("BC_NO"));
					hm.put("file_name", HotPictureList.get(i).get("FILE_NAME"));
					hm.put("upload_date", HotPictureList.get(i).get("UPLOAD_DATE"));
					hm.put("b_no", HotPictureList.get(i).get("B_NO"));
					hm.put("m_email", HotPictureList.get(i).get("M_EMAIL"));
					hm.put("bc_cont", HotPictureList.get(i).get("BC_CONT"));
					hm.put("bc_goods", HotPictureList.get(i).get("BC_GOODS"));
					hm.put("m_nickname", HotPictureList.get(i).get("M_NICKNAME"));
					hm.put("m_picture", HotPictureList.get(i).get("M_PICTURE"));
					hmlist.add(hm);

				}
			}

			JSONArray json = new JSONArray(hmlist);

			return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
		}

		// AJAX 본드 관리 권한 변경
		@RequestMapping(value = "/changeManage", method = RequestMethod.POST)
		public void changeManage(BlogMemberDTO dto, HttpServletResponse response) throws IOException {
			response.setContentType("text/html; charset=UTF-8");
			if (dto.getBm_Grade().equals("member")) {
				dto.setBm_Grade("manager");
			} else if (dto.getBm_Grade().equals("manager")) {
				dto.setBm_Grade("member");
			}
			PrintWriter out = response.getWriter();
			int res = this.bm_Dao.changeManage(dto);
			if (res > 0) {
				out.println("<script>");
				out.println("alert('권한 수정 완료')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('권한 수정 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}

		@RequestMapping(value = "/bond_Detail2", method = RequestMethod.GET)
		public String bond_Detail(@RequestParam("bond_no") int b_No, @RequestParam("bc_no") int bc_No, Model model,
				HttpSession session) {

			
			HashMap<String, Object> ngm = new HashMap<String, Object>();
			MemberDTO member = (MemberDTO) session.getAttribute("login");
			ngm.put("m_Email", member.getM_Email());
			ngm.put("b_No", b_No);
			// 블로그 객체 가져오기
			BlogDTO dto = this.b_Dao.getBlog_Detail(b_No);
			// 블로그 멤버수 가져오기
			int member_Count = this.b_Dao.Member_Count(b_No);
			// 관리자인지 확인
			int res = this.b_Dao.checkNotice(ngm);
			// 가입된 멤버인지 확인
			int gradeChk = this.b_Dao.checkGrade(ngm);
			model.addAttribute("manageCK", res);
			model.addAttribute("joinCK", gradeChk);
			model.addAttribute("Bond", dto);
			model.addAttribute("Member_Count", member_Count);
			model.addAttribute("Member", member);
			model.addAttribute("b_No", b_No);
			model.addAttribute("bc_No", bc_No);
			
			return "bond_Detail";
		}
		// 밴드 가입하기
		@RequestMapping(value = "/joinBond", method = RequestMethod.POST)
		public String joinBond(@RequestParam("b_No") int b_No, @RequestParam("M_Email") String m_Email, Model model,
				HttpSession session, HttpServletResponse response) throws IOException {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			HashMap<String, Object> req = new HashMap<String, Object>(); // 2개이상 인자
																			// 넣기 위한
																			// 해쉬맵
			req.put("b_No", b_No);
			req.put("m_Email", m_Email);
			int res = this.b_Dao.joinBondGuest(req);
			
			if (res > 0) {

				out.println("<script>");
				out.println("alert('블로그 가입 완료')");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('블로그 가입 실패')");
				out.println("history.back()");
				out.println("</script>");
			}

			MemberDTO member = (MemberDTO) session.getAttribute("login");
			// 블로그 객체 가져오기
			BlogDTO dto = this.b_Dao.getBlog_Detail(b_No);
			// 블로그 멤버수 가져오기
			int member_Count = this.b_Dao.Member_Count(b_No);
			// 관리자인지 확인
			int res2 = this.b_Dao.checkNotice(req);
			// 가입된 멤버인지 확인
			int gradeChk = this.b_Dao.checkGrade(req);

			if (res2 > 0) {
				model.addAttribute("Manager", res2);
			}
			model.addAttribute("manageCK", res2);
			model.addAttribute("joinCK", gradeChk);
			model.addAttribute("Bond", dto);
			model.addAttribute("Member_Count", member_Count);
			model.addAttribute("Member", member);
			model.addAttribute("b_No", b_No);

			return "bond_Detail";

		}

		// 밴드 탈퇴하기
		@RequestMapping(value = "/withdrawalBond", method = RequestMethod.POST)
		public String withdrawalBond(@RequestParam("b_No") int b_No, @RequestParam("M_Email") String m_Email, Model model,
				HttpSession session, HttpServletResponse response) throws IOException {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			HashMap<String, Object> req = new HashMap<String, Object>(); // 2개이상 인자
																			// 넣기 위한
																			// 해쉬맵
			req.put("b_No", b_No);
			req.put("m_Email", m_Email);
			int res = this.b_Dao.withdrawalBondMember(req);
			if (res > 0) {
				
				out.println("<script>");
				out.println("alert('블로그 탈퇴완료')");
				// out.println("location.href='/BOND/bond_Detail?bond_no=" + b_No +
				// "'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('블로그 탈퇴 실패')");
				out.println("history.back()");
				out.println("</script>");
			}

			MemberDTO member = (MemberDTO) session.getAttribute("login");
			// 블로그 객체 가져오기
			BlogDTO dto = this.b_Dao.getBlog_Detail(b_No);
			// 블로그 멤버수 가져오기
			int member_Count = this.b_Dao.Member_Count(b_No);
			// 관리자인지 확인
			int res2 = this.b_Dao.checkNotice(req);
			// 가입된 멤버인지 확인
			int gradeChk = this.b_Dao.checkGrade(req);
			
			if (res2 > 0) {
				model.addAttribute("Manager", res);
			}
			model.addAttribute("manageCK", res2);
			model.addAttribute("joinCK", gradeChk);
			model.addAttribute("Bond", dto);
			model.addAttribute("Member_Count", member_Count);
			model.addAttribute("Member", member);
			model.addAttribute("b_No", b_No);

			return "bond_Detail";

		}

		// bond_Detail 으로 넘겨주는 작업
		@RequestMapping(value = "/bond_Detail", method = RequestMethod.GET)
		public String bond_Detail(@RequestParam("bond_no") int b_No, Model model, HttpSession session) {
			MemberDTO member = (MemberDTO) session.getAttribute("login");
			HashMap<String, Object> ngm = new HashMap<String, Object>();
			ngm.put("m_Email", member.getM_Email());
			
			ngm.put("b_No", b_No);
			// 블로그 객체 가져오기
			BlogDTO dto = this.b_Dao.getBlog_Detail(b_No);
			// 블로그 멤버수 가져오기
			int member_Count = this.b_Dao.Member_Count(b_No);
			// 관리자인지 확인
			int res = this.b_Dao.checkNotice(ngm);
			// 가입된 멤버인지 확인
			int gradeChk = this.b_Dao.checkGrade(ngm);
		
			if (res > 0) {
				model.addAttribute("Manager", res);
			}
			model.addAttribute("manageCK", res);
			model.addAttribute("joinCK", gradeChk);
			model.addAttribute("Bond", dto);
			model.addAttribute("Member_Count", member_Count);
			model.addAttribute("Member", member);
			model.addAttribute("b_No", b_No);
			
			return "bond_Detail";
		}

		@ResponseBody // view가 아닌 데이터 리턴
		@RequestMapping(value = "/getHotlist", method = RequestMethod.POST, produces = "application/json; charset=utf8")
		public ResponseEntity getHotlist(@RequestParam("b_No") int b_No, Model model) {

			ArrayList<Blog_contentDTO> hotList = (ArrayList<Blog_contentDTO>) this.b_ContentService.HotBoardList(b_No);
			HttpHeaders responseHeaders = new HttpHeaders();
			ArrayList<HashMap> hmlist = new ArrayList<HashMap>();

			if (hotList.size() > 0) {
				for (int i = 0; i < hotList.size(); i++) {
					HashMap hm = new HashMap();
					hm.put("b_no", hotList.get(i).getB_No());
					hm.put("bc_no", hotList.get(i).getBc_No());
					hm.put("bc_cont", hotList.get(i).getBc_Cont());
					hm.put("bc_goods", hotList.get(i).getBc_Goods());

					hmlist.add(hm);
				}
			}

			JSONArray json = new JSONArray(hmlist);

			return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);

		}

		@RequestMapping(value = "/noticeList", method = RequestMethod.POST, produces = "application/json; charset=utf8")
		public ResponseEntity noticeList(@RequestParam("b_No") int b_No, Model model) {

			ArrayList<Blog_contentDTO> noticeList = (ArrayList<Blog_contentDTO>) this.b_ContentService
					.NoticeBoardList(b_No);
			HttpHeaders responseHeaders = new HttpHeaders();
			ArrayList<HashMap> hmlist = new ArrayList<HashMap>();

			if (noticeList.size() > 0) {
				for (int i = 0; i < noticeList.size(); i++) {
					HashMap hm = new HashMap();
					hm.put("b_no", noticeList.get(i).getB_No());
					hm.put("bc_no", noticeList.get(i).getBc_No());
					hm.put("bc_cont", noticeList.get(i).getBc_Cont());
					hm.put("bc_goods", noticeList.get(i).getBc_Goods());
					hmlist.add(hm);
				}
			}

			JSONArray json = new JSONArray(hmlist);

			return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);

		}

}