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
	
	table { margin-top: 30px; border-collapse: collapse; text-align: center; width: 800px; border: 1px solid #eee; }	
	th { border: 1px solid #eee; }
	td{ font-size:0.9em; padding: 10px 0; }
	
	.list_title { text-overflow: ellipsis; width:250px; overflow: hidden;  white-space: nowrap; display: inline-block; line-height: 37px; }
	#selFrm { margin-top: 20px; }
	#th_style { background-color: orange; color: white; height: 35px; }
	.list_style:hover { cursor: pointer; background: #FE7558; color: white; transition: 0.3s; }
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
	.like_cnt { padding-left: 30px; color: #F15285; font-weight: bold; }
	#selFrm{ position: absolute; left: 0; }
	.pf_img { display: inline-block; position: relative; top: 5px; }
	.pf_img img{ width: 40px; height: 40px; border-radius: 50%; }
	#prf_style:hover { font-weight: bold; }
	.highlight { color: red; font-weight: bold; }
	#likeListContainer {			
		padding: 10px;		
		border: 1px solid #bdc3c7;
		position: absolute;
		right: -200px; 
		top: 30px;
		width: 60px;
		height: auto;
		overflow-y: auto;
		background-color: white !important;
		transition-duration : 500ms;
		visibility: hidden;
	}		
		
	.profile {
		background-color: white !important;
		display: inline-block;	
		width: 25px;
		height: 25px;
	    border-radius: 50%;
	    overflow: hidden;
	}		
	
	.likeItemContainer {
		display: flex;
		width: 100%;
	}
	.prf_img { width: 30px; height:30px;
		       position: absolute; left: 15px; }
	.likeItemContainer .nm {
	    padding: 5px 0;	
		background-color: white !important;
		margin-left: 7px;
		font-size: 0.7em;
		display: flex;
		align-items: center;
	}
	.title_cmt_style{ font-weight: bold; position: absolute; top: 20px; }
	
</style>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

</head>
<body>
	<div class="container">
		<header>
			<h1>리스트</h1>
			<div id="user_div"><span id="id_style">${ loginUser.nm }</span>님, 환영합니다!
				<span id="prf_style"><a href="/profile">프로필</a></span>
				<div class="logout_write"><a href="/logout">로그아웃</a> / <a href="/board/regmod">글쓰기</a></div>
			</div>
			<div id="likeListContainer"></div>
		</header>
		
		<div id="listContainer">
			<a id="homeBtn" href="/board/list"><input type="button" class="btnStyle" value="목록"></a>
			<table>
				<tr id="th_style">
					<th style="width:50px;">번호</th>
					<th style="width:385px;" colspan="3">제목</th>					
					<th style="width:60px;">조회수</th>
					<th>작성자</th>
					<th id="date_style">등록일시</th>
				</tr>
				<c:forEach items="${list}" var="item">
					<tr class="list_style">
							<td onClick="moveToDetail(${ item.i_board })">${ item.i_board }</td>
							<td style="position:relative;" onClick="moveToDetail(${ item.i_board })"><span class="list_title">${ item.title }</span><span class="title_cmt_style">[${ item.cmt_count }]</span></td>
							<td class="like_cnt">
								<span onClick="getLikeList(${ item.i_board },${ item.like_count }, this)">${ item.like_count }</span>
							</td>
							<td>
								<c:if test="${ item.yn_like == 0 }">
									<span class="material-icons">
										favorite_border
									</span>						
								</c:if>
								<c:if test="${ item.yn_like == 1 }">
									<span class="material-icons" style="color: #F15285;">
										favorite
									</span>
								</c:if>			
							</td>		
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
				<select name="searchType" style="position: relative; top: 1px;">
					<option value="a" ${ searchType == 'a'? 'selected':'' }>제목</option>
					<option value="b" ${ searchType == 'b'? 'selected':'' }>내용</option>
					<option value="c" ${ searchType == 'c'? 'selected':'' }>제목+내용</option>
				</select>
				<input type="text" name="searchText" placeholder="검색어를 입력하세요." value="${ searchText }"> 
				<input type="Submit" class="btnStyle" value="검색" onclick="highlight('${ searchText }')">
			</form>
		</div>
		
		<div class="paging_style">
			<c:forEach begin="1" end="${ pagingCnt }" var="item">
			<span class="page_style">
				<c:choose>					
						<c:when test="${ page == item }">
							<span id="accent"><a href="/board/list?page=${ item }&record_cnt=${ param.record_cnt }&searchText=${ searchText }&searchType=${ searchType }">${ item }</a></span>
						</c:when>
						<c:when test="${ page != item }">
							<span><a class="address" href="/board/list?page=${ item }&record_cnt=${ param.record_cnt }&searchText=${ searchText }&searchType=${ searchType }">${ item }</a></span>
						</c:when>					
				</c:choose>
			</span>									
			</c:forEach>
		</div>
	</div>
	<script>
		var beforeI_board = 0;
		function changeRecordCnt(){
			selFrm.submit();
		}
		function moveToDetail(i_board){
			location.href='/board/detail?page=${ page }&record_cnt=${ param.record_cnt }&i_board=' + i_board + '&searchText=${ searchText }&searchType=${ searchType }'
		}
		function highlight(text){
			var list_style = document.getElementsByClassName("list_style");
			
		}
		function getLikeList(i_board, cnt, span){			
			if(cnt == 0){ return }
			if(beforeI_board == i_board){
				likeListContainer.style.display = 'none';
				return;
			}else{
				beforeI_board = i_board;
				likeListContainer.style.display = 'undset';
			}
			const locationX = window.scrollX + span.getBoundingClientRect().left
			const locationY = window.scrollY + span.getBoundingClientRect().top + 30
			likeListContainer.style.left = `\${locationX}px`
			likeListContainer.style.visibility = "visible";
			likeListContainer.style.top = `\${locationY}px`
			likeListContainer.innerHTML = "";
			
				
			axios.get('/board/like', {
				params: {
					'i_board': i_board
				}
			}).then(function(res){
				if(res.data.length > 0){
					for(let i=0; i<res.data.length; i++){
						const result = makeLikeUser(res.data[i])
						likeListContainer.innerHTML += result;
						console.log(result);
					}					
				}
			})			
		}
		
		function makeLikeUser(one){
			const img = one.profile_img == null ? '<img class="prf_img" src ="/img/default_profile.jpg">' : 
				`<img class="prf_img" src="/img/user/\${one.i_user}/\${one.profile_img}">`;
				
			const ele = `<div class="likeItemContainer">
				<div class="profileContainer">
					<div class="profile">
							\${img}
					</div>
					 <div class="nm">\${one.nm}</div>
				</div>
			</div>
			`;
			
			return ele;
		}
		
		
	</script>
</body>
</html>