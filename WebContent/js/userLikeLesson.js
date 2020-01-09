/**
 * <table>
				<tr>
					<td><img src=""></td>
				</tr>
				<tr>
					<td>Content</td>
				</tr>
			</table>
 */

$(document).ready(function(){
	output = "";
	$.ajax({
		url : "userLikeLesson.ue",
		type : "POST",
		dataType : "json",
		success : function(data){
			console.log(data);
			$(data).each(function(){
				// lesson_code,lesson_info,lesson_location,lesson_title,lesson_type,pic_name,pic_num
				output+="<div class='post-module'>";
				output+="<div class='thumbnail'>";
				output+="<input type='hidden' id='lesson"+this.lesson_code+" value='"+this.lesson_code+"' name='lesson_code'>";
        		output+="<a href='lessonInfo.lesson?sort=rv_recent&lesson_code="+this.lesson_code+"'><img class='card-img' src='"+this.pic_name+"'><a href='lessonInfo.lesson?sort=rv_recent&lesson_code="+this.lesson_code+"'></a>";
        		output+="</div>";
        		output+="<div class='post-content' id='"+this.lesson_code+"'>"
        		output+="<div class='category'>"+this.lesson_type+"</div>"
        		output+="<h1 class='title'><a href='lessonInfo.lesson?sort=rv_recent&lesson_code="+this.lesson_code+"'>"+this.lesson_title+"</a></h1>";
        		output+="<h2 class='sub_title'>"+this.lesson_info+"</h2>";
        		output+="</div></div></div>";
			})
			$(".teacher-info").append(output);	
		},
		error : function(data){
			alert('ajax 에러');
			return false;
		}
	});
})