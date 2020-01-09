<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 하루 수업 - 수업 삭제 하기</title>
<jsp:include page="../main/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/myprofile.css">
<link rel="stylesheet" type="text/css" href="css/lessonlist.css">
<link rel="stylesheet" type="text/css" href="css/post.css">
	<script>
		$(document).ready(function(){
			$("button[name=delete_lesson]").click(function(event){
			
		              var answer = confirm("정말 삭제하시겠습니까?");
		              console.log(answer); //취소를 클릭한 경우 : false
		              
		              if(!answer) { //취소를 클릭한 경우
		                 event.preventDefault();
		              }else{
		          
							location.href="lessondelete.lesson?lesson_code="+$(this).attr("id");
		              }
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
      		
        	<c:forEach var="list" items="${list}">
        	
        	<div class="post-module">
        	<div class="thumbnail">
        		<a href="lessonInfo.lesson?sort=rv_recent&lesson_code=${list.lesson_code}"><img class="card-img" src="${gmap[list.lesson_code]}"/></a>
        	</div>
        		 <div class="post-content" id="${list.lesson_code}">
        		 <div class="category">${list.lesson_type}</div>
                 <h1 class="title"><a href="lessonInfo.lesson?sort=rv_recent&lesson_code=${list.lesson_code}">${list.lesson_title}</a></h1>
                 <div class="post-info">
                 <span style="position: relative; top:20px;">${list.lesson_type }</span>
        			<button id="${list.lesson_code}" class="card-icon4" name="delete_lesson">삭제 하기</button>
        		</div>
        	</div>
        	</div>
        	</c:forEach>
        	
      </div>
  
   </div>

</body>
</html>