package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.user.UserController;

public class HandlerMapper {
	private UserController userCon;
	public HandlerMapper() {
		userCon = new UserController();
	}
	
	public String nav(HttpServletRequest request) {		
		String[] uriArr = request.getRequestURI().split("/");
		for(int i=0; i<uriArr.length; i++) {
			System.out.println("uriArr[" + i + "]: " + uriArr[i]);
		}
		if(uriArr.length < 3) {
			return "405";
		}
				
		switch(uriArr[1]) {
		// case "user"
		case ViewRef.URI_USER:
			switch(uriArr[2]) {
			case "login":
				return userCon.login(request);
			case "loginProc":
				return userCon.loginProc(request);
			case "join":
				return userCon.join(request);
			case "joinProc":
				return userCon.joinProc(request);
			case "ajaxIdChk":
				return userCon.ajaxIdChk(request);
			}
			break;		
		}		
		return "404";
	}
}
