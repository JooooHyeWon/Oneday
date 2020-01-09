<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 하루 수업 - 내 수업 목록</title>
<link rel="stylesheet" href="css/bootstrap.css">
<jsp:include page="../main/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/lessonlist.css">
<link rel="stylesheet" type="text/css" href="css/myprofile.css">
<link rel="stylesheet" type="text/css" href="css/post.css">
   <script>
      $(document).ready(function(){
         $("button[name=addschedule]").click(function(){
            var popup= window.open("scheduleaddpage.lesson?lesson_code="+$(this).attr("id"),"","width=630,height=470,left=350,top=200");
            
         });
         
         $("button[name=userlist]").click(function(){
            location.href="userlist.lesson?lesson_code="+$(this).attr("id");
            
         });
         
         $(".card-img").click(function(){
            location.href="lessonInfo.lesson?sort=rv_recent&lesson_code="+$(this).attr("id");
         });
         
         var id=$("#tea_id").val();
         $.ajax({
            url:"lessonlistcount.lesson",
            data:{"id": id },
            success:function(resp){
               $(".title-tab-cir").text(resp);
               }
            
         });//ajax
      });
   </script>
   
   <style>
      .center-block {
         display:flex;
         justify-content:center; /* 가운데 정렬 */
      }
      
      .current {background:#e9eff1 !important;}
   </style>

</head>
<body>
	<input type="hidden" id="tea_id" value="${id }">
    <div class="container">
     <div class="title-box">
   <h1>나의 하루수업</h1>
   <ul>
      <li class="cursor"><a href="teacherInfo.te">내 정보</a></li>
      <li class="cursor"><a href="teacheraddlesson.te">수업 관리</a></li>
      <li class="cursor on"><a href="teacherLessonList.te">내 수업 목록<div class="title-tab-cir">${lesson_num}</div></a></li>
   </ul>
      </div>
      <div class="teacher-info">
         <c:if test="${listcount > 0}">
         <c:set var="num" value="${listcount-(page-1)*9 }"/>            
           <c:forEach var="list" items="${list}">
           
           <div class="post-module">
              
              <div class="thumbnail">
      		<input type="hidden" id="lesson${list.lesson_code}" value="${list.lesson_code}" name="lesson_code">
              <a href="lessonInfo.lesson?sort=rv_recent&lesson_code=${list.lesson_code}"><img src="${gmap[list.lesson_code]}" id="${list.lesson_code}"/></a>
           </div>
              <div class="post-content" id="${list.lesson_code}">
                 <div class="category">${list.lesson_type}</div>
                  <h1 class="title"><a href="lessonInfo.lesson?sort=rv_recent&lesson_code=${list.lesson_code}">${list.lesson_title}</a></h1>
                 <div class="post-info">
                 <span>${list.lesson_type }</span>
                 <button id="${list.lesson_code}" class="card-icon2" name="userlist">학생 목록</button>
                 <button id="${list.lesson_code}" class="card-icon" name="addschedule">시간 등록</button>
                <div class="post-meta"><span class="comments">♥${list.bookmark }</span></div>
                </div>
              </div>
           </div>
           
           </c:forEach>
            </c:if>
           
      </div>
      
      <div class="center-block">
         <div class="row">
            <div class="col">
               <ul class="pagination">
               <c:if test="${page<=1 }">
                  <li class="page-item">
                  <a class="page-link current" href="#">이전&nbsp;</a>
                  </li>
               </c:if>
               <c:if test="${page>1 }">
                  <li class="page-item">
                  <a href="teacherLessonList.te?page=${page-1 }" class="page-link">이전</a> &nbsp;
                  </li>
               </c:if>
               
               <c:forEach var="a" begin="${startpage }" end="${endpage }">
                  <c:if test="${a==page }">
                  <li class="page-item">
                  <a class="page-link current" href="#">${a }</a>
                  </li>
                  </c:if>
                  <c:if test="${a!=page }">
                  <li class="page-item">
                  <a href="teacherLessonList.te?page=${a}" class="page-link">${a }</a>
                  </li>
                  </c:if>
               </c:forEach>
               
               <c:if test="${page>=maxpage }">
                  <li class="page-item">
                     <a class="page-link current" href="#">&nbsp;다음</a>
                  </li>
               </c:if>
               <c:if test="${page<maxpage }">
                  <li class="page-item">
                     <a class="page-link" href="teacherLessonList.te?page=${page+1}" >&nbsp;다음</a>
                  </li>
               </c:if>
               </ul>
            </div>
         </div>
      </div>
       
   </div>

</body>
</html>