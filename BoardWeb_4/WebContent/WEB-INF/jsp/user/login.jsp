<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
	.err { color: red; font-weight: bold; }
</style>
</head>
<body>
	<h1>로그인</h1>
	<div class="err">${msg}</div>	
	<form id="frm" action="/login" method="post" onsubmit="return chk()">
		<div><input type="text" name="user_id" placeholder="아이디" value="${user_id}"></div>
		<div><input type="password" name="user_pw" placeholder="비밀번호"></div>
		<div><input type="submit" value="로그인"></div>		
	</form>
	<div><a href="/join"><button>회원가입</button></a></div>
	<script>
		function chk(){
			if(frm.user_id.value == "" || frm.user_id.value.length < 5){
				alert("아이디를 다시 입력하세요.");
				return false;
			}
			if(frm.user_pw.value == ""){
				alert("비밀번호를 다시 입력하세요.");
				return false;
			}
		}
	</script>
</body>
</html>