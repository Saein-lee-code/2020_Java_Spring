<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div style="height:100%;">
	<div class="recMenuContainer">
		<c:forEach items="${recMenuList}" var="item">
			<div class="recMenuItem" id="recMenuItem_${item.seq}">
				<div class="pic">
					<c:if test="${item.menu_pic != null and item.menu_pic != ''}">
						<img src="/res/img/rest/${data.i_rest}/rec_menu/${item.menu_pic}">
					</c:if>
				</div>
				<div class="info">
					<div class="nm">${item.menu_nm}</div>
					<div class="price"><fmt:formatNumber type="number" value="${item.menu_price}"/>원</div>
				</div>
				<c:if test="${loginUser.i_user == data.i_user}">
					<div class="delIconContainer" onclick="delRecMenu(${item.seq})">
						<span class="material-icons">clear</span>
					</div>
				</c:if>
			</div>
		</c:forEach>
	</div>
	<div id="sectionContainerCenter">
		<div id="detailContainer">
			<c:if test="${loginUser.i_user == data.i_user}">
				<button class="btn" onclick="isDel()">가게 삭제</button>
				
				<h2>- 추천 메뉴 -</h2>
				<div>
					<div><button class="btn" type="button" onclick="addRecMenu()">추천 메뉴 추가</button></div>
					<form id="recFrm" action="/rest/recMenus" enctype="multipart/form-data" method="post">
						<input type="hidden" name="i_rest" value="${data.i_rest}">
						<div id="recItem"></div>
						<div><input class="btn" type="submit" value="등록"></div>
					</form>
				</div>
				
				<h2>- 메뉴 -</h2>
				<div>
					<form id="menuFrm" action="/rest/menus" enctype="multipart/form-data" method="post">
						<input type="hidden" name="i_rest" value="${data.i_rest}">
						<input type="file" name="menu_pic" multiple>
						<div><input class="btn" type="submit" value="등록"></div>
					</form>
				</div>
			</c:if>
			
			<div class="restaurant-detail">
				<div id="detail-header">
					<div class="restaurant_title_wrap">
						<h1 class="restaurant_name">${data.nm}</h1>
						<c:if test="${loginUser != null }">							
							<span class="favoriteIcon material-icons" id="favorite" onclick="toggleFavorite()">
								<c:if test="${data.is_favorite == 0}">favorite_border</c:if>
								<c:if test="${data.is_favorite == 1}">favorite</c:if></span>
						</c:if>
					</div>
					<div class="status branch_none">
						<span class="material-icons viewIcon"> remove_red_eye </span>
						<span class="cnt hit">${data.hits}</span>
						<span class="material-icons heartIcon">loyalty</span>					
						<span class="cnt favorite" id="cnt_favorite">${data.cnt_favorite}</span>
					</div>
				</div>
				<div>
					<table id="rest_detail_table">
						<caption><span class="material-icons">restaurant_menu</span>레스토랑 상세 정보</caption>
						<tbody>
							<tr>
								<th>주소</th>
								<td>${data.addr}</td>
							</tr>
							<tr>
								<th>카테고리</th>
								<td>${data.cd_category_nm}</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${data.user_nm}</td>
							</tr>
							<tr>
								<th>메뉴</th>
								<td>	
									<div id="conMenuList" class="menuList">
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="carouselContainer">
	<div id="imgContainer">
		<div class="swiper-container">
			<div id="swiperWrapper" class="swiper-wrapper">
			</div>
			<!-- If we need pagination -->
			<div class="swiper-pagination"></div>
			
			<!-- If we need navigation buttons -->
			<div class="swiper-button-prev"></div>
			<div class="swiper-button-next"></div>
			
			<div class=imgDel">
				<span class="material-icons listDel" onclick="delMenu()">delete</span> 
			</div>
		</div>		
	</div>
	<span class="material-icons" onclick="closeCarousel()">clear</span>
	
</div>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script>
	var menuList = [] // 메뉴 이미지 리스트 배열
	function toggleFavorite(){
		console.log('favorite: ' + (favorite.innerText.trim() == 'favorite'))
		
		let parameter = {
			params:{
				i_rest: ${data.i_rest}	
			}			
		}
		
		var icon = favorite.innerText.trim()
		var int_fav = parseInt(cnt_favorite.innerText);

		switch(favorite.innerText.trim()){
		case 'favorite':
			parameter.params.proc_type = 'del'		
			--int_fav;			
			break
		case 'favorite_border':
			parameter.params.proc_type = 'ins'
			++int_fav;
			break		
		}
		cnt_favorite.innerText = int_fav;
		axios.get('/user/ajaxToggleFavorite', parameter).then(function(res){
			if(res.data == 1){
				favorite.innerText = (icon == 'favorite' ? 'favorite_border' : 'favorite')
			}
		})
	}
	
	function delMenu(){
		if(!confirm('삭제하시겠습니까?')) { return }	
		const obj = menuList[mySwiper.realIndex]
		if(obj != undefined){
			// 서버 삭제 요청
			axios.get('/rest/ajaxDelMenu', {
				params: {
					i_rest: ${data.i_rest},
					seq: obj.seq,
					menu_pic: obj.menu_pic
				}
			}).then(function(res) {				
				if(res.data == 1) {
					//엘리먼트 삭제
					menuList.splice(mySwiper.realIndex, 1)
					refreshMenu()
				}else{
					alert('메뉴를 삭제할 수 없습니다.')
				}
			})	
		}		
	}
	
	function closeCarousel() {
		carouselContainer.style.opacity = 0
		carouselContainer.style.zIndex = -10
	}
	
	function openCarousel(idx) {
		mySwiper.slideTo(idx);
		carouselContainer.style.opacity = 1
		carouselContainer.style.zIndex = 40
	}
		
	var mySwiper = new Swiper('.swiper-container', {
		  // Optional parameters
		  direction: 'horizontal',
		  loop: true,
		
		  // If we need pagination
		  pagination: {
		    el: '.swiper-pagination',
		  },
		
		  // Navigation arrows
		  navigation: {
		    nextEl: '.swiper-button-next',
		    prevEl: '.swiper-button-prev',
		  }
		})

	function ajaxSelMenuList() {
		axios.get('/rest/ajaxSelMenuList', {
			params: {
				i_rest: ${data.i_rest}
			}
		}).then(function(res) {
			menuList = res.data
			refreshMenu()			
		})
	}
	
	function refreshMenu() {
		conMenuList.innerHTML = ''
		swiperWrapper.innerHTML = ''
		
		menuList.forEach(function(item, idx) {
			makeMenuItem(item, idx)
		})
	}
	
	function makeMenuItem(item, idx) {
		//메인 화면에서 메뉴 이미지 디스플레이 ------------------------- [start]
		const div = document.createElement('div')
		div.setAttribute('class', 'menuItem')
				
		const img = document.createElement('img')
		img.setAttribute('src', `/res/img/rest/${data.i_rest}/menu/\${item.menu_pic}`)
		img.style.cursor = 'pointer'
		img.addEventListener('click', function() {
			openCarousel(idx + 1)
		})
		
		div.append(img)	
		conMenuList.append(div)
		//메인 화면에서 메뉴 이미지 디스플레이 ------------------------- [end]		
		
		//팝업 화면에서 메뉴 이미지 디스플레이 ------------------------- [start]
		const swiperDiv = document.createElement('div')
		swiperDiv.setAttribute('class', 'swiper-slide')
		
		const swiperImg = document.createElement('img')
		swiperImg.setAttribute('src', `/res/img/rest/${data.i_rest}/menu/\${item.menu_pic}`)		
		swiperDiv.append(swiperImg)		
		mySwiper.appendSlide(swiperDiv);		
		//팝업 화면에서 메뉴 이미지 디스플레이 ------------------------- [end]
	}	
	
	<c:if test="${loginUser.i_user == data.i_user}">
	function delRecMenu(seq) {
		if(!confirm('삭제하시겠습니까?')) {
			return
		}	
		console.log('seq : ' + seq)
		
		axios.get('/rest/ajaxDelRecMenu', {
			params: {
				i_rest: ${data.i_rest},
				seq: seq
			}
		}).then(function(res) {
			console.log(res)
			if(res.data == 1) {
				//엘리먼트 삭제
				var ele = document.querySelector('#recMenuItem_' + seq)
				ele.remove()
			}
		})
	}
	
	var idx = 0;
	function addRecMenu() {
		var div = document.createElement('div')
		div.setAttribute('id', 'recMenu_' + idx++)
		
		var inputNm = document.createElement('input')
		inputNm.setAttribute('type', 'text')
		inputNm.setAttribute('name', 'menu_nm')
		var inputPrice = document.createElement('input')
		inputPrice.setAttribute('type', 'number')
		inputPrice.setAttribute('name', 'menu_price')
		inputPrice.value = '0'
		var inputPic = document.createElement('input')
		inputPic.setAttribute('type', 'file')
		inputPic.setAttribute('name', 'menu_pic')
		var delBtn = document.createElement('input')
		delBtn.setAttribute('type', 'button')
		delBtn.setAttribute('class', 'btn')
		delBtn.setAttribute('value', 'X')		
		delBtn.addEventListener('click', function() {
			div.remove()
		})		
		div.append('메뉴: ')
		div.append(inputNm)
		div.append(' 가격: ')
		div.append(inputPrice)
		div.append(' 사진: ')
		div.append(inputPic)
		div.append(delBtn)
		
		recItem.append(div)
	}
	function isDel() {
		if(confirm('삭제 하시겠습니까?')) {
			location.href = '/rest/del?i_rest=${data.i_rest}'
		}
	}
	addRecMenu()
	
	</c:if>
	
	ajaxSelMenuList()
	
</script>