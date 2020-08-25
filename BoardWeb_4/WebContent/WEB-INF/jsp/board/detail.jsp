<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<style>
	table{ border-collapse: collapse; }
	th, td{ border: 1px solid black; }
	th{ background-color: skyblue; color: white; }
	#ctnt_style{ height: 200px; padding-left: 20px; text-align: left; }
	#likes { width: 20px; border-left: none; color: #F15285; }
	#likes:hover{ cursor: pointer; }

</style>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
	<div>상세페이지</div>
	<a href="/board/list"><button>Back</button></a><br>
	<!-- session 객체에서 만듬 . login할때 생김 브라우저끄면 session 소멸  -->
	<c:if test = "${ loginUser.i_user == data.i_user }">
		<!-- 쿼리스트링 보내야함 
		/ 붙이고 안붙이고 차이: 1단계였을때는 차이없음
		2단계부터 차이가 생김. /board/ddd/aaa/bbb
		안붙이면 : 뒤에것만 바뀜, 붙이면 앞에것 다바뀜
		?i_board 뭐시기 쿼리스트링: 서버나 regmod와 mapping된것( 서블릿 ) 에 값전달하려고  -->
		<a href="/board/regmod?i_board=${ data.i_board }">수정</a>
		<form id="delFrm" action="/board/del?i_board=${ data.i_board }" method="post">
			<input type="hidden" name="i_board" value="${data.i_board}">						
			<a href="#" onclick="submitDel()">삭제</a>
		</form>
	</c:if>	
	<table>
		<tr>
			<th>제목</th>			
			<td> ${ data.title }</td>
			<td id="likes" onclick="toggleLike(${data.yn_like})">
				<c:if test="${ data.yn_like == 0 }">
					<span class="material-icons">
						favorite_border
					</span>
				</c:if>
				<c:if test="${ data.yn_like == 1 }">
					<span class="material-icons">
						favorite
					</span>
				</c:if>				
			</td>
			<th>작성자</th>
			<td> ${ data.nm } </td>
		</tr>
		<tr>			
			<th>작성일시</th>
			<td colspan="2" style="width: 200px;"> ${ data.r_dt } </td>
			<th>조회수</th>
			<td>${ data.hits }</td>
		<tr>
		<tr>
			<td colspan="5" id="ctnt_style">${ data.ctnt }</td>
		</tr>	
	</table>
	<script>
		function submitDel() {
			delFrm.submit()
		}
		
		function toggleLike(yn_like){
			location.href="/board/toggleLike?i_board=${data.i_board}&yn_like=" + yn_like;
		}
	
		
	</script>
</body>
</html>