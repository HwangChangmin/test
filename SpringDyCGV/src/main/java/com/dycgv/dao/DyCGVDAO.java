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
	
	
	//Constructor : 1~2�ܰ�
	public DyCGVDAO() {
		try {
			//1�ܰ� - ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2�ܰ� - Connection ��ü ����
			conn = DriverManager.getConnection(url,user,pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method	
	/** 3�ܰ� : pstmt ��ü ���� **/ 
	public void getPreparedStatement(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
		}catch(Exception e) {
			System.out.println("3�ܰ� ����");
		}
	}
	
	/** 6�ܰ� : ���� **/
	public void close() {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 6�ܰ� : ���� **/
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
