<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, bit.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프</title>
<%
	ArrayList list = new ArrayList();
	list = (ArrayList)request.getAttribute("list");
	request.setCharacterEncoding("UTF-8");
	BbsDao bd= new BbsDao();
%>
<%@ include file="../../template/lms_style.jspf" %>
<style type="text/css">
	     #login{
            text-decoration: none;
         
        }
</style>
</head>
<body>
	<%@ include file="../../template/lms_header.jspf" %>
	
		<div id="sector" class="row" >
			<div id="lecture" class="grid02">수업지원 >
				<span>공지사항</span>
			</div>
		</div>
		
		<div class="row">
			<div class="grid06">&nbsp;</div>
			<div id="btn" class="grid04">
				<form method="get">
					<button type="submit" formaction="noticeList.bit">조회</button>
					<button type="submit" formaction="noticeAdding.bit">신규</button>
					<button type="submit" formaction="#">삭제</button>
				</form>
			</div>
		</div>
		
		<div class="row">
			<div class="grid01">&nbsp;</div>
			<div class="grid08">
				<h1>공지사항</h1>
		<%
		if((Boolean)request.getSession().getAttribute("login")){
			for(int i=0;i<list.size();i++){
				BbsDto bean= new BbsDto();
				bean=(BbsDto)list.get(i);
				String writer=bd.getWriterName(bean.getHcode(), bean.getId());
		%>
			<form method="post" action="noticeDetail.bit" >
				<input type="hidden" name="code" value="<%=bean.getCode()%>"/>
				<input type="hidden" name="num" value="<%=bean.getNum()%>"/>
				<input type="hidden" name="subnum" value="<%=bean.getSubnum()%>"/>
				<table>
					<thead>
					</thead>
					<tbody>
						<tr>
							<td><%=bean.getNum()%>번</td>
							<td><input type="submit" value="<%=bean.getSub()%>"></td>
							<td><%=writer%></td>
							<td><%=bean.getTime() %></td>
							<td><%=bean.getTimeDetail() %></td>
							<td><%=bean.getCnt() %></td>
						</tr>
					</tbody>
				</table>
			</form>
		<%
			}
		}else{
		%>
			<h1>로그인을 먼저 해주세요!</h1>
			
		<%
		}
		%>
	</div>
			<div class="grid01">&nbsp;</div>
		</div>
	
	<%@ include file="../../template/lms_footer.jspf" %>
</body>
</html>