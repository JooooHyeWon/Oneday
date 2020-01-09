<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 하루 수업 - 좋아요 한 수업</title>
<jsp:include page="../main/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/userLikeLesson.css">
<script src="js/jquery-3.4.1.js"></script>
<script src="js/userLikeLesson.js"></script>
</head>
<body>
   <div class="container">
      <div class="title-box">
   <h1>나의 하루수업</h1>
   <ul>
      <li class="cursor"><a href="userInfo.ue">내 정보</a></li>
      <li class="cursor on"><a href="userLikeLesson2.ue">좋아요 한 수업</a></li>
      <li class="cursor"><a href="userMyLessonList.ue">내 수업 목록<div class="title-tab-cir"><%=request.getAttribute("ListCount") %></div></a></li>
   </ul>
      </div>
      <div class="teacher-info">
       <!-- <div class="wrap">
	<div>
	<h1 id='big-font'>좋아요 한 수업</h1>
	</div>
	
	<div class="content-wrap">
		<div class="pic-wrap">
			
		</div>
	</div>
</div> -->
      </div>
   </div>
</body>
</html>