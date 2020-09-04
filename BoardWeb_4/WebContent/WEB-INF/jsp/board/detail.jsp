<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<style>
	.container{ width: 800px; margin: 0 auto; }
	table{ border-collapse: collapse; width:800px; margin-top:30px; }
	th, td{ border: 1px solid #ccc; }
	th{ background-color: #FE7558; color: white; height: 45px; }
	td{ padding-left: 10px; }
	#cmt_table{ margin: 30px auto 0; width:800px; }
	.cmt_writer{ font-weight: bold; text-align: center; }
	.ctnt_style{ height: 200px; padding-left: 20px; text-align: left; }
	#likes { text-align: center; width: 80px; border-left: none; color: #F15285; padding:0; }
	#likes:hover{ cursor: pointer; }
	.prf_img{ width: 40px; height: 40px; border-radius: 50%; position:absolute; top:4px; right: 20px;  }
	.title_cmt_style { font-weight: bold; }
	.notice_like { color:#F15285; font-size: 0.9em; position: relative;
   				   left: 30px; top: 20px; 
    				text-shadow: 1px 1px 4px lightcoral; }
    .highlight { color: red; font-weight: bold; }
</style>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css" />

</head>
<body class="preload">
	<div class="container">상세페이지
		<a href="/board/list?page=${ param.page }&record_cnt=${ param.record_cnt }&searchText=${ param.searchText }&searchType=${ param.searchType }"><button>Back</button></a><br>
		<!-- session 객체에서 만듬 . login할때 생김 브라우저끄면 session 소멸  -->
		<c:if test = "${ loginUser.i_user == data.i_user }">
			<!-- 쿼리스트링 보내야함 
			/ 붙이고 안붙이고 차이: 1단계였을때는 차이없음
			2단계부터 차이가 생김. /board/ddd/aaa/bbb
			안붙이면 : 뒤에것만 바뀜, 붙이면 앞에것 다바뀜
			?i_board 뭐시기 쿼리스트링: 서버나 regmod와 mapping된것( 서블릿 ) 에 값전달하려고  -->
			<a href="/board/regmod?i_board=${ data.i_board }">수정</a>
			<form id="delFrm" action="/board/del?i_board=${ data.i_board }" method="post">
				<input type="hidden" name="i_board" value="${data.i_board}">						
				<a href="#" onclick="submitDel()">삭제</a>
			</form>
		</c:if>
		
		<c:if test="${ data.yn_like == 1 }">
			<span class="notice_like">
				<span class="material-icons">favorite</span>
				내가 좋아요를 누른 글입니다.
			</span>
		</c:if>								
		<table>
			<tr>
				<th style="width: 100px;">제목</th>			
				<td id="elTitle"> ${ data.title }<span class="title_cmt_style"> [${ data.cmt_count }]</span></td>
				<td id="likes" onclick="toggleLike(${ data.yn_like })">
					<c:if test="${ data.yn_like == 0 }">
						<span class="material-icons">
							favorite_border
						</span>						
					</c:if>
					<c:if test="${ data.yn_like == 1 }">
						<span class="material-icons">
							favorite
						</span>
					</c:if>					
					<c:if test="${ data.like_count != 0 }">
						${ data.like_count }개
					</c:if>
				</td>				
				<th>작성자</th>
				<td style="width: 120px; position:relative; "> ${ data.nm } 
				<c:choose>
					<c:when test="${loginUser.i_user == data.i_user}">
						<a href="/profile">
							<c:choose>
								<c:when test="${ data.profile_img != null }">
									<img class="prf_img"  src="/img/user/${ loginUser.i_user }/${ data.profile_img }">
								</c:when>
								<c:otherwise>
									<img class="prf_img"  src="/img/default_profile.jpg">
								</c:otherwise>							
							</c:choose>
						</a>
					</c:when>
					<c:otherwise>
						<c:choose>
								<c:when test="${ data.profile_img != null }">
									<img class="prf_img"  src="/img/user/${ data.i_user }/${ data.profile_img }">
								</c:when>
								<c:otherwise>
									<img class="prf_img"  src="/img/default_profile.jpg">
								</c:otherwise>							
							</c:choose>
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>			
				<th>작성일시</th>
				<td colspan="2" style="width: 200px;"> ${ data.r_dt } </td>
				<th>조회수</th>
				<td>${ data.hits }</td>
			<tr>
			<tr>
				<td colspan="5" id="elCtnt" class="ctnt_style">${ data.ctnt }</td>
			</tr>	 
		</table>
		<!-- 댓글  -->
		<div>
			<form id="cmtFrm" action="/board/cmt" method="post">
				<input type="hidden" name="i_cmt" value="0">
				<input type="hidden" name="i_board" value="${ data.i_board }">
				<div>
					<input type="text" name="cmt" placeholder="댓글내용">
					<input type="submit" id="cmtSubmit" value="전송"> <!-- 등록/수정 -->
					<input type="button" value="취소" onclick="clkCmtCancel()">					
				</div>				
			</form>
			
			<table id="cmt_table">
			<tr>
				<th>내용</th>
				<th>글쓴이</th>
				<th>등록일</th>
				<th>수정일</th>
				<th>비 고</th>				
			</tr>
			<!-- page context에 담김. -->			
				<c:forEach items="${ cmt_data }" var="item">					
				<tr>
					<td>${ item.ctnt }</td>				
					<td class="cmt_writer">${ item.nm }</td>
					<td style="width:213px">${ item.r_dt }</td>	
					<td style="width:213px">${ item.m_dt == item.r_dt ? '' : item.m_dt}</td>				
					<td>
						<c:if test="${ item.i_user == loginUser.i_user }">
							<a href="/board/cmt?i_cmt=${ item.i_cmt }&i_board=${ data.i_board }"><button onclick="clkCmtDel(${ item.i_cmt })">삭제</button></a>
							<button onclick="clkCmtMod(${ item.i_cmt }, `${ item.ctnt }`)">수정</button>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
			
		</div>
	</div>
	
	<script>
		function clkCmtCancel(){
			cmtFrm.i_cmt.value = 0;
			cmtFrm.cmt.value = '';
			cmtSubmit.value = '전송';			
		}
		function clkCmtDel(i_cmt){
			if(confirm('삭제 하시겠습니까?')){
				location.href = '/board/cmt?i_board=${data.i_board}&i_cmt=' + i_cmt;
			}
		}
		function submitDel() {
			delFrm.submit()
		}
		// 댓글 수정
		function clkCmtMod(i_cmt, ctnt){			
			cmtFrm.i_cmt.value = i_cmt;
			cmtFrm.cmt.value = ctnt;
			cmtSubmit.value = '수정';
		}
		function toggleLike(yn_like){			
			location.href='/board/toggleLike?page=${param.page}&record_cnt=${param.record_cnt}&searchType=${param.searchType}&searchText=${param.searchText}&i_board=${data.i_board}&yn_like=' + yn_like
		}
		
		function doHighlight(){			
			var searchType = '${ param.searchType }'
			var searchText = '${ param.searchText }'
			switch(searchType){
			case 'a':
				var txt = elTitle.innerText
				txt = txt.replace(new RegExp('${ searchText }'), '<span class="highlight">' + searchText + '</span>');
				elTitle.innerHTML = txt
				break
			case 'b':
				var txt = elCtnt.innerText
				txt = txt.replace(new RegExp('${ searchText }'), '<span class="highlight">' + searchText + '</span>')
				elCtnt.innerHTML = txt
				break
			case 'c':
				var txt = elTitle.innerText
				txt = txt.replace(new RegExp('${ searchText }'), '<span class="highlight">' + searchText + '</span>');
				elTitle.innerHTML = txt
				
				txt = elCtnt.innerText
				txt = txt.replace(new RegExp('${ searchText }'), '<span class="highlight">' + searchText + '</span>');
				elCtnt.innerHTML = txt				
				break			
			}
		}
		doHighlight()
		
	</script>
</body>
</html>