<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" import="java.util.*" %>
<%@ page import = "com.koreait.web.BoardVO" %>  
<% 
	request.setCharacterEncoding("UTF-8");
	// BoardVO vo = new BoardVO();	
	String sql = " INSERT INTO T_BOARD VALUES (?,?,?,?) ";
	String sql2 = " SELECT NVL(MAX(I_BOARD),0) + 1 AS COUNT FROM T_BOARD ";	
	// String sql = " INSERT INTO t_board (i_board, title, ctnt, i_student) "
	//			  + " SELECT VNL(MAX(I_BOARD),0) + 1 AS COUNT, ?, ?, ? FROM T_BOARD "
	
	int i_board = 0;
	//키값이 없이 받으면 null
	//request에 담겨있는것 = 주소값.method
	String title = request.getParameter("title");
	String ctnt = request.getParameter("ctnt");
	String strI_student = request.getParameter("writer");
	int i_student;
	try{
		// String 으로 받아오기때문에 형변환 꼭 해줄것. a처럼 문자열 넣으면 에러터짐. 
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
	// 이렇게해도 자바스크립트로 체크를 또 해줘야함.
	if("".equals(title) || "".equals(ctnt) || "".equals(strI_student)){		
		response.sendRedirect("/jsp/boardWrite.jsp?err=10");
		return;
	}
	
	Connection con = null;
	PreparedStatement ps = null;
	PreparedStatement ps2 = null;
	ResultSet rs = null;
	int result = -1;
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
			// 한줄 넘어가면 1 못넘어가면 0 초기화를 result = -1로 주면 어떤에러가 터져도 -1 ex) syntax 에러
			result = ps.executeUpdate();			
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(rs != null){ try{ rs.close(); }catch(Exception e){} } 
		if(ps != null){ try{ ps.close(); }catch(Exception e){} } 
		if(con != null){ try{ con.close(); }catch(Exception e){} }
	}
	System.out.println("result: " + result);
	int err = 0;
	switch(result){
		case -1:
			err = 20;
			break;
		case 0:
			err = 10;
			break;
		case 1:
			response.sendRedirect("/jsp/boardList.jsp");
			return;
	}
	response.sendRedirect("/jsp/boardWrite.jsp?err=" + err);
	
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