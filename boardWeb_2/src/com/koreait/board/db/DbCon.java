package com.koreait.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbCon {
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	public static Connection getCon() throws Exception{
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		 String userName = "hr";
		 String password = "koreait2020";		 
		 Class.forName("oracle.jdbc.driver.OracleDriver");			
		 Connection con = DriverManager.getConnection(url, userName, password);
		 System.out.println("접속성공!");
	     return con;
	}

	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		 if(rs != null){ try{ rs.close(); }catch(Exception e){} }
		 if(ps != null){ try{ ps.close(); }catch(Exception e){} }
		 if(con != null){ try{ con.close(); }catch(Exception e){} }
		 System.out.println("Disconnect!");
	}

	public static void close(Connection con, PreparedStatement ps) {
		 close(con, ps, null);
		 System.out.println("Disconnect!");
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static void setPs(PreparedStatement ps) {
		DbCon.ps = ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		DbCon.rs = rs;
	}

}
