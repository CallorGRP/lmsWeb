package bit.curriculum.controller;

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
 * 홈페이지 교육과정 컨트롤러
 */
public class CurriculumController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
//    	System.out.println("curriculum_Get");
    	LectureDao dao = new LectureDao();
		LectureDto bean = new LectureDto();
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		list = dao.list();
		req.setAttribute("list", list);
    	
    	RequestDispatcher rd = req.getRequestDispatcher("curriculum.jsp");
		rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
//    	System.out.println("curriculum_Post");
    	String requestUri = req.getRequestURI();	// 요청 URI 저장
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// 요청 URI 패턴 저장

    	if(uri.equals("curriculumDetail")) {
    		resp.sendRedirect(req.getContextPath()+"/curriculumDetail.jsp");
    	} else if(uri.equals("curriculumJoin")) {
    		resp.sendRedirect(req.getContextPath()+"/curriculumJoin.jsp");
    	} else if(uri.equals("curriculumJoinResult")) {
    		resp.sendRedirect(req.getContextPath()+"/curriculumJoinResult.jsp");
    	} else {
    		System.out.println("error");
    	}
    }
}
