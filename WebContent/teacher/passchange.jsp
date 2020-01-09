<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경 페이지</title>
<link rel="stylesheet" type="text/css" href="css/passchage.css">
<script src="js/jquery-3.4.1.js"></script> 
	<script>
		$(document).ready(function(){
			pattern = /^[a-zA-z0-9]{8,}$/;
			$("#alter-pass").blur(function(){
				var pass=$("#alter-pass").val();
				if(!pattern.test(pass)){
					$("#alter-pass").css("border-bottom","1px solid red")
									.prop("type","text")
									.css("color","red")
									.val("영, 숫자 8글자 이상 적어주세요");
					$("#alter-pass").focus(function(){
						$("#alter-pass").css("border-bottom","1px solid #fb878b")
										.prop("type","password")
										.val('')
										.css("color","black");
					});			
				}
			});
			
			$("form").submit(function(){
				var passcheck=$("#alter-pass-check").val();
				if(!pattern.test(passcheck)){
					$("#alter-pass-check").css("border-bottom","1px solid red")
									.css("color","red")
									.val("영, 숫자 8글자 이상 적어주세요");
					$("#alter-pass-check").focus(function(){
						$("#alter-pass").css("border-bottom","1px solid #fb878b")
										.prop("type","password")
										.val('')
										.css("color","black");
					});	
						return false;
									
				}else{
					var pass=$("#alter-pass").val();
					if(pass!=passcheck){
						alert("비밀번호 확인이 일치하지 않습니다.");
						return false;
					}
				}
			});
		});
	</script>
</head>
<body>
	<div style="padding:10%;">
	
		<div>
		<h1 id='big-font'>비밀번호 변경</h1>
		</div>
			<div id="myInfo">
		<form action="teacherPassUpdate.te" method="post">
		<div class="info">
			<label>원래 비밀번호</label>
			<input type="password" id="origin-pass" name="origin-pass" value="" required>
		</div>
		<div class="info">
			<label>바꿀 비밀번호</label>
			<input type="password" id="alter-pass" name="alter-pass" value="" required>
		</div>
		<div class="info">
			<label>비밀번호 확인</label>
			<input type="password" id="alter-pass-check" name="alter-pass-check" value="" required>
		
		</div>
		<div>
		<button class="alter" id="alter-accept">비밀 번호 변경하기</button>
		</div>
		</form>
	</div>
</div>
</body>
</html>