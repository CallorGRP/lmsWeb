package lms.community.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 */
public class TeacherEstimateController extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	System.out.println("teacherEstimate_Get");
    	String requestUri = req.getRequestURI();	// 요청 URI 저장
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// 요청 URI 패턴 저장
    	System.out.println(uri);
    	
    	if(uri.equals("teacherEstimate")) {
    		RequestDispatcher rd = req.getRequestDispatcher("teacherEstimateMain.jsp");
    		rd.forward(req, resp);
    	} else if(uri.equals("teacherEstimateDetail")) {
    		RequestDispatcher rd = req.getRequestDispatcher("teacherEstimateDetail.jsp");
    		rd.forward(req, resp);
    	} else if(uri.equals("teacherEstimateAdd")) {
    		// 신규등록 후 자료실 홈으로..
    		RequestDispatcher rd = req.getRequestDispatcher("teacherEstimateMain.jsp");
    	} else {
    		System.out.println("error");
    	}
		
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	System.out.println("teacherEstimate_Post");
    	String requestUri = req.getRequestURI();	// 요청 URI 저장
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// 요청 URI 패턴 저장
    	System.out.println(uri);
    	
    	if(uri.equals("teacherEstimateDetail")) {
    		resp.sendRedirect(req.getContextPath()+"/lms/community/teacherEstimateDetail.jsp");
    	} else {
    		System.out.println("error");
    	}

    }
}
