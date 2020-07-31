<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container">
	<!-- get, post 한글깨짐 -->
	<form action="/jsp/boardWritePro.jsp" method="post">
		<fieldset>
			<legend>글쓰기</legend>
			<!-- name = 키값 (서버에 보내줄때 씀) 키 = value--> 
			
			<label for="title">제목</label><input type="text" name="title" id="title">
			<label for="writer">작성자</label><input type="text" name="writer" id="writer" required><br><br>
			<textarea rows="10" cols="60" name="ctnt" placeholder="텍스트를 입력해 주세요."></textarea><br><br>
			<button type="submit">등록</button>			
		</fieldset>
	</form>
	<br>
	<a href="/jsp/boardList.jsp"><button>Back</button></a>
	</div>	
</body>
</html>