<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  <link type="text/css" rel="stylesheet" href="css/header.css"> -->
 <script src="js/jquery-3.4.1.js"></script> 
  <script src="js/header.js"></script>
<style>
   #headermargin{margin:70px;}
   .header_new .logo {
   text-align : center;
}
.header_new .rcont {
   width: 29%;
   box-sizing: border-box;
   float: right;
   position: relative;
   padding-right: 1%;
   padding-left: 2%;
   padding-top: 3.5%;
}

.header_new .rcont label {
   font-weight: 600;
   font-size: 16px;
   color: #333333;
   width: 70%;
   height: 30px;
   text-align: center;
   padding-right: 3%;
   cursor: pointer;
   padding-top: 28px;
}
</style>
<!-- Header 부분 (로고, 검색창, 회원가입, 로그인 등) -->
<div class="container">
   <div class="header_new">
      <div class="hcont">
         <div class="logo">
           <img src="image/logo.png" style="margin-top:20px; width:200px; height:100px;">
         </div>
            
         <div class="rcont">
             <label id="to-manage">관리자님&nbsp;&nbsp;</label>
             <label id="logout">로그 아웃</label>
         </div>
      </div>
   </div>
</div>
<div id="headermargin"></div>
<!-- Header 부분 끝 -->