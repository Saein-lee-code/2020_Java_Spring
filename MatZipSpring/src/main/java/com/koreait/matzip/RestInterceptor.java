package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.koreait.matzip.rest.RestMapper;

public class RestInterceptor extends HandlerInterceptorAdapter{
	// xml 에 where절에 체크안해줘도 된다고함.
	@Autowired
	private RestMapper mapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception{
		System.out.println("rest - interceptor");
		String uri = request.getRequestURI();
		System.out.println("uri: " + uri);
		String[] uriArr = uri.split("/");
		
		//쿼리문에 굳이 i_user값을 넘겨주지않아도 된다는 말.
		String[] checkKeywords = {"del", "Del", "upd", "Upd"};
		for(String keyword: checkKeywords) {
			if(uriArr[2].toLowerCase().contains(keyword)) {
				int i_rest = CommonUtils.getIntParameter("i_rest", request);
				if(i_rest == 0) {
					return false; // return false 면 안넘어감. 다른 유저가 삭제할수없음.
				}
				int i_user = SecurityUtils.getLoginUserPk(request); // 로그인한 사람의 i_user
				
				boolean result = _authSuccess(i_rest, i_user);
				System.out.println("=== auth result : " + result);
				return result;
			}
		}
		System.out.println("!!!=== auth result : true");
		return true;
	}
	
	
	private boolean _authSuccess(int i_rest, int i_user) {		
		return i_user == mapper.selRestChkUser(i_rest);
	}
}
