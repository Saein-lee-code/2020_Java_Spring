<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<style>
	label { display: block; }
</style>
</head>
<body>
	<form id="frm" action="/boardWrite" method="post" onsubmit="return chk()" autocomplete="off">
		<div><label for="title">제목</label><input type="text" name="title"></div>
		<div><label for="writer">작성자</label><input type="text" name="i_student"></div>
		<div><label for="ctnt">내용</label><textarea rows="5" cols="80" name="ctnt"></textarea></div>
		<button type="submit">등록</button>
	</form>
	<a href="/boardList"><button>Back</button></a>	
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