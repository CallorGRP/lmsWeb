<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, bit.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프-강좌상세</title>
<%@ include file="../../template/lms_style.jspf" %>
<style type="text/css">
   table{
      width:800px;
   }
   table th{
      background-color: rgb(4,72,121);
      border-bottom: 1px solid;
      color: white;
   }
   table td{
      border-bottom: 1px solid rgb(4,72,121);
   }

</style>
<script type="text/javascript">
   $(document).ready(function(){
      
   });
</script>
</head>
<body>
   <form method="get">
   
   <%@ include file="../../template/lms_header.jspf" %>
   <%
      int join=0;
      int confirm=0;
      String code = request.getParameter("code");
      LectureDto bean = new LectureDto();
      LectureDao dao = new LectureDao();
      bean=dao.detail(code);
      join=dao.joinStudentConunt(code);
      confirm=dao.confirmStudentConunt(code);
   %>
   
      <div id="sector" class="row" >
         <div id="lecture" class="grid02">수업관리 >
            <span>강좌상세</span>
         </div>
      </div>
      
      <div class="row">
         <div class="grid07">&nbsp;</div>
         <div id="btn" class="grid03">
               
               <input type="hidden" value="<%=code%>" name="code">
               
               <%
                            if(((String)session.getAttribute("id")).substring(0,2).equals("EM")) {
                            %>
               <button type="submit" formaction="<%=root%>/lms/teachManage/lectureEdit.bit?code=<%=code %>">수정</button>
               <button type="submit" formaction="<%=root%>/lms/teachManage/lectureDelete.bit">삭제</button>
               <%
               }
               %>
               &nbsp;
         </div>
      </div>
      
      <div class="row">
      <div class="grid01">&nbsp;</div>
      <div class="grid09">
      <h1>강좌상세페이지</h1>
         <table>
               <tr>
                  <th>제목</th><td>&nbsp;<%=bean.getLectureName() %></td>
                  <td>
                  <input name="lecture_name" type="hidden" value="<%=bean.getLectureName()%>">
                  </td>
               </tr>
               <tr>
                  <th>내용</th><td>&nbsp;<%=bean.getContent() %></td>
                  <td>
                  <input name="content" type="hidden" value="<%=bean.getContent()%>">
                  </td>
               </tr>
            <tr>
               <th>교육기간</th>
               <td>&nbsp;<%=bean.getDepartday()%> ~ <%=bean.getEndday() %></td>
                  <td>
                  <input name="departday" type="hidden" value="<%=bean.getDepartday()%>">
                  </td>
                  <td>
                  <input name="endday" type="hidden" value="<%=bean.getEndday()%>">
                  </td>
            </tr>
            <tr>
               <th>교육장소</th>
               <td>&nbsp;<%=bean.getRoom() %></td>
               <td>
                  <input name="room" type="hidden" value="<%=bean.getRoom()%>">
               </td>
            </tr>
            <tr>
               <th>교육정원</th>
               <td>&nbsp;<%=bean.getListener() %></td>
               <td>
                  <input name="listener" type="hidden" value="<%=bean.getListener()%>">
               </td>
            </tr>
            <tr>
               <th>수강인원</th>
               <td>&nbsp;<%=confirm %>/<%=join %></td>
            </tr>
            <tr>
               <th>문의전화</th>
               <td>&nbsp;02-3486-9600</td>
            </tr>
         </table>
      </div>
      </div>
   
   <%@ include file="../../template/lms_footer.jspf" %>
   </form>
</body>
</html>