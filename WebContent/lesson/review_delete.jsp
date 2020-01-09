<%@page import="review.db.Review"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="java.util.List" %>
<html>

 <%
      List<Review> reviews = (List<Review>)request.getAttribute("review");            
 %>
<head>
<title>MVC 게시판</title>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
  $(function(){
	 
	  $("button").last().click(function(){
		  history.back();
	  });
  })
</script>
</head>
<style>
 #myModal {
	 display: block 
} 
</style>


<body>
	<!-- The Modal -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">


				<!-- Modal body -->
				<div class="modal-body">
					<form name="deleteForm" action="reviewDeleteAction.rv"
						method="post">
						<input type="hidden" name="num" value="${param.num }">

						<div class="form-group">
							<label for="pwd">비밀번호</label> 
							   <input type="password"
								class="form-control" placeholder="Enter password"
								name="user_pass" id="user_pass">
						</div>
						<button type="submit" class="btn btn-primary" >Submit</button>
					    <button type="button" class="btn btn-danger">Close</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>