package com.koreait.second.dao;

import java.sql.Connection;
import java.sql.DriverManager;
// OracleDAO 는 싱글톤 패턴으로 제작
// 이 객체는 한개만 생성하게 한다 ( 싱글톤 )
// why ? 로그인할때 아이디를 만들때마다 객체 생성
// 아이디가 너무많이 만듬 -> 메모리 부족 -> 서버다운
// 현재는 문제가 크게 되진않지만 페이스북 같은 사이트는 어려움.
public class OracleDAO {
	
	//싱글톤
	// 변수 static
//	private static OracleDAO dao = null; 
	// 기본 생성자 원래는 public 붙음
	// 여기서 private 를 하면, OracleDAO 밖에서는 객체생성을 불가능. (instance할수없음)
	
//	private OracleDAO() {}
	
	// method static
	// OracleDAO 를 객체생성하지 않아도 여기서 만들어지면서 return을 해줌( 객체화가 된 주소값)
	// 딱 한번만 dao에 주소값이 저장될때까지 객체생성 (총1회)
	// 객체를 밖에선 못만들고 안에서 이미 만든것을 넣어줌
//	public static OracleDAO getInstance() {
//		if(dao == null) {
//			dao = new OracleDAO();			
//		}
//		return dao;
//	}
	
	public static void main(String[] args) {		
		OracleDAO d = new OracleDAO();
		try {
			d.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	// stream 은 쓰고 다쓰면 닫아줘야 함. 실제론 static으로 만듬.
	public Connection getConn() throws Exception{		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "hr";
		String password = "koreait2020";		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, userName, password);
		System.out.println("접속성공!");
		
		// Class.forName & DriverManager 외우기		
		return con;
	}
	public void closeConn(Connection conn){
		try {
			conn.close();
		} catch (Exception e) {
			
		}
		
		/* if(conn!=null){
		 * try{
		 * 		conn.close();
		 * 	}catch(Exception e){}
		 * }
		 */		 
	}
}
