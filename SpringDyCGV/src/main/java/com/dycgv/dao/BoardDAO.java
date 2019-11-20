package com.dycgv.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.dycgv.vo.BoardVO;

public class BoardDAO extends DyCGVDAO {
	ResultSet rs;
	
	/** 4단계 : 게시글 입력 **/ 	
	public boolean getResultBoard(BoardVO vo) {
		boolean result = false;
		String sql = "insert into dycgv_board values(dycgv_board_sequ.NEXTVAL,?,?,sysdate,0,?,?)";		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setString(3, vo.getBfile());
			pstmt.setString(4, vo.getBsfile());
			
			int val = pstmt.executeUpdate(); //insert라서 바로 비교
			if(val != 0) result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		close();
		
		return result;
	}
	
	/** 4단계 : 게시글 수정 **/ 	
	public boolean getBoardUpdateResult(BoardVO vo) {
		boolean result = false;
		String sql = "update dycgv_board set btitle=?,bcontent=?,bfile=?,bsfile=? where bid=?";		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setString(3, vo.getBfile());
			pstmt.setString(4, vo.getBsfile());
			pstmt.setInt(5, vo.getBid());
			
			int val = pstmt.executeUpdate(); //insert라서 바로 비교
			if(val != 0) result = true;		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		close();
		
		return result;
	}
	
	/** 4단계 : 게시글 수정(파일없음) **/ 	
	public boolean getBoardUpdateNofile(BoardVO vo) {
		boolean result = false;
		String sql = "update dycgv_board set btitle=?,bcontent=?,bfile='',bsfile='' where bid=?";		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setInt(3, vo.getBid());
			
			int val = pstmt.executeUpdate(); //insert라서 바로 비교
			if(val != 0) result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		close();
		
		return result;
	}
	
	/** 4단계 : 게시글 삭제 **/ 	
	public boolean getBoardDeleteResult(String bid) {
		boolean result = false;
		String sql = "delete from dycgv_board where bid=?";		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, bid);
			
			int val = pstmt.executeUpdate(); //insert라서 바로 비교
			if(val != 0) result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		close();
		
		return result;
	}
	
	/** 4단계 : 게시글 출력 **/ 	
	public ArrayList<BoardVO> getBoardList() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String sql = "select rownum,bid,btitle,bcontent,to_char(bdate,'yyyy/mm/dd'),bhits,bfile,bsfile "
				+ "from (select * from dycgv_board order by bdate desc)";

		getPreparedStatement(sql);
		
		try {			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setRno(rs.getInt(1));
				vo.setBid(rs.getInt(2));
				vo.setBtitle(rs.getString(3));
				vo.setBcontent(rs.getString(4));
				vo.setBdate(rs.getString(5));
				vo.setBhits(rs.getInt(6));
				vo.setBfile(rs.getString(7));
				vo.setBsfile(rs.getString(8));
				
				list.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		close(rs);
		
		return list;
	}
	/** 4단계 : 게시글 출력 - 페이징 **/ 	
	public ArrayList<BoardVO> getBoardList(int startCount,int endCount) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String sql = "select * from (select rownum rno,bid,btitle,bcontent,"
				+ "to_char(bdate,'yyyy/mm/dd'),bhits,bfile,bsfile "
				+ "from (select * from dycgv_board order by bdate desc)) "
				+ "where rno between ? and ?";		
		getPreparedStatement(sql);
		
		try {			
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setRno(rs.getInt(1));
				vo.setBid(rs.getInt(2));
				vo.setBtitle(rs.getString(3));
				vo.setBcontent(rs.getString(4));
				vo.setBdate(rs.getString(5));
				vo.setBhits(rs.getInt(6));
				vo.setBfile(rs.getString(7));
				vo.setBsfile(rs.getString(8));
				
				list.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		close(rs);
		
		return list;
	}
	
	/* 전체 카운트 가져오기*/
	public int execTotalCount(){
		int result =0;
		try{
			String sql = "select count(*) from dycgv_board";
			getPreparedStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception e){e.printStackTrace();}
		
		close(rs);
		
		return result;
	}
	
	/** 4단계 : 게시글 상세정보 **/ 	
	public BoardVO getBoardContent(int bid) {
		BoardVO vo = new BoardVO();
		/*String sql = "select rownum,bid,btitle,bcontent,to_char(bdate,'yyyy/mm/dd'),bhits,bfile,bsfile from dycgv_board "
				+ "where bid=?";	*/	
		String sql = "select rownum,b1.bid,btitle,bcontent,to_char(bdate,'yyyy/mm/dd'),bhits,bfile,bsfile,rcount "
				+ " from dycgv_board b1, "
					+ " (select bid,count(*) rcount from dycgv_board_reply group by bid) b2 "
				+ " where b2.bid (+)= b1.bid and b1.bid=? ";
		getPreparedStatement(sql);
		
		try {			
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				vo.setRno(rs.getInt(1));
				vo.setBid(rs.getInt(2));
				vo.setBtitle(rs.getString(3));
				vo.setBcontent(rs.getString(4));
				vo.setBdate(rs.getString(5));
				vo.setBhits(rs.getInt(6));
				vo.setBfile(rs.getString(7));
				vo.setBsfile(rs.getString(8));
				vo.setRcount(rs.getInt(9));

			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		close(rs);
		
		return vo;
	}
	/** 4단계 : 게시글 조회수 업데이트 **/ 	
	public void getResultUpdateBhits(int bid) {
		String sql = "UPDATE dycgv_board SET bhits=bhits+1 where bid=?";		
		
		getPreparedStatement(sql);
		
		try {			
			pstmt.setInt(1, bid);
			pstmt.executeUpdate();		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		close();
	}
}
