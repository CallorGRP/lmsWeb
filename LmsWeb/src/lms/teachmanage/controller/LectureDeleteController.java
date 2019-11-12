package lms.teachmanage.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.model.LectureDao;

@WebServlet("/lms/teachManage/lectureDelete.bit")
public class LectureDeleteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("test");
		String code=req.getParameter("code");
		LectureDao dao=new LectureDao();
		try {
			dao.lectureNull(code);
		} catch (SQLException e) {
			System.out.println("등록된 강좌가 없습니다!");
		}
		dao.lectureDelete(code);
		resp.sendRedirect(req.getContextPath()+"/lms/teachManage/lectureList.bit");
	}					
		
}
