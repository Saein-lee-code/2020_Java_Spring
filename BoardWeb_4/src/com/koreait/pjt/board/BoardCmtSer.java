package com.koreait.pjt.board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.vo.BoardCmtVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/cmt")
public class BoardCmtSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	// 댓글 삭제
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String strI_board = request.getParameter("i_board");
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);

		BoardCmtVO param = new BoardCmtVO();
		param.setI_cmt(MyUtils.getIntParameter(request, "i_cmt"));
		param.setI_user(loginUser.getI_user());
		BoardCmtDAO.delCmt(param);
		response.sendRedirect("/board/detail?i_board=" + strI_board);
	}

	// 댓글 등록/수정
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_cmt = request.getParameter("i_cmt");
		String cmt = request.getParameter("cmt");
		String strI_board = request.getParameter("i_board");
		
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);

		BoardCmtVO param = new BoardCmtVO();
		param.setI_board(Integer.parseInt(strI_board));
		param.setI_user(loginUser.getI_user());
		param.setCtnt(cmt);
		switch(strI_cmt) {
			case "0": // 등록
				BoardCmtDAO.insCmt(param);
				break;
			default: // 수정 (수정일자 변경)
				param.setI_cmt(MyUtils.parseStrToInt(strI_cmt));
				BoardCmtDAO.updCmt(param);
				break;
		}

		response.sendRedirect("/board/detail?i_board=" + strI_board);
	}
}
