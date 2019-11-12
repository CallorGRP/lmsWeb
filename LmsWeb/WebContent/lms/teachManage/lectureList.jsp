<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, bit.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프</title>
<%
	ArrayList list = new ArrayList();
	list = (ArrayList)request.getAttribute("list");
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
	<%@ include file="../../template/lms_header.jspf" %>
	
		<div id="sector" class="row" >
			<div id="lecture" class="grid02">수업관리 >
				<span>개설강좌현황</span>
			</div>
		</div>
		
		<!-- 
		<div class="row">
			<div class="grid08">&nbsp;</div>
			<div id="btn" class="grid02">
				<form>
					<button type="submit" formaction="lectureList.bit">조회</button>
					<button type="submit" formaction="lectureAdd.bit">신규</button>
				</form>
			</div>
		</div>
		 -->
		 
		<div class="row">
			<div class="grid01">&nbsp;</div>
			<div class="grid08">
			<h1>개설강좌현황</h1>

			<table>
				<thead>
					<tr>
						<div align="right">
						<form>
							<button type="submit" formaction="lectureList.bit">조회</button>
							
							<%
                            if(((String)session.getAttribute("id")).substring(0,2).equals("EM")) {
                            %>
							<button type="submit" formaction="lectureAdd.bit">신규</button>
							<%
                            }
							%>
							
						</form>
						</div>
						<p>
						<th>No</th>
						<th>교육강좌코드</th>
						<th>교육강좌명</th>
						<th>기간</th>
						<th>장소</th>
						<th>강사</th>
						<th>등록일자</th>
					</tr>
				</thead>
				<tbody>
			<%
				for(int i=0;i<list.size();i++){
					LectureDto bean= new LectureDto();
					bean=(LectureDto)list.get(i);
			%>
				<tr>
					<td><%=bean.getRownum() %></td>
					<td><%=bean.getCode()%></td>
					<td><a href="lectureDetail.bit?code=<%=bean.getCode()%>"><%=bean.getLectureName()%></a></td>
					<td><%=bean.getDepartday()%>~<%=bean.getEndday()%></td>
					<td><%=bean.getRoom()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getRegday()%></td>
				</tr>
			<%
				}
			%>
				</tbody>
			</table>
			
			<br><br>
			</div>
			<div class="grid01">&nbsp;</div>
		</div>
	
	<%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>