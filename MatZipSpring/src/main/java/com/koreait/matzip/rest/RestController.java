package com.koreait.matzip.rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestFile;
import com.koreait.matzip.rest.model.RestPARAM;
import com.koreait.matzip.rest.model.RestRecMenuVO;

@Controller
@RequestMapping("/rest")
public class RestController {
	
	@Autowired
	private RestService service;	
	
	@RequestMapping("/map")
	public String restMap(Model model) {
		model.addAttribute(Const.TITLE, "지도보기");
		model.addAttribute(Const.VIEW, "rest/restMap");
		return ViewRef.TEMP_MENU_TEMP;
	}	
	
	@RequestMapping(value="/ajaxGetList", produces= {"application/json; charset=UTF-8"})
	@ResponseBody public List<RestDMI> ajaxGetList(RestPARAM param, HttpSession hs) {
		System.out.println("sw_lat : " + param.getSw_lat());
		System.out.println("sw_lng : " + param.getSw_lng());
		System.out.println("ne_lat : " + param.getNe_lat());
		System.out.println("ne_lng : " + param.getNe_lng());
		
		int i_user = SecurityUtils.getLoginUserPk(hs);
		param.setI_user(i_user);
		
		return service.selRestList(param);
	}
	@RequestMapping("/reg")
	public String restReg(Model model) {
		model.addAttribute("categoryList", service.selCategoryList());
		model.addAttribute(Const.TITLE, "가게 등록");
		model.addAttribute(Const.VIEW, "rest/restReg");
		return ViewRef.TEMP_MENU_TEMP;
	}
	@RequestMapping(value="/reg", method=RequestMethod.POST)
	public String restReg(RestPARAM param, HttpSession hs) {
		param.setI_user(SecurityUtils.getLoginUserPk(hs));		
		int result = service.insRest(param);
		return "redirect:/";
	}
	@RequestMapping("/detail")
	public String detail(RestPARAM param, Model model, HttpServletRequest req) {
		service.updAddHits(param, req);
		int i_user = SecurityUtils.getLoginUserPk(req);
		param.setI_user(i_user);
		
		// param 에 i_rest가 저장되어 있음.		
		RestDMI data = service.selRest(param);
		
		model.addAttribute("css", new String[]{"restaurant", "swiper-bundle.min"});		
		model.addAttribute("data", data);
		
		model.addAttribute(Const.TITLE, data.getNm()); // 가게명
		model.addAttribute(Const.VIEW, "rest/restDetail");
		
		model.addAttribute("recMenuList", service.selRestRecMenus(param));
//		model.addAttribute("menuList", service.selRestMenus(param));
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	@RequestMapping("/ajaxSelMenuList")
	@ResponseBody 
	public List<RestRecMenuVO> ajaxSelMenuList(RestPARAM param) {
		return service.selRestMenus(param);
	}
	
	@RequestMapping("/del")
	public String del(RestPARAM param, HttpSession hs) {
		// restDetail 에서 자바스크립트를 통해 param에 i_rest가 저장되어 있음.
		int loginI_user = SecurityUtils.getLoginUserPk(hs);
		param.setI_user(loginI_user);
		int result = 1;
		try {
			service.delRestTran(param);
		}catch(Exception e) {
			result = 0;
		}
		System.out.println("result : " + result);
		return "redirect:/";
	}
	// 뒤에 method=RequestMethod.POST 하면 쿼리스트링은 뒤에안붙지만 값은 날아감.
	@RequestMapping(value="/recMenus", method=RequestMethod.POST)
	public String recMenus(MultipartHttpServletRequest mReq, RedirectAttributes ra) {
		int i_rest = service.insRecMenus(mReq);
		
		// 쿼리스트링이 뒤에 붙는다. https://galid1.tistory.com/504
		// session 이용해서 하는것도 있음. (응답하고 나면 세션 지움)
		ra.addAttribute("i_rest", i_rest);
		return "redirect:/rest/detail";
	}
	@RequestMapping("/ajaxDelRecMenu")
	@ResponseBody public int ajaxDelRecMenu(RestPARAM param, HttpSession hs) {
		String path = "/resources/img/rest/" + param.getI_rest() + "/rec_menu/";
		String realPath = hs.getServletContext().getRealPath(path);
		param.setI_user(SecurityUtils.getLoginUserPk(hs)); // login user pk 담기
		return service.delRestRecMenu(param, realPath);
	}
	
	@RequestMapping("/ajaxDelMenu")
	@ResponseBody public int ajaxDelMenu(RestPARAM param) {
		// i_rest, seq, menu_pic 다 보냄
		return service.delRestMenu(param); // restInterceptor , const.realPath 에 값을 넣어놨기 때문에 이렇게 간단하게 가능.
	}
	
	@RequestMapping("/menus")
	public String menus(@ModelAttribute RestFile param, RedirectAttributes ra, HttpSession hs) {
		int i_user = SecurityUtils.getLoginUserPk(hs);
		int result = service.insRestMenu(param, i_user);
		ra.addAttribute("i_rest", param.getI_rest());
		return "redirect:/rest/detail";
	}
}
