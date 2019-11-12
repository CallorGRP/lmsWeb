package bit.result.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.model.HumanDao;

public class ResultCheckController extends HttpServlet {

	int result=0;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			req.setAttribute("result", this.result);
			RequestDispatcher rd = req.getRequestDispatcher("resultCheck.jsp");
			rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		this.result=2;
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		email=email.replace("%40", "@");
		
		HumanDao dao= new HumanDao();
		result=dao.getJoinResult(name, phone, email);
		System.out.println(result);
		
		
		resp.sendRedirect("resultCheck.bit");
		
	}
}
