<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" import="java.util.*" %>
<%@ page import = "com.koreait.web.BoardVO" %>        
<%! 
	// 이 jsp에서만 사용 함. import 해서 java파일로 바꿈.method는 만듬.
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
<% 
   List<BoardVO> boardList = new ArrayList();
   Connection con = null;
   PreparedStatement ps = null;
   ResultSet rs = null;				 
   String sql = " SELECT i_board, title FROM t_board ORDER BY I_BOARD";
   // scope 때문에 try / finally 에서도 사용하기 위해서 위에서 선언해줌.
	
   try{
	   con = getCon();
	   ps = con.prepareStatement(sql);
	   // select 문만 executeQuery 써야함. CURD 중에 R만. 
	   rs = ps.executeQuery();
	   
	   // Parsing
	   // 한줄의 record를 하나의 object로 만듬  
	   // while(boolean) if(boolean) 무조건 0줄이나 한줄만 가져오면 if문을 쓰기도함.
	   // record 한줄있으면 true ( 1 안녕 112122 3 이런식으로)
	   // 없으면 false (마지막줄)
	   while(rs.next()){
		  int i_board = rs.getInt("i_board");
		  String title = rs.getNString("title");
		  // boardvo 객체를 while문 안에서만 만든다
		  // 밖에서 선언하면 결과가 -> 다똑같은값 (제일 마지막 값)만 나옴.
		  BoardVO vo = new BoardVO();
		  vo.setI_board(i_board);
		  vo.setTitle(title);		  
		  boardList.add(vo);
	   }
   }catch(Exception e){
	   e.printStackTrace();
   }finally{
	   // 생성된것과 반대로 닫아줘야함. 이것도 method 를 만들어서 불러올것임.
	   // 열때 con-> ps-> rs 닫을때 rs->ps-> con
	   // 하나의 try catch 안에 넣음안됌
	   // rs가 안되도 catch문으로가지 ps con 은 닫히지않게 하기위해서.
	   // 시분할
	   if(rs != null){ try{ rs.close(); }catch(Exception e){} }
	   if(ps != null){ try{ ps.close(); }catch(Exception e){} }
	   if(con != null){ try{ con.close(); }catch(Exception e){} }
   }
   //  http통신 연결하면 바로 끊어주고 결과물만 보여줌. 유지비 매우저렴.
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
	<div>게시판!!</div>
	<table>
		<tr>
			<th>No</th>
			<th>Title</th>
		</tr>
		<% for(BoardVO vo : boardList){ %>
			<tr>
				<td><%= vo.getI_board() %></td>
				<!-- ?i_board(key) + vo.getTitle(value) -->
				<!-- ?i_board=vo.getTitle()쿼리스트링 
				 	주소값에 보인다 = get방식 
				 	주소값에 안보인다 = post방식 
				 	list 뿌릴때 cntn를 가져오면 traffic 이 너무 발생함
				 	i_board 단추만 가져오고 컨텐트 같은거는 detail에서 가져오는것이 좋음. -->
				<td><a href="/jsp/boardDetail.jsp?i_board=<%= vo.getI_board() %>"><%= vo.getTitle() %></a></td></tr>
		<% } %>	
	</table>
</body>
</html>