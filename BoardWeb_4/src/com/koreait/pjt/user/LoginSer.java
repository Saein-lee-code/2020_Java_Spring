package com.koreait.pjt.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/login")
public class LoginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		System.out.println("ip: " + request.getRemoteAddr());
		if(loginUser != null) {			
			response.sendRedirect("board/list");
		}else {
			ViewResolver.forward("user/login", request, response);
		}
	}
	
	// 0 에러발생(db) 1 로그인성공 2 비밀번호 틀림 3 아이디없음
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = MyUtils.encryptString(user_pw);
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		int result = UserDAO.login(param);		
		if(result!=1) {
			String msg = null;
			switch(result) {
				case 2:
					msg = "비밀번호를 확인해 주세요.";
					break;				
				case 3:
					msg = "아이디를 확인해 주세요.";
					break;
				default:
					msg = "에러가 발생하였습니다.";					
			}
			request.setAttribute("msg", msg);
			request.setAttribute("user_id", user_id);
			doGet(request, response);
			return;
		}
		HttpSession hs = request.getSession();
		hs.setAttribute(Const.LOGIN_USER, param);
		response.sendRedirect("/board/list");
	}
}
