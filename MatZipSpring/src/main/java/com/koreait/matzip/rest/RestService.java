package com.koreait.matzip.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.Const;
import com.koreait.matzip.FileUtils;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.model.CodeVO;
import com.koreait.matzip.model.CommonMapper;
import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestFile;
import com.koreait.matzip.rest.model.RestPARAM;
import com.koreait.matzip.rest.model.RestRecMenuVO;

@Service
public class RestService {	
	// bean등록과 관련있음
	// 주소값을 넣어줌
	@Autowired
	private RestMapper mapper;
	@Autowired
	private CommonMapper cMapper;
	
	public RestDMI selRest(RestPARAM param) {
		return mapper.selRest(param);
	}
	public List<RestDMI>  selRestList(RestPARAM param) {		
		return mapper.selRestList(param);
	}
	
	public List<CodeVO> selCategoryList(){
		CodeVO p = new CodeVO();
		p.setI_m(1);
		return cMapper.selCodeList(p);
	}
	public List<RestRecMenuVO> selRestMenus(RestPARAM param) {
		return mapper.selRestMenus(param);
	}

	public List<RestRecMenuVO> selRestRecMenus(RestPARAM param) {
		return mapper.selRestRecMenus(param);
	}	
	
	public int insRest(RestPARAM param) {
		return mapper.insRest(param);
	}
	public int insRestMenu(RestFile param, int i_user) {
		if(_authFail(param.getI_rest(), i_user)) {
			return Const.FAIL;
		}
		// 지나쳤다는것은 내가 쓴글이 맞다는 것.
		
		System.out.println(Const.realPath);
		
		String path = Const.realPath + "/resources/img/rest/" + param.getI_rest() + "/menu/";
		FileUtils.makeFolder(path);
		
		List<RestRecMenuVO> list = new ArrayList();

		for(MultipartFile mf : param.getMenu_pic()) {
			RestRecMenuVO vo = new RestRecMenuVO();
			list.add(vo);			

			String saveFileNm = FileUtils.saveFile(path, mf);
			vo.setMenu_pic(saveFileNm);
			vo.setI_rest(param.getI_rest());
		}

		for(RestRecMenuVO vo : list) {
			mapper.insRestMenu(vo);
		}

		return Const.SUCCESS;
	}	
	public int insRecMenus(MultipartHttpServletRequest mReq) {		
		int i_user = SecurityUtils.getLoginUserPk(mReq.getSession());		
		int i_rest = Integer.parseInt(mReq.getParameter("i_rest"));
		if(_authFail(i_rest, i_user)) {
			return Const.FAIL;
		}// 장난질 못하도록. 내가 쓴글도아닌데 글등록이 됌
		// 통과가 된다는것은 내가 쓴글이 맞다는것.
		
		
		List<MultipartFile> fileList = mReq.getFiles("menu_pic");
		String[] menuNmArr = mReq.getParameterValues("menu_nm");
		String[] menuPriceArr = mReq.getParameterValues("menu_price");
		
		
//		String path = mReq.getServletContext().getRealPath("/resources/img/rest/" + i_rest + "/rec_menu/");
		String path = Const.realPath + "/resources/img/rest/" + i_rest + "/rec_menu/";
		FileUtils.makeFolder(path);

		List<RestRecMenuVO> list = new ArrayList();
		// 저장위치
		
		for(int i=0; i<menuNmArr.length; i++) {
			RestRecMenuVO vo = new RestRecMenuVO();
			
			list.add(vo);	
			
			String menu_nm = menuNmArr[i];
			int menu_price = CommonUtils.parseStringToInt(menuPriceArr[i]);
			vo.setI_rest(i_rest);
			vo.setMenu_nm(menu_nm);
			vo.setMenu_price(menu_price);
			
			// 각 파일 저장			
			MultipartFile mf = fileList.get(i);						
			String saveFileNm = FileUtils.saveFile(path, mf);
			vo.setMenu_pic(saveFileNm);	
		}
		
		for(RestRecMenuVO vo : list) {
			mapper.insRestRecMenu(vo);
		}
		return i_rest;
	}

	// 로그인한사람의 i_user. 이게 맞다면 로그인한사람이 썼다는것
	// true : 실패했다는것. 로그인한사람이 쓴게아님
	private boolean _authFail(int i_rest, int i_user) {
		RestPARAM param = new RestPARAM();
		param.setI_rest(i_rest);

		RestDMI dbResult = mapper.selRest(param); // 0: pk값 틀린것, 1: pk값 맞은것
		if(dbResult == null || dbResult.getI_user() != i_user) {
			return true;
		}

		return false;
	}
	
	@Transactional
	public void delRestTran(RestPARAM param) {
		mapper.delRestRecMenu(param);
		mapper.delRestMenu(param);
		mapper.delRest(param);
	}

	public int delRest(RestPARAM param) {
		return mapper.delRest(param);
	}
	
	public int delRestRecMenu(RestPARAM param) {
		return mapper.delRestRecMenu(param);
	}
	
	public int delRestMenu(RestPARAM param) {
		if(param.getMenu_pic() != null && "".equals(param.getMenu_pic())) {
			String path = Const.realPath + "/resources/img/rest/" + param.getI_rest() + "/menu/";
			if(FileUtils.delFile(path + param.getMenu_pic())) { // true(1) : db도 삭제
				return mapper.delRestMenu(param);
			}else { // 0번이 넘어옴
				return Const.FAIL;
			}			
		}
		return mapper.delRestMenu(param); // 파일명이 없어도 삭제가가능하도록 함.
	}

	public int delRestRecMenu(RestPARAM param, String realPath) {
		// 파일삭제
		List<RestRecMenuVO> list = mapper.selRestRecMenus(param);
		if(list.size() == 1) { // record수가 0개 아니면 1이 넘어옴. 1이넘어왔다 -> 내가 쓴글이맞음. 삭제할 정보가 넘어옴.
			RestRecMenuVO item = list.get(0); // index는 있으면 무조건 0임. 0부터 시작이므로.
			if(item.getMenu_pic() != null && !item.getMenu_pic().equals("")) { // 이미지 있을 때 -> 삭제
				File file = new File(realPath + item.getMenu_pic());
				if(file.exists()) {
					if(file.delete()) {
						return mapper.delRestRecMenu(param);
					}else
						return 0;
				}
			}
		}
		return mapper.delRestRecMenu(param);
	}
	public void updAddHits(RestPARAM param, HttpServletRequest req) {
		// 로그인안한사람도 접근할수 있으니까 ip로 체크
		String myIp = req.getRemoteAddr(); 
		
		ServletContext ctx = req.getServletContext(); // application(서버당 1개) || 개인 : request, session, pageContext
		String currentRestReadIp = (String)ctx.getAttribute(Const.CURRENT_REST_READ_ID + param.getI_rest());
		// ex) 5번글을 읽으면 currentRestReadIp_5
		// ctx에 저 키값으로 담겨있었나?
		// null -> 한번도 키값으로 뭔갈 넣은적이 없다. 처음들어간 글임
		// hits++
		
		int i_user = SecurityUtils.getLoginUserPk(req);
		if(currentRestReadIp == null || !currentRestReadIp.equals(myIp)) {
			// 내가 쓴 글 이면 조회수 안올라게 막을 것
			param.setI_user(i_user);
			// 조회수 올림 처리 할것			
			mapper.updAddHits(param);
			ctx.setAttribute(Const.CURRENT_REST_READ_ID + param.getI_rest(), myIp);
		}
		
	}	
}
