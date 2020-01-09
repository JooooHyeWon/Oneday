<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 하루 수업 - 수강 회원 목록</title>
<jsp:include page="../main/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/user_list.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$(".container-table100").click(function(){
			location.href="teacherLessonList.te";
			
		});
	});
</script>
</head>
<body style="background:#fcadad40;">
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table">
					<div class="row header">
						<div class="cell">수업날짜</div>
						<div class="cell">수강생 아이디</div>
						<div class="cell">수강생 이름</div>
					</div>
					
					<c:forEach var="list" items="${list}">
					<div class="row">

						<div class="cell" data-title="lesson_date">${map[list.lesson_de_code]}
						</div>
						<div class="cell" data-title="user_id">${list.user_id }</div>
						<div class="cell" data-title="user_name">${map[list.user_id]}</div>
					</div>
					</c:forEach>
					
				</div>
			</div>
		</div>
	</div>


</body>
</html>