<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	
	String msg = "";
	String err = request.getParameter("err"); 
	if(err != null){
		switch(err){
			case "10":
				msg = "등록할 수 없습니다."; break;
			case "20":
				msg ="DB에러 발생"; break;			
		}
	}	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="msg"><%= msg %></div>
	<div class="container">
	<!-- get, post 한글깨짐 -->
	<!-- return false 하면 글등록 눌러도 서버로 안날아감. -->
	<form id="frm" action="/jsp/boardWritePro.jsp" method="post" onsubmit="return chk()" autocomplete="off">
		<fieldset>
			<legend>글쓰기</legend>
			<!-- name = 키값 (서버에 보내줄때 씀) 키 = value--> 
			<!-- addon을 깔아서 장난질을 할수있음. -->
			<label for="title">제목</label><input type="text" name="title" id="title" >
			<label for="writer">작성자</label><input type="text" name="writer" id="writer"><br><br>
			<textarea rows="10" cols="60" name="ctnt" placeholder="텍스트를 입력해 주세요." ></textarea><br><br>
			<button type="submit">등록</button>			
		</fieldset>
	</form>
	<br>
	<a href="/jsp/boardList.jsp"><button>Back</button></a>
	</div>	
	<!-- element를 불러오기위해 function은 젤밑에 적어주는것이 좋다. css는 밑에적으면 안됌(번쩍임현상이 나타남)-->
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