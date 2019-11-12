<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, bit.model.*"%>
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
				<span>FAQ 수정</span>
			</div>
		</div>
		<form method="post">
		<div class="row">
			<div class="grid06">&nbsp;</div>
			<div id="btn" class="grid04">
				
					<button type="submit" formaction="faqEdit.bit">저장</button>
					<!-- 수정 페이지에서 삭제할 필요는 없음. <button type="submit" formaction="#">삭제</button> -->
					<input type="reset" value="취소" name="reset" />
				
			</div>
		</div>
	<%
		request.setCharacterEncoding("UTF-8");
		BbsDto bean= new BbsDto();
		BbsDao dao = new BbsDao();
		bean=(BbsDto)session.getAttribute("faqDetail");
		session.setAttribute("faqDetail", null);
		session.setAttribute("faqDetail", bean);
	%>
		<div class="row">
			<div class="grid01">&nbsp;</div>
			<div class="grid08">
				<table>
					<thead>
					</thead>
					<tbody>
						<tr>
							<td><label>글번호 :<%=bean.getNum()%></label></td>
						</tr>
						<tr>
							<td><label>작성자ID<%=bean.getHcode()+bean.getId()%></label></td>
						</tr>
						<tr>
							<td><label>글쓴이 :<%=dao.getWriterName(bean.getHcode(), bean.getId())%></label></td>
						</tr>
						<tr>
							<td><label>작성일 :<%=bean.getTime()%></label></td>
						</tr>
						<tr>
							<td><label>작성시간:<%=bean.getTimeDetail()%></label></td>
						</tr>
						<tr>
							<td><input type="text" name="sub" value="<%=bean.getSub()%>"/></td>
						</tr>
						<tr>
							<td><input type="text" name="content" value="<%=bean.getContent()%>"/></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="grid01">&nbsp;</div>
		</div>
		</form>
	<%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>