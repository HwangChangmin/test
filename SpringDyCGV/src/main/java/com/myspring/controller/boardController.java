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

	/** �Խ��� ��� **/
	@RequestMapping(value = "/board.do", method = RequestMethod.GET)
	public ModelAndView board(String bpage) {
		ModelAndView mv = new ModelAndView();

		if(bpage=="" || bpage==null){
			bpage = "1";
		}
		BoardCheckService board = new BoardCheckService();
		
		//����¡ ó�� - startCount, endCount ���ϱ�
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//���������� �Խù� ��
		int reqPage = 1;	//��û������	
		int pageCount = 1;	//��ü ������ ��
		int dbCount = board.execTotalCount();	//DB���� ������ ��ü ���
		
		//�� ������ �� ���
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//��û ������ ���
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
	
	/** �Խ��� �󼼳��� **/
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
	
	/** �Խ��� ���ۼ� **/
	@RequestMapping(value = "/board_write.do", method = RequestMethod.GET)
	public String board_write() {		
		return "/board/board_write";
	}
	
	/** �Խ��� ���ۼ� ���μ��� **/
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
	
	/** �Խ��� �ۼ��� **/
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
	
	/** �Խ��� �ۼ��� ���μ��� **/
	@RequestMapping(value = "/board_update_proc.do", method = RequestMethod.POST)
	public ModelAndView board_update_proc(BoardVO vo,String bpage) {
		ModelAndView mv = new ModelAndView();
		
		BoardCheckService board = new BoardCheckService();
		boolean result = false;
		
		if(vo.getBfile() != "") {
			System.out.println("���� ����");
			result = board.getBoardUpdateResult(vo);
		}else {
			System.out.println("���� ����");
			result = board.getBoardUpdateNofile(vo);
		}
		
		if(result) {
			//list �������� �� ���� �Խ��� ����� list�� �ʿ��ѵ� list�� �Ѱ����� �ʾ� NullPointerException�� ���
			//mv.setViewName("/board/board_list");
			//�׷��� 'redirect:/ȣ���� ������' �� �Ἥ �ٽ� cilent�� �������� ȣ���ϰ� ���ش�(response�� ����)
			/*mv.setViewName("redirect:/board.do");*/		
			
			/*mv.addObject("vo",vo);*/ //content�� �̵��ϱ�
			mv.setViewName("redirect:/board_content.do?bid="+vo.getBid()+"&page="+bpage);
		}else {
			mv.setViewName("errorPage");
		}
		
		return mv;
	}

	/** �Խ��� �ۻ��� **/
	@RequestMapping(value = "/board_delete.do", method = RequestMethod.GET)
	public ModelAndView board_delete(String bid) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("bid",bid);
		mv.setViewName("/board/board_delete");
		
		return mv;
	}
	
	/** �Խ��� �ۻ��� ���μ��� **/
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
	
	/** �Խ��� ��� �ۼ� **/
	@RequestMapping(value = "/board_reply_write_proc.do", method = RequestMethod.POST)
	@ResponseBody
	public String board_reply_write_proc(String bid,String rcontent,String bpage) {
	 	//DB���� - BoardReplyDAO ��ü ���� �� 
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
