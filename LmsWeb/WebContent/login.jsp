<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>안양캠프-LMS</title>
<%@ include file="../template/style.jspf" %>

<style type="text/css">
	table th{
		background-color: rgb(4,72,121);
		color: white;
	}

</style>

</head>
<body>
<%@ include file="../template/header.jspf" %>
		
	<%
	// lms 내부 테스트를 위한 임의 login session값 넣음
	//String userId = "ST20190301001";
	//String userName = "최준호짱";
	//session.setAttribute("login", true);
	//session.setAttribute("id", userId);
	//session.setAttribute("name", userName);
	%>
	
	<div id="content" class="row">
		<div class="grid05">&nbsp;</div>
		<div class="grid06"><p>
			<h2>비트캠프 학습관리시스템</h2>
			<form method="post" action="loginResult.bit">
				<input type="hidden" name="ip" value="<%=request.getRemoteAddr() %>">
				<table>
					<tr>
						<th>
							아이디
						</th>
						<td>
							<input type="text" name="id"  value="" placeholder=" 아이디">
						</td>
					</tr>
					<tr>
						<th>
							비밀번호
						</th>
						<td>
							<input type="password" name="pw" value="" placeholder=" 비밀번호">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="로그인">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="grid01">&nbsp;</div>
	</div>

<%@ include file="../template/footer.jspf" %>
</body>
</html>