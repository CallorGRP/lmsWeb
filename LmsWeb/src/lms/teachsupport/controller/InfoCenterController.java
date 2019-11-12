package lms.teachsupport.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.model.BbsDao;
import bit.model.BbsDto;
import lms.teachsupport.model.InfoCenterDao;

/**
 * 
 */
public class InfoCenterController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/lms/teachSupport/infoCenterList.jsp");
		ArrayList<BbsDto> list = new ArrayList<>();
		BbsDao dao = new BbsDao();
		list=dao.list("QE");
		req.setAttribute("list", list);
		
		rd.forward(req, resp);
	}
     
}
