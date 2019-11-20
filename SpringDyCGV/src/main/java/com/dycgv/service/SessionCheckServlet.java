package com.dycgv.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionCheckServlet
 */
//서블릿을 여는 주소http://localhost:9090/dycgv/SessionCheckServlet
@WebServlet("/SessionCheckServlet")
public class SessionCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션의 sid 값이 존재하면 index 페이지로 이동, 존재하지 않으면 errorPage로 이동
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sid");
		if(sid != null) {
			response.sendRedirect("http://localhost:9090/dycgv/index.jsp");
		}else {
			response.sendRedirect("http://localhost:9090/dycgv/s_errorPage.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
