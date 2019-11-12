<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        
        <%@ include file="../../template/lms_style.jspf" %>
        <script type="text/javascript">
           $(document).ready(function(){
               
               
               for(var i=0; i<3; i++){
               var center = $('#main img').eq(i);
               center.click(function(){
                   alert('준비중입니다.');
               });
               }
               
               var newCenter = $('#main img').eq(3);
               newCenter.click(function(){
                
               });
              
               $('#bxslider>#bx').bxSlider({
                    auto: true,
                    slideWidth: 600,
                    speed: 500,
                    autoHover: true,
                    pager: false
               });
               
           });
            
        </script>
        
       
        <style type="text/css">    
            /* 메인 부분 */
            
            
            
            #main img{
                width: 250px;
            }
            #bx img{
                width: 600px;
            }
            
            /******************/
            </style>
            
        
        <title>안양캠프</title>
    </head>
    <body>
        <%@ include file="../../template/lms_header.jspf" %>
            <div id="content">
            	<h1>커뮤니터 Home 과제게시판 입니당</h1>
            	과제게시물을 보여주세요~
            	<form method="post" action="assignBoardDetail.bit">
				<input type="hidden" name="ip" value="<%=request.getRemoteAddr() %>">
				<table border="1" cellspacing="0">
					<tr>
						<td align="center">
							<input type="submit" value="테이블에 과제게시를 선택했을 경우(상세정보)">
						</td>
					</tr>
				</table>
			</form>
            </div>
         <%@ include file="../../template/lms_footer.jspf" %> 
    </body>
</html>
