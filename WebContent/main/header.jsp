<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <link type="text/css" rel="stylesheet" href="css/header.css">
 <script src="js/jquery-3.4.1.js"></script> 
  <script src="js/header.js"></script>
<!-- Header 부분 (로고, 검색창, 회원가입, 로그인 등) -->
<div class="container">
   <div class="header_new">
      <div class="hcont">
         <div class="logo">
            <a href="main.net"><img src="image/logo.png" style="margin-top:20px; width:130px; height:58px;"></a>
         </div>
      
         <div class="search">   
            <form class="flyout-search" action="search.cate" name="search" accept-charset="utf-8">
            
               <div class="lcont">                  
                  <input type="text" name="search" id="search" 
                        autocomplete="off" placeholder="배우고 싶은 수업을 검색하세요">            
               </div>
            </form>
         </div>
      
         <div class="rcont">
            <c:if test="${empty id }">
            <label id="login">로그인&nbsp;&nbsp;</label>
             <label id="join">회원가입</label>
             </c:if>
             <c:if test="${user_type=='0'}">
             <label id="to-userinfo">${id} 님 &nbsp;&nbsp;</label>
             <label id="logout">로그아웃</label>
             </c:if>
              <c:if test="${user_type=='1'}">
             <label id="to-teacherinfo">${id} 강사님 &nbsp;</label>
             <label id="logout">로그아웃</label>
             </c:if>
             <c:if test="${user_type=='admin'}">
             <label id="to-manage">관리자님&nbsp;&nbsp;</label>
             <label id="logout">로그아웃</label>
             </c:if>
         </div>
      </div>
   </div>
</div>
<!-- Header 부분 끝 -->

<!-- login popup -->
<div id="popup-login" class="popup popup-login">
      <div class="popuptop" onclick="location.href='main.net'" style="width: 100%; height: 25%;"></div>
      <div class="popupright" onclick="location.href='main.net'" style="width: 42%; height: 100%; float:right"></div>
      <div class="popupleft" onclick="location.href='main.net'" style="width: 43%; height: 100%; float:left"></div>
      <div class="popupbottom" onclick="location.href='main.net'" style="width: 100%; height: 19%; position: absolute; top: 680px;"></div>
      <div class="popup-content">
         <label><img src="image/logo.png"></label>
         <br><br>
         <form action="login.net" method="post" id="login">
            
            <input type="radio" name="user_type" value="0" id="login_user" >
            <label for="login_user">수강생</label>
            
            <input type="radio" name="user_type" value="1" id="login_teacher">
            <label for="login_teacher">강사</label>
            
            
            <br><br><br>
            <input type="text" name="id" id="login_id" placeholder="ID">
            <br>
            <input type="password" name="password" id="login_password" placeholder="Password">
            <br><br>
            <button type="submit" class="btn" id="login_btn">로그인</button>
            </form>
      </div>
      <label class="to-join">아직 회원이 아니세요?</label>
   </div>
<!-- login popup 끝 -->   

<!-- join popup -->   
   <div id="popup-join" class="popup popup-join">
      <div class="popuptop" onclick="location.href='main.net'" style="width: 100%; height: 14%;"></div>
      <div class="popupright" onclick="location.href='main.net'" style="width: 36%; height: 100%; float:right"></div>
      <div class="popupleft" onclick="location.href='main.net'" style="width: 36%; height: 100%; float:left"></div>
      
      <div class="popup-content">
         <label><img src="image/logo.png"></label>
         <br><br>
         <form action="join.net" method="post" id="join">
            
            <input type="radio" name="user_type" value="0" id="join_user" >
            <label for="join_user">수강생</label>
            
            <input type="radio" name="user_type" value="1" id="join_teacher">
            <label for="join_teacher">강사</label>
            
            
            <br><br>
            <div>
            <input type="text" id="id" name="id" placeholder="ID"> <button type="button" id="idcheck" >중복확인</button>
            </div>
            <input type="password" id="password" name="password" placeholder="Password">
            <br>
            <input type="password" id="password_check" name="password_check" placeholder="Password check">
            <br>
            <input type="text" id="name" name="name" placeholder="Name">
            <br>
            <input type="text" id="phone" name="phone" placeholder="Phone(00000000000)">
            <br>
            <input type="text" id="email" name="email" placeholder="E-mail">
            <button type="submit" class="btn" id="join_btn">회원가입</button>
            </form>
      </div>
      <label class="to-login">이미 회원이세요?</label>
   </div>
<!-- join popup 끝 -->   