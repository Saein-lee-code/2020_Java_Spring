package com.koreait.matzip.user;

import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.vo.UserVO;

public class UserService {
	// 객체 생성
	private UserDAO dao;	
	public UserService() {
		dao = new UserDAO();
	}
	
	public int join(UserVO param) {
		String pw = param.getUser_pw();
		String salt = SecurityUtils.generateSalt();
		String encryptPw = SecurityUtils.getEncrypt(pw, salt);
		
		param.setUser_pw(encryptPw);
		param.setSalt(salt);
		return dao.join(param);
	}
	
	// 1: 로그인성공 2: 아이디없음 3: 비밀번호 틀림
	public int login(UserVO param) {
		int result = 0;
		UserVO dbResult = dao.selUser(param); // id랑 비밀번호 담겨있음.
		// 이때 i_user 는 안넣어서 아마도 0 (default)
		// 두번째 if문 실행
		// 정보 다 가져옴
		
		if(dbResult.getI_user() == 0) { //아이디 없음			
			result = 2;
		}else {
			String salt = SecurityUtils.generateSalt();
			String encryptPw = SecurityUtils.getEncrypt(param.getUser_pw(), salt);		
		
			if(encryptPw.equals(dbResult.getUser_pw())){				
				result = 1;
			}else if(!encryptPw.equals(dbResult.getUser_pw()) || encryptPw == null) {
				result = 3;
			}
		}
		return result;
	}
}
