package lms.teachmanage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.model.LectureDao;
import bit.model.LectureDto;

/**
 * 
 */
public class LectureController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LectureDao dao = new LectureDao();
		LectureDto bean = new LectureDto();
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		list = dao.list();
		
		RequestDispatcher rd = req.getRequestDispatcher("/lms/teachManage/lectureList.jsp");
		req.setAttribute("list", list);
		rd.forward(req, resp);
	}

}
