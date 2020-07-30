package com.koreait.second.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleDAO {	
	private static OracleDAO dao = null;
	public OracleDAO () {}
	private static OracleDAO getInstance() {
		if(dao == null)
			dao = new OracleDAO();
		return dao;
	}
	public static void main(String[] args) {
//		OracleDAO d = new OracleDAO();
		OracleDAO.getInstance();
		try {
			dao.getConn();
		}catch(Exception e) {
			e.printStackTrace();		
		}
	}
	public Connection getConn() throws Exception {
		String url ="jdbc:oracle:thin:@localhost:1521:orcl";
		String userName ="hr";
		String password ="koreait2020";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, userName, password);
		System.out.println("접속성공");
		return con;
	}
}
