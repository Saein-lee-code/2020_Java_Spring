package com.koreait.matzip.restaurant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.FileUtils;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantRecommendMenuVO;
import com.koreait.matzip.vo.RestaurantVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RestaurantService {
	private RestaurantDAO dao;

	public RestaurantService() {
		dao = new RestaurantDAO();
	}

	public int regRes(RestaurantVO param) {
		return dao.insRestaurant(param);
	}

	public String getRestList() {
		List<RestaurantDomain> list = dao.selRestList();
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	public RestaurantDomain getRest(RestaurantVO param) {
		return dao.selRest(param);
	}

	public int addRecMenus(HttpServletRequest request) {		
		// 앞에 /로 시작하는것 상대주소, 없는것 절대주소
		// 역슬러쉬 쓸 필요 없음.
		String savePath = request.getServletContext().getRealPath("/res/img/restaurant"); // /앞의 절대경로가 필요한것
		//E:\shareSpringBackend\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MatZip + /res/img/restaurant (getRealPath가 자동으로 넣어줌)
		
		String tempPath = savePath + "/temp";	
		// E:\shareSpringBackend\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MatZip\res\img\restaurant\temp
		// 서버가 돌아가는 위치의 절대주소가 나옴.
		// 임시로 놔두는 이유: i_rest 값으로 폴더 만들어서 넣어야함
		// 그럴려면 multi part request가 객체화가 되어야함. 왜? 왜냐하면 tom에서 넘길때 encoding때문에 get parameter로 이미지가 안받아짐.
		// get parameter 하고싶으면 저걸 객체화해서 getParameterValues로 하면 스트링으로 넘어온다.
		FileUtils.makeFolder(tempPath);
		
		int maxFileSize = 10_485_760; //1024 * 1024 * 10 (10mb) //최대 파일 사이즈 크기
		MultipartRequest multi = null;
		int i_rest = 0;
		String[] menu_nmArr = null;
		String[] menu_priceArr = null;
		List<RestaurantRecommendMenuVO> list = null;
		try {
			// defaultfilerename.. : 이미 있으면 다른이름으로 저장해줌.
			multi = new MultipartRequest(request, tempPath, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());			
			i_rest = CommonUtils.getIntParameter("i_rest", multi);			
			System.out.println("i_rest : " + i_rest);
			
			menu_nmArr = multi.getParameterValues("menu_nm");
			menu_priceArr = multi.getParameterValues("menu_price");
			
			if(menu_nmArr == null || menu_priceArr == null) {
				return i_rest;
			} 
			
			if(menu_nmArr != null && menu_priceArr != null) {
				list = new ArrayList();
				for(int i=0; i<menu_nmArr.length; i++) {
					RestaurantRecommendMenuVO vo = new RestaurantRecommendMenuVO();

					vo.setI_rest(i_rest);
					vo.setMenu_nm(menu_nmArr[i]);
					vo.setMenu_price(CommonUtils.parseStringToInt(menu_priceArr[i]));
					list.add(vo);
				}	
			}
			
			String targetPath = savePath + "/" + i_rest;
			FileUtils.makeFolder(targetPath); // 폴더 만들기 (없으면 만듬)
			
			String originFileNm = "";
			String saveFileNm = "";
			Enumeration files = multi.getFileNames();

			// pic
			while(files.hasMoreElements()) {
				String key = (String)files.nextElement(); // 다음것을 가리키면서 key 값을 줌.
				System.out.println("key : " + key);
				
				originFileNm = multi.getFilesystemName(key); // 파일 선택을 안했으면 null 이 넘어옴
				System.out.println("fileNm : " + originFileNm);
				
				if(originFileNm != null) { // 사진 파일선택 됌(확인)
					String ext = FileUtils.getExt(originFileNm); // 확장자
					saveFileNm = UUID.randomUUID() + ext; 
					
					System.out.println("saveFileNm : " + saveFileNm);				
					File oldFile = new File(tempPath + "/" + originFileNm);
				    File newFile = new File(targetPath + "/" + saveFileNm);
				    oldFile.renameTo(newFile); // 파일이 이동하면서 이름도 바뀜.
				    
				    int idx = CommonUtils.parseStringToInt(key.substring(key.lastIndexOf("_") + 1));
					RestaurantRecommendMenuVO vo = list.get(idx); // pic position 에 (idx)
				    vo.setMenu_pic(saveFileNm); // set 해줌
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(list != null) {
			for(RestaurantRecommendMenuVO vo : list) {
				dao.insRecommendMenu(vo);
			}	
		}		
		return i_rest;
	}
	
	public List<RestaurantRecommendMenuVO> getRecommendMenuList(int i_rest) {
		return dao.selRecommendMenuList(i_rest);
}

	public int delRecMenu(RestaurantRecommendMenuVO param) {		
		return dao.delRecMenu(param);
	}
}
