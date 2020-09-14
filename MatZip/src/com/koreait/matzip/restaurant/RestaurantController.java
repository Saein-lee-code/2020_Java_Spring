package com.koreait.matzip.restaurant;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		RestaurantDomain param = new RestaurantDomain();
		param.setI_rest(i_rest);
		
		request.setAttribute(Const.TITLE, "가게 디테일");
		request.setAttribute(Const.VIEW, "restaurant/restDetail");
		request.setAttribute("data", service.restDetail(param));
		
		return ViewRef.TEMP_MENU;
	}
	
	public String addRecMenusProc(HttpServletRequest request) {
		String uploads = request.getRealPath("/res/img");
		MultipartRequest multi = null;
		String strI_rest = null;
		String[] menu_nmArr = null;
		String[] menu_priceArr = null;
		try {
			multi=new MultipartRequest(request, uploads, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			strI_rest = multi.getParameter("i_rest");
			menu_nmArr = multi.getParameterValues("menu_nm");
			menu_priceArr = multi.getParameterValues("menu_price");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if(menu_nmArr != null && menu_priceArr != null) {
			for(int i=0; i<menu_nmArr.length; i++) {
				System.out.println(i + ":" + menu_nmArr[i] + ", " + menu_priceArr[i]);
			}	
		}

		return "redirect:/restaurant/restDetail?i_rest=" + strI_rest;
	}
}
