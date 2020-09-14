package com.koreait.matzip.restaurant;

import java.util.List;

import com.google.gson.Gson;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantVO;

public class RestaurantService {
	private RestaurantDAO dao;
	
	public RestaurantService() {
		dao = new RestaurantDAO();
	}
	
	public int regRes(RestaurantVO param) {		
		return dao.regRest(param);
	}
	
	public String getRestList() {
		List<RestaurantDomain> list = dao.selRestList();
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	public RestaurantDomain restDetail(RestaurantDomain param) {
		return dao.restDetail(param);
	}	
}