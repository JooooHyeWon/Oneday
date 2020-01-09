<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/jquery-3.4.1.js"></script>
<html>
<head>
<link href='<%=request.getContextPath()%>/css/bootstrap.css' rel='stylesheet' type='text/css'>
<title>회원 목록 보기</title>
<link type="text/css" rel="stylesheet" href="css/admin.css">
<jsp:include page="/main/admin_header.jsp"/>
	<script>
		$(document).ready(function(){
			
			$("tbody >tr > td:nth-child(4) > a").click(function(event){
	              
	              var answer = confirm("정말 삭제하시겠습니까?");
	              console.log(answer); //취소를 클릭한 경우 : false
	              
	              if(!answer) { //취소를 클릭한 경우
	                 event.preventDefault();
	              }
	           });
		});
	</script>
</head>
<body>
<div class="container">
<div id="btn_group">
  <button name="manage" class="manage now" value="user" onclick="location.href='userInfo.net'">회원 관리</button>
  <button name="manage" class="manage" value="lesson" onclick="location.href='lesson.net'">수업 관리</button>
  <button name="manage" class="manage" value="teacher" onclick="location.href='teacher.net'">강사 관리</button>
</div>
  
   <%-- 회원이 있는 경우 --%>
 <c:if test="${listcount > 0 }">
   <table class="list">
     <thead>
      <tr>
        <td>회원 아이디</td><td>회원 비밀번호</td><td>회원 이름</td><td>회원 수 : ${listcount }</td>
      </tr>
      </thead>   
      <tbody>
       <c:forEach var="user" items="${userlist }">
      <tr>
       <td>    
           ${user.user_id}
       </td>
      <td>${user.user_pass}</td>  
      <td>${user.user_name}</td>    
      <td><a href="delete.net?code=${user.user_id}&type=userInfo">삭제</a></td>   
       </tr>
   </c:forEach>
   </tbody>
   </table>
 <div class="center-block">
      <div class="row">
          <div class="col">
               <ul class="pagination">      
         <c:if test="${page <= 1 }">
            <li class="page-item">
            <a class="page-link current" href="#">이전&nbsp;</a>
            </li>
         </c:if>
         <c:if test="${page > 1 }">         
             <li class="page-item">
             <a href="userInfo.net?page=${page-1}&search_field=${search_field}&search_word=${search_word}" 
                class="page-link">이전</a>&nbsp;
            </li> 
         </c:if>
               
         <c:forEach var="a" begin="${startpage}" end="${endpage}">
            <c:if test="${a == page }">
               <li class="page-item" >
               <a class="page-link current" href="#" >${a}</a>
               </li>
            </c:if>
            <c:if test="${a != page }">
                <li class="page-item">
                   <a href="userInfo.net?page=${a}&search_field=${search_field}&search_word=${search_word}" 
                      class="page-link">${a}</a>
                </li>   
            </c:if>
         </c:forEach>
         
         <c:if test="${page >= maxpage }">
            <li class="page-item">
                <a class="page-link current" href="#">&nbsp;다음</a> 
            </li>
         </c:if>
         <c:if test="${page < maxpage }">
            <li class="page-item">
               <a href="userInfo.net?page=${page+1}&search_field=${search_field}&search_word=${search_word}" 
                  class="page-link ">&nbsp;다음</a>
            </li>   
         </c:if>
      </ul>
   </div>
  </div>
 </div>
 </c:if>
</div> 

<%-- 회원이 있는 경우 --%>
 <c:if test="${listcount == 0 }">
   <h1>  검색 결과가 없습니다.  </h1>
 </c:if>
</body>
</html>
