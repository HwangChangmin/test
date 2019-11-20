package com.dycgv.service;

import java.util.ArrayList;

import com.dycgv.dao.JoinDAO;
import com.dycgv.dao.NoticeDAO;
import com.dycgv.vo.JoinVO;
import com.dycgv.vo.MemberVO;

public class JoinCheckService {	
	//회원가입 실행 메소드
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
	
	//회원 리스트 출력
	public ArrayList<MemberVO> getResultList() {
		JoinDAO dao = new JoinDAO();
		ArrayList<MemberVO> list= dao.getResultList();
		
		return list;
	}
	
	//회원 리스트 출력
	public ArrayList<MemberVO> getResultList(int startCount, int endCount) {
		JoinDAO dao = new JoinDAO();
		ArrayList<MemberVO> list= dao.getResultList(startCount,endCount);
		
		return list;
	}
	
	/* 전체 카운트 가져오기*/
	public int execTotalCount(){
		int result =0;
		JoinDAO dao = new JoinDAO();
		result = dao.execTotalCount();
		return result;
	}
	
	//회원 컨텐츠 출력
	public MemberVO getResultMember(String id) {
		JoinDAO dao = new JoinDAO();
		MemberVO vo= dao.getResultMember(id);
		
		return vo;
	}
	
}
