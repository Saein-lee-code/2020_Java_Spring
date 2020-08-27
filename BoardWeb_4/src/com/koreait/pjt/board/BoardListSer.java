package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;


@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		// 0이 넘어가는 경우 : null일때, 숫자+문자 같이 들어가있을 때(예외)
		int page = MyUtils.getIntParameter(request,  "page");
		int sIndex = 0;
		int eIndex =  0;
		page = page == 0 ? 1 : page;		
		eIndex = (Const.RECORD_CNT * page);
		sIndex = eIndex - Const.RECORD_CNT;
		
		
		BoardDomain param = new BoardDomain();
		param.setRecord_cnt(Const.RECORD_CNT); // 한페이지당 n개씩 뿌리겠다.
		request.setAttribute("pagingCnt", BoardDAO.selPagingCnt(param));
		request.setAttribute("list", BoardDAO.selBoardList(sIndex, eIndex));
		ViewResolver.forwardLoginChk("board/list", request, response);
	}		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
