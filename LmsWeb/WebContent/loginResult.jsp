<%@page import="lms.login.model.LoginDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, lms.login.model.*"%>
<%@page errorPage="loginError.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="refresh" content="2;url=/home2/lms/myinfo/myInfoMain.bit">  -->
<title>안양캠프-LMS</title>
<%
	ArrayList list = new ArrayList();
	list = (ArrayList)request.getAttribute("list");
	
	LoginDto bean = new LoginDto();
	bean = (LoginDto)list.get(0);	// controller에서 list값 없을경우 login 실패, loginError.jsp 이동
	
	// login 정보 session 저장
	if( ((String)(bean.getLoginId())) != null && ((String)(bean.getChk())) != null) {
		session.setAttribute("login", true);
		session.setAttribute("id", bean.getLoginId());
		session.setAttribute("name", bean.getName());
		System.out.println("session 저장 확인");
	}

%>
<%@ include file="../template/style.jspf" %>
</head>
<body>
<script type="text/javascript">
	window.location.replace('/home2/lms/myinfo/myInfoMain.bit')		// page 바로이동
</script>
<%@ include file="../template/header.jspf" %>
	<div id="content" class="row">
		<div class="grid01">&nbsp;</div>
		<div class="grid10"><p>
			<h2>비트캠프 학습관리시스템(로그인결과)</h2>
			<form method="post" action="loginResult.bit">
				<input type="hidden" name="ip" value="<%=request.getRemoteAddr() %>">
				<table border="1" cellspacing="0">
					<tr>
						<td>
							아이디
						</td>
						<td>
							<input type="text" name="id"  value="<%=bean.getLoginId() %>" placeholder=" 아이디">
						</td>
					</tr>
					<tr>
						<td>
							비밀번호
						</td>
						<td>
							<input type="password" name="pw" value="<%=bean.getChk() %>" placeholder=" 비밀번호">
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