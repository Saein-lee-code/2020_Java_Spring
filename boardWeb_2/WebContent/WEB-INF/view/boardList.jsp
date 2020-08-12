<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.koreait.board.db.BoardDAO" %> 
<%@ page import = "com.koreait.board.vo.BoardVO" %> 
<%@ page import="java.sql.*" import="java.util.*" %>
<%
	@SuppressWarnings("unchecked")
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("data"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style> 
	table{ cursor: pointer;background-color: #ecf0f1;  }
</style>
</head>
<body>
	<div>리스트</div>
	<a href="/boardWrite"><button>글쓰기</button></a>
	<table>
		<%for(BoardVO vo : list){%>
		<tr class="itemRow" onclick="moveToDetail(<%= vo.getI_board() %>)">		
			<td><%=vo.getI_board() %></td>
			<td><%=vo.getTitle() %></td>
			<td><%=vo.getI_student() %></td>
		</tr>
		<%} %>
	</table>
	<script>
		function moveToDetail(i_board){
			console.log('moveToDetail - i_board : ' + i_board);
			location.href = 'boardDetail?i_board=' + i_board;
		}
	</script>

</body>
</html>