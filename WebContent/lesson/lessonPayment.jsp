<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="EUC-KR">
   <title>나의 하루 수업 - 일정 선택하기</title>
   <link type="text/css" rel="stylesheet" href="css/style.css">
   <link type="text/css" rel="stylesheet" href="css/bootstrap-soo.css">
   <link type="text/css" rel="stylesheet" href="css/testbody.css">
   <link type="text/css" rel="stylesheet" href="css/pay.css">
   
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
   <script>   
      $(document).ready(function(){
         $('#payform').submit(function(){
            
            var agree_ck = $('input:checkbox:checked').length;
            if(agree_ck < 1){
               alert('결제에 동의하세요');
               return false;
            }         
            
         });         
      });      
   </script>
</head>
<body>

   <div class="tutor_cont">
      <div class="title_box">
         <h3>수업신청</h3>
         <div class="steps">
            <li><b>01</b>일정/장소</li>
            <img src="image/next.png">
            <li class="on"><b>02</b>결제</li>
         </div>
      </div>      
   </div>
   
   <div>
      <form action="gopay.lesson?lesson_code=${lesson.lesson_code }" id="payform">
         <fieldset>
            <legend class="screen_out"></legend>
               <div class="wrap_head">
                  <h3 class="tit_info">${lesson.lesson_title}</h3>
                  <dl class="list_price">
                     <dt>상품 금액</dt>
                     <dd>${lesson.lesson_price }<span class="txt_won">원</span></dd>
                  </dl>
               </div>
               <div class="wrap_method">
                  <button type="submit" class="method_type method_simple" onclick="">
                  
                     <div class="tit_pay">
                        <img src="image/logo_kakaopay.png" style="border:none">
                        <span>카카오페이</span>
                     </div>
                        
                  </button>
                  
                  <input type="hidden" name="lesson_code" value="${lesson.lesson_code }">
                  <input type="hidden" name="lesson_de_code" value="${lesson_de_code }">
                  
                  <!-- <button type="submit" class="method_type method_normal" onclick="">
                                       
                     <div  class="method_head" id="divNormalPayment">
                        <div class="btn_toggle">
                           <img src="image/credit.png" class="tit_normal"><span>일반결제</span>                        
                        </div>
                     </div>
                  </button> -->
                        
                  <div class="checkbox_agree">
                     <input type="checkbox" id="chbox_agree_in" name="chbox_agree_in" class="chbox_agree_in" value="check"><span class="chbox_agree_span">결제에 동의합니다.</span>   
                  </div>                  
               </div>               
         </fieldset>
      </form>   
   </div>
</body>
</html>