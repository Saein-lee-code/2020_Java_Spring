<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.menu_input input{ margin-right: 20px; }
</style>    
<div id="sectionContainerCenter">
	<div>
		<c:if test="${loginUser.i_user == data.i_user}">
			<div>
				<button id="delBtn" onclick="isDel()">삭제</button>
				<form id="recFrm" action="/restaurant/addRecMenusProc" enctype="multipart/form-data" method="post">
					<div><button type="button" id="addMenuBtn" onclick="addRecMenu()">메뉴 추가</button></div>
					<input type="hidden" name="i_rest" value="${data.i_rest}">
					<div id="recItem">
						<div class="menu_input">
							메뉴: <input type="text" name="menu_nm">가격: <input type="number" name="menu_price">사진: <input type="file" name="menu_pic">
						</div>
					</div>
				</form>
				<div><input type="submit" value="등록"></div>				
			</div>
		</c:if>
		<div>
			가게사진들
		</div>
		<div class="restaurant-detail">
			<div id="detail-header">
				<div class="restaurant_title_wrap">
					<span class="title">
						<h1 class="restaurant_name">${data.nm}</h1>						
					</span>
				</div>
				<div class="status branch_none">
					<span class="cnt hit">${data.cntHits}</span>
					<span class="cnt favorite">${data.cntFavorite}</span>
				</div>				
			</div>
			<div>
				<table>
					<caption>레스토랑 상세정보</caption>
					<tbody>
						<tr>
							<th>주소</th>
							<td>${data.addr}</td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td>${data.cdCategoryName}</td>
						</tr>						
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script>
		function addRecMenu(){
			var div = document.createElement('div')
			div.className = 'menu_input'
			
			var inputNm = document.createElement('input')
			inputNm.setAttribute('type', 'text')
			inputNm.setAttribute('name', 'menu_nm')
			var inputPrice = document.createElement('input')
			inputPrice.setAttribute('type', 'number')
			inputNm.setAttribute('name', 'menu_price')
			var inputPic = document.createElement('input')
			inputPic.setAttribute('type', 'file')
			inputNm.setAttribute('name', 'menu_pic')
			
			div.append('메뉴: ')
			div.append(inputNm) // append & appendChild 상관없음
			div.append('가격: ')
			div.append(inputPrice)
			div.append('사진: ')
			div.append(inputPic)
			
			recItem.append(div)
		}
		
		function isDel(){
			if(confirm('삭제 하시겠습니까?')){
				location.href = "/restaurant/restDel?i_rest=${data.i_rest}";
			}
		}
	</script>
</div>