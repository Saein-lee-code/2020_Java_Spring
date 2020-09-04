package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = (HttpSession)request.getSession();
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		String searchType = request.getParameter("searchType");
		searchType = (searchType == null) ? "a" : searchType;
		
		String searchText = request.getParameter("searchText");
		searchText = ( searchText == null? "" : searchText );
		
		// 0이 넘어가는 경우 : null일때, 숫자+문자 같이 들어가있을 때(예외)
		int page = MyUtils.getIntParameter(request,  "page");
		page = page == 0 ? 1 : page;		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");	// 페이지 수
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		int eIndex = (recordCnt * page);
		int sIndex = (eIndex - recordCnt);		
		
		BoardDomain param = new BoardDomain();
		param.setsIdx(sIndex);
		param.seteIdx(eIndex);		
		param.setRecord_cnt(recordCnt);
		param.setSearchType(searchType);
		param.setSearchText("%" + searchText + "%");
//		param.setSearchText(searchText);
		
		int pagingCnt = BoardDAO.selPagingCnt(param);
		// 이전 레코드수 값이 있고, 이전 레코드수보다 변경한 레코드 수가 더 크다면
		// 마지막 페이지수로 변경
		if(page > pagingCnt) {
			page = pagingCnt; //마지막페이지 값으로 변경
		}
		
		request.setAttribute("page", page);	
		request.setAttribute("pagingCnt", pagingCnt);
		param.setI_user(loginUser.getI_user());
		List<BoardDomain> list = BoardDAO.selBoardList(param);
		if(!"".equals(searchText) && ("a".equals(searchType) || "c".equals(searchType))) {
			for(BoardDomain item : list) {				
				String title = item.getTitle();
				title = title.replace(searchText, "<span class=\"highlight\">" + searchText + "</span>");
				item.setTitle(title);
			}
		}
		
		request.setAttribute("searchText", searchText);
		request.setAttribute("searchType", searchType);
		request.setAttribute("list", list);
		
		hs.setAttribute("recordCnt", recordCnt);		
		ViewResolver.forwardLoginChk("board/list", request, response);
	}
}
