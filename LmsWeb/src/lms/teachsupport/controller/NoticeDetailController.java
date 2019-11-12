package lms.teachsupport.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String code=req.getParameter("code");
		String num=req.getParameter("num");
		String subnum=req.getParameter("subnum");
		
		req.setAttribute("code", code);
		req.setAttribute("num", num);
		req.setAttribute("subnum", subnum);
		
		RequestDispatcher rd = req.getRequestDispatcher("/lms/teachSupport/noticeDetail.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code=req.getParameter("code");
		String num=req.getParameter("num");
		String subnum=req.getParameter("subnum");
		
		req.setAttribute("code", code);
		req.setAttribute("num", num);
		req.setAttribute("subnum", subnum);
		
		RequestDispatcher rd = req.getRequestDispatcher("/lms/teachSupport/noticeDetail.jsp");
		rd.forward(req, resp);
	}
}
