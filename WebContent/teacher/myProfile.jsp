<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 하루 수업 - 내 정보</title>
 <jsp:include page="../main/header.jsp"></jsp:include>
 <link rel="stylesheet" type="text/css" href="css/myprofile.css">
	<script>
		$(document).ready(function(){
			$("#alter-pass").click(function(){
				var popup= window.open("teacherPassCheck.te","","width=600,height=550,left=250,top=300");
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
   <div class="container">
      <div class="title-box">
   <h1>나의 하루수업</h1>
   <ul>
      <li class="cursor on"><a href="teacherInfo.te">내 정보</a></li>
      <li class="cursor"><a href="teacheraddlesson.te">수업 관리</a></li>
      <li class="cursor"><a href="teacherLessonList.te">내 수업 목록<div class="title-tab-cir">${lesson_num}</div></a></li>
   </ul>
      </div>
      <div class="teacher-info">
            <div class="wrap">
	<div id="teacher-info-myinfo">
	<h1 id='big-font'>내 정보</h1>
	</div>
	<form method="post" action="teacherInfoUpdate.te">
	<div id="myInfo">
		<div class="info">
			<label>아이디</label>
			<input type="text" id="tea_id" name="tea_id" value="${teacher.getTea_id()}" readonly> 
		</div>
		<div class="info">
			<label>비밀번호</label>
			<input type="password" id="tea_pass" name="tea_pass" value="${teacher.getTea_pass() }" readonly>
			<button type="button" class="alter" id="alter-pass">비밀번호 수정하기</button>
		</div>
		<div class="info">
			<label>이름</label>
			<input type="text" id="tea_name" name="tea_name" value="${teacher.getTea_name() }" readOnly>
		</div>
		<div class="info">
			<label>전화번호</label>
			<input type="text" id="tea_phone" name="tea_phone" value="${teacher.getTea_phone() }">
		</div>
		<div class="info">
			<label>이메일</label>
			<input type="text" id="tea_email" name="tea_email" value="${teacher.getTea_email() }">
		</div>
	</div>
	<div>
	<button type="submit" class="alter" id="alter-accept">수정하기</button>
	</div>
	</form>
</div>
      </div>
      
    
   </div>
</body>
</html>