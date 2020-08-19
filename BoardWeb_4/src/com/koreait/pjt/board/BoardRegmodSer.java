package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		ViewResolver.forward("board/regmod", request, response);
//		HttpSession hs = request.getSession();
//		UserVO loginUser = (UserVO) hs.getAttribute(Const.LOGIN_USER);
//		// if(null == hs.getAttribute(Const.LOGIN_USER)) {
//			response.sendRedirect("/login");
//			return;
//		}
//		
//		BoardVO param = new BoardVO();
//		param.setI_board(Integer.parseInt(request.getParameter("i_board")));	
//		
//		request.setAttribute("data", BoardDAO.detailBoard(param));
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);		
		BoardVO param = new BoardVO();		
		param.setTitle(request.getParameter("title"));
		param.setCtnt(request.getParameter("ctnt"));
		param.setI_user(Integer.parseInt(request.getParameter("i_user")));
		// param.setI_user(loginUser.getI_user());
		int result = BoardDAO.insBoard(param);
		
		if(result != 1) { // 에러
			doGet(request, response);				
			return;
		}else {
			response.sendRedirect("/board/list");
		}
		request.setAttribute("data", param);
	}
}
