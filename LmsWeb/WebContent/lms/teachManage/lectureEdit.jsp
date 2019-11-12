<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bit.model.*, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프-강좌개설</title>
<%@ include file="../../template/lms_style.jspf" %>
<script type="text/javascript">
	$(document).ready(function(){
		$('#imgBtn').click(function(){
			return false;
		});
		$('#edit').click(function(){
			var day1 = $('#day1').val();
			var day2 = $('#day2').val();
			var departday=$('#departday');
			var rem=$('#remove')
			if(day1=="" || day2==""){
				rem.remove();
				departday.append('<span style=color:red; id="remove">날짜를 입력해주세요</span>');
				return false;
			}else{
				return true;
			}
		});
		
	});
	
</script>
<style type="text/css">
	table th{
		background-color: rgb(4,72,121);
		border-bottom: 1px solid rgb(4,72,121);
		color: white;
	}

</style>
</head>
<body>
	<%@ include file="../../template/lms_header.jspf" %>
	<form method="post" enctype="utf-8">
		<div id="sector" class="row" >
			<div id="lecture" class="grid02">수업관리 >
				<span>강좌수정</span>
			</div>
		</div>
		
		<div class="row">
			<div class="grid07">&nbsp;</div>
			<div id="btn" class="grid03">
				
				<%
					request.setCharacterEncoding("utf-8");
					String path = request.getContextPath();
					String code = request.getParameter("code");
					LectureDto bean = new LectureDto();
					LectureDao dao = new LectureDao();
					bean=dao.detail(code);
				%>
					<button id="edit" type="submit" formaction="<%=path %>/lms/teachManage/lectureEdit.bit">수정</button>
					<input type="reset" value="리셋" name="reset" />
					
				
			</div>
		</div>
		
		<div class="row">
			
		<div class="row">
		<div class="grid01">&nbsp;</div>
		<div class="grid09">
		<h2>강좌수정페이지</h2>
			<table>
					<tr>
						<th>강의코드</th>
						<td>
						<input type="text" value="<%=code%>" disabled="disabled"/>
						<input type="hidden" name="code2" value="<%=code%>" />
					</tr>
					<tr>
						<th>제목</th><td>
						<input type="text" name="lecture_name2" value="<%=bean.getLectureName()%>"/>
					</tr>
				<tr>
					<th>교육시작</th>
					<td id="departday"><input type="date" name="departday2" id="day1">
					
				</tr>
				<tr>
					<th id="endday">교육종료</th>
					<td><input type="date" name="endday2" id="day2"></td>
				</tr>
				<tr>
					<th>정원수</th>
					<td>
					<select name="listener2">
						<option>20</option>
						<option selected="selected">30</option>
						<option>40</option>
					</select>
				</tr>
				<tr>
					<th>강의실</th>
					<td><select name="room2">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
					<option>6</option>
					</select></td>
				</tr>
				<tr>
					<th>강의 내용</th>
					<td><textarea name="content2" rows="10" cols="50" ><%=bean.getContent() %></textarea>
					
				</tr>
				<tr>
					<th>이미지첨부</th>
					<td><button id="imgBtn" formaction="#">이미지첨부</button></td>
				</tr>
			</table>
		</div>
		</div>
		</div>
	</form>
	
	<%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>