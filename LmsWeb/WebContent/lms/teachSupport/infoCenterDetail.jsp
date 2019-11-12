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
				<span>문의사항 상세</span>
			</div>
		</div>
		
		<div class="row">
			<div class="grid06">&nbsp;</div>
			<div id="btn" class="grid04">
				<form method="post">
					<!-- <button type="submit" formaction="noticeEdit.jsp">수정</button> -->
					<!-- <button type="submit" formaction="noticeDelete.bit">삭제</button> -->
					<!-- <input type="reset" value="취소" name="reset" /> -->
				</form>
			</div>
		</div>
		
		<div class="row">
			<div class="grid01">&nbsp;</div>
	<%
	request.setCharacterEncoding("UTF-8");
	BbsDao bd= new BbsDao();
	BbsDto bean= new BbsDto();

	String code=(String)request.getParameter("code");
	int num= Integer.parseInt((String)request.getParameter("num"));
	int subnum= Integer.parseInt((String)request.getParameter("subnum"));
	
	bean=bd.detail(code,num, subnum);
	session.setAttribute("detail", bean);
	%>
	<div class="grid08">
		<p>글번호  :<%=bean.getNum() %></p>
		<p>글쓴이  :<%=bd.getWriterName(bean.getHcode(), bean.getId())%></p>
		<p>작성일  :<%=bean.getTime() %></p>
		<p>작성시간:<%=bean.getTimeDetail() %></p>
		<p>제 목   :<%=bean.getSub() %></p>
		<p>내 용   :<%=bean.getContent() %></p>
		<p></p>
	</div>
	<form method="post" action="/home2/lms/teachSupport/infoCenterAnswer.bit">
		<div class="grid08">
			<label>답변 등록</label>
			<input type="hidden" name="num" value="<%=bean.getNum() %>"/>
			<textarea rows="10" cols="40" name="subboardContent"></textarea><button type="submit">등록</button>
		</div>
	</form>
	<div class="grid01">&nbsp;</div>
		</div>
	
	<%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>