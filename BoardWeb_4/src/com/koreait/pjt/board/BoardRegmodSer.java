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
			request.setAttribute("data", BoardDAO.detailBoard(param));			
		}
		ViewResolver.forward("board/regmod", request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		
		
		String filter1 = scriptFilter(ctnt);
		String filter2 = swearWordFilter(filter1);
		
		BoardVO param = new BoardVO();		
		
		param.setTitle(title);
		param.setCtnt(filter2);
		param.setI_user(loginUser.getI_user());
		String strI_board = request.getParameter("i_board");
		int result = 0;
		// hidden 값으로 줬기때문에 값을 안받아도 null 아님	 
		if("".equals(strI_board)){
			result = BoardDAO.insBoard(param);	
			response.sendRedirect("/board/list");
			return;
		}else {
			int i_board = MyUtils.parseStrToInt(strI_board, 0);
			param.setI_board(i_board);		
			result = BoardDAO.uptBoard(param);
			param.setI_board(i_board);
			response.sendRedirect("/board/detail?i_board=" + i_board);
		}	
	}
	// 욕필터
	private String swearWordFilter(final String ctnt) {
		String[] filters = {"개새끼", "미친년", "ㄱㅐㅅㅐㄲㅣ"};
		String result = ctnt;
		for(int i=0; i<filters.length; i++) {
			result = result.replace(filters[i], "***");			
		}
		return result;
	}
	// 스크립트 필터
	private String scriptFilter(final String ctnt) {
		String[] filters = {"<script>", "</script>"};
		String[] filterReplaces = {"&lt;script&gt;", "&lt;/script&gt;"};
		String result = ctnt;
		for(int i=0; i<filters.length; i++) {
			result = ctnt.replace(filters[i], filterReplaces[i]);
		}
		return result;
	}
}
