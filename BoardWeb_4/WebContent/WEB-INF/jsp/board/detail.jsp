<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>
</head>
<body>
	<div>상세페이지</div>
	<table>
		<tr>
			<th>제목</th>
			<td> ${ data.title } </td>
			<th>작성자</th>
			<td> ${ data.name } </td>
		</tr>
		<tr>			
			<th>작성일시</th>
			<td> ${ data.r_dt } </td>
			<th>조회수</th>
			<td>${ data.hits }</td>
		<tr>
		<tr>
			<td colspan="4" id="ctnt_style">${ data.ctnt }</td>
		</tr>	
	</table>
	<a href="/board/list"><button>Back</button></a>
</body>
</html>