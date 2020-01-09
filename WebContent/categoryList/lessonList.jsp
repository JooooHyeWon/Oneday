<%@page import="lesson.db.Lesson"%>
<%@page import="gallery.db.Gallery"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../main/header.jsp"/>
<link href='<%=request.getContextPath()%>/css/header.css' rel='stylesheet' type='text/css'>
<link href='<%=request.getContextPath()%>/css/category.css?after' rel='stylesheet' type='text/css'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>   
<script src="js/lessonList.js" charset="UTF-8"></script>   
<title>하루수업</title>
</head>
<body>
<div class="content-wrap" style="margin: 0 15%">
<input type="hidden" id="id" value="${id }" name="id">
<div class="margin1"></div>
<div class="content-wrap2">
   <div class="cate-wrap">
      <ul>
         <a href="popular.cate?cate=popular"><li class="catelist1" id="popular">전체 강의</li></a>
         <a href="diy.cate?cate=diy"><li class="catelist2" id="diy">공예</li></a>
         <a href="cook.cate?cate=cook"><li class="catelist3" id="cook">요리</li></a>
         <a href="art.cate?cate=art"><li class="catelist4" id="art">미술</li></a>
         <a href="music.cate?cate=music"><li class="catelist5" id="music">음악</li></a>
         <a href="lang.cate?cate=lang"><li class="catelist6" id="lang">어학</li></a>
         <a href="pic.cate?cate=pic"><li class="catelist7" id="pic">사진/영상</li></a>
      </ul>
   </div>
</div>

<div class="clear"></div>

<%    /* 정렬용 */
   String cate = (String)request.getAttribute("cate");
   request.setAttribute("cate", cate);
%>
<input type="hidden" id="cate" value="<%= cate %>" name="cate">

	<div class="lessonListwrap">
	<div class="btn">
   <input class="sortbtn" type="button" value="인기순" onclick="location.href='list.cate?cate=<%=cate%>&sort=bm'">
   <input class="sortbtn" type="button" value="신상순" onclick="location.href='list.cate?cate=<%=cate%>&sort=nw'">
	</div>
<c:forEach var="lesson" items="${Lesson}">
 <c:forEach var="pic" items="${mainPic}">
  <c:if test="${pic.lesson_code == lesson.lesson_code}">
     
    <div class="column">
    <!-- Post-->
    <div class="post-module">
      <!-- Thumbnail-->
      <div class="thumbnail">
      <input type="hidden" id="lesson${lesson.lesson_code}" value="${lesson.lesson_code}" name="lesson_code">   
        <div class="date">
          <div class="bookmark">
                    
        </div>
        </div>
        <img src="${pic.pic_name}"/>
      </div>
      <!-- Post Content-->
      <div class="post-content" id="${lesson.lesson_code}">
        <div class="category">${lesson.lesson_type}</div>
        <h1 class="title">${lesson.lesson_title}</h1>
        <h2 class="sub_title">${lesson.lesson_info}</h2>
        <div class="post-meta"><span class="comments">♥${lesson.bookmark}</span></div>
      </div>
     </div> 
   </div>
  </c:if>
 </c:forEach>
</c:forEach>
</div>
</div>
</body>
</html>