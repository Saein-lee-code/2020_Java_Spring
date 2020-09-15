<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link type="text/css" rel="stylesheet" href="/res/css/common.css">
<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">

</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/view/${view}.jsp"></jsp:include>
	</div>
</body>
</html>