package com.dycgv.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.dycgv.vo.NoticeVO;

public class NoticeDAO extends DyCGVDAO{
	ResultSet rs;
	
	/** 4단계 : 공지사항 입력 **/ 	
	public boolean getResultNotice(NoticeVO vo) {
		boolean result = false;
		String sql = "insert into dycgv_notice values(dycgv_notice_sequ.NEXTVAL,?,?,sysdate,0,?,?)";		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getNtitle());
			pstmt.setString(2, vo.getNcontent());
			pstmt.setString(3, vo.getNfile());
			pstmt.setString(4, vo.getNsfile());
			
			int val = pstmt.executeUpdate(); //insert라서 바로 비교
			if(val != 0) result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** 4단계 : 공지사항 수정 **/ 	
	public boolean getNoticeUpdateResult(NoticeVO vo) {
		boolean result = false;
		String sql = "update dycgv_notice set ntitle=?,ncontent=?,nfile=?,nsfile=? where nid=?";		
		getPreparedStatement(sql);

		try {
			pstmt.setString(1, vo.getNtitle());
			pstmt.setString(2, vo.getNcontent());
			pstmt.setString(3, vo.getNfile());
			pstmt.setString(4, vo.getNsfile());
			pstmt.setInt(5, vo.getNid());
			
			int val = pstmt.executeUpdate(); //insert라서 바로 비교
			if(val != 0) result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** 4단계 : 공지사항 수정 **/ 	
	public boolean getNoticeUpdateNofile(NoticeVO vo) {
		boolean result = false;
		String sql = "update dycgv_notice set ntitle=?,ncontent=?,nfile='',nsfile='' where nid=?";		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getNtitle());
			pstmt.setString(2, vo.getNcontent());
			pstmt.setInt(3, vo.getNid());
			
			int val = pstmt.executeUpdate(); //insert라서 바로 비교
			if(val != 0) result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/** 4단계 : 공지사항 삭제 **/ 	
	public boolean getNoticeDeleteResult(String nid) {
		boolean result = false;
		String sql = "delete from dycgv_notice where nid=?";		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, nid);
			
			int val = pstmt.executeUpdate(); //insert라서 바로 비교
			if(val != 0) result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 전체 카운트 가져오기*/
	public int execTotalCount(){
		int result =0;
		try{
			String sql = "select count(*) from dycgv_notice";
			getPreparedStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception e){e.printStackTrace();}
		
		return result;
	}
	
	/** 4단계 : 공지사항 전체리스트 출력 **/ 	
	public ArrayList<NoticeVO> getNoticeList() {
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		String sql = "select rownum,nid,ntitle,ncontent,to_char(ndate,'yyyy/mm/dd'),nhits,nfile,nsfile "
				+ "from (select * from dycgv_notice order by ndate desc)";		
		getPreparedStatement(sql);
		
		try {			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setRowNum(rs.getInt(1));
				vo.setNid(rs.getInt(2));
				vo.setNtitle(rs.getString(3));
				vo.setNcontent(rs.getString(4));
				vo.setNdate(rs.getString(5));
				vo.setNhits(rs.getInt(6));
				vo.setNfile(rs.getString(7));
				vo.setNsfile(rs.getString(8));
				
				list.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/** 4단계 : 공지사항 전체리스트 출력 - 페이징 처리 start-end **/ 	
	public ArrayList<NoticeVO> getNoticeList(int startCount, int endCount) {
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		String sql = "select * from (select rownum rno,nid,ntitle,ncontent,"
				+ "to_char(ndate,'yyyy/mm/dd'),nhits,nfile,nsfile "
				+ "from (select * from dycgv_notice order by ndate desc)) "
				+ "where rno between ? and ?";		
		getPreparedStatement(sql);
		
		try {						
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setRowNum(rs.getInt(1));
				vo.setNid(rs.getInt(2));
				vo.setNtitle(rs.getString(3));
				vo.setNcontent(rs.getString(4));
				vo.setNdate(rs.getString(5));
				vo.setNhits(rs.getInt(6));
				vo.setNfile(rs.getString(7));
				vo.setNsfile(rs.getString(8));
				
				list.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	/** 4단계 : 공지사항 상세정보 **/ 	
	public NoticeVO getNoticeContent(int nid) {
		NoticeVO vo = new NoticeVO();
		String sql = "select rownum,nid,ntitle,ncontent,to_char(ndate,'yyyy/mm/dd'),nhits,nfile,nsfile from dycgv_notice "
				+ "where nid=?";		
		
		getPreparedStatement(sql);
		
		try {			
			pstmt.setInt(1, nid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				vo.setRowNum(rs.getInt(1));
				vo.setNid(rs.getInt(2));
				vo.setNtitle(rs.getString(3));
				vo.setNcontent(rs.getString(4));
				vo.setNdate(rs.getString(5));
				vo.setNhits(rs.getInt(6));
				vo.setNfile(rs.getString(7));
				vo.setNsfile(rs.getString(8));

			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	/** 4단계 : 공지사항 조회수 업데이트 **/ 	
	public void getResultUpdateNhits(int nid) {
		String sql = "UPDATE dycgv_notice SET nhits=nhits+1 where nid=?";		
		
		getPreparedStatement(sql);
		
		try {			
			pstmt.setInt(1, nid);
			pstmt.executeUpdate();		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
