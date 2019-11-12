package lms.myinfo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.model.HumanDao;
import bit.model.HumanDto;

/**
 * 
 */
public class MyInfoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	System.out.println("myInfo_Get");
    	String requestUri = req.getRequestURI();	// ��û URI ����
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// ��û URI ���� ����
    	
    	HumanDao dao = new HumanDao();
    	HumanDto bean = new HumanDto();
    	
    	String id = (String) (req.getSession().getAttribute("id"));
    	//System.out.println(req.getSession().getAttribute("pw"));
		

    	/* jsp ���� root id �޾ƾ���.. �ӽ÷� id ����� �Է�... */
    	
    	if(uri.equals("myInfoMain")) {
    		// ȸ������ Ȩ �⺻��ȸ
    		ArrayList<HumanDto> list1 = new ArrayList<HumanDto>();
        	ArrayList<HumanDto> list2 = new ArrayList<HumanDto>();
        	ArrayList<HumanDto> list3 = new ArrayList<HumanDto>();
    		list1 = dao.myInfo1List(id);
    		list2 = dao.myInfo2List(id);
    		list3 = dao.myInfo3List(id);
    		RequestDispatcher rd = req.getRequestDispatcher("myInfoMain.jsp");
    		req.setAttribute("list1", list1);
    		req.setAttribute("list2", list2);
    		req.setAttribute("list3", list3);
    		rd.forward(req, resp);
    	} else if(uri.equals("myInfoEdit")) {
    		// ȸ���������� �⺻��ȸ
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoEditList(id);
    		RequestDispatcher rd = req.getRequestDispatcher("myInfoEdit.jsp");
    		req.setAttribute("list", list);
    		rd.forward(req, resp);
    	} else if(uri.equals("myInfoPwEdit")) {
    		// ��й�ȣ���� �⺻��ȸ
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoPwEditList(id);
    		RequestDispatcher rd = req.getRequestDispatcher("myInfoPwEdit.jsp");
    		req.setAttribute("list", list);
    		rd.forward(req, resp);
    	} else if(uri.equals("myInfoJoinOut")) {
    		// Ż�� �⺻��ȸ
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoJoinList(id);
    		RequestDispatcher rd = req.getRequestDispatcher("myInfoJoinOut.jsp");
    		req.setAttribute("list", list);
    		rd.forward(req, resp);
    	} else if(uri.equals("myInfoPrtComplete")) {
    		// ������ ��ȸ
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoPrtCompleteList(id);
    		req.setAttribute("list", list);
    		RequestDispatcher rd = req.getRequestDispatcher("myInfoPrtComplete.jsp");
    		rd.forward(req, resp);
    	}
    	else {
    		System.out.println("error");
    	}
		
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	System.out.println("myInfo_Post");
    	String requestUri = req.getRequestURI();	// ��û URI ����
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// ��û URI ���� ����
    	System.out.println(uri);
    	HumanDao dao = new HumanDao();
    	HumanDto bean = new HumanDto();
    	
    	if(uri.equals("myInfoEdit")) {
    		// ȸ���������� ����
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoEditSave(req.getParameter("id"), req.getParameter("pw2"), req.getParameter("name"), req.getParameter("birth"), req.getParameter("gender")
    								,req.getParameter("address"), req.getParameter("phone"), req.getParameter("email"));
    		resp.sendRedirect(req.getContextPath()+"/lms/myinfo/myInfoMain.bit");
    	} else if(uri.equals("myInfoPwEdit")) {
    		// ��й�ȣ���� ����
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoPwSave(req.getParameter("id"), req.getParameter("pw2"), req.getParameter("pwre2"));
    		resp.sendRedirect(req.getContextPath()+"/lms/myinfo/myInfoMain.bit");
    	} else if(uri.equals("myInfoJoinOut")) {
    		// Ż�� 
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoJoinOutDelete(req.getParameter("id"), req.getParameter("pw2"));
    		resp.sendRedirect(req.getContextPath()+"/");
    	} else {
    		System.out.println("error");
    	}
    	
    }
}
