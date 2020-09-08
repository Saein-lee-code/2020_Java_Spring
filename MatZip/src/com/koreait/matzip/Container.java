package com.koreait.matzip;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class Container extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapper mapper; // tomcat 켜질때 생성. Container 클래스가 만들어질 때, 주소값 생김.
	public Container() {
		mapper = new HandlerMapper();
	}
	// request 톰캣 컨테이너가 줌. 호출당함 -> framework *doGet이 호출 당함.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		proc(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proc(request, response);
	}
	
	private void proc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp = mapper.nav(request); // 보통 템플릿 파일명		
		if(temp.indexOf(":") >= 0) {
			String prefix = temp.substring(0, temp.indexOf(":"));
			String value = temp.substring(temp.indexOf(":") + 1);
			if("redirect".equals(prefix)){				
				response.sendRedirect(value);				
				return;			
			}else if("ajax".equals(prefix)) {				
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.print(value);
				return;
			}
		}	
		
		switch(temp) {
		case "405":
			temp ="/WEB-INF/view/error.jsp";
			break;
		case "404":
			temp ="/WEB-INF/view/notFound.jsp";
			break;
		}
		request.getRequestDispatcher(temp).forward(request, response);
	}
}
