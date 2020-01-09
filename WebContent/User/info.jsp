<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<% 
	HttpSession session=request.getSession();
	String id=(String)session.getAttribute("id"); 
%>
<script>
function windowopenPopup(){
	window.open('passchange.ue?id=<%=id%>','asdf','width=670, height=510, menubar=no, status=no, toolbar=no');
}
</script>
<div class="wrap">
	<div>
	<h1 id='big-font'>내 정보</h1>
	</div>
	<form action="userInfoUpdate.ue" method="post" id="user_info_change">
	<div id="myInfo">
		<div class="info">
			<label>아이디</label><input type="text" id="USER_ID" name="USER_ID" value="${user.user_id }"  readonly>
		</div>
		<div class="info">
			<label>비밀번호</label><input type="password" id="USER_PASSWORD" name="USER_PASSWORD" value="${user.user_pass }" readonly><button class="alter" id="alter-pass" onclick="windowopenPopup()">비밀번호 수정하기</button>
		</div>
		<div class="info">
			<label>이름</label><input type="text" id="USER_NAME" name="USER_NAME" value="${user.user_name }" placeholder="${user.user_name }" required>
		</div>
		<div class="info">
			<label>전화번호</label><input type="text" id="USER_PHONE" name="USER_PHONE" value="${user.user_phone }" placeholder="${user.user_phone }" required>
		</div>
		<div class="info">
			<label>이메일</label><input type="text" id="USER_EMAIL" name="USER_EMAIL" value="${user.user_email }" placeholder="${user.user_email }" required>
		</div>
	</div>
	<div>
	<button type="submit" class="alter" id="alter-accept">수정하기</button>
	</div>
	</form>
</div>