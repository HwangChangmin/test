package com.dycgv.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.dycgv.vo.JoinVO;
import com.dycgv.vo.MemberVO;

public class JoinDAO extends DyCGVDAO {
	ResultSet rs;
	
	/** 4단계 : 회원가입 **/ 	
	public boolean getResultJoin(JoinVO vo) {
		boolean result = false;
		String sql = "insert into dycgv_member values(?,?,?,?,?,?,?,?,?,?,sysdate,'vip',0)";		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getPass());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getAddr());
			pstmt.setString(7, vo.getPhone_comp());
			pstmt.setString(8, vo.getPhone_number());
			pstmt.setString(9, vo.getHobbyList());
			pstmt.setString(10, vo.getIntro());
			
			int val = pstmt.executeUpdate();
			if(val != 0) result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** 4단계 : 회원 리스트 출력 **/ 	
	public ArrayList<MemberVO> getResultList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		String sql = "select rownum rno,name,phone_number,to_char(jdate,'yyyy/mm/dd'),jgrade,jstatus,id "
					 +"from (select * from dycgv_member order by jdate desc)";
		getPreparedStatement(sql);
		
		try {			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				
				vo.setRno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPhone_number(rs.getString(3));
				vo.setJdate(rs.getString(4));
				vo.setJgrade(rs.getString(5));
				vo.setJstatus(rs.getInt(6));
				vo.setId(rs.getString(7));
				
				list.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	/** 4단계 : 회원 리스트 출력 **/ 	
	public ArrayList<MemberVO> getResultList(int startCount, int endCount) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		String sql = "select * from (select rownum rno,name,phone_number,to_char(jdate,'yyyy/mm/dd'),jgrade,jstatus,id "
					 +"from (select * from dycgv_member order by jdate desc))"
					 + " where rno between ? and ?";
		getPreparedStatement(sql);
		
		try {			
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				
				vo.setRno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPhone_number(rs.getString(3));
				vo.setJdate(rs.getString(4));
				vo.setJgrade(rs.getString(5));
				vo.setJstatus(rs.getInt(6));
				vo.setId(rs.getString(7));
				
				list.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/* 전체 카운트 가져오기*/
	public int execTotalCount(){
		int result =0;
		try{
			String sql = "select count(*) from dycgv_member";
			getPreparedStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception e){e.printStackTrace();}
		
		return result;
	}
	
	/** 4단계 : 회원 컨텐츠 출력 **/ 	
	public MemberVO getResultMember(String id) {
		MemberVO vo = new MemberVO();
		String sql = "select rownum rno,name,phone_number,to_char(jdate,'yyyy/mm/dd'),"
				+ "jgrade,jstatus,addr,email,dy_hobby,intro,id from dycgv_member where id=?";	
		getPreparedStatement(sql);
		
		try {			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	
				vo.setRno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPhone_number(rs.getString(3));
				vo.setJdate(rs.getString(4));
				vo.setJgrade(rs.getString(5));
				vo.setJstatus(rs.getInt(6));
				vo.setAddr(rs.getString(7));
				vo.setEmail(rs.getString(8));
				vo.setDy_hobby(rs.getString(9));
				vo.setIntro(rs.getString(10));
				vo.setId(rs.getString(10));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	/** 4단계 : 회원 아이디 중복체크 **/ 	
	public int getResultCheckId(String jid) {
		int result = 0;
		String sql = "select count(*) from dycgv_member where id=?";	
		getPreparedStatement(sql);
		
		try {			
			pstmt.setString(1, jid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	
				result = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 회원삭제 */
	public int getResultDelete(String mid) {
		int result = 0;
		String sql = "delete from dycgv_member where id=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, mid);
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
