package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/detail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
//		if(MyUtils.isLogout(request)) {}
		String strI_board = request.getParameter("i_board");
		int i_board = Integer.parseInt(strI_board);
		
		// 단독으로 조회수 올리기 방지! -- [start]
		// application 내장객체 얻어오기
		ServletContext application = getServletContext();
		// read_ 키값을 새로만들어서 strI_board를 넣음 처음들어가면 null->그다음 i_user값들어감
		// set한적이 없으니까
		Integer readI_user = (Integer)application.getAttribute("read_" + strI_board);
		System.out.println(readI_user);

		if(readI_user == null || readI_user != loginUser.getI_user()) {
			BoardDAO.addHits(i_board);
			application.setAttribute("read_" + strI_board, loginUser.getI_user());
		}
		// -- [end]
		
		request.setAttribute("data", BoardDAO.detailBoard(i_board));		
		ViewResolver.forward("board/detail", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
