package com.dycgv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DyCGVDAO {
	String url="jdbc:oracle:thin:@localhost:1521";
	String user="scott";
	String pass = "tiger";
	Connection conn;
	PreparedStatement pstmt;
	
	
	//Constructor : 1~2단계
	public DyCGVDAO() {
		try {
			//1단계 - 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2단계 - Connection 객체 생성
			conn = DriverManager.getConnection(url,user,pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method	
	/** 3단계 : pstmt 객체 생성 **/ 
	public void getPreparedStatement(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
		}catch(Exception e) {
			System.out.println("3단계 실패");
		}
	}
	
	/** 6단계 : 종료 **/
	public void close() {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 6단계 : 종료 **/
	public void close(ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
