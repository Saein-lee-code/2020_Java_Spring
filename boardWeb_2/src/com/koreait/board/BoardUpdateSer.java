package com.koreait.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.common.Utils;
import com.koreait.board.db.BoardDAO;
import com.koreait.board.vo.BoardVO;


@WebServlet("/boardUpdate")
public class BoardUpdateSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 화면에 보여주는것
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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/boardRegmod.jsp");
		rd.forward(request, response);		
	}
	// 처리하는 것
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_board = Utils.parseStrToInt(strI_board, 0);		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);	
		param.setTitle(request.getParameter("title"));
		param.setCtnt(request.getParameter("ctnt"));		
		param.setI_student(Utils.parseStrToInt(request.getParameter("i_student"), 0));
		int result = BoardDAO.boardUpdate(param);
		System.out.println("result:" + result);
		if(result == 1) { // 정상
			response.sendRedirect("/boardDetail?i_board=" + i_board);
		}else {
			request.setAttribute("msg", "에러가 발생하였습니다.");
			doGet(request, response);
		}		
	}
}
