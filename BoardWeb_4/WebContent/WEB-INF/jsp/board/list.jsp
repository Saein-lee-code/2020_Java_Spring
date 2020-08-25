<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "com.koreait.pjt.db.BoardDAO" %> 
<%@ page import = "com.koreait.pjt.vo.BoardVO" %>
<%@ page import="java.sql.*" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
<style>
	table { border-collapse: collapse; text-align: center; }
	td, th { border: 1px solid black; }
	.list_style:hover { cursor: pointer; background: #FE7558; color: white; }
	#id_style { font-weight: bold; }
	#date_style { width: 200px; }
</style>
</head>
<body>
	<h1>리스트</h1>
	<div><span id="id_style">${ loginUser.nm }</span>님, 환영합니다!<a href="/logout">로그아웃</a></div>
	<div>
		<a href="/board/regmod">글쓰기</a>
	</div>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성자</th>
			<th id="date_style">등록일시</th>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr class="list_style" onClick="location.href='/board/detail?i_board=${item.i_board}'">
					<td>${ item.i_board }</td>
					<td>${ item.title }</td>			
					<td>${ item.hits }</td>
					<td>${ item.i_user }</td>
					<td>${ item.r_dt }</td>
			</tr>					
		</c:forEach>
	</table>
</body>
</html>