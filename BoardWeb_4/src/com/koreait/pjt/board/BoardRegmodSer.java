package com.koreait.pjt.board;

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
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}		
		String strI_board = request.getParameter("i_board");
		if(strI_board != null) {	
			int i_board = MyUtils.parseStrToInt(request.getParameter("i_board"), 0);
			BoardVO param = new BoardVO();
			param.setI_board(i_board);
			request.setAttribute("data", BoardDAO.detailBoard(i_board));			
		}
		ViewResolver.forward("board/regmod", request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		
		BoardVO param = new BoardVO();		
		param.setTitle(request.getParameter("title"));
		param.setCtnt(request.getParameter("ctnt"));
		param.setI_user(loginUser.getI_user());
		String strI_board = request.getParameter("i_board");
		
		// hidden 값으로 줬기때문에 값을 안받아도 null 아님	 
		if("".equals(strI_board)){
			int result = BoardDAO.insBoard(param);	
			response.sendRedirect("/board/list");
			return;
		}		
		int i_board = MyUtils.parseStrToInt(strI_board, 0);
		param.setI_board(i_board);		
		BoardDAO.uptBoard(param);
		response.sendRedirect("/board/detail?i_board=" + i_board);	
	}
}
