package com.dycgv.service;

import java.util.ArrayList;

import com.dycgv.dao.BoardDAO;
import com.dycgv.dao.NoticeDAO;
import com.dycgv.vo.BoardVO;

public class BoardCheckService {
	/** �Խñ� �� ��� ó�� �޼ҵ� **/
	public ArrayList<BoardVO> getResultList() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardDAO dao = new BoardDAO();
		list = dao.getBoardList();
		
		return list;
	}
	/** �Խñ� �� ��� ó�� �޼ҵ� - ����¡ **/
	public ArrayList<BoardVO> getResultList(int startCount,int endCount) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardDAO dao = new BoardDAO();
		list = dao.getBoardList(startCount, endCount);
		
		return list;
	}
	
	/* �Խ��� �� ��� ó�� �޼ҵ� */
	public boolean getBoardWriteResult(BoardVO vo) {
		boolean result = false;

		BoardDAO dao = new BoardDAO();
		result = dao.getResultBoard(vo);
		
		return result;
	}
	/* �Խñ� �� ���� ó�� �޼ҵ� */
	public boolean getBoardUpdateResult(BoardVO vo) {
		boolean result = false;

		BoardDAO dao = new BoardDAO();
		result = dao.getBoardUpdateResult(vo);
		
		return result;
	}
	
	/* �Խñ� �� ����(���Ͼ���) ó�� �޼ҵ� */
	public boolean getBoardUpdateNofile(BoardVO vo) {
		boolean result = false;

		BoardDAO dao = new BoardDAO();
		result = dao.getBoardUpdateResult(vo);
		
		return result;
	}
	
	/* �Խñ� �� ���� ó�� �޼ҵ� */
	public boolean getBoardDeleteResult(String bid) {
		boolean result = false;

		BoardDAO dao = new BoardDAO();
		result = dao.getBoardDeleteResult(bid);
		
		return result;
	}
	
	/* �Խñ� ��ȸ�� ������Ʈ */
	public void getResultUpdateBhits(int bid) {
		BoardDAO dao = new BoardDAO();
		dao.getResultUpdateBhits(bid);
	}
	
	/* ��ü ī��Ʈ ��������*/
	public int execTotalCount(){
		int result =0;
		BoardDAO dao = new BoardDAO();
		result = dao.execTotalCount();
		return result;
	}
}
