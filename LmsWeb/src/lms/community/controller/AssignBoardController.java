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
public class AssignBoardController extends HttpServlet{

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	System.out.println("assignBoard_Get");
    	String requestUri = req.getRequestURI();	// 요청 URI 저장
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// 요청 URI 패턴 저장
    	System.out.println(uri);
    	
    	if(uri.equals("assignBoard")) {
    		RequestDispatcher rd = req.getRequestDispatcher("assignBoardMain.jsp");
    		rd.forward(req, resp);
    	} else if(uri.equals("assignBoardDetail")) {
    		RequestDispatcher rd = req.getRequestDispatcher("assignBoardDetail.jsp");
    		rd.forward(req, resp);
    	} else if(uri.equals("assignBoardAdd")) {
    		// 신규등록 후 과제게시판 홈으로...
    		RequestDispatcher rd = req.getRequestDispatcher("assignBoardMain.jsp");
    	} else {
    		System.out.println("error");
    	}
		
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	System.out.println("assignBoard_Post");
    	String requestUri = req.getRequestURI();	// 요청 URI 저장
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// 요청 URI 패턴 저장
    	System.out.println(uri);
    	
    	if(uri.equals("assignBoardDetail")) {
    		resp.sendRedirect(req.getContextPath()+"/lms/community/assignBoardDetail.jsp");
    	} else {
    		System.out.println("error");
    	}

    }

}
