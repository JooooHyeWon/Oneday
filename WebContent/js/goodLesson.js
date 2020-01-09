
$(document).ready(function() {
   $.ajax({
      type : "post",
      url : "getgoodLesson.ue",
      data : {"lesson_type" : $(".cate ul li:first-child a").attr('id')},
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
            $('.subject a').prop("href","lessonInfo.lesson?lesson_code="+this.lesson_code+"&sort=rv_recent");
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
      output = "";
      $(".cate ul li a").on("click", function() {
      $('.subject a').text("");
      $('.sub-contents span').text("");
      $.ajax({
         type : "post",
         url : "getgoodLesson.ue",
         data : {"lesson_type" : $(this).attr('id')},
         dataType : "json",
         success : function(data) {
            
            console.log(data);
            $(data).each(function(){
               $('.cate ul li a').click(function(){
                  $('.subject a').text("");
                  $('.sub-contents span').text("");
               })
               $('.subject a').prop("href","lessonInfo.lesson?lesson_code="+this.lesson_code+"&sort=rv_recent");
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
   })
   
   $('.cate ul li').find("a").each(function(){
	   if($(this).hasClass("on")){
		   $('.subject a').text("");
		      $('.sub-contents span').text("");
		      $.ajax({
		         type : "post",
		         url : "getgoodLesson.ue",
		         data : {"lesson_type" : $(this).attr('id')},
		         dataType : "json",
		         success : function(data) {
		            
		            console.log(data);
		            $(data).each(function(){
		               $('.cate ul li a').click(function(){
		                  $('.subject a').text("");
		                  $('.sub-contents span').text("");
		               })
		               $('.subject a').prop("href","lessonInfo.lesson?lesson_code="+this.lesson_code+"&sort=rv_recent");
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
   })
})