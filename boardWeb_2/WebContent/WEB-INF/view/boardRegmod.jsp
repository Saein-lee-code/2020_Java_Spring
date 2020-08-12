<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data==null?'글등록':'글수정'}</title>
<style>
	form{ margin-bottom: 20px; }
	label { display: block; }
	.err{ color: red; }
	button { box-shadow:inset 0px 1px 0px 0px #ffffff;
			background:linear-gradient(to bottom, #ededed 5%, #dfdfdf 100%);
			background-color:#ededed;
			border-radius:6px;
			border:1px solid #dcdcdc;
			display:inline-block;
			cursor:pointer;
			color:#777777;
			font-family:Arial;
			font-size:15px;
			font-weight:bold;
			padding:6px 24px;
			text-decoration:none;
			text-shadow:0px 1px 0px #ffffff; } 
	button:hover { background:linear-gradient(to bottom, #dfdfdf 5%, #ededed 100%);
					background-color:#dfdfdf; }	
	button:active { position:relative; top:1px; } 
</style>
</head>
<body>
	<div>${data==null?'글등록':'글수정'}</div>
	<div class="err">${msg}</div>
	<form id="frm" action="/${data==null?'boardWrite':'boardUpdate'}" method="post" onsubmit="return chk()" autocomplete="off">
		<input type="hidden" name="i_board" value="${data.i_board}">
		<div><label for="title">제목</label><input type="text" name="title" value="${data.title}"></div>
		<div><label for="writer">작성자</label><input type="text" name="i_student" value="${data.i_student}" ${data==null?'':'readonly'}></div>
		<div><label for="ctnt">내용</label><textarea rows="5" cols="80" name="ctnt">${data.ctnt}</textarea></div>
		<input type="submit" value="${data==null?'글등록':'글수정'}">
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