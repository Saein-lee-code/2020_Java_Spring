<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.koreait.board.db.BoardDAO" %> 
<%@ page import = "com.koreait.board.vo.BoardVO" %> 
<%@ page import="java.sql.*" import="java.util.*" %>
<%
	// String a = "11";
	// BoardVO vo = (BoardVO) request.getAttribute("data");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<style>
	table { border-collapse : collapse; }
	td { border: 1px solid black; }
</style>
</head>
<body>
	<!-- setAttribute 로 키값을 준 value들만 쓸 수 있음. data(키).getterValue -->
	<!-- 값이 없으면 null이 아닌 빈칸으로 나옴. -->
	<table>
		<tr>
			<td>글쓴이: ${data.i_student}</td>
			<td>제목: ${data.title}</td>
		</tr>		
		<tr>
			<td colspan = "2">내용: ${data.ctnt}</td>
		</tr>		
	</table>
	<a href="/boardList"><button>Back</button></a>	
	
</body>
</html>