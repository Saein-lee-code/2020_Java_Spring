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
	.container{ width: 800px; margin: 0 auto; text-align: center; }	
	table { border-collapse: collapse; text-align: center; width: 800px; }
	td, th { border: 1px solid black; }
	.list_style:hover { cursor: pointer; background: #FE7558; color: white; }
	#id_style { font-weight: bold; }
	#date_style { width: 200px; }
	a{ text-decoration: none; color: black; font-size: 18px; }
	.page_style{ margin-right: 10px; font-size: 18px; text-align: center;  }
	.page_style:hover{ font-weight: bold; }
	#accent { font-weight: bold; color: red; }
</style>
</head>
<body>
	<div class="container">
		<h1>리스트</h1>
		<div><span id="id_style">${ loginUser.nm }</span>님, 환영합니다!<a href="/logout">로그아웃</a></div>
		<a href="/board/regmod">글쓰기</a>
		<div>
			<form id="selFrm" action ="/board/list">
				<input type="hidden" name="page" value="${ param.page == null ? 1 : param.page }">
				게시글 수 :
				<select name="record_cnt"  onchange="changeRecordCnt()">
					<c:forEach begin="10" end ="30" step="10" var="item">
						<option value="${item}">${item}개</option>
					</c:forEach>
				</select>
			</form>			
		</div>
		<table>
			<tr>
				<th style="width:60px;">번호</th>
				<th style="width:385px;">제목</th>
				<th>조회수</th>
				<th>작성자</th>
				<th id="date_style">등록일시</th>
			</tr>
			<c:forEach items="${list}" var="item">
				<tr class="list_style" onClick="location.href='/board/detail?i_board=${item.i_board}'">
						<td>${ item.i_board }</td>
						<td>${ item.title }</td>			
						<td>${ item.hits }</td>
						<td>${ item.nm }</td>
						<td>${ item.r_dt }</td>
				</tr>					
			</c:forEach>
		</table>
		<div class="paging_style">
			<c:forEach begin="1" end="${ pagingCnt }" var="item">
			<span class="page_style">
				<c:choose>					
						<c:when test="${ param.page == item || (param.page == null && item == 1)}">
							<span id="accent">${ item }</span>
						</c:when>
						<c:otherwise>
							<a class="address" href="/board/list?page=${ item }">${ item }</a>
						</c:otherwise>					
				</c:choose>
			</span>									
			</c:forEach>
		</div>
	</div>
	<script>
		function changeRecordCnt(){
			selFrm.submit();
		}
	</script>
</body>
</html>