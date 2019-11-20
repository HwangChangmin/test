package com.myspring.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dycgv.dao.BoardDAO;
import com.dycgv.dao.BoardReplyDAO;
import com.dycgv.service.BoardCheckService;
import com.dycgv.vo.BoardReplyVO;
import com.dycgv.vo.BoardVO;

@Controller
public class boardController {

	/** 게시판 목록 **/
	@RequestMapping(value = "/board.do", method = RequestMethod.GET)
	public ModelAndView board(String bpage) {
		ModelAndView mv = new ModelAndView();

		if(bpage=="" || bpage==null){
			bpage = "1";
		}
		BoardCheckService board = new BoardCheckService();
		
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = board.execTotalCount();	//DB에서 가져온 전체 행수
		
		//총 페이지 수 계산
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//요청 페이지 계산
		if(bpage != null){
			reqPage = Integer.parseInt(bpage);
			startCount = (reqPage-1) * pageSize+1;
				endCount = reqPage *pageSize;
		}else{
			reqPage= 1;
			startCount = 1;
			endCount = 5;
		}
		
		ArrayList<BoardVO> list = board.getResultList(startCount, endCount);
		
		mv.addObject("list",list);
		mv.addObject("bpage",reqPage);
		mv.addObject("pageSize",pageSize);
		mv.addObject("dbCount",dbCount);
		mv.setViewName("/board/board_list");
		
		return mv;
	}
	
	/** 게시판 상세내용 **/
	@RequestMapping(value = "/board_content.do", method = RequestMethod.GET)
	public ModelAndView board_content(String bpage,String bid) {
		ModelAndView mv = new ModelAndView();

		if(bpage=="" ||bpage==null){
			bpage = "1";
		}
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.getBoardContent(Integer.parseInt(bid));
		
		BoardCheckService board = new BoardCheckService();	
		
	 	BoardReplyDAO rdao = new BoardReplyDAO();
		ArrayList<BoardReplyVO> rlist = rdao.getBoardReplyList(Integer.parseInt(bid));
		
		if(vo.getBtitle() != "" && vo.getBtitle() != null){
			board.getResultUpdateBhits(Integer.parseInt(bid));
			if(vo.getBcontent() != "" && vo.getBcontent() != null){
				vo.setBcontent(vo.getBcontent().replace("\r\n","<br>"));
			}else{
				vo.setBcontent("");
			}
			mv.addObject("vo",vo);
			mv.addObject("rlist",rlist);
			mv.addObject("bpage",bpage);
			mv.addObject("bid",bid);
			mv.setViewName("/board/board_content");
		}else{
			mv.setViewName("erroePage");
		}


		return mv;
	}
	
	/** 게시판 글작성 **/
	@RequestMapping(value = "/board_write.do", method = RequestMethod.GET)
	public String board_write() {		
		return "/board/board_write";
	}
	
	/** 게시판 글작성 프로세스 **/
	@RequestMapping(value = "/board_write_proc.do", method = RequestMethod.POST)
	public ModelAndView board_write_proc(BoardVO vo) {
		ModelAndView mv = new ModelAndView();
		
		BoardCheckService checkservice = new BoardCheckService();
		boolean result = false;
		
		result = checkservice.getBoardWriteResult(vo);	
		
		if(result){
			mv.setViewName("redirect:/board.do");
		}else{
			mv.setViewName("errorPage");
		}
		
		return mv;
	}
	
	/** 게시판 글수정 **/
	@RequestMapping(value = "/board_update.do", method = RequestMethod.GET)
	public ModelAndView board_update(String bid) {
		ModelAndView mv = new ModelAndView();
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.getBoardContent(Integer.parseInt(bid));
		
		BoardCheckService board = new BoardCheckService();
		
		if(vo.getBtitle() != "" && vo.getBtitle() != null){
			board.getResultUpdateBhits(Integer.parseInt(bid));
			if(vo.getBcontent() != "" && vo.getBcontent() != null){
				vo.setBcontent(vo.getBcontent().replace("\r\n","<br>"));
			}else{
				vo.setBcontent("");
			}
			/* if(vo.getBfile() == "" || vo.getBfile() == null){
				vo.setBfile("");
			} */
		}/*else{
			response.sendRedirect("../errorPage.jsp");
		}*/
		
		mv.addObject("bid",bid);
		mv.addObject("vo",vo);
		mv.setViewName("/board/board_update");
		
		return mv;
	}
	
	/** 게시판 글수정 프로세스 **/
	@RequestMapping(value = "/board_update_proc.do", method = RequestMethod.POST)
	public ModelAndView board_update_proc(BoardVO vo,String bpage) {
		ModelAndView mv = new ModelAndView();
		
		BoardCheckService board = new BoardCheckService();
		boolean result = false;
		
		if(vo.getBfile() != "") {
			System.out.println("파일 있음");
			result = board.getBoardUpdateResult(vo);
		}else {
			System.out.println("파일 없음");
			result = board.getBoardUpdateNofile(vo);
		}
		
		if(result) {
			//list 페이지로 갈 때는 게시판 목록인 list가 필요한데 list를 넘겨주지 않아 NullPointerException이 뜬다
			//mv.setViewName("/board/board_list");
			//그래서 'redirect:/호출할 페이지' 를 써서 다시 cilent가 페이지를 호출하게 해준다(response와 동일)
			/*mv.setViewName("redirect:/board.do");*/		
			
			/*mv.addObject("vo",vo);*/ //content로 이동하기
			mv.setViewName("redirect:/board_content.do?bid="+vo.getBid()+"&page="+bpage);
		}else {
			mv.setViewName("errorPage");
		}
		
		return mv;
	}

	/** 게시판 글삭제 **/
	@RequestMapping(value = "/board_delete.do", method = RequestMethod.GET)
	public ModelAndView board_delete(String bid) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("bid",bid);
		mv.setViewName("/board/board_delete");
		
		return mv;
	}
	
	/** 게시판 글삭제 프로세스 **/
	@RequestMapping(value = "/board_delete_proc.do", method = RequestMethod.GET)
	public ModelAndView board_delete_proc(String bid) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("bid",bid);
		mv.setViewName("/board/board_delete");
		
		BoardCheckService checkservice = new BoardCheckService();
		boolean result = checkservice.getBoardDeleteResult(bid);
		
		if(result) {
			mv.setViewName("redirect:/board.do");
		}else {
			mv.setViewName("errorPage");
		}
		return mv;
	}
	
	/** 게시판 댓글 작성 **/
	@RequestMapping(value = "/board_reply_write_proc.do", method = RequestMethod.POST)
	@ResponseBody
	public String board_reply_write_proc(String bid,String rcontent,String bpage) {
	 	//DB연동 - BoardReplyDAO 객체 생성 및 
	 	BoardReplyDAO dao = new BoardReplyDAO();
	 	BoardReplyVO vo = new BoardReplyVO();
		vo.setBid(Integer.parseInt(bid));
		vo.setRcontent(rcontent);
	 		
		int result = dao.getBoardReplyResult(vo);
		
		return String.valueOf(result);
	}
	
	@RequestMapping(value = "/board_reply_content_process.do", method = RequestMethod.GET)
	public ModelAndView board_reply_content_process(String rid) {
		ModelAndView mv = new ModelAndView();
		
	 	BoardReplyDAO dao = new BoardReplyDAO();
 		
		String rcontent = dao.getBoardReplyContent(rid);
		
		mv.addObject("rid",rid);
		mv.addObject("rcontent",rcontent);
		mv.setViewName("/board/board_reply_content_process");
		
		return mv;
	}
}
