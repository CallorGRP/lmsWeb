package lms.teachmanage.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import bit.model.HumanDao;
import bit.model.HumanDto;

/**
 * 
 */
public class JoinController extends HttpServlet {
	
	int idx=0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if(req.getParameter("page")!=null){
		String getIdx=req.getParameter("page");
		this.idx=Integer.parseInt(getIdx);
		}
		
		
		int search = 0;
		String searchP="0";
		int p=0;
		if(req.getParameter("search")==null){
			searchP="0";
		}else{
			searchP = req.getParameter("search");
		}
		
		search = Integer.parseInt(searchP);
		HumanDao dao = new HumanDao();
		ArrayList<HumanDto> searchList = new ArrayList<HumanDto>();
		searchList=dao.getJoinStudentList(search);
		try {
			p=dao.getPage(search);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("p", p);
		req.setAttribute("search", searchList);
		
		RequestDispatcher rd = req.getRequestDispatcher("joinList.jsp");
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		int page=this.idx;
		
		HumanDao dao=new HumanDao();
		
		ArrayList<String> list=new ArrayList<String>();
		ArrayList<String> list1=new ArrayList<String>();
		String[] list2=null;
		
		String id=null;
		String email=null;
		String param=null;
		
		int start = 1;
		if(page!=0){
			start=(page-1)*10;
		}
		
		/* page=0 start=1
		 * page=1 start=1
		 * page=2 start=10
		 * page=3 start=20
		*/
		int end = start+11;  
		
		for(int i=start; i<end; i++){
			if(req.getParameter("check"+i)==null){
				String bin = "555";
				list.add(bin);
				list1.add(bin);
			}else{
				param = req.getParameter("check"+i);
				list2=param.split("&");
				list2[1].replace("%40", "@");
				
				list.add(list2[0]);
				list1.add(list2[1]);
			}
		}
		for(int i=0; i<list.size(); i++){
			id=list.get(i);
			email=list1.get(i);
			if(id=="555" && email=="555"){
			}else{
				dao.studentConfirm(id);
				dao.sendMail(email);
			}
		}
		
		resp.sendRedirect(req.getContextPath()+"/lms/teachManage/joinList.bit");
	}

}
