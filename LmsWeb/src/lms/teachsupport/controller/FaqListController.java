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

/**
 * 
 */
public class FaqListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BbsDao bd = new BbsDao();
		BbsDto bean = new BbsDto();
		//lms.bbs.model로 가면 캐스팅 과정이 이상해져서 그냥 bit.model 에서 가져옴
		ArrayList<BbsDto> list = new ArrayList<BbsDto>();
		list = bd.list("AQ");
		
		RequestDispatcher rd = req.getRequestDispatcher("/lms/teachSupport/faqList.jsp");
		req.setAttribute("list", list);
		
		rd.forward(req, resp);
	}
}
