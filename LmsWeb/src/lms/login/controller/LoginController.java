package lms.login.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lms.login.model.LoginDao;
import lms.login.model.LoginDto;

/**
 * Ȩ������ LMS ��Ʈ�ѷ�
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	System.out.println("login_Get");
    	RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
		rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	System.out.println("login_Post");
    	/*
    	LoginDto ldto = new LoginDto();
    	LoginDao ld = new LoginDao();
    	
    	req.getSession().setAttribute("login", false);
    	
    	ldto.setLoginId(req.getParameter("id"));
    	ldto.setLoginPw(req.getParameter("pw"));
    	if(ld.login(ldto)) {
    		req.getSession().setAttribute("login", true);//������ true���ְ�
    		req.setAttribute("SESS", ldto);				 //��Ʈ����Ʈ�� ���̵��� �ش�.
    		RequestDispatcher rd= req.getRequestDispatcher("/lms/myinfo/myInfoMain.bit");
    		rd.forward(req, resp);
    	}else {
    		req.setAttribute("loginFail", "<h1>�α��ο� �����ϼ̽��ϴ�.</h1>");
    		req.getSession().setAttribute("login", false);
    		RequestDispatcher rd= req.getRequestDispatcher("/login.jsp");
    		rd.forward(req, resp);
    	}
    	*/
    	
    	/*Ȳ�¿�11111*/ 
    	String requestUri = req.getRequestURI();	// ��û URI ����
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// ��û URI ���� ����
    	
    	LoginDao dao = new LoginDao();
    	LoginDto dto = new LoginDto();
    	
    	if(uri.equals("loginResult")) {
    		
    		ArrayList<LoginDto> list = new ArrayList<LoginDto>();
    		list = dao.daoLoginConn(req.getParameter("id"), req.getParameter("pw"));
    		RequestDispatcher rd = req.getRequestDispatcher("loginResult.jsp");
    		req.setAttribute("list", list);
    		rd.forward(req, resp);
    	} else {
    		System.out.println("error");
    	}
    	
		/*Ȳ�¿�11111*/
    		
		/*Ȳ�¿�22222*/ 
    		/* �α��� �õ���... DB�� �����Ͽ� �α��� ����/���� ����� ����... */
    		/*
    		int result = 1;
    		if(result == 1) {
    			// �α��� ������ MyInfoMain ȣ��
    			System.out.println("success");
    			// myInfoMain�� ������ ����DB ��ȸ �� ����
    			
    			resp.sendRedirect(req.getContextPath()+"/lms/myinfo/myInfoMain.bit");
    		} else {
    			// �α��� ���н� ���� �������� ���г��� ���
    			System.out.println("fail");
    			resp.sendRedirect(req.getContextPath()+"/loginResult.bit");
    		}
    	} else {
    		System.out.println("error");
    	}
    	*/
    	/*Ȳ�¿�22222*/ 
    }

}
