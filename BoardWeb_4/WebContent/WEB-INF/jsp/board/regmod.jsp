<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data==null?'글등록':'글수정'}</title>
</head>
<body>
	<h1>${data==null?'글등록':'글수정'}</h1>
	<div>
		<form id="frm" action="regmod" method="post" onsubmit="return chk()">
			<input type="hidden" name="i_user" value="${loginUser.i_user}">
			<input type="hidden" name="i_board" value="${data.i_board}">			
			<div>제목: <input type="text" name="title" value="${data.title}"></div>
			<div>내용: <textarea name="ctnt">${data.ctnt}</textarea></div>
			<div><input type="submit" value="${data==null?'글등록':'글수정'}"></div>
		</form>		
	</div>
	<script>
		function chk(){
			if(frm.title.value === ""){
				alert('제목을 입력하세요')
				return false
			}else if(frm.ctnt.value === ""){
				alert('내용을 입력하세요')
				return false
			}
		}
	</script>
</body>
</html>