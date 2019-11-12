package bit.curriculumjoin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.model.HumanDao;

public class CurriculumJoinResultController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("curriculumJoinResult.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String genderP = req.getParameter("gender");
		int gender = Integer.parseInt(genderP);
		String lecture = req.getParameter("lecture");
		String birth = req.getParameter("birth");
		String address = req.getParameter("juso");
		address=address.replace("+", " ");
		address=address.replace("%2B", " ");
		
		HumanDao dao = new HumanDao();
		dao.addStudent(name, lecture, birth, gender, address, phone, email);
		
		resp.sendRedirect("curriculumJoinResult.jsp");
		
	}
}
