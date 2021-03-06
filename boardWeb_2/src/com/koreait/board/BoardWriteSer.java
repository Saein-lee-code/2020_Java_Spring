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

@WebServlet("/boardWrite")
public class BoardWriteSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/boardRegmod.jsp");
		rd.forward(request, response);		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO param = new BoardVO();	
		param.setTitle(request.getParameter("title"));
		param.setCtnt(request.getParameter("ctnt"));
		param.setI_student(Utils.parseStrToInt(request.getParameter("i_student"), 0));
		int result = BoardDAO.boardInsert(param);
		System.out.println("result:" + result);
		if(result == 1) { // 정상
			response.sendRedirect("/boardList");
		}else {
			request.setAttribute("msg", "에러가 발생하였습니다.");
			doGet(request, response);
		}		
	}
}
