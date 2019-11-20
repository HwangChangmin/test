package com.dycgv.service;

import java.util.ArrayList;

import com.dycgv.dao.NoticeDAO;
import com.dycgv.vo.NoticeVO;

public class NoticeCheckService {
	/** �������� �� ��� ó�� �޼ҵ� **/
	public ArrayList<NoticeVO> getResultList() {
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		NoticeDAO dao = new NoticeDAO();
		list = dao.getNoticeList();
		
		return list;
	}
	/** �������� �� ��� ó�� �޼ҵ� **/
	public ArrayList<NoticeVO> getResultList(int startCount, int endCount) {
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		NoticeDAO dao = new NoticeDAO();
		list = dao.getNoticeList(startCount,endCount);
		
		
		
		return list;
	}
	/* �������� �� ��� ó�� �޼ҵ� */
	public boolean getNoticeWriteResult(NoticeVO vo) {
		boolean result = false;

		//DB���� �� insert �۾� ����
		NoticeDAO dao = new NoticeDAO();
		result = dao.getResultNotice(vo);
		
		return result;
	}
	/* �������� �� ���� ó�� �޼ҵ� */
	public boolean getNoticeUpdateResult(NoticeVO vo) {
		boolean result = false;

		//DB���� �� insert �۾� ����
		NoticeDAO dao = new NoticeDAO();
		result = dao.getNoticeUpdateResult(vo);
		
		return result;
	}
	/* �������� �� ����(���Ͼ���) ó�� �޼ҵ� */
	public boolean getNoticeUpdateNofile(NoticeVO vo) {
		boolean result = false;

		//DB���� �� insert �۾� ����
		NoticeDAO dao = new NoticeDAO();
		result = dao.getNoticeUpdateResult(vo);
		
		return result;
	}
	
	/* �������� �� ���� ó�� �޼ҵ� */
	public boolean getNoticeDeleteResult(String nid) {
		boolean result = false;

		//DB���� �� insert �۾� ����
		NoticeDAO dao = new NoticeDAO();
		result = dao.getNoticeDeleteResult(nid);
		
		return result;
	}
	
	/* �������� ��ȸ�� ������Ʈ */
	public void getResultUpdateNhits(int nid) {
		//DB���� �� insert �۾� ����
		NoticeDAO dao = new NoticeDAO();
		dao.getResultUpdateNhits(nid);
	}
	
	/* ��ü ī��Ʈ ��������*/
	public int execTotalCount(){
		int result =0;
		NoticeDAO dao = new NoticeDAO();
		result = dao.execTotalCount();
		return result;
	}
}
