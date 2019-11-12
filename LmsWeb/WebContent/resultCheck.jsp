<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>안양캠프-결과조회</title>
<%@ include file="../template/style.jspf" %>
</head>
<body>
<%@ include file="../template/header.jspf" %>

	<div id="content" class="row">
		<div class="grid04">&nbsp;</div>
		<div class="grid07">
			<h2>교육과정 신청 결과조회 확인</h2>
			<%
			int result = (Integer)request.getAttribute("result");
			if(result==0){
			%>
			<p>아직 승인 대기중입니다!</p>
			<%	
			}else if(result==3){
			%>
			<p>축하드립니다~ 합격하셨습니다! 메일을 확인해주세요!!!</p>							
			<%	
			}else{
			%>
			<p>검색된 내용이 없습니다. 정보를 확인해주세요!</p>
			<%
			}			
			%>
			
		</div>
		<div class="grid01">&nbsp;</div>
	</div>
	<div class="row">
		<p>&nbsp;</p>
	</div>

<%@ include file="../template/footer.jspf" %>
</body>
</html>