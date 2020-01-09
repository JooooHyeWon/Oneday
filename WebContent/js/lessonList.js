$(function(){   
   $(".thumbnail").on({
      mouseenter: function(){                  
         // 전역변수로 선언해버림. 다른데서도 다 사용가능
         $this = $(this);   
         $code = $(this).children("input").val();
         getBMList($code);
      },         
      mouseleave:function(){
         removeBMList();
      }
   }); // hover end
   
   $(".post-content").click(function(){
      location.href="lessonInfo.lesson?sort=rv_recent&lesson_code="+$(this).attr("id");
   });
         
   function removeBMList(){
      $(".bookmark").html('');
   }

   function getBMList(code){      
      $.ajax({
         type:"post",
         url:"checkBM.cate",
         data:{"lesson_code" : code,"loginid" : $("#id").val() },
         dataType : "json",
         success : function(result){
            output ='';
            if(result==false){   
               add = "javascript:addBM('"+$code+"')";
               output +='<a onclick="'+add+'">'
                  +'<img id="bookmarkImg" alt="" src="image/empty_heart.png">' 
                  +'</a>';
            }else{
               add = "javascript:deleteBM("+$code+")";
               output +='<a href='+add+'>'
                  +'<img id="bookmarkImg" alt="" src="image/full_heart.png">' 
                  +'</a>';
            }
            // 여기 this는 전역변수로 내가 설정해둔 '선택한 애'
            $this.find(".bookmark").html(output); 
         }
      }); // ajax end
   }   
});

$(document).ready(function(){
   for(var i=1;i<8;i++){
      if($("#cate").val()==$(".catelist"+i).attr("id")){
         $(".catelist"+i).attr('style', "background:#fb878b75;");
      }
   }   
});

function addBM(code){
   location.href="bookmarkAdd.cate?lesson_code="+code+"&loginid="
            + $("#loginid").val()+"&cate="+$("#cate").val();
}

function deleteBM(code){   
   location.href="bookmarkDelete.cate?lesson_code="+code+"&loginid="
            + $("#loginid").val()+"&cate="+$("#cate").val();
}   