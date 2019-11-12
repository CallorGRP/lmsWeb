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
 * 홈페이지 LMS 컨트롤러
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
    		req.getSession().setAttribute("login", true);//세션을 true로주고
    		req.setAttribute("SESS", ldto);				 //어트리뷰트에 아이디값을 준다.
    		RequestDispatcher rd= req.getRequestDispatcher("/lms/myinfo/myInfoMain.bit");
    		rd.forward(req, resp);
    	}else {
    		req.setAttribute("loginFail", "<h1>로그인에 실패하셨습니다.</h1>");
    		req.getSession().setAttribute("login", false);
    		RequestDispatcher rd= req.getRequestDispatcher("/login.jsp");
    		rd.forward(req, resp);
    	}
    	*/
    	
    	/*황태연11111*/ 
    	String requestUri = req.getRequestURI();	// 요청 URI 저장
    	String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf(".bit"));	// 요청 URI 패턴 저장
    	
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
    	
		/*황태연11111*/
    		
		/*황태연22222*/ 
    		/* 로그인 시도시... DB쪽 접근하여 로그인 성공/실패 결과에 따라... */
    		/*
    		int result = 1;
    		if(result == 1) {
    			// 로그인 성공시 MyInfoMain 호출
    			System.out.println("success");
    			// myInfoMain에 보여줄 정보DB 조회 후 리턴
    			
    			resp.sendRedirect(req.getContextPath()+"/lms/myinfo/myInfoMain.bit");
    		} else {
    			// 로그인 실패시 현재 페이지에 실패내용 출력
    			System.out.println("fail");
    			resp.sendRedirect(req.getContextPath()+"/loginResult.bit");
    		}
    	} else {
    		System.out.println("error");
    	}
    	*/
    	/*황태연22222*/ 
    }

}
