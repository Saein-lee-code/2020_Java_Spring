package com.koreait.pjt.user;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/profile")
public class ProfileSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 프로필 화면 (나의 프로필 이미지, 이미지 변경 가능한 화면)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		request.setAttribute("data", UserDAO.selUser(loginUser.getI_user()));
		ViewResolver.forward("user/profile", request, response);
	}
	
	// 이미지 변경 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		String savePath = getServletContext().getRealPath("img") + File.separator + "user" + File.separator + loginUser.getI_user(); // 저장경로
		System.out.println("savePath: " + savePath);
		// directory 없으면 폴더생성
		File directory = new File(savePath);
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		int maxFileSize = 10_485_760;// 1024 * 1024 * 10 (10 mb) // 최대 파일 사이즈 크기
		String fileNm = "";
		String originFileNm = "";
		String saveFileNm = "";
		try {
			MultipartRequest mr = new MultipartRequest(request, savePath, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
			System.out.println("mr: " + mr);
			Enumeration files = mr.getFileNames();
			while(files.hasMoreElements()) {
				String key = (String)files.nextElement();
				fileNm = mr.getFilesystemName(key);
				originFileNm = mr.getOriginalFileName(key);
				System.out.println("key: " + key);
				System.out.println("fileNm: " + fileNm);
				System.out.println("originFileNm: " + originFileNm);

				// 확장자 찾기
				int temp = fileNm.indexOf(".");				
				String ex = fileNm.substring(temp);
				saveFileNm = UUID.randomUUID() + ex;
				
				File oldFile = new File(savePath + "/" + fileNm);
				File newFile = new File(savePath + "/" + saveFileNm );
				
				oldFile.renameTo(newFile);				
				
				UserVO param = new UserVO();
				param.setI_user(loginUser.getI_user());
				param.setProfile_img(saveFileNm);				
				UserDAO.updUser(param);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(saveFileNm != null) {
			UserVO param = new UserVO();
			param.setProfile_img(saveFileNm);
		}
		response.sendRedirect("/profile");
	}

}
