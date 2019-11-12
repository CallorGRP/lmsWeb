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
	String pw1 = bean.getPw();		// DB 비밀번호
%>
<%@ include file="../../template/lms_style.jspf" %>
<script type="text/javascript">
	function valueCheck() {
		
		var pw1 = "<%=pw1%>";									// DB pw
		var pw2 = document.getElementById("pw2").value;			// view 현재비밀번호
		var pwre1 = document.getElementById("pwreChk1").value;	// view 변경비밀번호 확인
		var pwre2 = document.getElementById("pwreChk2").value;  // view 변경비밀번호 재확인
		
		// 현재 비밀번호 길이
		if(pw2.length == 0) {
			alert("현재비밀번호를 입력해주세요.");
			return false;
		}
		
		// 현재 비밀번호
		if(pw1 != pw2) {
			alert("현재비밀번호가 일치하지 않습니다.");
			return false;
		}
		
		// 변경 비밀번호 길이
		if(pwre1.length == 0) {
			alert("변경할 비밀번호를 입력해주세요.");
			return false;
		}
		
		// 변경 비밀번호 일치여부
		if(pwre1 != pwre2) {
			alert("변경할 비밀번호와 재입력 비밀번호가 일치하지 않습니다.");
			return false;
		}
		
		document.myInfoPwEdit.action='myInfoPwEdit.bit';
		document.myInfoPwEdit.submit();
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
	<form method="post" name="myInfoPwEdit" id= "myInfoPwEdit" enctype="utf-8">
		<div id="sector" class="row" >
			<div id="lecture" class="grid02">My서비스 >
				<span>비밀번호변경</span>
			</div>
		</div>
		
		<div class="row">
			<div class="grid07">&nbsp;</div>
			<!-- 
			<div id="btn" class="grid03">
				<%
               		String path = request.getContextPath();
            	%>
               <input type="button" value="저장" onClick="valueCheck()">
               <input type="reset" value="취소" name="reset" />
			</div>
			 -->
		</div>
		
		<div class="row">
			<div class="grid01">&nbsp;</div>
			<div class="grid08">
			<%
				
			 %>
			<h1>비밀번호변경</h1>
			<table>
				<div align="right">
	               <input type="button" value="저장" onClick="valueCheck()">
	               <input type="reset" value="취소" name="reset" />
				</div>
				<tr>
					<td><input type="hidden" name="code" readonly value="<%=bean.getCode() %>"></td>
				</tr>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" readonly value="<%=bean.getCode() %><%=bean.getId() %>"></td>
				</tr>
				<tr>
					<th>현재비밀번호</th>
					<td><input type="password" name="pw2" id="pw2" value=""></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="pwre1" id="pwreChk1" onchange="" value=""></td>
				</tr>
				<tr>
					<th>비밀번호 재입력</th>
					<td><input type="password" name="pwre2" id="pwreChk2" onchange="" value=""></td>
				</tr>
			</table>
			</div>
			<div class="grid01">&nbsp;</div>
		</div>
		</form>
	<%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>