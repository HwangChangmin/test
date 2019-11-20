package com.dycgv.service;

import java.util.ArrayList;

import com.dycgv.dao.BoardDAO;
import com.dycgv.dao.NoticeDAO;
import com.dycgv.vo.BoardVO;

public class BoardCheckService {
	/** 게시글 글 목록 처리 메소드 **/
	public ArrayList<BoardVO> getResultList() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardDAO dao = new BoardDAO();
		list = dao.getBoardList();
		
		return list;
	}
	/** 게시글 글 목록 처리 메소드 - 페이징 **/
	public ArrayList<BoardVO> getResultList(int startCount,int endCount) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardDAO dao = new BoardDAO();
		list = dao.getBoardList(startCount, endCount);
		
		return list;
	}
	
	/* 게시판 글 등록 처리 메소드 */
	public boolean getBoardWriteResult(BoardVO vo) {
		boolean result = false;

		BoardDAO dao = new BoardDAO();
		result = dao.getResultBoard(vo);
		
		return result;
	}
	/* 게시글 글 수정 처리 메소드 */
	public boolean getBoardUpdateResult(BoardVO vo) {
		boolean result = false;

		BoardDAO dao = new BoardDAO();
		result = dao.getBoardUpdateResult(vo);
		
		return result;
	}
	
	/* 게시글 글 수정(파일없음) 처리 메소드 */
	public boolean getBoardUpdateNofile(BoardVO vo) {
		boolean result = false;

		BoardDAO dao = new BoardDAO();
		result = dao.getBoardUpdateResult(vo);
		
		return result;
	}
	
	/* 게시글 글 삭제 처리 메소드 */
	public boolean getBoardDeleteResult(String bid) {
		boolean result = false;

		BoardDAO dao = new BoardDAO();
		result = dao.getBoardDeleteResult(bid);
		
		return result;
	}
	
	/* 게시글 조회수 업데이트 */
	public void getResultUpdateBhits(int bid) {
		BoardDAO dao = new BoardDAO();
		dao.getResultUpdateBhits(bid);
	}
	
	/* 전체 카운트 가져오기*/
	public int execTotalCount(){
		int result =0;
		BoardDAO dao = new BoardDAO();
		result = dao.execTotalCount();
		return result;
	}
}
