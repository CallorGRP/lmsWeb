package lms.teachsupport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bit.model.BbsDto;
import bit.model.SubboardDao;

public class SubboardAddController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("zjadhs?");
		req.setCharacterEncoding("UTF-8");
		SubboardDao sDao = new SubboardDao();
		BbsDto bDto = new BbsDto();
		
		HttpSession hs= req.getSession();
		bDto = (BbsDto)hs.getAttribute("detail");
		System.out.println(bDto);
		hs.setAttribute("detail", null);
		String fullID=(String)hs.getAttribute("id");
		
		String hCode=fullID.substring(0, 2);
		String id=fullID.substring(2, 13);
		String content=req.getParameter("subboardContent");

		System.out.println(bDto.getCode());
		System.out.println(bDto.getNum());
		System.out.println(bDto.getSubnum());
		System.out.println(hCode);
		System.out.println(id);
		System.out.println(content);
		
		sDao.add(bDto.getCode(), bDto.getNum(), bDto.getSubnum(), hCode, id, content);
		
		if(bDto.getCode().equals("NO")) {
			resp.sendRedirect("/home2/lms/teachSupport/noticeDetail.bit?code="+bDto.getCode()+"&num="+bDto.getNum()+"&subnum="+bDto.getSubnum()+"");
		}else if(bDto.getCode().equals("AQ")) {
			resp.sendRedirect("/home2/lms/teachSupport/faqDetail.bit?code="+bDto.getCode()+"&num="+bDto.getNum()+"&subnum="+bDto.getSubnum()+"");
		}else if(bDto.getCode().equals("UG")) {
			resp.sendRedirect("/home2/lms/teachSupport/useGuideDetail.bit?code="+bDto.getCode()+"&num="+bDto.getNum()+"&subnum="+bDto.getSubnum()+"");
		}else if(bDto.getCode().equals("QE")) {
			resp.sendRedirect("/home2/lms/teachSupport/infoCenterDetail.bit?code="+bDto.getCode()+"&num="+bDto.getNum()+"&subnum="+bDto.getSubnum()+"");
		}
	}

}
