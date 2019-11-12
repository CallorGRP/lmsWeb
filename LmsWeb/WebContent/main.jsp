<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        
        <%@ include file="../template/style.jspf" %>
        <script type="text/javascript">
           $(document).ready(function(){
               
               
               for(var i=0; i<3; i++){
               var center = $('#main img').eq(i);
               center.click(function(){
                   open("http://bitcamp.co.kr/index.php?main_page=home");
               });
               }
               
               var newCenter = $('#main img').eq(3);
               newCenter.click(function(){
                
               });
              
               $('#bxslider>#bx').bxSlider({
                    auto: true,
                    slideWidth: 450,
                    speed: 500,
                    autoHover: true,
                    pager: true
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
        <%@ include file="../template/header.jspf" %>
            <div id="content">
               <div class="row">
                   <div class="grid04">
                        &nbsp;
                    </div>
                    <div class="grid07">
                       <div id="bxslider" class="row">
                        <div class="grid02">&nbsp;</div>
                        <div id="bx" class="grid04">
                            <a href="curriculum.bit"><img alt="img" src="imgs/AD1.jpg" /></a>
                            <a href="curriculum.bit"><img alt="img" src="imgs/AD2.jpg" /></a>   
                            <a href="curriculum.bit"><img alt="img" src="imgs/AD3.jpg" /></a>   
                            <a href="curriculum.bit"><img alt="img" src="imgs/AD4.jpg" /></a>   
                        </div>
                       
                        
                        <div class="grid01">&nbsp;</div>
                        </div>
                    </div>
                    <div class="grid01">
                        &nbsp;
                    </div>
               </div>
                <div class="row">
                    <div class="grid01">
                        &nbsp;
                    </div>
                    <div id="main" class="grid10">
                        <div class="grid02" >
                            <a href="#"><img src="imgs/center_1.jpg"></a>
                        </div>
                        <div class="grid02" >
                            <a href="#"><img src="imgs/center_2.jpg"></a>
                        </div>
                        <div class="grid02" >
                            <a href="#"><img src="imgs/center_3.jpg"></a>
                        </div>
                        <div class="grid02" >
                            <a href="main.jsp"><img src="imgs/center_4.png"></a>
                        </div>
                    </div>
                    <div class="grid01">
                        &nbsp;
                    </div>
                </div>
                
            </div>
         <%@ include file="../template/footer.jspf" %> 
    </body>
</html>
