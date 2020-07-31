<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" import="java.util.*" %>
<%@ page import = "com.koreait.web.BoardVO" %>  
<% 
	request.setCharacterEncoding("UTF-8");
	// BoardVO vo = new BoardVO();	
	String sql = " INSERT INTO T_BOARD VALUES (?,?,?,?) ";
	String sql2 = " SELECT NVL(MAX(I_BOARD),0) + 1 AS COUNT FROM T_BOARD ";	
	
	int i_board = 0;
	String title = request.getParameter("title");
	String ctnt = request.getParameter("ctnt");
	String strI_student = request.getParameter("writer");
	int i_student;
	try{
		i_student = Integer.parseInt(strI_student);	
	}catch(NumberFormatException exception){
		%>
		<script>
			alert("잘못된 아이디 입니다.");
			location.href = 'boardWrite.jsp';
			
		</script>
		<%
		return;
	}
	Connection con = null;
	PreparedStatement ps = null;
	PreparedStatement ps2 = null;
	ResultSet rs = null;
	try{
		con = getCon();
		ps = con.prepareStatement(sql);		
		ps2 = con.prepareStatement(sql2);
		rs = ps2.executeQuery();
		if(rs.next()){
			i_board = rs.getInt("count");
			
			ps.setInt(1, i_board);		
			ps.setString(2, title);
			ps.setString(3, ctnt);
			ps.setInt(4, i_student);
			ps.executeUpdate();
			
			//vo.setI_board(i_board);		
			//vo.setTitle(title);
			//vo.setCtnt(ctnt);
			//vo.setI_student(i_student);
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(rs != null){ try{ rs.close(); }catch(Exception e){} } 
		if(ps != null){ try{ ps.close(); }catch(Exception e){} } 
		if(con != null){ try{ con.close(); }catch(Exception e){} }
	}
	response.sendRedirect("boardDetail.jsp?i_board=" + i_board);
	
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