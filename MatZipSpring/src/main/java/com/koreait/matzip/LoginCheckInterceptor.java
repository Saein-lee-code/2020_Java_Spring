package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
// 인터셉터에서 request값을 받아서 (i_user 체크를 여기서 편하게 다해버릴수도 있음)
public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{		
		String uri = request.getRequestURI();
		System.out.println("uri: " + uri);
		String[] uriArr = uri.split("/");
		
		System.out.println("uriArr.length : " + uriArr.length);
		if(uri.equals("/")) { // 주소가 이상한경우
			return true;
		}else if(uriArr.length < 3) {
			return false;
		}else if(uriArr[1].contentEquals("res")) { // 리소스 (js, css, img)
			return true;
		}
		
		System.out.println("Intetceptor!!!");
				
		boolean isLogout = SecurityUtils.isLogout(request);
		
		switch(uriArr[1]) {
			case ViewRef.URI_USER: // user
				switch(uriArr[2]) {
				case "login" : case "join":
					if(!isLogout) { // 로그인 되어있는 상태
						response.sendRedirect("/rest/map");
						return false;
					}
				}
			case ViewRef.URI_REST: // rest
				switch(uriArr[2]) {
					case "reg":
						if(isLogout) { // 로그아웃인 상태
							response.sendRedirect("/user/login");
							return false;
						}
				}
		}
		return true;
	}
}
