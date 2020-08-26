package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/board/toggleLike")
public class ToggleLikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);		
		String strYn_like = request.getParameter("yn_like");
		int yn_like = MyUtils.parseStrToInt(strYn_like);
		// 0이 넘어가는 경우 : null일때, 숫자+문자 같이 들어가있을 때(예외)
		UserVO loginUser = MyUtils.getLoginUser(request);		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setI_user(loginUser.getI_user());
		if(yn_like == 0) {
			BoardDAO.insBoardLike(param);
		}else if(yn_like == 1) {
			BoardDAO.delBoardLike(param);
		}
		response.sendRedirect("/board/detail?i_board=" + strI_board);
	}

}
