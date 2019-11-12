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
    	String requestUri = req.getRequestURI();	// ��û URI ����
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// ��û URI ���� ����
    	System.out.println(uri);
    	
    	if(uri.equals("assignBoard")) {
    		RequestDispatcher rd = req.getRequestDispatcher("assignBoardMain.jsp");
    		rd.forward(req, resp);
    	} else if(uri.equals("assignBoardDetail")) {
    		RequestDispatcher rd = req.getRequestDispatcher("assignBoardDetail.jsp");
    		rd.forward(req, resp);
    	} else if(uri.equals("assignBoardAdd")) {
    		// �űԵ�� �� �����Խ��� Ȩ����...
    		RequestDispatcher rd = req.getRequestDispatcher("assignBoardMain.jsp");
    	} else {
    		System.out.println("error");
    	}
		
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	System.out.println("assignBoard_Post");
    	String requestUri = req.getRequestURI();	// ��û URI ����
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// ��û URI ���� ����
    	System.out.println(uri);
    	
    	if(uri.equals("assignBoardDetail")) {
    		resp.sendRedirect(req.getContextPath()+"/lms/community/assignBoardDetail.jsp");
    	} else {
    		System.out.println("error");
    	}

    }

}
