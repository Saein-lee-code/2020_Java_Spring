package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.UserVO;

public class RestaurantController {
	// 메소드 정의
	public String restMap(HttpServletRequest request) {
		HttpSession hs = (HttpSession)request.getSession();		
		UserVO loginUser = (UserVO) hs.getAttribute("loginUser");
		
		request.setAttribute(Const.TITLE, "RestMAP");
		request.setAttribute(Const.LOGIN_USER, loginUser);
		request.setAttribute(Const.VIEW, "restaurant/restMap");
		
		return ViewRef.TEMP_MENU;
	}
}
