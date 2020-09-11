package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantVO;
import com.koreait.matzip.vo.UserVO;

public class RestaurantController {
	private RestaurantService service;
	public RestaurantController () {
		service = new RestaurantService();
	}
		
	// 메소드 정의
	public String restMap(HttpServletRequest request) {
		HttpSession hs = (HttpSession)request.getSession();		
		UserVO loginUser = (UserVO) hs.getAttribute("loginUser");
		
		request.setAttribute(Const.TITLE, "RestMAP");
		request.setAttribute(Const.LOGIN_USER, loginUser);
		request.setAttribute(Const.VIEW, "restaurant/restMap");
		
		return ViewRef.TEMP_MENU;
	}

	public String restReg(HttpServletRequest request) {
		final int I_M = 1; // category code
		request.setAttribute("categoryList", CommonDAO.selCodeList(I_M));
		request.setAttribute(Const.TITLE, "가게 등록");
		request.setAttribute(Const.VIEW, "restaurant/restReg");
		return ViewRef.TEMP_MENU;
	}
	
	public String restRegProc(HttpServletRequest request) {		
		int i_user = SecurityUtils.getLoginUser(request).getI_user();
		String nm = request.getParameter("nm");
		String addr = request.getParameter("addr");
		String str_lat = request.getParameter("lat");
		String str_lng = request.getParameter("lng");
		String str_cd_category = request.getParameter("cd_category");
		
		int cd_category = CommonUtils.parseStringToInt(str_cd_category);
		double lat = CommonUtils.parseStringToDouble(str_lat);
		double lng = CommonUtils.parseStringToDouble(str_lng);
		
		
		RestaurantVO param = new RestaurantVO();
		param.setI_user(i_user);
		param.setNm(nm);
		param.setAddr(addr);
		param.setCd_category(cd_category);
		param.setLat(lat);
		param.setLng(lng);
		
		int result = service.regRes(param);
		if(result == 1) {
			return "redirect:/restaurant/restMap";
		}		
		return "redirect:/restaurant/restReg";
	}
	
	public String ajaxGetList(HttpServletRequest request) {
		return null;		
	}
}
