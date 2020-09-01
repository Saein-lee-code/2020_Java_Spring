<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "com.koreait.pjt.db.BoardDAO" %> 
<%@ page import = "com.koreait.pjt.vo.BoardVO" %>
<%@ page import="java.sql.*" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet"><style>
	body{ font-family: 'Nanum Gothic', sans-serif; }
	.container{ position: relative; width: 800px; margin: 0 auto; text-align: center; }
	a{ text-decoration: none; color: black; font-size: 18px; }
	#user_div{ position: relative; }
	.logout_write { position: absolute; right: 0px; }
	.logout_write a{ font-size: 0.9em; padding: 10px 0; }
	.logout_write a:hover { font-weight: bold; }
		
	#homeBtn { position: absolute; left:0; top: 50px; }
	
	table { margin-top: 30px; border-collapse: collapse; text-align: center; width: 800px; }	
	td, th { border: 1px solid #eee; }
	td{ font-size:0.9em; padding: 10px 0; }
	
	#selFrm { margin-top: 20px; }
	#th_style { background-color: orange; color: white; height: 35px; }
	.list_style:hover { cursor: pointer; background: #FE7558; color: white; }
	#id_style { font-weight: bold; }
	#date_style { width: 200px; }
	.select_style { background: #FDEDEC; }
	#search_style{ margin-top: 30px; }
	
	.paging_style{ margin: 30px 0; }
	.page_style{ margin-right: 10px; font-size: 18px; text-align: center;  }
	.page_style a:hover{ font-weight: bold; color: red; }
	#accent a { font-weight: bold; color: red; }
	
	.btnStyle {	box-shadow:inset 0px 34px 0px -15px #b54b3a;
				background-color:#a73f2d;
				border:1px solid #ccc;
				border-radius: 5px; 
				display:inline-block;
				cursor:pointer;
				color:#ffffff;
				font-family:Arial;
				font-size:15px;
				font-weight:bold;
				padding:2px 10px;
				text-decoration:none;
				text-shadow:0px -1px 0px #7a2a1d;
	}
	.btnStyle:hover { background-color:#b34332; }
	.btnStyle:active { position:relative; top:1px; }
	#selFrm{ position: absolute; bottom: 80px; left: 0; }
	.pf_img { display: inline-block; position: relative; top: 5px; }
	.pf_img img{ width: 40px; height: 40px; border-radius: 50%; }
	.title_cmt_style{ font-weight: bold; }
</style>
</head>
<body>
	<div class="container">
		<header>
			<h1>리스트</h1>
			<div id="user_div"><span id="id_style">${ loginUser.nm }</span>님, 환영합니다!
				<a href="/profile">프로필</a>
				<div class="logout_write"><a href="/logout">로그아웃</a> / <a href="/board/regmod">글쓰기</a></div>
			</div>
		</header>
		
		<div id="listContainer">
			<a id="homeBtn" href="/board/list"><input type="button" class="btnStyle" value="목록"></a>
			<table>
				<tr id="th_style">
					<th style="width:50px;">번호</th>
					<th style="width:385px;">제목</th>
					<th style="width: 60px;">조회수</th>
					<th>작성자</th>
					<th id="date_style">등록일시</th>
				</tr>
				<c:forEach items="${list}" var="item">
					<tr class="list_style" onClick="location.href='/board/detail?page=${ page }&record_cnt=${ param.record_cnt }&i_board=${item.i_board}&searchText=${ searchText }'">
							<td>${ item.i_board }</td>
							<td>${ item.title } <span class="title_cmt_style">[${ item.cmt_count }]</span></td>			
							<td>${ item.hits }</td>
							<td style="width:130px; padding: 5px;">
								<div class="pf_img">
								<c:choose>
									<c:when test="${ item.profile_img != null }">
										<img src="/img/user/${ item.i_user}/${ item.profile_img }">
									</c:when>
									<c:otherwise>
										<img src="/img/default_profile.jpg">
									</c:otherwise>												
								</c:choose>
								</div>
								${ item.nm }
							
							</td>
							<td>${ item.r_dt }</td>
					</tr>					
				</c:forEach>
			</table>
		</div>
		<div id="recordCountContainer">
			<form id="selFrm" action ="/board/list">
				<input type="hidden" name="page" value="${ page }">
				<input type="hidden" name="searchText" value="${ searchText }">
				게시글 수 :
				<select class="select_style" name="record_cnt"  onchange="changeRecordCnt()" style="height: 20px;">
					<c:forEach begin="10" end ="30" step="10" var="item">
						<c:choose>
							<c:when test="${ param.record_cnt == item }">
								<option value="${ item }" selected>${ item }개
							</c:when>
							<c:otherwise>
								<option value="${item}">${item}개</option>	
							</c:otherwise>
						</c:choose>						
					</c:forEach>
				</select>
			</form>			
		</div>
		<div id="searchContainer">
			<form id="search_style" action="/board/list">
				<input type="text" name="searchText" placeholder="검색어를 입력하세요." value="${ searchText }"> 
				<input type="Submit" class="btnStyle" value="검색">
			</form>
		</div>
		
		<div class="paging_style">
			<c:forEach begin="1" end="${ pagingCnt }" var="item">
			<span class="page_style">
				<c:choose>					
						<c:when test="${ page == item }">
							<span id="accent"><a href="/board/list?page=${ item }&record_cnt=${ param.record_cnt }&searchText=${ searchText }">${ item }</a></span>
						</c:when>
						<c:when test="${ page != item }">
							<span><a class="address" href="/board/list?page=${ item }&record_cnt=${ param.record_cnt }&searchText=${ searchText }">${ item }</a></span>
						</c:when>					
				</c:choose>
			</span>									
			</c:forEach>
		</div>
	</div>
	<script>
		function changeRecordCnt(){
			selFrm.submit();
		}
	</script>
</body>
</html>