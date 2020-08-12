<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" import="java.util.*" %>
<%@ page import = "com.koreait.web.BoardVO" %>
<% 
	request.setCharacterEncoding("UTF-8");	
	String sql = " UPDATE T_BOARD SET TITLE = ?, CTNT = ? WHERE I_BOARD = ? ";
	String title = request.getParameter("title");
	String ctnt = request.getParameter("ctnt");
	String strI_board = request.getParameter("i_board");
	int i_board = Integer.parseInt(strI_board);
	System.out.println(strI_board);
	Connection con = null;
	PreparedStatement ps = null;
	int result = -1;	
	try{
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, title);	
		ps.setString(2, ctnt);
		ps.setInt(3, i_board);
		result = ps.executeUpdate();	
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(ps != null){ try{ ps.close(); }catch(Exception e){} } 
		if(con != null){ try{ con.close(); }catch(Exception e){} }
	}
	response.sendRedirect("/jsp/boardDetail.jsp?i_board=" + strI_board);	
%>
<%!
 	Connection getCon() throws Exception{
		 String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		 String userName = "hr";
		 String password = "koreait2020";
		 // thorws or try catch 써줘야함. 밑에서 try catch를 한번에 줄것.
		 Class.forName("oracle.jdbc.driver.OracleDriver");
			// 객체화를 하지않고 바로씀. static이 붙은 method DriverManager.getConnection
			// 언제 사용하는가? static 이 안붙은 멤버필드를 사용 안할때
			// parameter로 들어오는 값만으로 쓸수 있음. (멤버필드를 안쓴다. 다만 만약 멤버필드들이 static이면 써도됌)
		 Connection con = DriverManager.getConnection(url, userName, password);
		 System.out.println("접속성공!");
	     return con;
	}
%>