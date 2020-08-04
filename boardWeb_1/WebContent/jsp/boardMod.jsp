<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" import="java.util.*" %>
<%@ page import="com.koreait.web.BoardVO" %>
<%
	String msg = "";
	String err = request.getParameter("err"); 
	if(err != null){
		switch(err){
			case "10":
				msg = "등록할 수 없습니다."; break;
			case "20":
				msg ="DB에러 발생"; break;			
		}
	}	
	
	String strI_board = request.getParameter("i_board");
	String sql = " SELECT title, ctnt, i_student FROM t_board WHERE i_board = ? ";
	int i_board = Integer.parseInt(strI_board);
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	BoardVO vo = new BoardVO();
	try{
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setInt(1, i_board);
		rs = ps.executeQuery();
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
<title>수정하기</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="msg"><%= msg %></div>
	<div class="container">
	<!-- get, post 한글깨짐 -->
	<!-- return false 하면 글등록 눌러도 서버로 안날아감. -->
	<form id="frm" action="/jsp/boardModPro.jsp" method="post" onsubmit="return chk()" autocomplete="off">	
		<fieldset>		
			<legend>글쓰기</legend>
			<!-- name = 키값 (서버에 보내줄때 씀) 키 = value--> 
			<!-- 위에 action에 쓰는대신 hidden 으로해서 input 으로 값 보냄. -->
			<input type="hidden" name="i_board" value="<%=strI_board %>">
			<label for="title">제목</label><input type="text" name="title" id="title" value="<%= vo.getTitle() %>">
			<label for="writer">작성자</label><input type="text" name="writer" id="writer" value="<%= vo.getI_student() %>" disabled><br><br>
			<textarea rows="10" cols="60" name="ctnt" ><%= vo.getCtnt() %></textarea><br><br>
			<button type="submit">수정</button>			
		</fieldset>
	</form>
	<br>
	<a href="/jsp/boardList.jsp"><button>Back</button></a>
	</div>	
	<!-- element를 불러오기위해 function은 젤밑에 적어주는것이 좋다. css는 밑에적으면 안됌(번쩍임현상이 나타남)-->
	<script>
		function eleValid(ele, nm){
			if(ele.value.length ==0){
				alert(nm + '을(를) 입력해 주세요.');
				ele.focus();
				return true;
			}	
		}
		function chk(){
			console.log(`title:\${frm.title.value}`);
			// console.log('title:' + frm.title.value);
			if(frm.title.value == ''){
				alert('제목을 입력해 주세요.');
				frm.title.focus();
				return false;
			}else if(frm.ctnt.value.length == 0){
				alert('내용을 입력해 주세요.');
				frm.ctnt.focus()
				return false
			}else if(frm.i_student.value.length === 0){
				alert('작성자를 입력해 주세요.');
				frm.i_student.focus();
			}
			
		}
	</script>
</body>
</html>