$(document).ready(function(){
   var idcheck=false;
   $("#login").click(function(){
      $("#popup-login").css("display","block");
      $("#popup-join").css("display","none");
   });
   
   $(".to-join").click(function(){
      $("#popup-join").css("display","block");
      $("#popup-login").css("display","none");
   });
   
   $("#join").click(function(){
      $("#popup-join").css("display","block");
      $("#popup-login").css("display","none");
   });
   
   $(".to-login").click(function(){
      $("#popup-login").css("display","block");
      $("#popup-join").css("display","none");
   });
   
   $("#idcheck").blur(function(){
      var id=$("#id").val();
      pattern=/^[a-zA-Z0-9]{6,15}$/;
      if(!pattern.test(id)){
         $("#id").css("border-bottom","2px solid red")
               .css("color","red");
         $("#id").val("영,숫자 6글자 이상 입니다.");
         
         $("#id").focus(function(){
            $("#id").val("");
            $("#id").css("border-bottom","2px solid lightgray")
                  .css("color","black");
            
         });
      }
   });
   
   $("#idcheck").click(function(){
      var id=$("#id").val();
      var user_type=$("input[type=radio]:checked").val();
      
      console.log(id)
      console.log(user_type)
         pattern=/^[a-zA-Z0-9]{6,}$/;
         if(!pattern.test(id)){
            $("#id").css("border-bottom","2px solid red")
                  .css("color","red");
            $("#id").val("영,숫자 6글자 이상 입니다.");
         
            $("#id").focus(function(){
               $("#id").val("");
               $("#id").css("border-bottom","2px solid lightgray")
                     .css("color","black");
            
         });
         return false;
      }else{
         if(user_type==null){
            alert("수강생/강사 중에 선택하세요");
         }else{
         $.ajax({
            url:"idcheck.net",
            data:{"id":id ,"user_type":user_type},
            success:function(resp){
               if(resp=='-1'){
                  alert("사용 가능한 아이디 입니다.");
                  checkid=true;
               }else{
                  alert("사용 불가능한 아이디 입니다.");
                  checkid=false;
               }
            }//if
         
         });//ajax
         }
      }
   });
   
   $("#password").blur(function(){
      var password=$("#password").val();
      pattern=/^[a-zA-Z0-9]{8,}$/;
      if(!pattern.test(password)){
         $("#password").css("border-bottom","2px solid red")
                     .prop("type","text")
                  .css("color","red");
         $("#password").val("영,숫자 섞어서 8글자 이상 입니다.");
         
         $("#password").focus(function(){
            $("#password").val("");
            $("#password").css("border-bottom","2px solid lightgray")
                     .prop("type","password")
                     .css("color","black");
            
         });
         
      }
   });   
   
   $("#password_check").blur(function(){
      var password=$("#password").val();
      var password_check=$("#password_check").val();
      pattern=/^[a-zA-Z0-9]{8,}$/;
      if(password!=password_check){
         $("#password_check").css("border-bottom","2px solid red")
                        .prop("type","text")
                     .css("color","red");
         $("#password_check").val("비밀번호가 일치하지 않습니다.");
         
         $("#password_check").focus(function(){
            $("#password_check").val("");
            $("#password_check").css("border-bottom","2px solid lightgray")
                           .prop("type","password")
                        .css("color","black");
            
         });
         
      }
      
   });   
   
   $("#phone").blur(function(){
      var phone=$("#phone").val();
      pattern=/^[0][1][0-9][0-9]{4}[0-9]{4}$/;
      if(!pattern.test(콜)){
         $("#phone").css("border-bottom","2px solid red")
                  .css("color","red");
         $("#phone").val("전화번호 형식을 확인하세요");
         
         $("#phone").focus(function(){
            $("#phone").val("");
            $("#phone").css("border-bottom","2px solid lightgray")
                     .css("color","black");
            
         });
         
      }
   });   
   
   
   $("#join_btn").click(function(){
      var user_type=$("input[type=radio]:checked").val();
      if(user_type==null) {
            alert('수강생/강사 중에 선택하세요');
            return false;
         } 
      if($("#id").val()==""){
         alert("아이디를 입력해주세요");
         return false;
      }
      
      if($("#password").val()==""){
         alert("비밀번호를 입력해주세요");
         return false;
      }
      
      if($("#password_check").val()==""){
         alert("비밀번호 확인을 입력해주세요");
         return false;
      }
      
      if($("#name").val()==""){
         alert("이름 입력해주세요");
         return false;
      }
      
      if($("#phone").val()==""){
         alert("전화번호를 입력해주세요");
         return false;
      }
      
      if($("#email").val()==""){
         alert("이메일을 입력해주세요");
         return false;
      }else{
         var email=$("#email").val();
         pattern=/^\w+@\w+[.]\w{3}$/;
         if(!pattern.test(email)){
            $("#email").css("border-bottom","2px solid red")
                     .css("color","red");
            $("#email").val("이메일 형식을 확인하세요");
            
            $("#email").focus(function(){
               $("#email").val("");
               $("#email").css("border-bottom","2px solid lightgray")
                        .css("color","black");
               
            });
            
         }
      }
      
   });
   
   $("#login_btn").click(function(){
      var user_type=$("input[type=radio]:checked").val();
      if(user_type==null) {
            alert('수강생/강사 중에 선택하세요');
            return false;
         } 
      
      if($("#login_id").val()==""){
         alert("아이디를 입력해주세요");
         return false;
      }
      
      if($("#login_password").val()==""){
         alert("비밀번호를 입력해주세요");
         return false;
      }
      
       
   });   
   
   $("#to-userinfo").click(function(){
      location.href="userInfo.ue";
   });
   
   $("#to-teacherinfo").click(function(){
      location.href="teacherInfo.te";
   });
   $("#logout").click(function(){
      location.href="logout.net";
   });
   
   $('.flyout-search').submit(function(){
	      var id=$("#loginid").val();
	      console.log(id);
	      if(id==""){
	         alert("로그인 먼저 해주세요");
	         return false;
	      }
	}); 
});