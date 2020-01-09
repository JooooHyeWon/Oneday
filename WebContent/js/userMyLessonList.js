$(document).ready(function(){

	output = "";
	$.ajax({
		url : "userMyLessonList2.ue",
		type : "POST",
		dataType : "json",
		success : function(data){
			console.log(data);
			$(data).each(function(){
				output+="<div class='post-module'>";
				output+="<div class='thumbnail'>";
				output+="<input type='hidden' id='lesson"+this.lesson_code+" value='"+this.lesson_code+"' name='lesson_code'>";
        		output+="<a href='lessonInfo.lesson?sort=rv_recent&lesson_code="+this.lesson_code+"'><img class='card-img' src='"+this.pic_name+"'></a>";
        		output+="</div>";
        		output+="<div class='post-content' id='"+this.lesson_code+"'>"
        		output+="<div class='category'>"+this.Lesson_type+"</div>"
        		output+="<h1 class='title'><a href='lessonInfo.lesson?sort=rv_recent&lesson_code="+this.lesson_code+"'>"+this.Lesson_title+"</a></h1>";
        		output+="<button class='card-icon5' data-toggle='modal' data-target='#just-data' data-Lcode='"+this.lesson_code+"' data-Ltitle='"+this.Lesson_title+"' data-Linfo='"+this.lesson_info+"' data-pic_name='"+this.pic_name+"'>리뷰 쓰기</button>";
        		output+="</div>"
        		output+="</div>"
        		output+="</div>";
			})
			$(".teacher-info").append(output);
		},
		error : function(data){
			alert('ajax 에러');
			return false;
		}
	});
	
	$('#just-data').on('show.bs.modal', function (event) {
	      var button = $(event.relatedTarget); // Button that triggered the modal
	      var title = button.data('ltitle'); // Extract info from data-* attributes
	      var info = button.data('linfo');
	      var code = button.data('lcode');
	      var img_name = button.data('pic_name');
	      var modal = $(this);
	      modal.find('.modal-title').text(title +" : 리뷰 작성");
	      modal.find('.modal-body img').attr('src',img_name);
	      modal.find('.modal-body #title').text(title);
	      modal.find('.modal-body #content').text(info);
	      modal.find('.modal-body .left-content input').val(code);
	      });
});