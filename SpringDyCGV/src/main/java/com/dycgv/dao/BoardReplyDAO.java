package com.dycgv.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.dycgv.vo.BoardReplyVO;

public class BoardReplyDAO extends DyCGVDAO {
	ResultSet rs;
	
	/** 4�ܰ� : �Խñ� �Է� **/ 	
	public int getBoardReplyResult(BoardReplyVO vo) {
		int result = 0;
		String sql = "insert into dycgv_board_reply values(dycgv_board_reply_sequ.NEXTVAL,?,sysdate,0,?)";		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1,vo.getRcontent());
			pstmt.setInt(2, vo.getBid());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	//��� ����Ʈ ���
	public ArrayList<BoardReplyVO> getBoardReplyList(int bid){
		ArrayList<BoardReplyVO> list = new ArrayList<BoardReplyVO>();
		
		String sql = "select * from dycgv_board_reply where bid=? order by rdate";		
		getPreparedStatement(sql);
		
		try {
			pstmt.setInt(1,bid);
			
			rs = pstmt.executeQuery(); //insert�� �ٷ� ��
			while(rs.next()) {
				BoardReplyVO vo = new BoardReplyVO();
				vo.setRid(rs.getInt(1));
				vo.setRcontent(rs.getString(2));
				vo.setRdate(rs.getString(3));
				vo.setRhits(rs.getInt(4));
				vo.setBid(rs.getInt(5));
				
				list.add(vo);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//�ش� ����� ��ü ���� ���
	//��� ����Ʈ ���
	public String getBoardReplyContent(String rid){
		String rcontent = "";
		
		String sql = "select rcontent from dycgv_board_reply where rid=?";		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1,rid);
			
			rs = pstmt.executeQuery(); //insert�� �ٷ� ��
			while(rs.next()) {
				rcontent = rs.getString(1);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return rcontent;
	}
	
}
