<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 하루 수업 - 내 수업 목록</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<jsp:include page="../main/header.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="css/userLikeLesson.css">
<link rel="stylesheet" type="text/css" href="css/lessonlist.css">
<script src="js/jquery-3.4.1.js"></script>
<script src="js/userMyLessonList.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> -->
<script>
$(document).ready(function(){
$('#reviewform').on('submit', function(e){
    e.preventDefault();
    $.ajax({
        url: "reviewWrite.rv",
        type: "POST",
        data: $("#reviewform").serialize(),
        success: function(data){
           alert(data);
            $('#just-data').modal("hide");
        },error :function(data){
           alert("sad");
        }
    });
});

$('.star').click(function(){
      console.log($(this).attr('id'));
      $(this).parent().children('span').removeClass('checked');
      $(this).parent().children('span').empty();
       $(this).addClass('checked').prevAll('span').addClass('checked');
       $(this).append("<input type='hidden' name='star' value="+$(this).attr('id')+">");
        return false;
   })
});
</script>
</head>
<body>
   <div class="container">
      <div class="title-box">
         <h1>나의 하루수업</h1>
         <ul>
            <li class="cursor"><a href="userInfo.ue">내 정보</a></li>
            <li class="cursor"><a href="userLikeLesson2.ue">좋아요 한 수업</a></li>
            <li class="cursor on"><a href="userMyLessonList.ue">내 수업 목록
                  <div class="title-tab-cir"><%=request.getAttribute("ListCount") %></div>
            </a>
            </li>
         </ul>
      </div>
      <div class="teacher-info">
         <!-- <div class="wrap">
            <div>
               <h1 id='big-font'>내 수업 목록</h1>
            </div>
            <div class="content-wrap">
               <div class="pic-wrap">
                  
               </div>
            </div>
         </div> -->
      </div>
   </div>
   
   
   <div class="modal fade" id="just-data" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">리뷰 작성</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="reviewWrite.rv" method="post" id="reviewform">
      <div class="modal-body">
             
                <div class="m-wrap" style="width:300px;">
                   <div class="left-wrap">
                      <div class="img">
                         <img src="" style="width: 100%;height: 319px;">
                      </div>
                      <div class="left-content">
                         <span id="title" name ="title"></span>
                         <span id="content"></span>
                         <input hidden="true" name="code" id="code_check" value="">
                      </div>
                   </div>
                   <div class="right-wrap">
                      <table>
                         <tr>
                            <td><label>리뷰 내용</label><textarea name ="reaview_content" placeholder="내용을 입력해주세요!" required></textarea></td>
                         </tr>
                      </table>
                       <div class="star_style">
                       <span class="fa fa-star star" id="1"></span>
                        <span class="fa fa-star star" id="2"></span>
                        <span class="fa fa-star star" id="3"></span>
                       <span class="fa fa-star star" id="4"></span>
                        <span class="fa fa-star star" id="5"></span>
                        </div>
                   </div>
                </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">작성 취소</button>
        <button type="submit" class="btn btn-primary">작성하기</button>
      </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>

   