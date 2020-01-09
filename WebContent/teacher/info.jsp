<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="wrap">
	<div>
	<h1 id='big-font'>내 정보</h1>
	</div>
	<div id="myInfo">
		<div class="info">
			<label>아이디</label>
			<!-- <input type="text" id="id" name="id" readonly> -->
			<label>${id }</label>
		</div>
		<div class="info">
			<label>비밀번호</label>
			<input type="password" id="password" name="password" value="asdfasdf" readonly><button class="alter" id="alter-pass">비밀번호 수정하기</button>
		</div>
		<div class="info">
			<label>이름</label><input type="text" id="name" name="name">
		</div>
		<div class="info">
			<label>전화번호</label><input type="text" id="tel" name="tel">
		</div>
		<div class="info">
			<label>이메일</label><input type="text" id="email" name="email">
		</div>
	</div>
	<div>
	<button class="alter" id="alter-accept">수정하기</button>
	</div>
</div>