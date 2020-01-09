<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 하루 수업 - 수업 관리</title>
<jsp:include page="../main/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/myprofile.css">
 
	<script>
	$(document).ready(function(){
		
		$("#class_add_btn").click(function(){
			location.href="lessonAddForm.lesson";
		});
		
		$("#class_delete_btn").click(function(){
			location.href="lessondeletepage.lesson";
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
      <li class="cursor on"><a href="teacheraddlesson.te">수업 관리</a></li>
      <li class="cursor"><a href="teacherLessonList.te">내 수업 목록<div class="title-tab-cir">${lesson_num}</div></a></li>
   </ul>
      </div>
  
      <div class="class_add">
		<div class="profile-container">
			<div class="inner-cont">
			<button class="pink-submit" id="class_add_btn">등록하기</button>		
			<button class="blue-submit" id="class_delete_btn">삭제하기</button>
			</div>
		</div>			
		</div>
      </div>
  

</body>
</html>