package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantRecommendMenuVO;
import com.koreait.matzip.vo.RestaurantVO;

public class RestaurantController {
	private RestaurantService service;
	public RestaurantController () {
		service = new RestaurantService();
	}
		
	// 메소드 정의
	public String restMap(HttpServletRequest request) {		
		request.setAttribute(Const.TITLE, "RestMAP");	
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
		int cd_category = CommonUtils.getIntParameter("cd_category", request);
		double lat = CommonUtils.getDoubleParameter("lat", request);
		double lng = CommonUtils.getDoubleParameter("lng", request);
		
		
		RestaurantVO param = new RestaurantVO();
		param.setI_user(i_user);
		param.setNm(nm);
		param.setAddr(addr);
		param.setCd_category(cd_category);
		param.setLat(lat);
		param.setLng(lng);
		
		int result = service.regRes(param);
		
		return "redirect:/restaurant/restMap";		
	}
	
	public String ajaxGetList(HttpServletRequest request) {
		return "ajax:" + service.getRestList();		
	}

	public String restDetail(HttpServletRequest request) {
		int i_rest = CommonUtils.getIntParameter("i_rest", request);
		RestaurantVO param = new RestaurantVO();
		param.setI_rest(i_rest);
		request.setAttribute("css", new String[] {"restaurant"});
		request.setAttribute("recommendMenuList", service.getRecommendMenuList(i_rest));
		request.setAttribute("data", service.getRest(param));
		request.setAttribute(Const.TITLE, "디테일");
		request.setAttribute(Const.VIEW, "restaurant/restDetail");
		
		return ViewRef.TEMP_MENU;
	}
	//추천 메뉴
	public String addRecMenusProc(HttpServletRequest request) {
		int i_rest = service.addRecMenus(request);
		return "redirect:/restaurant/restDetail?i_rest=" + i_rest;
	}
	// 메뉴
	public String addMenuProc(HttpServletRequest request) {
		int i_rest = service.addMenus(request);
		return "redirect:/restaurant/restDetail?i_rest=" + i_rest;
	}
	public String ajaxDelRecMenu(HttpServletRequest request) {
		int i_rest = CommonUtils.getIntParameter("i_rest", request);
		int i_user = SecurityUtils.getLoginUserPk(request);
		int seq = CommonUtils.getIntParameter("seq", request);
		RestaurantRecommendMenuVO param = new RestaurantRecommendMenuVO();
		param.setI_rest(i_rest);
		param.setSeq(seq);
		param.setI_user(i_user);
		
		int result = service.delRecMenu(param);
		return "ajax:" + result;
	}
}
