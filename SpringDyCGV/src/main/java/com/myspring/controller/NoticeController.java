package com.myspring.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dycgv.dao.NoticeDAO;
import com.dycgv.service.NoticeCheckService;
import com.dycgv.vo.NoticeVO;

@Controller
public class NoticeController {

	@RequestMapping(value = "/notice.do", method = RequestMethod.GET)
	public ModelAndView notice(String rpage) {
		ModelAndView mv = new ModelAndView();
		NoticeCheckService service = new NoticeCheckService();

		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5; //한페이지당 게시물 수
		int reqPage = 1; //요청페이지	
		int pageCount = 1; //전체 페이지 수
		int dbCount = service.execTotalCount(); //DB에서 가져온 전체 행수

		//총 페이지 수 계산
		if (dbCount % pageSize == 0) {
			pageCount = dbCount / pageSize;
		} else {
			pageCount = dbCount / pageSize + 1;
		}

		//요청 페이지 계산
		if (rpage != null) {
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage - 1) * pageSize + 1;
			endCount = reqPage * pageSize;
		} else { //가장 처음 페이지 즉, 1번페이지가 나온다.
			startCount = 1;
			endCount = 5;
		}

		NoticeCheckService notice = new NoticeCheckService();
		ArrayList<NoticeVO> list = notice.getResultList(startCount, endCount);
		
		mv.addObject("list",list);
		mv.addObject("dbCount",dbCount);
		mv.addObject("rpage",rpage);
		mv.addObject("pageSize",pageSize);
		mv.setViewName( "/notice/notice_list");
		
		return mv;
	}
	
	@RequestMapping(value = "/notice_content.do", method = RequestMethod.GET)
	public ModelAndView notice_content(String nid) {
		
		ModelAndView mv = new ModelAndView();
		NoticeDAO dao = new NoticeDAO();
		NoticeCheckService check = new NoticeCheckService();
		NoticeVO vo = dao.getNoticeContent(Integer.parseInt(nid));
		

		if(vo.getNtitle() != "" && vo.getNtitle() != null){
			vo.setNcontent(vo.getNcontent().replace("\r\n","<br>"));
			check.getResultUpdateNhits(vo.getNid());
			mv.addObject("vo",vo);
			mv.setViewName( "/notice/notice_content");
		}else{
			mv.setViewName( "errorPage");
		}
		


		return mv;
	}
}
