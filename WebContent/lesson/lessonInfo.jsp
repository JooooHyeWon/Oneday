<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="review.db.Review" %>
<%@page import="java.util.List" %>

<!DOCTYPE html>
<html>
   <%
      List<Review> reviews = (List<Review>)request.getAttribute("review");            
   %>

<head>
   <jsp:include page="../main/header.jsp" />
   <title>${lesson.lesson_title }</title>

   
   <link type="text/css" rel="stylesheet" href="css/testbody.css">
   <link type="text/css" rel="stylesheet" href="css/classprice_style.css">
   <link type="text/css" rel="stylesheet" href="css/bodystyle.css">
   <link type="text/css" rel="stylesheet" href="css/page.css">
   <link type="text/css" rel="stylesheet" href="css/bootstrap-soo.css">
   
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <script src="js/prototype.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    
   <style>
      .center-block {
         display:flex;
         justify-content:center; /* 가운데 정렬 */
      }
      
      .current {background:#e9eff1 !important;}
   </style>
   
   <script>
   $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
        
      });
   </script>
</head>
<body> 
<input type="hidden" name="lesson_code" id="lesson_code" value="${lesson.lesson_code }">
<div id="container_detail">
   <div class="class_wrap">
   
      <!-- 옆에 떠다니는 div -->
      <div class="class_price" id="class_price" style="position:relative;">
         <div class="regions">
            <div class="title">
               ${lesson.lesson_title }
            </div>
            
            <div class="region" id="region">               
               <div class="option">
                  <c:forEach var="schedule" items="${schedule }">
                  <div class="top">
                     <div class="timedetail">
                        <div class="indate">
                           ${schedule.lesson_date }
                        </div>
                        <span>|</span>
                         ${schedule.lesson_start } ~ ${schedule.lesson_end }
                        <span>|</span><font>${min_loc }</font><span>|</span>
                        <font>${schedule.user_count } / ${schedule.max_user }</font>                 
                     </div>
                  </div>   
                  </c:forEach>                  
               </div>               
            </div>
         </div>
               
         <a onclick="openchat()" class="btn_qna" data-toggle="tooltip" data-placement="bottom" title="오픈채팅">오픈 채팅</a>
         <script>
            function openchat() {
               var answer = confirm("오픈 채팅을 시작하시겠습니까?");            
               if (answer == true) {
                  location.href = "${lesson.lesson_chat}";
               }
            }      
         </script>
         
         <div class="bnt_payment new_bnt">
            <a href="register.lesson?lesson_code=${lesson.lesson_code }"><span class="btntxt_s">수업 신청하기</span></a>            
         </div>
         
         <div class="price">￦<span>${lesson.lesson_price }</span></div>            
      </div>
      <!-- 끝 -->
      
      
      <!-- 수업 이미지 보여지는거 -->
      <div class="class_img">
         <div class="container" style="width:840px; height:500px">
            <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="false">

               <ol class="carousel-indicators">      
                  <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                  
                  <c:forEach var="gal" items="${gallery }" varStatus="status" begin="1">         
                  <li data-target="#myCarousel" data-slide-to="${status.count }" class=""></li>
                  </c:forEach>
               </ol>

               <div class="carousel-inner">
               
                  <div class="item active">
                     <img src="${main }" style="width:100%; height:540px;">
                  </div>   
                  
                  <c:forEach var="gal" items="${gallery }" begin="1">
                  <div class="item">
                     <img src="${gal.pic_name }" style="width:100%; height:540px;">
                  </div>            
                  </c:forEach>            
               </div>
      
   
               <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                  <span class="glyphicon glyphicon-chevron-left"></span>
                     <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                     <span class="glyphicon glyphicon-chevron-right"></span>
                     <span class="sr-only">Next</span>
                </a>
            </div>
         </div>
      </div>
      <!-- /상단이미지 -->
      
            
      <div class="class_d_wrqp">
         <!-- 요약 강사 수업소개 리뷰 -->
         <div id="class_navi" class="class_navi" style="left: 0px;">
            <ul>
               <li><a href="#sumary" class="on" id="sli">요약</a></li>
               <li><a href="#tutorinfo" id="tli">강사</a></li>
               <li><a href="#Introduction" id="ili">수업소개</a></li>
               <li><a href="#review" id="rli">리뷰</a></li>
            </ul>
         </div>
         <!-- / -->

         <!-- 수업타이틀 -->
         <div class="class_detail" id="sumary">
            <div class="class_title">
               <div style="width:350px">            
                  <span class="title">${lesson.lesson_title }</span>
               </div>
               <div class="info">
                  <ul>
                     <li class="loc"><font color="black" style="text-weight:bold; position:relative; top:40px">${sum_loc }</font></li>                     
                     <li class="time"><font color="black" style="position:relative; top:40px">${process_h }시간 ${process_m }분</font></li>
                     <li class="mon"><font color="black" style="position:relative; top:40px">￦<span>${lesson.lesson_price }</span></font></li>
                  </ul>
               </div>
            </div>
         </div>
         <!-- /수업타이틀 -->

         <!-- 강사소개 -->
         <div class="class_detail detail_sec_bor" id="tutorinfo">
            <div class="section01" id="tutor">
               <h1>강사정보</h1>
               <div class="d_info04">
                  ${lesson.tea_info }     
               </div>               
            </div>
         </div>         
         
         <script>
             function display(id, val) {
               if(val == 1) {
                  document.getElementById(id).style.display="none"
                  document.getElementById(id+'Full').style.display="block";
                  document.getElementById(id+'Open').style.display="none";
                  document.getElementById(id+'Close').style.display="block"; } 
               else {
                  document.getElementById(id).style.display="block"
                  document.getElementById(id+'Full').style.display="none";
                  document.getElementById(id+'Open').style.display="block";
                  document.getElementById(id+'Close').style.display="none";
               }
            }
            
            docuwidth = $( window ).width()/2;

            $( window ).resize(function() {
               docuwidth = $( window ).width()/2;
            });

            
            $(window).scroll(function() {   

               if ($(window).scrollTop() > 691){ 
                  $('#class_navi').addClass("fixedLayer");
                  $("#class_navi").css("left", docuwidth - 590);
               }
               else{
                  $("#class_navi").css("left", 0);
                  $('#class_navi').removeClass("fixedLayer");
               }

               c = document.getElementById("checker");
               var limiter = c.offsetTop+80;

               
               if($(window).scrollTop() > 100) {
                  if($(window).scrollTop() < limiter) {
                     $("#class_price").addClass("fixedLayerRemote");
                     $("#class_price").css("left", docuwidth + 260);
                  } else {
                     $("#class_price").removeClass("fixedLayerRemote");
                     $("#class_price").animate({"top" : limiter-200 + "px"}, 10);
                     $("#class_price").css("left", 0);
                  }
               } else {
                     $("#class_price").removeClass("fixedLayerRemote");
                     $("#class_price").animate({"top" : 0 + "px"}, 10);
                     $("#class_price").css("left", 0);
               }
               
               toffsetTop = document.getElementById("tutorinfo").offsetTop + 600;
               IoffsetTop = document.getElementById("Introduction").offsetTop + 600;
               roffsetTop = document.getElementById("review").offsetTop + 600;
               qoffsetTop = document.getElementById("qna").offsetTop + 600;
               
               if($(window).scrollTop() > toffsetTop && $(window).scrollTop() < IoffsetTop)
               {
                  $('#sli').removeClass("on");
                  $('#tli').addClass("on");
                  $('#ili').removeClass("on");
                  $('#rli').removeClass("on");
                  $('#qli').removeClass("on");
               }
               else if($(window).scrollTop() > IoffsetTop && $(window).scrollTop() < roffsetTop)
               {      
                  $('#sli').removeClass("on");      
                  $('#tli').removeClass("on");
                  $('#ili').addClass("on");
                  $('#rli').removeClass("on");
                  $('#qli').removeClass("on");
               }
               else if($(window).scrollTop() > roffsetTop && $(window).scrollTop() < qoffsetTop)
               {      
                  $('#sli').removeClass("on");
                  $('#tli').removeClass("on");
                  $('#ili').removeClass("on");
                  $('#rli').addClass("on");
                  $('#qli').removeClass("on");
               }
               else
               {
                  $('#sli').addClass("on");
                  $('#tli').removeClass("on");      
                  $('#ili').removeClass("on");
                  $('#rli').removeClass("on");
                  $('#qli').removeClass("on");
               }
            });            
         </script>
         <!-- /강사소개 -->

         <!-- 수업소개 -->
         <div class="class_detail detail_sec_bor" id="Introduction">
            <div class="section01">
               <h1>수업소개</h1>
               <div class="d_info04">
                  ${lesson.lesson_content }
               </div>
            </div>
         </div>
         <!-- /수업소개 -->

         <!-- 리뷰 -->
         <div class="class_detail detail_sec_bor" id="review">
         
         	<!-- 리뷰 없을 때 -->
            <c:if test="${review_cnt == 0 }">
               <div class="section01">
               <h1>리뷰</h1>
               <h5>등록된 리뷰가 없습니다.</h5>
               </div>
            </c:if>
            
            
            <!-- 리뷰 있을 때 -->
            <c:if test="${review_cnt != 0 }">
            <div class="section01">
            	<h1>리뷰</h1>
            	<div class="review_rating">${review_avg } / 5</div>            
            		<div class="review_sort">      
      					<input type="button" id="rv_recent" class="btn btn-default" value="최신순" onclick="location.href='lessonInfo.lesson?lesson_code=${lesson.lesson_code}&sort=rv_recent#review'">
      					<input type="button" id="rv_high" class="btn btn-default" value="별점순" onclick="location.href='lessonInfo.lesson?lesson_code=${lesson.lesson_code}&sort=rv_high#review'">
					</div>
               
               		
                  
               		<div class="review_list" id="bookmarkReview">
                 		<div id="innerReviewDiv">
                        
                     	<c:set var= "num" value="${listcount - (page-1) *3}" />
                        
                        
	                     <% for (Review review : reviews) {
	                           
	                        /* 아이디 익명 표시 */
	                        int userid_len = review.getUser_id().length();
	                        String cut_userid = review.getUser_id().substring(0, 3);
	                        String idstar = "";
	                           for(int i=1; i<=userid_len-3; i++){
	                              idstar += "*";
	                           }
	                        String userid = cut_userid.concat(idstar);
	                           
	                        /* 별 모양 */
	                        int star_num = review.getReview_star();
	                        String yellowstar = "";
	                        String graystar = "";
	                           for(int i=1; i<=star_num; i++) {
	                              yellowstar += "★";
	                           }
	                           
	                           for(int i=1; i<=5 - star_num; i++) {
	                              graystar += "★";
	                           }                                               
	                     %>                  
                     <div class="review_div">
                        <div class="review_id"><%=userid %></div>
                        <div class="review_star">
                        	<div class="review_goldstar"><%=yellowstar %></div><div class="review_graystar"><%=graystar %></div>
                        </div>
                        <div class="review_content_date">
                           <table class="">
                              <tr><td class="review_content"><%=review.getReview_content() %></td></tr>
                              <tr>
                              	<td class="review_date" width="98%">작성일 : <%=review.getReview_date() %></td>
                              	<%if (review.getUser_id().equals(session.getAttribute("id"))) { %>
                              	<td class="review_del">
                              		<a href="reviewDelete.rv?num=<%=review.getReview_code()%>">
										<button class="btn btn-danger">삭제</button>
									</a>
                              	</td>
                              	<%} %>
                              </tr>
                           </table>
                        </div>
                     </div>
                     <%} %>   
                        
                  </div>
               </div>
                  
                  
               <div class="center-block">
                  <div class="row">
                     <div class="col">
                        <ul class="pagination">
                           <c:if test="${page <= 1 }">
                           <li class="page-item">
                              <a class="page-link current" href="#review">이전&nbsp;</a>
                           </li>
                           </c:if>
                     
                           <c:if test="${page > 1 }">
                           <li class="page-item">
                              <a href="lessonInfo.lesson?lesson_code=${lesson.lesson_code }&sort=${sort }&page=${page -1 }#review" class="page-link">이전</a> &nbsp;
                           </li>
                           </c:if>
                     
                           <c:forEach var="a" begin="${startpage }" end="${endpage }">
                              <c:if test="${a==page }">
                                 <li class="page-item">
                                    <a class="page-link current" href="#review">${a }</a>
                                 </li>
                              </c:if>
                              <c:if test="${a != page }">
                                 <li class="page-item">
                                    <a href="lessonInfo.lesson?lesson_code=${lesson.lesson_code }&sort=${sort }&page=${a }#review" class="page-link">${a }</a>
                                 </li>
                              </c:if>
                           </c:forEach>
                     
                           <c:if test="${page >= maxpage }">
                              <li class="page-item">
                                 <a class="page-link current" href="#review">&nbsp;다음</a>
                              </li>
                           </c:if>
                           <c:if test="${page < maxpage }">
                              <li class="page-item">
                                 <a href="lessonInfo.lesson?lesson_code=${lesson.lesson_code }&sort=${sort }&page=${page+1 }#review" class="page-link">&nbsp;다음</a>
                              </li>
                           </c:if>
                     
                        </ul>
                     </div>
                  </div>
               </div>   
            </div>
            </c:if>
         </div>
         <!-- /리뷰 -->

         <!-- 문의 -->
         <div class="class_detail detail_sec_bor" id="qna">
            <div id="checker"></div>
         </div>
         <!-- /문의-->       
      </div>
   </div>
</div>
</body>
</html>