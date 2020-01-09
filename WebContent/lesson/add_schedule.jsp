<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 하루 수업 - 수업 시간 등록하기</title>
<link rel="stylesheet" type="text/css" href="css/add_shcedule.css">
<script src="js/jquery-3.4.1.js"></script> 
<script src="js/add_schedule.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<% String lesson_code=(String)request.getParameter("lesson_code"); %>
	<script>
	$(function () {
		$("#lesson_date").datepicker({
			dateFormat:"yy/mm/dd",
			minDate:0,
			dayNamesMin:["일", "월", "화", "수", "목", "금", "토"],
			monthNames:["1월", "2월", "3월", "4월", "5월", "6월",
						"7월", "8월", "9월", "10월", "11월", "12월"],
			onSelect:function( d ){
	//-------------------------여기까지가 달력형태로 만든 후 클릭했을 때를 설정해주는 부분이야
	//-------------------------여기서부터는 선택한 날짜를 잘라서 각각의 요소에 넣어주는 부분이야
				var arr = d.split("/");
				$("#lesson_date_update").val(arr[0].trim()+arr[1].trim()+arr[2].trim());
	//-------------------------가져온 날짜를 Date Class 에 넣어주는 부분이야
				
				var date = new Date(
						$("#lesson_date").datepicker({dataFormat:"yy/mm/dd E"}).val());
				// 선택한 날짜의 요일 번호를 가져와서 무슨 요일인지 알아보려는거야
				var week = new Array("일", "월", "화", "수", "목", "금", "토");
				$("#lesson_yoil").val(week[date.getDay()]);
				
			}
		});
	});
	</script>
</head>
<body>
	<div class="container">
	
		<div>
		<h1 id='big-font'>수업 시간 등록</h1>
		</div>
		<form action="scheduleadd.lesson" method="post" name="frm" autocomplete="off">
		
				<div class="outer-div">
					<div class="inner-div">
						<label>최대 인원</label>
							<select name="max_user">
								<option value="1" selected>1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
							</select>
					</div>
					<div class="inner-div">
						<label>수업 날짜</label>
							<input type="text"  name="lesson_date" id="lesson_date">
							<input type="hidden" name="lesson_yoil" id="lesson_yoil"> 
							<input type="hidden" name="lesson_date_update" id="lesson_date_update">
							<input type="hidden" name="lesson_code" id="lesson_code" value=<%=lesson_code %>>
					</div>
				</div>
				
				<div class="outer-div">
					<div class="inner-div">
						<label>수업 시작</label>
							<select name="lesson_start" id="lesson_start">
								<option value="10:00" selected>10:00</option>
								<option value="10:30">10:30</option>
								<option value="11:00">11:00</option>
								<option value="11:30">11:30</option>
								<option value="12:00">12:00</option>
								<option value="12:30">12:30</option>
								<option value="13:00">13:00</option>
								<option value="13:30">13:30</option>
								<option value="14:00">14:00</option>
								<option value="14:30">14:30</option>
								<option value="15:00">15:00</option>
								<option value="15:30">15:30</option>
								<option value="16:00">16:00</option>
								<option value="16:30">16:30</option>
								<option value="17:00">17:00</option>
								<option value="17:30">17:30</option>
								<option value="18:00">18:00</option>						
							</select>
						</div>
						
					<div class="inner-div">
						<label>수업 종료</label>
							<select name="lesson_end" id="lesson_end">							
								<option value="11:00" selected>11:00</option>
								<option value="11:30">11:30</option>
								<option value="12:00">12:00</option>
								<option value="12:30">12:30</option>
								<option value="13:00">13:00</option>
								<option value="13:30">13:30</option>
								<option value="14:00">14:00</option>
								<option value="14:30">14:30</option>
								<option value="15:00">15:00</option>
								<option value="15:30">15:30</option>
								<option value="16:00">16:00</option>
								<option value="16:30">16:30</option>
								<option value="17:00">17:00</option>
								<option value="17:30">17:30</option>
								<option value="18:00">18:00</option>
								<option value="18:30">18:30</option>
								<option value="19:00">19:00</option>						
							</select>
					</div>
				</div>
			
			<div>
				<button class="alter" id="add-schedule">시간 등록 하기</button>
			</div>
		</form>
</div>

</body>
</html>