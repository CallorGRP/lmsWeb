<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bit.model.*, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프-강좌개설</title>
<%@ include file="../../template/lms_style.jspf" %>
<style type="text/css">
	table{
		width:800px;
	}
	table th{
		background-color: rgb(4,72,121);
		border-bottom: 1px solid rgb(4,72,121);
		border-right: 1px solid rgb(4,72,121);
		border-left:  1px solid rgb(4,72,121);
		border-top:  1px solid rgb(4,72,121);
		color: white;
	}

</style>
<script type="text/javascript">
	
	$(document).ready(function(){
		$('#imgBtn').click(function(){
			return false;
		});
		$('#res').click(function(){
			var code=$('#code').val();
			var name=$('#lectureName').val();
			var day1=$('#day1').val();
			var day2=$('#day2').val();
			var content=$('#content').val();
			if(code==""||name==""||day1==""||day2==""||content==""){
				alert('모든 정보를 입력해주세요');				
				return false;
			}
			return true;
		});
	});
</script>
</head>
<body>
	<%@ include file="../../template/lms_header.jspf" %>
	<form method="post" enctype="utf-8">
		<div id="sector" class="row" >
			<div id="lecture" class="grid02">수업관리 >
				<span>강좌개설</span>
			</div>
		</div>
		
		<div class="row">
			<div class="grid07">&nbsp;</div>
			<!-- 
			<div id="btn" class="grid03">
						<%
							String path = request.getContextPath();
						%>
					<button id="res" type="submit" formaction="<%=path %>/lms/teachManage/lectureAdd.bit">저장</button>
					<input type="reset" value="취소" name="reset" />
			</div>
			 -->
		</div>
		
		<div class="row">
		<div class="row">
		<div class="grid01">&nbsp;</div>
		<div class="grid09">
		<h1>강좌개설페이지</h1>
			<table>
					<div align="right">
						<button id="res" type="submit" formaction="<%=path %>/lms/teachManage/lectureAdd.bit">저장</button>
						<input type="reset" value="취소" name="reset" />
					</div>
					<p>
					<tr>
						<th>강의코드</th>
						<td><input type="text" name="code" id="code"/>
					</tr>
					<tr>
						<th>제목</th><td>
						<input type="text" name="lecture_name" id="lectureName"/>
					</tr>
					<tr>
						<th>강사</th><td>
						<select name="name">
						<option value=null >미정</option>
						<% 
						LectureDao dao = new LectureDao();
						ArrayList<HumanDto> name=dao.getTeacher();
						for(int i=0; i<name.size(); i++){
						%>
						<option><%=name.get(i).getName() %></option>
						<%
						}
						 %>
						</select>
					</tr>
				<tr>
					<th>교육시작</th>
					<td><input type="date" name="departday" id="day1"></td>
				</tr>
				<tr>
					<th>교육종료</th>
					<td><input type="date" name="endday" id="day2"></td>
				</tr>
				<tr>
					<th>정원수</th>
					<td>
					<select name="listener">
						<option>20</option>
						<option selected="selected">30</option>
						<option>40</option>
					</select>
				</tr>
				<tr>
					<th>강의실</th>
					<td><select name="room">
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
					<td><textarea name="content" rows="10" cols="50" id="content"></textarea>
				</tr>
				<tr>
					<th>이미지</th>
					<td><button id="imgBtn">이미지첨부</button></td>
				</tr>
			</table>
		</div>
		</div>
		</div>
	</form>
	<%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>