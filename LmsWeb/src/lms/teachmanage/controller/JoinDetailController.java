package lms.teachmanage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.model.HumanDao;

public class JoinDetailController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("joinDetail.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email=req.getParameter("email");
		String id = req.getParameter("id");
		HumanDao dao= new HumanDao();
		dao.studentConfirm(id);
		dao.sendMail(email);
		resp.sendRedirect(req.getContextPath()+"/lms/teachManage/joinList.bit");
	}
}
