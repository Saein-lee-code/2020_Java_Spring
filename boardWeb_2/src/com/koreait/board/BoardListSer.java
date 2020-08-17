package com.koreait.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.db.BoardDAO;
import com.koreait.board.vo.BoardVO;
// servlet 로직담당
// jsp 뷰담당
@WebServlet("/boardList")
// 이거여기서안할꺼면 xml에 적어야됌 (상당히 길게)...그래서 여기다 적는게 나음

// 주소맵핑 (원하는 주소값) jsp container 가 실행시켜줌. get이든 post든 보내면 알아서 처리.
// 주소값이 클래스랑 같은것 = 보안 안좋음
// /만 적으면 index.hmtl 처럼 아무것도 없이 접근 할수있음.
public class BoardListSer extends HttpServlet {
	// HttpServlet protected public 을 쓸수있다. 
	private static final long serialVersionUID = 1L;
	// default 를 쓰기위해서는 지우면 static final.. default 임.
	// 멤버필드	
	
	// 생성자 
    public BoardListSer() {
        super(); // 부모 생성자 
        // this는 나자신 super은 부모
        // super. super() this. this()
        // ()는 생성자, .은 메소드호출
    }
    // 메소드와 다른 특징: 이름이 클래스명과 동일하다, return 타입이 없다.    
    // 최상위 부모 = object. 메모리에 먼저올림 -> httpservlet 메모리에 올리고 boardLIstser 객체 생성
    // 최상위 부모 생성자 실행 종료 -> 그다음 실행 종료 -> 그다음 실행 종료.
    
    // get방식안쓰면 삭제해도 됌.
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 예외처리하지않고 (해결하지않고) 던짐.
		// request: 고객이 니한테 보낸 모든정보가 들어있음. response: 응답 
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// request dispatcher ? 값전달 jsp까지 값전달. parameter에서 받은걸 다 jsp로 넘김.
		// doGet은 화면에 보여주는 담당 그래서 jsp랑 같이 씀	
		
		// 객체로서 연결. request response(생성) 다같이 감.
		// 주소값이 변하지 않음. (유지)parameter-jsp뿐만아니라 servlet 요청도 가능
		// doGet에서 날리면 get, doPost에서 날리면 post 방식으로 날아감.
		// 
		// 
		// sendRedirect 없어지고 저쪽에서 새로 받음(새로발행)(응답하자마자 사라짐) 주소값이 변함. 엔터치는거랑 비슷한 기능
		// sendRedirect 는 get방식

		// 객체지향
		List<BoardVO> list = BoardDAO.selBoardList();
		// 여러 타입보낼수 있는것 Object
		// 부모는 자식을 가리킬수있다. 모든 타입 다 가리킬수있다.
		request.setAttribute("data", list);		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/boardList.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 처리하는 담당.
		// ->borderList (sendRedirect or doGet을 씀) 만약 클래스가 바뀐다면 sendRedirect를 씀
		doGet(request, response);
	}
}
