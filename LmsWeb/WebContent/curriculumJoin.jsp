<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, bit.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프-문의하기</title>
<%@ include file="../template/style.jspf" %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$('#submit').click(function(){
			var name = $('#name').val();
			var phone1 = $('#phone1').val(); 
			var phone2 = $('#phone2').val(); 
			var phone3 = $('#phone3').val(); 
			var email1 = $('#email1').val(); 
			var email2 = $('#email2').val(); 
			var gender=$('#gender').val();
			var lecture = $('#lecture').val(); 
			var juso1 = $('#juso1').val(); 
			var juso2 = $('#juso2').val(); 
			var juso3 = $('#juso3').val();
			var birth = $('#birthday').val();
			
			$('#na').val(name);
			$('#ph').val(phone1+phone2+phone3);
			$('#em').val(email1+email2);
			$('#ge').val(gender);
			$('#le').val(lecture);
			$('#bi').val(birth);
			$('#ju').val(juso1+juso2+"+"+juso3);
			
			$('#nameS').remove();
			$('#phoneS').remove();
			$('#emailS').remove();
			$('#birthS').remove();
			$('#jusoS').remove();
			
			if(name==""){
				$('#nameT').append('<span id="nameS">이름을 입력해주세요!</span>');
				return false;
			}
			else if(phone2=="" ||phone3=="" ){
				$('#phoneT').append('<span id="phoneS">핸드폰번호를 입력해주세요!</span>');
				return false;
			}else if(email1==""){
				$('#eamilT').append('<span id="emailS">이메일을 입력해주세요!</span>');
				return false;
			}else if(birth==""){
				$('#birthT').append('<span id="birthS">생일을 입력해주세요!</span>');
				return false;
			}else if(juso1==""||juso2==""||juso3==""){
				$('#jusoT').append('<span id="jusoS">주소를 입력해주세요!</span>');
				return false;
			}
			
			
		});
		
		
		$('#juso').click(function(){
			
			
			new daum.Postcode({
		        oncomplete: function(data) {
		        	$('#juso1').val(data.sigungu);
		        	$('#juso2').val(data.roadname);
		        }
		    }).open();
		});
	});
</script>
<style type="text/css">
	td>span{
		color:red;
	}
	
	#btn{
	margin-left: 150px;
	}
</style>

</head>
<body>
<%@ include file="../template/header.jspf" %>

	<div id="content" class="row">
		<div class="grid04">&nbsp;</div>
		<div class="grid07">
			<h2>강의 신청</h2>
			<table>
				<tr>
					<td>이름<td>
					<input id="name" type="text" />
					<span id="nameT"></span>
					</td>
					
				</tr>
				<tr>
					<td>전화번호<td>
					<select id="phone1" >
						<option>010</option>
						<option>02</option>
						<option>031</option>
						<option>042</option>
					</select>
					-
					<input id="phone2" type="text" size="4" />
					-
					<input id="phone3" type="text" size="4" />
					<span id="phoneT"></span>
					</td>
					
				</tr>
				<tr>
					<td>E-mail<td>
					<input id="email1" type="text" size="10" />
					
					<select id="email2" >
						<option>@naver.com</option>
						<option>@gmail.com</option>
						<option>@daum.com</option>
					</select>
					<span id="eamilT"></span>
					</td>
				</tr>
				<tr>
					<td>성별<td>
					<select id="gender">
					<option value=1>남</option>
					<option value=2>여</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>강의<td>
					<select id="lecture" >
					<%
						
						ArrayList<LectureDto> list = new ArrayList<LectureDto>();
						LectureDao dao = new LectureDao(); 
						list=dao.getLecture();
						
						for(int i=0; i<list.size(); i++){
						LectureDto bean= new LectureDto();
						bean=list.get(i);
						
					%>
					<option><%=bean.getCode() %></option>
					<%
						}
					%>
					
					</select>
					</td>
				</tr>
				
				<tr>
					<td>생일</td>
					<td><input id="birthday" type="date" />
					<span id="birthT"></span></td>
				</tr>
				
				<tr>
					<td>주소<td>
					<input id="juso1" type="text" disabled="disabled" />
					<input id="juso2" type="text" disabled="disabled"/>
					<span id="jusoT"></span>
				</tr>
				<tr>
					<td>상세주소<td>
					<input id="juso3" type="text"/>
					<button id="juso">주소검색</button>
					</td>
				</tr>
				
				
				
				<tr>
				<td>&nbsp;
				<td>
				
				
				
				</td>
				</tr>
			</table>
			
			<form method="post">
				
				<input type="hidden" id="na" name="name"/>
				<input type="hidden" id="ph" name="phone"/>
				<input type="hidden" id="em" name="email"/>
				<input type="hidden" id="ge" name="gender"/>
				<input type="hidden" id="le" name="lecture"/>
				<input type="hidden" id="bi" name="birth"/>
				<input type="hidden" id="ju" name="juso"/>
				
				<div id="btn">
				<button id="submit" type="submit" formaction="curriculumJoinResult.bit">신청</button>
				<button type="reset">취소</button>
				</div>
			
			</form>
			
		</div>
		<div class="grid01">&nbsp;</div>
	</div>
	<div class="row">
		<div class="grid01">&nbsp;</div>
	</div>
	
<%@ include file="../template/footer.jspf" %>
</body>
</html>