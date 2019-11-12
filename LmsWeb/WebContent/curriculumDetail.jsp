<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bit.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>안양캠프-교육과정</title>
<%@ include file="../template/style.jspf" %>

<style type="text/css">
	table th{
		background-color: rgb(4,72,121);
		color: white;
		border-bottom: 1px solid rgb(4,72,121);
	}
	table td{
		border-bottom: 1px solid rgb(4,72,121);
		text-align: center;
	}
	#sub{
		margin-left: 100px;
	}

</style>

</head>
<body>
<%@ include file="../template/header.jspf" %>
<%
	String code = request.getParameter("code");
	LectureDto bean = new LectureDto();
	LectureDao dao = new LectureDao();
	bean=dao.detail(code);
	
%>
	<div id="content" class="row">
		<div class="grid05">&nbsp;</div>
		<div class="grid06">
			<h2>교육과정 상세정보</h2>
			<form method="get" action="curriculumJoin.jsp">
				<table>
					<tr>
						<tr>
						<th>제목</th><td>&nbsp;<%=bean.getLectureName() %></td>
						<td>
						<input name="lecture_name" type="hidden" value="<%=bean.getLectureName()%>">
						</td>
					</tr>
					<tr>
						<th>내용</th><td>&nbsp;<%=bean.getContent() %></td>
						<td>
						<input name="content" type="hidden" value="<%=bean.getContent()%>">
						</td>
					</tr>
				<tr>
					<th>교육기간</th>
					<td>&nbsp;<%=bean.getDepartday()%> ~ <%=bean.getEndday() %></td>
						<td>
						<input name="departday" type="hidden" value="<%=bean.getDepartday()%>">
						</td>
						<td>
						<input name="endday" type="hidden" value="<%=bean.getEndday()%>">
						</td>
				</tr>
				<tr>
					<th>교육장소</th>
					<td>&nbsp;<%=bean.getRoom() %></td>
					<td>
						<input name="room" type="hidden" value="<%=bean.getRoom()%>">
					</td>
				</tr>
				<tr>
					<th>교육정원</th>
					<td>&nbsp;<%=bean.getListener() %></td>
					<td>
						<input name="listener" type="hidden" value="<%=bean.getListener()%>">
					</td>
				</tr>
				<tr>
					<th>문의전화</th>
					<td>&nbsp;02-3486-9600</td>
				</tr>
					
				</table>
				<br>
						<input id="sub" type="submit" value="교육과정 신청">
			</form>
		</div>
		<div class="grid01">&nbsp;</div>
	</div>
	<div class="row">
		<div class="grid01">&nbsp;</div>
	</div>

<%@ include file="../template/footer.jspf" %>
</body>
</html>