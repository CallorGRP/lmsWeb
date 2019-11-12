<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, bit.model.*, lms.login.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프</title>
<%

	ArrayList list1 = new ArrayList();		// 프로필사항
	ArrayList list2 = new ArrayList();		// 과제게시판
	ArrayList list3 = new ArrayList();		// 공지사항
	list1 = (ArrayList)request.getAttribute("list1");
	list2 = (ArrayList)request.getAttribute("list2");
	list3 = (ArrayList)request.getAttribute("list3");
%>
<%@ include file="../../template/lms_style.jspf" %>
<style type="text/css">
table{
		width:800px;
		border-collapse: collapse;
		border-bottom: 1px solid rgb(4,72,121);
		border-right: 1px solid rgb(4,72,121);
	}
	table th{
		background-color: rgb(4,72,121);
		border-bottom: 1px solid rgb(4,72,121);
		color: white;
	}
	table td{
	text-align:center;
	border-right: 1px solid rgb(4,72,121);
	border-left:  1px solid rgb(4,72,121);
	border-top:  1px solid rgb(4,72,121);
	}
	
	table td>a{
		color:gray;
		text-decoration: none;
	}
	table td>a:HOVER {
		color:rgb(4,72,121);
	}
</style>
</head>
<body>
	<%@ include file="../../template/lms_header.jspf" %>
	
		<div id="sector" class="row" >
			<div id="lecture" class="grid02">My서비스 >
				<span>홈</span>
			</div>
		</div>
		
		<div class="row">
			<div class="grid07">&nbsp;</div>
			<!-- 
			<div id="btn" class="grid03">
				<form>
					<button type="submit" formaction="myInfoMain.bit">조회</button>
				</form>
			</div>
			 -->
		</div>
		
		<div class="row">
			<div class="grid01">&nbsp;</div>
			<div class="grid08">
			<%
				HumanDto bean1 = new HumanDto();
				bean1 = (HumanDto)list1.get(0);
			 %>
			<h1>반갑습니다 <%=bean1.getName() %>님!</h1>
			<table>
				<tr>
					<div align="right">
						<form>
							<button type="submit" formaction="myInfoMain.bit">조회</button>
						</form>
					</div>
					<p>
					<td rowspan="6" colspan="4" width="90" height="60" align="center">
						<% if(bean1.getGender()==1) { %>
							<img src="/home2/imgs/man.jpg" height="50">
						<% } else { %>
							<img src="/home2/imgs/woman.jpg" height="50">
						<% } %>	
					</td>
				</tr>
				<tr>
					<th colspan="2">아이디</th>
					<td colspan="2"><%=bean1.getCode() %><%=bean1.getId() %></td>
				</tr>
				<tr>
					<th colspan="2">이름</th>
					<td colspan="2"><%=bean1.getName() %></td>
				</tr>
				<tr>
					<th colspan="2">내강좌코드</th>
					<td colspan="2"><%=bean1.getLecture() %></td>
				</tr>
				<tr>
					<th colspan="2">내강좌명</th>
					<td colspan="2"><%=bean1.getLectureName() %></td>
				</tr>
				<tr>
					<th colspan="2">수료예정일</th>
					<td colspan="2"><%=bean1.getEndday() %></td>
				</tr>
			</table>
			<p>
			<h1>과제게시판</h1>
			<table>
				<tr>
					<th>교육강좌명</th>
					<th>제목</th>
					<th>기간</th>
					<th>작성자</th>
					<th>등록일자</th>
				</tr>
				<%
				for(int i=0;i<list2.size();i++){
					HumanDto bean2 = new HumanDto();
					bean2 = (HumanDto)list2.get(i);
				 %>
				<tr>
					<td><%=bean2.getCode() %></td>
					<td><%=bean2.getLectureName() %></td>
					<td><%=bean2.getDuedate() %></td>
					<td><%=bean2.getName() %></td>
					<td><%=bean2.getTime() %></td>
				</tr>
				<%} %>
			</table>
			<p>
			<h1>공지사항</h1>
			<table>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일자</th>
				</tr>
				<%
				for(int i=0;i<list3.size();i++){
					HumanDto bean3 = new HumanDto();
					bean3 = (HumanDto)list3.get(i);
				%>
				<tr>
					<td><%=bean3.getRownum() %></td>
					<td><%=bean3.getSub() %></td>
					<td><%=bean3.getName() %></td>
					<td><%=bean3.getTime() %></td>
				</tr>
				<%} %>
			</table>
			</div>
			<div class="grid01">&nbsp;</div>
		</div>
	
	<%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>