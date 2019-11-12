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
    	String requestUri = req.getRequestURI();	// 요청 URI 저장
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// 요청 URI 패턴 저장
    	
    	HumanDao dao = new HumanDao();
    	HumanDto bean = new HumanDto();
    	
    	String id = (String) (req.getSession().getAttribute("id"));
    	//System.out.println(req.getSession().getAttribute("pw"));
		

    	/* jsp 에서 root id 받아야함.. 임시로 id 명시적 입력... */
    	
    	if(uri.equals("myInfoMain")) {
    		// 회원정보 홈 기본조회
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
    		// 회원정보변경 기본조회
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoEditList(id);
    		RequestDispatcher rd = req.getRequestDispatcher("myInfoEdit.jsp");
    		req.setAttribute("list", list);
    		rd.forward(req, resp);
    	} else if(uri.equals("myInfoPwEdit")) {
    		// 비밀번호변경 기본조회
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoPwEditList(id);
    		RequestDispatcher rd = req.getRequestDispatcher("myInfoPwEdit.jsp");
    		req.setAttribute("list", list);
    		rd.forward(req, resp);
    	} else if(uri.equals("myInfoJoinOut")) {
    		// 탈퇴 기본조회
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoJoinList(id);
    		RequestDispatcher rd = req.getRequestDispatcher("myInfoJoinOut.jsp");
    		req.setAttribute("list", list);
    		rd.forward(req, resp);
    	} else if(uri.equals("myInfoPrtComplete")) {
    		// 수료증 조회
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
    	String requestUri = req.getRequestURI();	// 요청 URI 저장
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// 요청 URI 패턴 저장
    	System.out.println(uri);
    	HumanDao dao = new HumanDao();
    	HumanDto bean = new HumanDto();
    	
    	if(uri.equals("myInfoEdit")) {
    		// 회원정보변경 저장
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoEditSave(req.getParameter("id"), req.getParameter("pw2"), req.getParameter("name"), req.getParameter("birth"), req.getParameter("gender")
    								,req.getParameter("address"), req.getParameter("phone"), req.getParameter("email"));
    		resp.sendRedirect(req.getContextPath()+"/lms/myinfo/myInfoMain.bit");
    	} else if(uri.equals("myInfoPwEdit")) {
    		// 비밀번호변경 저장
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoPwSave(req.getParameter("id"), req.getParameter("pw2"), req.getParameter("pwre2"));
    		resp.sendRedirect(req.getContextPath()+"/lms/myinfo/myInfoMain.bit");
    	} else if(uri.equals("myInfoJoinOut")) {
    		// 탈퇴 
    		ArrayList<HumanDto> list = new ArrayList<HumanDto>();
    		list = dao.myInfoJoinOutDelete(req.getParameter("id"), req.getParameter("pw2"));
    		resp.sendRedirect(req.getContextPath()+"/");
    	} else {
    		System.out.println("error");
    	}
    	
    }
}
