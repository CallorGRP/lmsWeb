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
   String pw1 = bean.getPw();      // DB 비밀번호
%>
<%@ include file="../../template/lms_style.jspf" %>
<script type="text/javascript">
   function valueCheck() {
      var form = document.myInfoEdit;                     // form data 저장
      
      var chPt1 = 0;   // email 패턴체크 변수, '@'
      var chPt2 = 0;   // email 패턴체크 변수, '.'
      
      var pw1 = "<%=pw1%>";                           // DB pw
      var pw2 = form.pw2.value;                        
      var name = form.name.value;                        
      var birth = form.birth.value;
      var phone = form.phone.value;
      var email = form.email.value;
      
      
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
      
      // 이름 길이
      if(name.length <= 0) {
         alert("이름을 입력해주세요.");
         return false;
      }
      
      // 이름 유효성
      for(i=0; i<name.length; i++) {
         var ch = name.charCodeAt(i);
         if(!((0x1100 <= ch && ch <= 0x11FF) || (0x3130 <= ch && ch <= 0x318F) || (0xAC00 <= ch && ch <= 0xD7A3) || (ch >= 65 && ch <= 122))) {
            alert("한글과 영문 대 소문자를 사용하세요.(한글 4자 이하, 영어 12자 이하), (특수기호, 공백 사용 불가)");
            return false;
         }
      }
      
      // 생년월일 유효성
      if(birth.length == 0) {
         alert("생년월일을 확인해주세요.");
         return false;
      }
      
      // 휴대전화 유효성
      for(i=0; i<phone.length; i++) {
         var ch = phone.charCodeAt(i);
         if(!(ch >= 48 && ch <= 57)) {
            alert("형식에 맞지 않는 번호입니다.");
            return false;
         }   
      }
      
      // 이메일 유효성
      for(i=0; i<email.length; i++) {
         var ch = email.charCodeAt(i);
         if(ch == 64) {
            chPt1 += 1;
            alert(chPt1);
         }
         if(ch == 46) {
            chPt2 += 1;
            alert(chPt2);
         }
         if(i == email.length-1) {
            if(!((chPt1 == 1) && (chPt2 == 1))) {
               alert("이메일 주소를 다시 확인해주세요.");
               return false;
            }
            chPt1 = 0;
            chPt2 = 0;
         }
      }
      
      // form data 전송
      document.myInfoEdit.action='myInfoEdit.bit';
      document.myInfoEdit.submit();
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
   <form method="post" name="myInfoEdit" id= "myInfoEdit" enctype="utf-8">
      <div id="sector" class="row" >
         <div id="lecture" class="grid02">My서비스 >
            <span>회원정보변경</span>
         </div>
      </div>
      
      <div class="row">
         <div class="grid07">&nbsp;</div>
         <!-- 
         <div id="btn" class="grid03">
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
         <h1>회원정보변경</h1>
         <table>
            <div align="right">
               <%
                        String path = request.getContextPath();
                  %>
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
               <th>비밀번호 확인</th>
               <td><input type="password" id="pw2" name="pw2" value=""></td>
            </tr>
            <tr>
               <th>이름</th>
               <td><input type="text" name="name" value="<%=bean.getName() %>"></td>
            </tr>
            <tr>
               <th>생년월일</th>
               <td><input type="date" name="birth" value="<%=bean.getBirth() %>"></td>
            </tr>
            <tr>
               <th>성별</th>
               <td>
                  <select name="gender">
                  <%
                     if(bean.getGender() == 1) {
                  %>
                     <option value="1" selected>남</option>
                     <option value="2">여</option>
                  <% } else { %>
                           <option value="1">남</option>
                           <option value="2" selected>여</option>
                      <% } %>
                        </select>
                     </td>
            </tr>
            <tr>
               <th>거주지</th>
               <td><input type="text" name="address" value="<%=bean.getAddress() %>"></td>
            </tr>
            <tr>
               <th>휴대전화</th>
               <td><input type="text" name="phone" value="<%=bean.getPhone() %>"></td>
            </tr>
            <tr>
               <th>이메일</th>
               <td><input type="text" name="email" value="<%=bean.getEmail() %>"></td>
            </tr>
         </table>
         </div>
         <div class="grid01">&nbsp;</div>
      </div>
      </form>
   <%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>