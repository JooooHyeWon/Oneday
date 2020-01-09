$(document).ready(function(){
	$("form").submit(function(){
		if($("#lesson_date").val()==""){
			alert("수업 날짜를 선택해 주세요");
			return false;
		}
		
		var start=$("#lesson_start").val().substring(0,2);
		var end=$("#lesson_end").val().substring(0,2);
		
		if(start>end){
			alert("수업 종료 시간이 시작 시간보다 이릅니다.");
			return false;
		}
		
		
	});
	
});