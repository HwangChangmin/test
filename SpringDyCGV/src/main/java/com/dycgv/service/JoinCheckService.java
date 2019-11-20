package com.dycgv.service;

import java.util.ArrayList;

import com.dycgv.dao.JoinDAO;
import com.dycgv.dao.NoticeDAO;
import com.dycgv.vo.JoinVO;
import com.dycgv.vo.MemberVO;

public class JoinCheckService {	
	//ȸ������ ���� �޼ҵ�
	public boolean getJoinResult(JoinVO vo) {
		boolean result = false;
			
		JoinDAO dao = new JoinDAO();
		result = dao.getResultJoin(vo);
		
		/*if(!vo.getId().equals("")&&!vo.getName().equals("")&&!vo.getPass().equals("")&&!vo.getCpass().equals("")
				&&!vo.getGender().equals("")&&!vo.getEmail().equals("")&&!vo.getAddr().equals("")
				&&!vo.getPhone_comp().equals("")&&!vo.getPhone_number().equals("")&&!vo.getHobby().equals("")) {
			result=true;
		}*/
		
		return result;
	}
	
	//ȸ�� ����Ʈ ���
	public ArrayList<MemberVO> getResultList() {
		JoinDAO dao = new JoinDAO();
		ArrayList<MemberVO> list= dao.getResultList();
		
		return list;
	}
	
	//ȸ�� ����Ʈ ���
	public ArrayList<MemberVO> getResultList(int startCount, int endCount) {
		JoinDAO dao = new JoinDAO();
		ArrayList<MemberVO> list= dao.getResultList(startCount,endCount);
		
		return list;
	}
	
	/* ��ü ī��Ʈ ��������*/
	public int execTotalCount(){
		int result =0;
		JoinDAO dao = new JoinDAO();
		result = dao.execTotalCount();
		return result;
	}
	
	//ȸ�� ������ ���
	public MemberVO getResultMember(String id) {
		JoinDAO dao = new JoinDAO();
		MemberVO vo= dao.getResultMember(id);
		
		return vo;
	}
	
}
