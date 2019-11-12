<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList, bit.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>안양캠프</title>
<%@ include file="../../template/lms_style.jspf"%>
<style type="text/css">
table {
	width: 800px;
	border-collapse: collapse;
	border-bottom: 1px solid rgb(4, 72, 121);
}

table th {
	text-align: center;
	background-color: rgb(4, 72, 121);
	border-bottom: 1px solid;
	color: white;
}

table td {
	text-align: center;
}

table td>a {
	color: gray;
	text-decoration: none;
}

table td>a:HOVER {
	color: rgb(4, 72, 121);
}

#search {
	font-weight: bold;
}

#under a {
	color: gray;
	text-decoration: none;
}
#under a:hover{
	color: rgb(4, 72, 121);
}
</style>
</head>
<body>
	<%@ include file="../../template/lms_header.jspf"%>
	<form>
		<div id="sector" class="row">
			<div id="lecture" class="grid02">
				수업관리 > <span>교육신청 승인</span>
			</div>
		</div>



		<div class="row">
			<div class="grid01">&nbsp;</div>
			<div class="grid08">
				<div>
					<h1>리스트</h1>
				</div>
				<div class="row">
					<div class="grid06">
						<label id="search" for="search">승인여부</label> <select name="search">
							<option value=0>신청</option>
							<option value=3>승인</option>
						</select>
					</div>
					<div id="btn" class="grid02">
						<%
							String confirmBtn="0";
								if(request.getParameter("search")!=null){
									confirmBtn=request.getParameter("search");
								}
								int confirmB=Integer.parseInt(confirmBtn);
								if(confirmB==3){
									System.out.println("승인된 학생 리스트");
								}else{
						%>
						<button id="confirm" type="submit" formmethod="post" formaction="joinList.bit">승인</button>
						<%
							}
						%>


						<button formmethod="get" type="submit" formaction="joinList.bit">조회</button>
						<input type="reset" value="취소" name="reset" />

					</div>
				</div>
				
				<div class="row">
					<div class="grid01">&nbsp;</div>
				</div>
				
				<div class="row">
					<div class="grid08">
						<table>
							<tr>
								<th>과목</th>
								<th>ID</th>
								<th>이름</th>
								<th>이메일</th>
								<th>승인</th>
							</tr>

							<%
								HumanDao dao= new HumanDao();
											ArrayList<HumanDto> list=(ArrayList<HumanDto>)request.getAttribute("search");
											
											
											int total=0;
											int p=(Integer)request.getAttribute("p");
											total=p;
											//97들어감
											String param1=request.getParameter("page");
											if(param1==null)param1="1";
											int pageNum=Integer.parseInt(param1);
											System.out.println(pageNum);
											//pageNum들어감
											
											int start=(pageNum-1)*10;
											int end1=pageNum*10;
											
											int fin=(total/10)+1;
											if(fin==pageNum){
												end1=list.size();
											}
											
											for(int i=start; i<end1; i++){
												HumanDto bean = new HumanDto();
												bean=list.get(i);
							%>

							<tr>
								<td><%=bean.getLecture()%></td>
								<td><%=bean.getId()%></td>
								<td><a href="joinDetail.bit?id=<%=bean.getId()%>"><%=bean.getName()%></a></td>
								<td><%=bean.getEmail()%></td>
								<%
									if(confirmB==3){
								%>
								<td><input name="check<%=i+1%>" type="checkbox"
									value="<%=bean.getId()%>&<%=bean.getEmail()%>"
									checked="checked" /></td>
								<%
									}else{
								%>
								<td><input name="check<%=i+1%>" type="checkbox"
									value="<%=bean.getId()%>&<%=bean.getEmail()%>" /></td>
								<%
									}
								%>

								<td><input type="hidden" value="<%=bean.getInterview()%>" /></td>
								<td><input type="hidden" value="<%=bean.getEmail()%>" /></td>

							</tr>

							<%
								}
							%>
						</table>
						<div class="row">
							<div class="grid05">&nbsp;</div>
							<div class="grid02"></div>
						</div>
						<%
							int pStart=0;
									pStart=((pageNum-1)/5)*5;
									int end2=0;
									end2=total/10;
									if(total%10!=0){
										end2++;
									}
									int end3=end2;
									if(pStart+5<end2){
										end2=pStart+5;
									}
									int endPage=pageNum+1;
						%>
					</div>
					<div class="row">
						<div class="grid12">&nbsp;</div>
					</div>
					<div class="row" id="under">
						<div class="grid03">&nbsp;</div>
						<div class="grid04">
							<%
								if(pStart>0){
							%><a
								href="<%=root%>/lms/teachManage/joinList.bit?page=<%=pageNum-5%>&search=<%=confirmB%>">
								◀ </a>
							<%
								}
							%>
							<%
								for(int i=pStart; i<end2; i++){
							%>
							<a
								href="<%=root%>/lms/teachManage/joinList.bit?page=<%=i+1%>&search=<%=confirmB%>">[&nbsp;<%=i+1%>&nbsp;]
							</a>
							<%
								}
							%>
							<%
								if(endPage==fin+1){
												
											}else if(end2<=end3){
							%>
							<a
								href="<%=root%>/lms/teachManage/joinList.bit?page=<%=endPage%>&search=<%=confirmB%>">
								▶ </a>
							<%
								}
							%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<%@ include file="../../template/lms_footer.jspf"%>
</body>
</html>