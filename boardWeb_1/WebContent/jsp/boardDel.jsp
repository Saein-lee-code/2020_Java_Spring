<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" import="java.util.*" %>
<%@ page import="com.koreait.web.BoardVO" %>    
<%
	String strI_board = request.getParameter("i_board");
	String sql = " DELETE FROM T_BOARD WHERE I_BOARD = ? ";
	// strI_board 가 null 이면 -> 에러터짐 그래서 try catch로 감싸거나 strI_board가 null인지 문자열이 섞여있는지 체크해준다.(실무에서)
	
	if(strI_board == null){
	%>
		<script>
			alert('잘못된 접근입니다');
			location.href='/jsp/boardList.jsp'
		</script>
	<%
	}

	int i_board = Integer.parseInt(strI_board);	
	Connection con = null;
	PreparedStatement ps = null;	
	int result = 0;
	try{
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setInt(1, i_board);
		result = ps.executeUpdate();		
		System.out.println("result : " + result);
		switch(result){
			case -1:
				response.sendRedirect("boardDetail.jsp?err=-1&i_board=" + i_board);
				break;
			case 0:
				response.sendRedirect("boardDetail.jsp?err=0&i_board=" + i_board);
				break;
			case 1:
				response.sendRedirect("boardList.jsp");
				break;
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(ps != null){ try{ ps.close(); }catch(Exception e){} } 
		if(con != null){ try{ con.close(); }catch(Exception e){} }
	}
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