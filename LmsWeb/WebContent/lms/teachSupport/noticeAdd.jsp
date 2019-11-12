<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bit.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프</title>
<%@ include file="../../template/lms_style.jspf" %>
<style type="text/css">
</style>
</head>
<body>
	<%@ include file="../../template/lms_header.jspf" %>
	
		<div id="sector" class="row" >
			<div id="lecture" class="grid02">수업지원 >
				<span>공지사항 추가</span>
			</div>
		</div>
		
		<div class="row">
			<div class="grid06">&nbsp;</div>
			<div id="btn" class="grid04">
				<form>
					<button type="submit" formaction="#">저장</button>
					<input type="reset" value="취소" name="reset" />
				</form>
			</div>
		</div>
		
		<div class="row">
			<div class="grid01">&nbsp;</div>
			<%
				BbsDto bean= new BbsDto();
				BbsDao dao = new BbsDao();
				String writer=(String)session.getAttribute("name");
				String hcode=((String)session.getAttribute("id")).substring(0, 2);
				String id=((String)session.getAttribute("id")).substring(2,13);
				System.out.println("hcode"+hcode);
				System.out.println("id"+id);
				bean.setHcode(hcode);
				bean.setId(id);
				request.getSession().setAttribute("NoticeAdd", bean);
			%>
			<div class="grid08">
				<form method="post">
					<table>
						<thead>
						</thead>
						<tbody>
							<tr>
								<td>글쓴이 :<%=writer %></td>
							</tr>
							<tr>
								<td>제목 :<input type="text" name="sub"/></td>
							</tr>
							<tr>
								<td>내용 :<input type="text" name="content"/></td>
							</tr>
							<tr>
								<td><button type="submit" formaction="noticeAdd.bit">등록</button></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<%
			%>
			<div class="grid01">&nbsp;</div>
		</div>
	
	<%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>