<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bit.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프</title>
<%@ include file="../../template/lms_style.jspf" %>
<style type="text/css">
	table{
		
	}
	table th{
		background-color: rgb(4,72,121);
		color: white;
		border-bottom: 1px solid rgb(4,72,121);
	}
	table td{
		border-bottom: 1px solid rgb(4,72,121);
		text-align: center;
	}

</style>
</head>
<body>
<form>
	<%@ include file="../../template/lms_header.jspf" %>
	
		<div id="sector" class="row" >
			<div id="lecture" class="grid02">수업관리 >
				<span>신청 상세보기</span>
			</div>
		</div>
		
		<div class="row">
			<div class="grid05">&nbsp;</div>
			<div id="btn" class="grid04">
				
					<button type="submit" formmethod="post" formaction="joinDetail.bit">승인</button>
				
			</div>
		</div>
		
		<div class="row">
			<div class="grid01">&nbsp;</div>
			<div class="grid08">
				<%
					String id = request.getParameter("id");
					HumanDto bean = new HumanDto();
					HumanDao dao = new HumanDao();
					bean=dao.joinStudentDetail(id);
					
				%>
				<h2>학생 상세정보</h2>
				<table>
					<tr>
						<th>이름</th>
						<td>&nbsp;<%=bean.getName() %></td>
					</tr>
					<tr>
						<th>아이디</th>
						<td>&nbsp;<%=bean.getId() %></td>
						<td><input type="hidden" value="<%=bean.getId()%>" name="id"></td>
					</tr>
					<tr>
						<th>신청강의</th>
						<td>&nbsp;<%=bean.getLecture() %></td>
					</tr>
					<tr>
						<th>신청날짜</th>
						<td>&nbsp;<%=bean.getRecruit() %></td>
					</tr>
					<tr>
						<th>생일</th>
						<td>&nbsp;<%=bean.getBirth() %></td>
					</tr>
					<tr>
						<th>성별</th>
						<%
						String gender="남";
						if(bean.getGender()==1){
							gender="남";
						}else if(bean.getGender()==2){
							gender="여";
						}
						%>
						<td>&nbsp;<%=gender%></td>
					</tr>
					<tr>
						<th>주소</th>
						<td>&nbsp;<%=bean.getAddress() %></td>
					</tr>
					<tr>
						<th>연락처</th>
						<td>&nbsp;<%=bean.getPhone() %></td>
					</tr>
					<tr>
						<th>e-Mail</th>
						<td>&nbsp;<%=bean.getEmail() %></td>
						<td><input name="email" type="hidden" value="<%=bean.getEmail() %>" ></td>
					</tr>
					<tr>
						<th>상태</th>
						<%
						String interview=null;
						if(bean.getInterview()==0){
							interview="승인 대기 중";
						}else if(bean.getInterview()==3){
							interview="승인";
						}
						%>
						<td>&nbsp;<%=interview %></td>
					</tr>
					
				</table>
			</div>
			<div class="grid01">&nbsp;</div>
		</div>
	
	<%@ include file="../../template/lms_footer.jspf" %>
	</form>
</body>
</html>