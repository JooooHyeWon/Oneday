<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 하루 수업 - 수업 등록하기 </title>
<meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="http://t1.daumcdn.net/postcode/api/core/191007/1570443254160/191007.js"></script>
<link type="text/css" rel="stylesheet" href="css/add_lesson.css">
<script src="js/jquery-3.4.1.js"></script>
<script src="js/add_lesson.js"></script>
<script>
var uf = "";
function file_add(size, ext) {
   var filecountTemp = parseInt(document.getElementById("file_cnt").value);
   var parents = document.getElementById("file_add_form");
   var br = document.createElement("br");
   br.setAttribute("id", "br" + (filecountTemp + 1));
   parents.appendChild(br);
   if (filecountTemp == 10) {
      alert("파일업로드는 최대 10개까지 가능합니다"); //< --- 이 부분 지우면 무한대로 추가됨.
      return;
   } else {
      var obj = document.createElement("input");
      obj.setAttribute("class","input-style-2");
      obj.setAttribute("type", "file");
      obj.setAttribute("size", size);
      obj.setAttribute("name", "class_file" + (filecountTemp + 1));
      obj.setAttribute("id", "file" + (filecountTemp + 1));
      parents.appendChild(obj);
   }
   document.getElementById("file_cnt").value = filecountTemp + 1;
}

function file_delete() {
   var filecountTemp = parseInt(document.getElementById("file_cnt").value);
   var parents = document.getElementById("file_add_form");
   var obj = document.getElementById('file' + filecountTemp);
   var br = document.getElementById('br' + filecountTemp);

   parents.removeChild(obj);
   parents.removeChild(br);
   
   document.getElementById("file_cnt").value = filecountTemp-1;
}
function Postcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
             $('#post').val(data.zonecode); //5자리 새우편번호 사용
            $('#lesson_location').val(fullRoadAddr);          
        }
    }).open();
}
</script>

</head>
<body>
   <div class="page-wrapper pb100 pt180 bg-red">
   <div class="wrapper-w960 wrapper">
      <div class="card card-2">
         <div class="card-heading"></div>
         <div class="card-body">
            <label class="title">
            <h1>수업 등록</h1>
            </label>
            
            <form method="post" enctype="multipart/form-data" 
               action="LessonAdd.lesson">
               
               <div class="row row-space">
                  <div class="col-2">
                     <label>카테고리</label>
                     <select name="lesson_type">
                        <option value="diy" selected>공예</option>
                        <option value="cook">요리</option>
                        <option value="art">미술</option>
                        <option value="music">음악</option>
                        <option value="lang">어학</option>
                        <option value="pic">사진/영상</option>
                     </select>
                  </div>
                  
               </div>
               
               <div class="input-group">
                  <input class="input-style-2" type="text" name="lesson_title"
                        id="lesson_title" placeholder="수업 명">
               </div>
               
               <div class="input-group">
                  <input class="input-style-2" type="text" name="lesson_price"
                        id="lesson_price" placeholder="수업 가격">
               </div>
               
               <div class="row2 row-space">
                  <div class="col-2">
                     <div class="input-group">
                     <input class="input-style-2" type="text" name="post" id="post"
                          id="post" placeholder="우편 번호">
                     </div>
                  </div>
                  <div class="col-2">
                     <input type="button" id="postsearch" onclick="Postcode()" value="우편 검색">
                  </div>
               </div>
               
               <div class="input-group">
                  <input class="input-style-2" type="text" name="lesson_location" id="lesson_location"
                         id="lesson_location" placeholder="수업 장소">
               </div>
               
               <div class="input-group">
                  <textarea class="input-style" type="text" cols=90 rows=5 name="tea_info"
                         id="tea_info" placeholder="강사 소개"></textarea>
               </div>
               
               <div class="input-group">
                  <input class="input-style-2" type="text" name="lesson_info"
                        id="lesson_info" placeholder="수업 요약">
               </div>
               
               <div class="input-group">
                  <textarea class="input-style" cols=90 rows=10 
                        name="lesson_content" id="lesson_centent" placeholder="수업 내용을 적어주세요."></textarea>
               </div>
               
               <div class="input-group">
                  <input class="input-style-2" type="text" name="lesson_chat"
                        id="lesson_chat" placeholder="오픈 채팅 주소">
               </div>
               
               
                  <div>
                  <div class="file_btn">
                        <input type="hidden" id="file_cnt" name="file_cnt" value="1" style=""> 
                        
                        <a href="javascript:file_add(10, 'class=input_write');">
                        <b>파일 추가</b></a>
                  </div>
                  <div class="file_btn">
                        <a href="javascript:file_delete();">
                        <b>파일 삭제</b></a>
                  </div>
                  </div>
               
               
                  <div id="file_add_form" class="input-group">
                     <input class="input-style-2 input_write" type="file" name="class_file1" id="file" size="10">   
                  </div>
               
               <div class="pt30">
                  <button class="btn-add" type="submit">등록하기</button>
               </div>
            
            </form>
         </div>
      
      </div>
   </div>
   </div>

</body>
</html>