package lms.teachsupport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bit.model.BbsDao;
import bit.model.BbsDto;

public class FaqEditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		BbsDto bean= new BbsDto();
		BbsDao dao = new BbsDao();
		HttpSession hs=req.getSession();
		bean=(BbsDto)hs.getAttribute("faqDetail");
		String changeSub=req.getParameter("sub");
		String changeContent=req.getParameter("content");
		dao.edit(bean.getCode(), bean.getNum(), bean.getSubnum(), changeSub, changeContent);
		hs.setAttribute("faqDetail", null);
		
		resp.sendRedirect("/home2/lms/teachSupport/faqList.bit");
	}
}