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

		//����¡ ó�� - startCount, endCount ���ϱ�
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5; //���������� �Խù� ��
		int reqPage = 1; //��û������	
		int pageCount = 1; //��ü ������ ��
		int dbCount = service.execTotalCount(); //DB���� ������ ��ü ���

		//�� ������ �� ���
		if (dbCount % pageSize == 0) {
			pageCount = dbCount / pageSize;
		} else {
			pageCount = dbCount / pageSize + 1;
		}

		//��û ������ ���
		if (rpage != null) {
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage - 1) * pageSize + 1;
			endCount = reqPage * pageSize;
		} else { //���� ó�� ������ ��, 1���������� ���´�.
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
