package lms.teachmanage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.model.LectureDao;

public class LectureAddController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("lectureAdd.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String code = (req.getParameter("code")).toUpperCase();
		String lectureName = req.getParameter("lecture_name");
		String departday = req.getParameter("departday");
		String endday = req.getParameter("endday");
		String room = req.getParameter("room");
		String content = req.getParameter("content");
		String param=req.getParameter("listener");
		int listener = Integer.parseInt(param);
		String name=req.getParameter("name");
		
		LectureDao dao = new LectureDao();
		dao.lectureAdd(code, lectureName, departday, endday, listener, room, content);
		dao.teacherLectureUpdate(name, code);
		
		resp.sendRedirect(req.getContextPath()+"/lms/teachManage/lectureList.bit");
	}
}
