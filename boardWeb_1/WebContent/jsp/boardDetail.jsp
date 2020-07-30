<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" import="java.util.*" %>
<%@ page import="com.koreait.web.BoardVO" %>
<%
	// 주소값에서 value 가져옴 get.Parameter("key") -> value 값을 strI_board에 넣어줌
	// 이런게 프로토콜. (약속)
	// get방식으로 들어간것이 들어가고 post방식으로 들어간건 무시
	// 값없으면 getParameter -> null
	// request >> jsp가 servlet파일이 됌 -> 
	// public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
	// 프레임워크
    // 컴파일러가 이 메소드를 호출해줌.
    // 그리고 자동으로 넣어줌. 실제로 파일에 삽입되있음. 
    // 파라미터 앞 final이 붙어있다. final javax.servlet.http.HttpServletRequest request
    // HttpServletRequest 클래스의 변수 (참조변수) 주솟값을 바꿀수없다. 접근은 가능. 값을 바꿀수있음.
	String strI_board = request.getParameter("i_board"); // 5	
	String sql = " SELECT title, ctnt, i_student FROM t_board WHERE i_board = ? ";
	int i_board = Integer.parseInt(strI_board);
	//클래스.멤버필드 or 멤버메소드 클래스를 쓰겠다는것
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	BoardVO vo = new BoardVO();
	
	try{		 
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setInt(1, i_board); // ps.setString(1,strI_board); ?에 숫자 넣는 과정인듯
		
		rs = ps.executeQuery();	// (Select문의 결과). 다른 CUD executeUpdate()
		
		if(rs.next()){
			int i_student = rs.getInt("i_student");
			String title = rs.getNString("title");
			String ctnt = rs.getNString("ctnt");
			vo.setI_student(i_student);
			vo.setTitle(title);
			vo.setCtnt(ctnt);
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(rs != null){ try{ rs.close(); }catch(Exception e){} }
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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container">상세 페이지 : <%=strI_board %><br>	
	<h3>제목: <%= vo.getTitle() %></h3>	
	<p>작성자: <%= vo.getI_student() %></p>
	<div class="content">	
	<p><%= vo.getCtnt() %></p>
	</div>
	<a href="/jsp/boardList.jsp"><button>Back</button></a>
	<a href="#"><button onclick="procDel(<%= i_board %>)">삭제</button></a>
	</div>
	<script>
		function procDel(i_board){
			alert('i_board: ' + i_board);
			if(confirm('삭제하시겠습니까?')){
				location.href = '/jsp/boardDel.jsp?i_board=' + i_board;				
			}
		}
	</script>
</body>
</html>