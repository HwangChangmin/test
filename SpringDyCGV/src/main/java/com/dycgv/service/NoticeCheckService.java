package com.dycgv.service;

import java.util.ArrayList;

import com.dycgv.dao.NoticeDAO;
import com.dycgv.vo.NoticeVO;

public class NoticeCheckService {
	/** 공지사항 글 목록 처리 메소드 **/
	public ArrayList<NoticeVO> getResultList() {
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		NoticeDAO dao = new NoticeDAO();
		list = dao.getNoticeList();
		
		return list;
	}
	/** 공지사항 글 목록 처리 메소드 **/
	public ArrayList<NoticeVO> getResultList(int startCount, int endCount) {
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		NoticeDAO dao = new NoticeDAO();
		list = dao.getNoticeList(startCount,endCount);
		
		
		
		return list;
	}
	/* 공지사항 글 등록 처리 메소드 */
	public boolean getNoticeWriteResult(NoticeVO vo) {
		boolean result = false;

		//DB연동 후 insert 작업 진행
		NoticeDAO dao = new NoticeDAO();
		result = dao.getResultNotice(vo);
		
		return result;
	}
	/* 공지사항 글 수정 처리 메소드 */
	public boolean getNoticeUpdateResult(NoticeVO vo) {
		boolean result = false;

		//DB연동 후 insert 작업 진행
		NoticeDAO dao = new NoticeDAO();
		result = dao.getNoticeUpdateResult(vo);
		
		return result;
	}
	/* 공지사항 글 수정(파일없음) 처리 메소드 */
	public boolean getNoticeUpdateNofile(NoticeVO vo) {
		boolean result = false;

		//DB연동 후 insert 작업 진행
		NoticeDAO dao = new NoticeDAO();
		result = dao.getNoticeUpdateResult(vo);
		
		return result;
	}
	
	/* 공지사항 글 삭제 처리 메소드 */
	public boolean getNoticeDeleteResult(String nid) {
		boolean result = false;

		//DB연동 후 insert 작업 진행
		NoticeDAO dao = new NoticeDAO();
		result = dao.getNoticeDeleteResult(nid);
		
		return result;
	}
	
	/* 공지사항 조회수 업데이트 */
	public void getResultUpdateNhits(int nid) {
		//DB연동 후 insert 작업 진행
		NoticeDAO dao = new NoticeDAO();
		dao.getResultUpdateNhits(nid);
	}
	
	/* 전체 카운트 가져오기*/
	public int execTotalCount(){
		int result =0;
		NoticeDAO dao = new NoticeDAO();
		result = dao.execTotalCount();
		return result;
	}
}
