<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, bit.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프</title>
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList list = new ArrayList();		
	list = (ArrayList)request.getAttribute("list");
	
	HumanDto bean = new HumanDto();
	bean = (HumanDto)list.get(0);
%>
<%@ include file="../../template/lms_style.jspf" %>
<script type="text/javascript">
	function print() {
		var strFeature = "";
		
		var year = (endday.value).substring(0,4);
		var month = (endday.value).substring(5,7);
		var day = (endday.value).substring(8,10);
		
		strFeature += "width=800, height=600, all=no";
		
		var objWin = window.open('', 'print', strFeature);		
		objWin.document.write("<p><br></p>");
		objWin.document.write("<p><br></p>");
		objWin.document.write("<p><br></p>");
		objWin.document.write("<table width='90%' height:'100%' border='0' cellspacing='0' align='center'>");
		objWin.document.write("<h1 align='center'>수  료  증</h1>");
		objWin.document.write("<h1 align='center'>Certificate of Completion</h1>");
		objWin.document.write("<p><br></p>");
		objWin.document.write("<tr>");
		objWin.document.write("<td width='370' align='right'><h3>성   명 : </h3></td>");
		objWin.document.write("<td>");
		objWin.document.write("<h3> "+name1.value+" </h3>");
		objWin.document.write("</td>");
		objWin.document.write("</tr>");
		objWin.document.write("<tr>");
		objWin.document.write("<td width='370' align='right'><h3>교육명 : </h3></td>");
		objWin.document.write("<td>");
		objWin.document.write("<h3> "+lectureName.value+" </h3>");
		objWin.document.write("</td>");
		objWin.document.write("</tr>");
		objWin.document.write("<tr>");
		objWin.document.write("<td width='370' align='right'><h3>기   수 : </h2></td>");
		objWin.document.write("<td>");
		objWin.document.write("<h3> "+lecture1.value+" </h3>");
		objWin.document.write("</td>");
		objWin.document.write("</tr>");
		objWin.document.write("<tr><td><br></td><td><br></td></tr>");
		objWin.document.write("<tr><td><br></td><td><br></td></tr>");
		objWin.document.write("<p><br></p>");
		objWin.document.write("</table>");
		objWin.document.write("<h1>위 사람은 "+year+"년도 비트캠프에서 진행하는 교육을 성실하게 수료하였기에 이 수료증을 수여함.</h1>");
		objWin.document.write("<p><br></p>");
		objWin.document.write("<h2 align='center'>" +year+"년 "+ month+"월 "+ day+"일</h2>");
		objWin.document.write("<p><br></p>");
		objWin.document.write("<h1 align='center'>비 트 캠 프 강 남 센 터 장 </h1>");
		

		objWin.document.close();

		objWin.print();
		objWin.close();

	}
</script>
<style type="text/css">
	table{
		width:500px;
	}
	table th{
		background-color: rgb(4,72,121);
		color: white;
	}

</style>
</head>
<body>
	<%@ include file="../../template/lms_header.jspf" %>
	<form method="post" name="myInfoPrtComplete" id= "myInfoPrtComplete" enctype="utf-8">
		<div id="sector" class="row" >
			<div id="lecture" class="grid02">My서비스 >
				<span>수료증</span>
			</div>
		</div>
		
		<div class="row">
			<div class="grid07">&nbsp;</div>
			<!-- 
			<div id="btn" class="grid03">
				<%
               		String path = request.getContextPath();
            	%>
               <input type="button" value="프린트" onClick="print()">
			</div>
			 -->
		</div>
		
		<div class="row">
			<div class="grid01">&nbsp;</div>
			<div class="grid08">
			<%
				
			 %>
			<h1>수료증</h1>
			<table>
				<div align="right">
	               <input type="button" value="프린트" onClick="print()">
				</div>
				<tr>
					<td><input type="hidden" name="code" id="code" readonly value=""></td>
				</tr>
				<tr>
					<th>성명</th>
					<td><input type="text" name="name" id="name1" readonly value="<%=bean.getName() %>"></td>
				</tr>
				<tr>
					<th>교육명</th>
					<td><input type="text" name="lectureName" id="lectureName" readonly value="<%=bean.getLectureName() %>"></td>
				</tr>
				<tr>
					<th>교육기수</th>
					<td><input type="text" name="lecture" id="lecture1" readonly value="<%=bean.getLecture() %>"></td>
				</tr>
				<tr>
					<th>수료일자</th>
					<td><input type="text" name="endday" id="endday" readonly value="<%=bean.getEndday() %>"></td>
				</tr>
			</table>
			</div>
			<div class="grid01">&nbsp;</div>
		</div>
		</form>
	<%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>