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
public class LectureEstimateController extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	System.out.println("lectureEstimate_Get");
    	String requestUri = req.getRequestURI();	// ��û URI ����
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// ��û URI ���� ����
    	System.out.println(uri);
    	
    	if(uri.equals("lectureEstimate")) {
    		RequestDispatcher rd = req.getRequestDispatcher("lectureEstimateMain.jsp");
    		rd.forward(req, resp);
    	} else if(uri.equals("lectureEstimateDetail")) {
    		RequestDispatcher rd = req.getRequestDispatcher("lectureEstimateDetail.jsp");
    		rd.forward(req, resp);
    	} else if(uri.equals("lectureEstimateAdd")) {
    		// �űԵ�� �� �ڷ�� Ȩ����..
    		RequestDispatcher rd = req.getRequestDispatcher("lectureEstimateMain.jsp");
    	} else {
    		System.out.println("error");
    	}
		
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	System.out.println("lectureEstimate_Post");
    	String requestUri = req.getRequestURI();	// ��û URI ����
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// ��û URI ���� ����
    	System.out.println(uri);
    	
    	if(uri.equals("lectureEstimateDetail")) {
    		resp.sendRedirect(req.getContextPath()+"/lms/community/lectureEstimateDetail.jsp");
    	} else {
    		System.out.println("error");
    	}

    }
}
