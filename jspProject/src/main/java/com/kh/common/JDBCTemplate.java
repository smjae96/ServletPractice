package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	//	1. Connection 객체 생성한 후 생성된 Connection 객체를 반환해주는 getConnection 메소드
	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties(); // Map 계열의 컬렉션
		
		// 읽어오고자 하는 classes 폴더 내의 driver.properties 파일의 물리적인 경로
		String filepath = JDBCTemplate.class.getResource("/db/driver/driver.properties").getPath();
		// => "C:\workspace\5_JSP\jspProject\src\main\webapp\classes\db\driver\driver.properties
//		System.out.println(filepath);
		
		try {
			prop.load(new FileInputStream(filepath));
			// jdbc driver 등록
//			Class.forName("oracle.jdbc.dirver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
			
			// 접속하는 db 정보(url/사용자계정이름/비밀번호)를 통해서 Connection 객체 생성
			/*
			conn = DriverManager.getConnection("jdbc:oracle:thin:@locatlhost:1521:xe",
										"C##SERVER",
										"SERVER");
			*/
			conn = DriverManager.getConnection(prop.getProperty("url"),
											   prop.getProperty("username"),
											   prop.getProperty("password"));
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 2. commit 메소드
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3. rollback 메소드
	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4. 반납(close)해주는 메소드 => Connection/[Prepared]Statement/ResultSet 
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stat) {
		try {
			if(stat != null && !stat.isClosed()) {
				stat.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
