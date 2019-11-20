package com.dycgv.dao;

import java.sql.ResultSet;

import com.dycgv.vo.LoginVO;
import com.dycgv.vo.SessionVO;

public class LoginDAO extends DyCGVDAO{
	ResultSet rs;
	
	/** 4�ܰ� : �α��� ó�� **/
	public SessionVO getResultLogin(LoginVO vo) {
		SessionVO svo = new SessionVO();
		String sql = "select count(*),name from dycgv_member where id=? and pass=? group by name";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());		
			rs = pstmt.executeQuery(); //select �� rs�� �޾Ƽ� ��
			
			if(rs.next()) {
				int val = rs.getInt(1);
				if(val != 0) {
					svo.setResult(true);
					svo.setName(rs.getString(2));
				}				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return svo;		
	}
	
	
}
