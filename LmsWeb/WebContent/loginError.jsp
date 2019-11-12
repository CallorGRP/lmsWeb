<%@page import="lms.login.model.LoginDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, lms.login.model.*"%>
<%@page errorPage="loginError.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>안양캠프-LMS</title>
<%

%>
<%@ include file="../template/style.jspf" %>
</head>
<body>
<%@ include file="../template/header.jspf" %>
	<div id="content" class="row">
		<div class="grid01">&nbsp;</div>
		<div class="grid10"><p>
			<h2>비트캠프 학습관리시스템(로그인결과)</h2>
			로그인 에러입니다
			
		</div>
		<div class="grid01">&nbsp;</div>
	</div>

<%@ include file="../template/footer.jspf" %>
</body>
</html>