package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

public class LoginChkInterceptor {
	// null 리턴 -> 아무일없음
	// 문자열이 리턴되면 그문자열로 센드 리다이렉트 !!!
	public static String routerChk(HttpServletRequest request) {
		// 로그인 되어 있으면 login, join 접속 x
		// 로그인에 따른 접속 가능 여부 판단 : 로그인 안되어 있으면 접속할 수 있는 주소만 체크,
		// 나머지는 전부 로그인이 되어있어야함
		// 로그인o -> false 로그인x -> true
		
		String[] chkUserUriArr = {"login", "loginProc", "join", "joinProc", "ajaxIdChk"};
		
		boolean isLogout = SecurityUtils.isLogout(request);
		String[] targetUri = request.getRequestURI().split("/");
		if(targetUri.length < 3) {
			return null;
		}
		
		if(isLogout) { // 로그아웃 상태 > 갈수있는 주소: user/login & user/join			
			if(targetUri[1].equals(ViewRef.URI_USER)) {				
				for(String uri : chkUserUriArr) {
					if(uri.equals(targetUri[2])) {
						return null;
					}
				}
			}
			return "/user/login";
		}else{ // 로그인 상태 > 갈수있는 주소: 
			if(targetUri[1].equals(ViewRef.URI_USER)) {
				for(String uri : chkUserUriArr) {
					if(uri.equals(targetUri[2])) {
						return "/restaurant/restMap";
					}
				}
			}
			return null;
		}	
	}
}


