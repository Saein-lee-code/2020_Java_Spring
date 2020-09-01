<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<style>
	img { width: 300px; height: 300px; }
	input:first-child { border: 2px solid skyblue; }
</style>
</head>
<body>
	<div class="container">
		<h1>프로필</h1>
		<a href="/board/list"><button>Home</button></a>
		<div>
			<div>
				<c:choose>
					<c:when test="${ data.profile_img != null }">
						<img src="/img/user/${loginUser.i_user}/${data.profile_img}">
					</c:when>
					<c:otherwise>
						<img src="img/default_profile.jpg">
					</c:otherwise>					
				</c:choose>
				<!-- <img src="${ data.profile_img == null ? 'img/user/default_profile.jpg' : data.profile_img } "> -->
			</div>
			<div>
				<form action="/profile" method="post" enctype="multipart/form-data">
					<div>
						<label>프로필 이미지: <input type="file" name="profile_img" accept="image/*"></label>
						<input type="submit" value="업로드">
					</div>
				</form>
			</div>			
			<div>ID: ${ data.user_id }</div>
			<div>이름: ${ data.nm }</div>
			<div>Email: ${ data.email }</div>
			<div>가입일시: ${ data.r_dt }</div>
		</div>
	</div>	
</body>
</html>