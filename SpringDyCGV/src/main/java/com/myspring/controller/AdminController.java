package com.myspring.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dycgv.dao.JoinDAO;
import com.dycgv.dao.NoticeDAO;
import com.dycgv.service.JoinCheckService;
import com.dycgv.service.NoticeCheckService;
import com.dycgv.vo.MemberVO;
import com.dycgv.vo.NoticeVO;

@Controller
public class AdminController {
	
	@RequestMapping(value = "/admin.do", method = RequestMethod.GET)
	public String admin() {
		return "/admin/admin";
	}
	
	/** 관리자 공지사항 목록 **/
	@RequestMapping(value = "/admin_notice.do", method = RequestMethod.GET)
	public ModelAndView admin_notice(String rpage) {
		ModelAndView mv = new ModelAndView();

		NoticeCheckService notice = new NoticeCheckService();
		
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = notice.execTotalCount();	//DB에서 가져온 전체 행수
		
		//총 페이지 수 계산
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//요청 페이지 계산
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
				endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = 5;
		}
		
		
		ArrayList<NoticeVO> list = notice.getResultList(startCount, endCount);
		
		mv.addObject("list",list);
		mv.addObject("rpage",reqPage);
		mv.addObject("pageSize",pageSize);
		mv.addObject("dbCount",dbCount);
		mv.setViewName("/admin/admin_notice_list");
		
		return mv;
	}
	
	/** 관리자 공지사항 상세내용 **/
	@RequestMapping(value = "/admin_notice_content.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_content(String rpage,String nid) {
		ModelAndView mv = new ModelAndView();
		
		NoticeDAO dao = new NoticeDAO();
		NoticeVO vo = dao.getNoticeContent(Integer.parseInt(nid));
		
		NoticeCheckService notice = new NoticeCheckService();
		
		if(vo.getNtitle() != "" && vo.getNtitle() != null){
			notice.getResultUpdateNhits(Integer.parseInt(nid));
			if(vo.getNcontent() != "" && vo.getNcontent() != null){
				vo.setNcontent(vo.getNcontent().replace("\r\n","<br>"));
			}else{
				vo.setNcontent("");
			}
			mv.addObject("vo",vo);
			mv.addObject("rpage",rpage);
			mv.addObject("nid",nid);
			mv.setViewName("/admin/admin_notice_content");
		}else{
			mv.setViewName("erroePage");
		}
		
		return mv;
	}
	
	/** 관리자 공지사항 글작성 **/
	@RequestMapping(value = "/admin_notice_write.do", method = RequestMethod.GET)
	public String board_write() {		
		return "/admin/admin_notice_write";
	}
	
	/** 관리자 공지사항 글작성 프로세스 **/
	@RequestMapping(value = "/admin_notice_write_proc.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_write_proc(NoticeVO vo) {
		ModelAndView mv = new ModelAndView();
		
		NoticeCheckService checkservice = new NoticeCheckService();
		
		boolean result = false;
		
		result = checkservice.getNoticeWriteResult(vo);
		
		if(result){
			mv.setViewName("redirect:/admin_notice.do");
		}else{
			mv.setViewName("errorPage");
		}
		
		return mv;
	}
	
	/** 관리자 공지사항 글수정 **/
	@RequestMapping(value = "/admin_notice_update.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_update(String nid) {
		ModelAndView mv = new ModelAndView();
		
		NoticeDAO dao = new NoticeDAO();
		NoticeVO vo = dao.getNoticeContent(Integer.parseInt(nid));
		
		NoticeCheckService notice = new NoticeCheckService();
		
		if(vo.getNtitle() != "" && vo.getNtitle() != null){
			notice.getResultUpdateNhits(Integer.parseInt(nid));
			if(vo.getNcontent() != "" && vo.getNcontent() != null){
				vo.setNcontent(vo.getNcontent().replace("\r\n","<br>"));
			}else{
				vo.setNcontent("");
			}
			/* if(vo.getNfile() == "" || vo.getNfile() == null){
				vo.setNfile("");
			} */
		}/*else{
			response.sendRedirect("../errorPage.jsp");
		}*/

		mv.addObject("nid",nid);
		mv.addObject("vo",vo);
		mv.setViewName("/admin/admin_notice_update");
		
		return mv;
	}
	
	/** 관리자 공지사항 글수정 프로세스 **/
	@RequestMapping(value = "/admin_notice_update_proc.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_update_proc(NoticeVO vo,String rpage) {
		ModelAndView mv = new ModelAndView();
		
		NoticeCheckService notice = new NoticeCheckService();
		boolean result = false;
		
		if(vo.getNfile() != "") {
			System.out.println("파일 있음");
			result = notice.getNoticeUpdateResult(vo);
		}else {
			System.out.println("파일 없음");
			result = notice.getNoticeUpdateNofile(vo);
			
		}
		if(result) {
			//list 페이지로 갈 때는 게시판 목록인 list가 필요한데 list를 넘겨주지 않아 NullPointerException이 뜬다
			//mv.setViewName("/board/board_list");
			//그래서 'redirect:/호출할 페이지' 를 써서 다시 cilent가 페이지를 호출하게 해준다(response와 동일)
			mv.setViewName("redirect:/admin_notice.do");		
			
			mv.addObject("vo",vo); //content로 이동하기
			mv.setViewName("redirect:/admin_notice_content.do?nid="+vo.getNid()+"&page="+rpage);
		}else {
			mv.setViewName("errorPage");
		}
		
		return mv;
	}

	/** 관리자 공지사항 글삭제 **/
	@RequestMapping(value = "/admin_notice_delete.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_delete(String nid) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("nid",nid);
		mv.setViewName("/admin/admin_notice_delete");
		
		return mv;
	}
	
	/** 관리자 공지사항 글삭제 프로세스 **/
	@RequestMapping(value = "/admin_notice_delete_proc.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_delete_proc(String nid) {
		ModelAndView mv = new ModelAndView();
		
		NoticeCheckService checkservice = new NoticeCheckService();
		boolean result = checkservice.getNoticeDeleteResult(nid);
		
		if(result) {
			mv.setViewName("redirect:/admin_notice.do");
		}else {
			mv.setViewName("errorPage");
		}
		return mv;
	}
	
	/** 관리자 회원관리 목록 **/
	@RequestMapping(value="admin_member.do",method=RequestMethod.GET)
	public ModelAndView admin_member(String rpage) {
		ModelAndView mv = new ModelAndView();

		JoinCheckService service = new JoinCheckService();
		
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = service.execTotalCount();	//DB에서 가져온 전체 행수
		
		//총 페이지 수 계산
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//요청 페이지 계산
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
				endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = 5;
		}
		
		ArrayList<MemberVO> list = service.getResultList(startCount, endCount);
		
		mv.addObject("list",list);
		mv.addObject("rpage",reqPage);
		mv.addObject("pageSize",pageSize);
		mv.addObject("dbCount",dbCount);
		mv.setViewName("/admin/admin_member_list");
		
		return mv;
	}
	
	/** 관리자 회원관리 상세내용 **/
	@RequestMapping(value="/admin_member_content.do",method=RequestMethod.GET)
	public ModelAndView admin_member_content(String id) {
		ModelAndView mv = new ModelAndView();
		
		JoinCheckService service = new JoinCheckService();
		MemberVO vo = service.getResultMember(id);
		
		mv.addObject("vo",vo);
		mv.addObject("id",id);
		mv.setViewName("/admin/admin_member_content");
		
		return mv;
	}
	
	/** 관리자 회원관리 삭제 프로세스 **/
	@RequestMapping(value="/admin_member_delete_proc.do",method=RequestMethod.GET)
	@ResponseBody
	public String admin_member_delete_proc(String mid) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println(mid);
		
		JoinDAO dao = new JoinDAO();
		int result = dao.getResultDelete(mid);
		
		return String.valueOf(result);
	}

}
