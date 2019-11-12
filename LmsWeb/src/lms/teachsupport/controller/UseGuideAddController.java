package lms.teachsupport.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bit.model.BbsDao;
import bit.model.BbsDto;

public class UseGuideAddController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/lms/teachSupport/useGuideAdd.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession hs= req.getSession();
		BbsDao bd= new BbsDao();
		BbsDto bean= (BbsDto)hs.getAttribute("UseGuideAdd");
		hs.setAttribute("UseGuideAdd", null);
		int subnum=0;
		if(req.getParameter("subnum")==null) {
			subnum=0;
		}else {
			subnum=Integer.parseInt(req.getParameter("subnum"));
		}
		bd.add("UG", subnum, req.getParameter("sub"), bean.getHcode(), bean.getId(), req.getParameter("content"));
		resp.sendRedirect("/home2/lms/teachSupport/useGuideList.bit");
	}
}
