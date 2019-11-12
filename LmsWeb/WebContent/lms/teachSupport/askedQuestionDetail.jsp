<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프</title>
<%@ include file="../../template/lms_style.jspf" %>
<style type="text/css">
</style>
</head>
<body>
	<%@ include file="../../template/lms_header.jspf" %>
	
		<div id="sector" class="row" >
			<div id="lecture" class="grid02">수업지원 >
				<span>FAQ 상세</span>
			</div>
		</div>
		
		<div class="row">
			<div class="grid06">&nbsp;</div>
			<div id="btn" class="grid04">
				<form>
					<button type="submit" formaction="askedQuestionEdit.bit">수정</button>
					<button type="submit" formaction="#">삭제</button>
					<input type="reset" value="취소" name="reset" />
				</form>
			</div>
		</div>
		
		<div class="row">
			<div class="grid01">&nbsp;</div>
			<div class="grid08">리스트 받을 곳</div>
			<div class="grid01">&nbsp;</div>
		</div>
	
	<%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>