package lms.teachmanage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.model.LectureDao;

public class LectureEditController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/lms/teachManage/lectureEdit.jsp");
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String code = req.getParameter("code2");
		String departday=req.getParameter("departday2");
		String endday=req.getParameter("endday2");
		String lectureName=req.getParameter("lecture_name2");
		String param = req.getParameter("listener2");
		int listener = Integer.parseInt(param);
		String room = req.getParameter("room2");
		String content = req.getParameter("content2");
		
		LectureDao dao = new LectureDao();
		dao.lectureEdit(code, departday, endday, lectureName, listener, room, content);
		
		resp.sendRedirect(req.getContextPath()+"/lms/teachManage/lectureDetail.bit?code="+code);
		
	}
	

}
