<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
function moveCate(cate){
	var id=$("#loginid").val();
	console.log(id)
	if(id==""){
		alert("로그인 먼저 해주세요");
	}else{
   	location.href=cate+".cate?cate="+cate;
	}
}
 	var current=1;
	$(document).ready(function(){
		
		$('.subject a').on("click",function(){
			   var id=$("#loginid").val();
			   console.log(id);
			   if(id==""){
					alert("로그인 먼저 해주세요");
					return false;
				}
		}); 
		
		
	 $('.cate ul li').eq(current-1).find("a").addClass("on");
	 $.ajax({
	      type : "post",
	      url : "getgoodLesson.ue",
	      data : {"lesson_type" : $('.cate ul li').eq(current-1).find("a").attr('id')},
	      dataType : "json",
	      success : function(data) {
	         $('.subject a').text("");
	         $('.sub-contents span').text("");
	         console.log(data);
	         $(data).each(function(){
	           
	            $('.subject a').prop("href","lessonInfo.lesson?sort=rv_recent&lesson_code="+this.lesson_code);
	            $('.subject a').append(this.lesson_title);
	            $('.sub-contents span').append(this.lesson_info);
	            $('.img div img').attr('src',this.pic_name);
	         })
	      },
	      error : function(data){
	         alert('ajax 에러');
	         return false;
	      }
	   })  
	  
	   
	   $(".cate ul li").click(function(e){
		e.preventDefault();
		$(".cate ul li").find("a").each(function(){
			if($(this).hasClass("on")){
				$(this).removeClass("on");
			}
		}); 
			
			
			$.ajax({
			      type : "post",
			      url : "getgoodLesson.ue",
			      data : {"lesson_type" : $(this).find("a").attr('id')},
			      dataType : "json",
			      success : function(data) {
			         $('.subject a').text("");
			         $('.sub-contents span').text("");
			         console.log(data);
			         $(data).each(function(){
			           
			            $('.subject a').prop("href","lessonInfo.lesson?sort=rv_recent&lesson_code="+this.lesson_code);
			            $('.subject a').append(this.lesson_title);
			            $('.sub-contents span').append(this.lesson_info);
			            $('.img div img').attr('src',this.pic_name);
			         })
			      },
			      error : function(data){
			         alert('ajax 에러');
			         return false;
			      }
			   })  
			   
			$(this).find("a").addClass("on");
			current=$(this).index();
			current=current+1;
			 
		});
	});
	
	var interval;
	interval=setInterval("interMove()",2000);

	

function interMove(){
		if(current<6){
			current=current+1;
		}else{
			current=1;
		}
		console.log("current="+current);
	
	$('.cate ul li').find("a").each(function(){
		if($(this).hasClass("on")){
			$(this).removeClass("on");
		}
	});
	$('.cate ul li').eq(current-1).find("a").addClass("on");
	
	$.ajax({
	      type : "post",
	      url : "getgoodLesson.ue",
	      data : {"lesson_type" : $('.cate ul li').eq(current-1).find("a").attr('id')},
	      dataType : "json",
	      success : function(data) {
	         $('.subject a').text("");
	         $('.sub-contents span').text("");
	         console.log(data);
	         $(data).each(function(){
	           	 	$('.cate ul li a').click(function(){
	            	$('.subject a').text("");
	              	$('.sub-contents span').text("");
	            })
	            $('.subject a').prop("href","lessonInfo.lesson?sort=rv_recent&lesson_code="+this.lesson_code);
	            $('.subject a').append(this.lesson_title);
	            $('.sub-contents span').append(this.lesson_info);
	            $('.img div img').attr('src',this.pic_name);
	         })
	      },
	      error : function(data){
	         alert('ajax 에러');
	         return false;
	      }
	   })
}	

</script>

<input type="hidden" id="loginid" value="${id }">
<div class="content-wrap" style="margin: 0 15%">
      <div class="content">
         <div class="img">
            <div>
               <img src="http://www.imgworlds.com/wp-content/uploads/2015/12/18-CONTACTUS-HEADER.jpg">
            </div>
         </div>
      <div class="right">
         <div class="cate">
            <ul>
               <li><a href="#" id="diy">공예</a></li>
               <li><a href="#" id="cook">요리</a></li>
               <li><a href="#" id="art">미술</a></li>
               <li><a href="#" id="music">음악</a></li>
               <li><a href="#" id="lang">어학</a></li>
               <li><a href="#" id="pic">사진/영상</a></li>
            </ul>
         </div>
         <div class="sub">
            <div class="subject"><a href="#">플라워 클래스</a> <div class="best"><span>Best!</span></div></div>
            <div class="sub-contents"><span>인테리어에 포인트를 줄 수 있는 아름다운 꽃꽂이 하러 오세요! </span></div>
         </div>
      </div>
      </div>
   </div>
   
   <c:if test="${user_type=='1'}">
	<div class="tea_footer"><a href="lessonAddForm.lesson">나의 수업을 등록해 보아요&nbsp;<img src="image/smile.png"></a></div>
	</c:if>
	
	<c:if test="${user_type!='1' }">
   <div class="content-wrap2">
   <div class="cate-wrap">
      <ul>
         <li><a href="javascript:moveCate('popular')">전체 강의</a></li>
         <li><a href="javascript:moveCate('diy')">공예</a></li>
         <li><a href="javascript:moveCate('cook')">요리</a></li>    
		<li><a href="javascript:moveCate('art')">미술</a></li>
         <li><a href="javascript:moveCate('music')">음악</a></li>
         <li><a href="javascript:moveCate('lang')">어학</a></li>
         <li><a href="javascript:moveCate('pic')">사진/영상</a></li>
      </ul>
   	</div>
	</div>
	</c:if>