$(document).ready(function(){
   
   $('form').submit(function(){
      if($("#lesson_title").val()==""){
         alert("수업 제목을 넣어주세요");
         return false;
      }
      
      if($("#lesson_price").val()==""){
         alert("수업 가격을 정해주세요");
         return false;
      }
      
      if($("#post").val()==""){
         alert("우편 번호 찾기를 해주세요");
         return false;
      }
      
      if($("#lesson_location").val()==""){
         alert("수업 장소를 적어주세요");
         return false;
      }
      
      if($("#tea_info").val()==""){
         alert("강사 소개를 적어주세요");
         return false;
      }
      
      if($("#lesson_info").val()==""){
         alert("수업 요약을 적어주세요");
         return false;
      }
      
      if($("#tea_info").val()==""){
         alert("강사 소개를 적어주세요");
         return false;
      }
      
      if($("#file").val()==""){
         alert("수업 관련 사진을 첨부해주세요");
         return false;
      }
      
      if($("#lesson_content").val()==""){
         alert("수업 내용을 작성 해주세요");
         return false;
      }
      
      
   });
   
   
});

