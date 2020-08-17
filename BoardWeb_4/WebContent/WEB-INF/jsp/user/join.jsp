<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

</head>
<body>
	
	<h1>회원가입</h1>
	<div class="container">
		<div>
			<form id="frm" action="/join" method="post" onsubmit="return chk()" autocomplete="off">
				<div><input type="text" name="user_id" placeholder="아이디" required></div>
				<div><input type="password" name="user_pw" placeholder="비밀번호" required></div>
				<div><input type="password" name="user_pwre" placeholder="비밀번호확인" required></div>
				<div><input type="text" name="nm" placeholder="이름" required></div>
				<div><input type="email" name="email" placeholder="이메일"></div>
				<div><input type="submit" value="회원가입"></div>
			</form>
		</div>
	</div>
	<script>
		function chk(){
			if(frm.user_id.value.length < 5){
				alert('아이디는 5글자 이상이 되어야 합니다.')
				frm.user_id.focus()
				return false
			}else if(frm.user_pw.value.length < 5){
				alert('비밀번호는 5글자 이상이 되어야 합니다.')
				return false
			}else if(frm.user_pw.value != frm.user_pwre.value){
				alert('비밀번호가 일치하지 않습니다.')
				return false
			} 
			if(frm.nm.value.length > 0){
				const korean = /[^가-힣]/;
				// 한글이외는 다 true
				if(korean.test(frm.nm.value)){
					alert('이름은 한글만 입력해 주세요.')
					frm.nm.focus()
					return false
				}
			}
			if(frm.email.value.length > 0){
				const email = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
				if(!email.test(frm.email.value)){
					alert('이메일을 확인해 주세요.')
					frm.email.focus()
					return false
				}
			}			
		}
	</script>
</body>
</html>