<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="lesson.db.Lesson"%>
<%@page import="schedule.db.Schedule"%>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
   <head>
   <jsp:include page="../main/header.jsp" />
   <title>${lesson.lesson_title } 신청</title>
   <link type="text/css" rel="stylesheet" href="css/style.css">
   <link type="text/css" rel="stylesheet" href="css/bootstrap-soo.css">
   
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script>
      $(document).ready(function(){
         
         $('#register_form').submit(function(){
            
            if($('input:radio').is(':checked') == false) {
               alert('일정을 선택하세요');
               return false;
            }            
            
         });
         
                  
         $('input:radio').click(function(){            
            $('#nextButton').addClass('on').html('결제 하러 가기')                     
         });         
         
      });
         
   </script>
   
</head>
<body>
   
   <div class="tutor_cont">
      <div class="title_box">
         <h3>수업신청</h3>
         <div class="steps">
            <li class="on"><b>01</b>일정/장소</li>
            <img src="image/next.png">
            <li><b>02</b>결제</li>
         </div>
      </div>
      

      <div class="class_title">
         ${lesson.lesson_title }
      </div>
      
      <div class="apply">
         <div class="sh_box class_price">
            <div class="head">
               <img style="margin-top:-7px" src="image/calendar.png">원하시는 수업일정을 선택해주세요.
            </div>

         <form action="paymentPage.lesson" id="register_form">
            <input type="hidden" name="lesson_code" value="${lesson.lesson_code }">
            <div class="regions">
               <div class="region on" id="region">   
                  <table class="table">
                  <c:forEach var="schedule" items="${schedule }">
                     <tr>
                        <td style="padding:10px 0">
                  
                  <input id="select_sch" type="radio" name="schedule" value="${schedule.lesson_date }">
                  ${schedule.lesson_date } <span style="color: #c1c1c1; margin: 0px 7px;"> | </span>
                  ${schedule.lesson_start } ~ ${schedule.lesson_end } <span style="color: #c1c1c1; margin: 0px 7px;"> | </span>
                  ${lesson.lesson_location }
                        </td>
                     </tr>
                  
                  </c:forEach>
                  
                     <tr>
                        <td>
                        <button type="submit" id="nextButton" class="next button">날짜를 선택해주세요</button>
                        </td>
                     </tr>
                  </table>
               </div>
            </div>

         </form>         
      </div>
   </div>   
</div>
</body>
</html>