package bit.question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.model.HumanDao;
import bit.model.LectureDao;
import bit.question.model.QuestionDao;

/**
 * 홈페이지 문의 컨트롤러
 */
public class QuestionController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		System.out.println("question_Get");
		RequestDispatcher rd = req.getRequestDispatcher("question.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		System.out.println("question_Post");
    	String requestUri = req.getRequestURI();	// 요청 URI 저장
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// 요청 URI 패턴 저장
    	req.setCharacterEncoding("UTF-8");
    	if(uri.equals("questionResult")) {
    		LectureDao lecDao = new LectureDao();
    		HumanDao humDao = new HumanDao();
    		QuestionDao queDao = new QuestionDao();
    		
    		String name =req.getParameter("name");
    		String phone =req.getParameter("phone1")+req.getParameter("phone2")+req.getParameter("phone3");
    		String email =req.getParameter("email1")+req.getParameter("email2");
    		int sex =Integer.parseInt(req.getParameter("sex"));
    		String lecture =req.getParameter("lecture");
    			String lecDepart = lecture.substring(0, 10);
    			String lecName = lecture.substring(22);
    		String lecCode = lecDao.getCode(lecDepart, lecName);
    		String address =req.getParameter("juso1")+" "+req.getParameter("juso2");
    		String content =req.getParameter("content");
    		if(humDao.addQuestionStu(name, lecCode, sex, address, phone, email)&&queDao.addQuestion(name+"님의 문의글입니다.", lecCode, sex, address, phone, email, content)) {
    			resp.sendRedirect(req.getContextPath()+"/questionResult.jsp");
    			
    			
    		}else {
    			resp.sendRedirect(req.getContextPath()+"/questionError.jsp");
    		}    		
    	} else {
    		System.out.println("error");
    	}
	}
}