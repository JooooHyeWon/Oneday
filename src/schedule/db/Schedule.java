package schedule.db;

public class Schedule {
	private int lesson_code;
	private String lesson_date;
	private String lesson_start;
	private String lesson_end;
	private int max_user;
	private int user_count;
	private String lesson_de_code;
	
	
	public String getLesson_de_code() {
		return lesson_de_code;
	}
	public void setLesson_de_code(String lesson_de_code) {
		this.lesson_de_code = lesson_de_code;
	}
	public int getLesson_code() {
		return lesson_code;
	}
	public void setLesson_code(int lesson_code) {
		this.lesson_code = lesson_code;
	}
	public String getLesson_date() {
		return lesson_date;
	}
	public void setLesson_date(String lesson_date) {
		this.lesson_date = lesson_date;
	}
	public String getLesson_start() {
		return lesson_start;
	}
	public void setLesson_start(String lesson_start) {
		this.lesson_start = lesson_start;
	}
	public String getLesson_end() {
		return lesson_end;
	}
	public void setLesson_end(String lesson_end) {
		this.lesson_end = lesson_end;
	}
	public int getMax_user() {
		return max_user;
	}
	public void setMax_user(int max_user) {
		this.max_user = max_user;
	}
	public int getUser_count() {
		return user_count;
	}
	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}
	
	
}
