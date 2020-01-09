package user_lesson.db;

public class UserLesson {
	private String user_id;
	private int lesson_code;
	private String lesson_de_code;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getLesson_code() {
		return lesson_code;
	}
	public void setLesson_code(int lesson_code) {
		this.lesson_code = lesson_code;
	}
	public String getLesson_de_code() {
		return lesson_de_code;
	}
	public void setLesson_de_code(String lesson_de_code) {
		this.lesson_de_code = lesson_de_code;
	}
	
	
}
