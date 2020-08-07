package com.koreait.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.db.BoardDAO;
import com.koreait.board.vo.BoardVO;
import com.koreait.board.common.Utils;

@WebServlet("/boardDetail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// get 방식은 주로 화면띄우기용.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_board = Utils.parseStrToInt(strI_board, 0);
		if(i_board == 0) {
			response.sendRedirect("/boardList");
			return;
		}
		BoardVO param = new BoardVO();		
		param.setI_board(i_board);		
		request.setAttribute("data", BoardDAO.boardDetail(param));	
		// key 값 지정
		// detail 에서 ${} 가져오는건 getter 에서 가져오는 것.
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/boardDetail.jsp");
		rd.forward(request, response);
		
		// String jsp = "/WEB-INF/view/boardDetail.jsp";
		// request.getRequestDispatcher(jsp).forward(request.response);
	}
	// form 에서  submit
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
