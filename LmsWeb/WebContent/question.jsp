<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bit.model.*, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프-문의하기</title>
<%@ include file="../template/style.jspf" %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
      $('#juso').click(function(){
         new daum.Postcode({
              oncomplete: function(data) {
                 document.getElementById("juso1").innerHTML='<span>'+data.sido+' '+data.sigungu+' '+data.roadname+'<input name="juso1" type="hidden" value="'+data.sido+' '+data.sigungu+' '+data.roadname+'"/>'+'</span>';
              }
          }).open();
      });
   });
</script>

</head>
<body>
<%@ include file="../template/header.jspf" %>
<% request.setCharacterEncoding("UTF-8"); %>
   <div id="content" class="row">
      <div class="grid04">&nbsp;</div>
      <div class="grid07">
         <h2>문의 내용</h2>
         <form method="post">
         <table>
            <tr>
               <td>이름</td>
               <td><input type="text" name="name" />
               </td>
               
            </tr>
            <tr>
               <td>전화번호</td>
               <td>
               	<select name="phone1">
                  <option>010</option>
                  <option>02</option>
                  <option>031</option>
                  <option>042</option>
               </select>
               <span>-</span>
               <input type="text" name="phone2" size="4" />
               <span>-</span>
               <input type="text" name="phone3" size="4" />
               </td>
               
            </tr>
            <tr>
               <td>E-mail</td>
               <td><input type="text" name="email1" size="10" />
               
               <select name="email2">
                  <option>@naver.com</option>
                  <option>@gmail.com</option>
                  <option>@daum.com</option>
               </select>
               </td>
            </tr>
            <tr>
               <td>성별</td>
               <td><label for="sex1">남</label>
               <input type="radio" name="sex" value="1" checked="checked" />
               <label for="sex2">여</label>
               <input type="radio" name="sex" value="2" />
               </td>
            </tr>
            <tr>
               <td>강의</td>
               <td><select name="lecture">
               <%
               	LectureDao lecDao = new LectureDao();
            	ArrayList<String> itemList= lecDao.nameList();
            	for(int i=0;i<itemList.size();i++){
               %>
               <option><%=itemList.get(i) %></option>
               <%
            	}
               %>
               </select>
               </td>
            </tr>
            <tr>
               <td>주소</td>
               <td><label id="juso1"></label></td>
               
            </tr>
            <tr>
               <td>상세주소</td>
               <td><input name="juso2" type="text"/>
               <button id="juso" type="button">주소검색</button>
               </td>
            </tr>
            <tr>
               <td>문의 내용</td>
               <td><textarea cols="25" rows="" name="content"></textarea></td>
            </tr>
            <tr>
            <td>&nbsp;</td>
            <td>
            <button type="submit" formaction="questionResult.bit">문의</button>
            <button type="reset">취소</button>
            </td>
            </tr>
            <tr></tr>
         </table>
            </form>
      </div>
      <div class="grid01">&nbsp;</div>
   </div>

<%@ include file="../template/footer.jspf" %>
</body>
</html>