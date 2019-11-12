<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, bit.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>안양캠프-교육과정</title>
<%@ include file="../template/style.jspf" %>

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
		border-top: 1px solid rgb(4,72,121);
		border-left: 1px solid rgb(4,72,121);
		border-right: 1px solid rgb(4,72,121);
		color: white;
	}
	table td{
	text-align:center;
	border-right: 1px solid rgb(4,72,121);
	border-left:  1px solid rgb(4,72,121);
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
<%@ include file="../template/header.jspf" %>

	<div id="content" class="row">
		<div class="grid02">&nbsp;</div>
		<div class="grid09">
			<h2>교육과정</h2>
			
				
				
				<table>
					<tr>
						<th>교육강좌코드</th>
						<th>교육강좌명</th>
						<th>기간</th>
						<th>장소</th>
						<th>강사</th>
					</tr>
					<%
					
					ArrayList<LectureDto> list=(ArrayList<LectureDto>)request.getAttribute("list");
					LectureDto bean = new LectureDto();
					
					for(int i=0; i<list.size(); i++){
						bean = list.get(i);
					
					%>
					<tr>
						<td><a href="curriculumDetail.jsp?code=<%=bean.getCode()%>"><%=bean.getLectureName()%></a></td>
						<td><%=bean.getCode() %></td>
						<td><%=bean.getDepartday() %>~<%=bean.getEndday() %></td>
						<td><%=bean.getRoom() %></td>
						<td><%=bean.getName() %></td>
					</tr>
					<%
					}
					%>
				</table>
		</div>
		<div class="grid01">&nbsp;</div>
	</div>
	<div class="row">
		<div class="grid02">&nbsp;</div>
	</div>

<%@ include file="../template/footer.jspf" %>
</body>
</html>