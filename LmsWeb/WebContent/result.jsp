<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>안양캠프-결과조회</title>
<%@ include file="../template/style.jspf"%>

<script type="text/javascript">
	$(document).ready(function(){
		
		$('#submit').click(function(){
			var name = $('#name').val();
			var phone1 = $('#phone1').val(); 
			var phone2 = $('#phone2').val(); 
			var phone3 = $('#phone3').val(); 
			var email1 = $('#email1').val(); 
			var email2 = $('#email2').val(); 
			
			$('#na').val(name);
			$('#ph').val(phone1+phone2+phone3);
			$('#em').val(email1+email2);
			
			$('#nameS').remove();
			$('#phoneS').remove();
			$('#emailS').remove();
			
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
			}
			
			
		});
		
	});
</script>
<style type="text/css">
td>span {
	color: red;
}
</style>
</head>
<body>
	<%@ include file="../template/header.jspf"%>

	<div id="content" class="row">
		<div class="grid04">&nbsp;</div>
		<div class="grid06">
			<h2>교육과정 신청 결과조회</h2>
			<form method="post" action="resultCheck.bit" enctype="utf-8">
				<table>
					<tr>
						<td>이름</td>
						<td><input id="name" type="text" /> <span id="nameT"></span>
						</td>
					</tr>
					<tr>
						<td>전화번호
						<td><select id="phone1">
								<option>010</option>
								<option>02</option>
								<option>031</option>
								<option>042</option>
						</select> - <input id="phone2" type="text" size="4" /> - <input
							id="phone3" type="text" size="4" /> <span id="phoneT"></span></td>

					</tr>
					<tr>
						<td>E-mail
						<td><input id="email1" type="text" size="10" /> <select
							id="email2">
								<option>@naver.com</option>
								<option>@gmail.com</option>
								<option>@daum.com</option>
						</select> <span id="eamilT"></span></td>
					</tr>
					<tr>
						<td><input type="hidden" id="na" name="name" /> <input
							type="hidden" id="ph" name="phone" /> <input type="hidden"
							id="em" name="email" /></td>
					</tr>

				</table>
				<div class="row">
					<div class="grid01">&nbsp;</div>
					<div class="grid01">
						<input id="submit" type="submit" value="결과확인">
					</div>
				</div>
			</form>
		</div>
		<div class="grid01">&nbsp;</div>
	</div>
	<div class="row">
		<div class="grid01">&nbsp;</div>
	</div>


	<%@ include file="../template/footer.jspf"%>
</body>
</html>