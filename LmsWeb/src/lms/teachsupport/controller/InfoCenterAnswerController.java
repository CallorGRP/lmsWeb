package lms.teachsupport.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.model.BbsDao;
import bit.model.HumanDao;

public class InfoCenterAnswerController extends HttpServlet {
	final private String user		=	"qwerty930704";
	final private String password	=	"Arss176974!";

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void sendMail(String email, String content) {
		String host				=	"smtp.naver.com";
		String to = email;
		
		Properties props = new Properties();
		props.put("mail.smtp.host",host);
		props.put("mail.smtp.auth","true");
		
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(getUser(),getPassword());
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(getUser()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject("BitCamp 안양점에 문의하신 내용에 대한 답변입니다.");
			message.setText(content);
			
			Transport.send(message);
			System.out.println("send successfully");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int num=Integer.parseInt(req.getParameter("num"));
		String content = req.getParameter("subboardContent");
		
		HumanDao hDao = new HumanDao();
		BbsDao bDao = new BbsDao();
		
		String email=hDao.getEmail(num);
		this.sendMail(email,content);
		bDao.subNumEdit("QE", num, 0);
		
		resp.sendRedirect("infoCenterList.bit");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("??");
	}
}
