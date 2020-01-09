package lesson.db;

public class Lesson {
	private int lesson_code;
	private String tea_id;
	private String lesson_type;
	private String lesson_title;
	private String lesson_price;
	private String lesson_location;
	private String lesson_content;
	private int bookmark;
	private String lesson_chat;
	private String tea_info;
	private String lesson_info;
	
	
	public String getLesson_info() {
		return lesson_info;
	}
	public void setLesson_info(String lesson_info) {
		this.lesson_info = lesson_info;
	}
	public String getTea_info() {
		return tea_info;
	}
	public void setTea_info(String tea_info) {
		this.tea_info = tea_info;
	}
	public int getLesson_code() {
		return lesson_code;
	}
	public void setLesson_code(int lesson_code) {
		this.lesson_code = lesson_code;
	}
	public String getTea_id() {
		return tea_id;
	}
	public void setTea_id(String tea_id) {
		this.tea_id = tea_id;
	}
	public String getLesson_type() {
		return lesson_type;
	}
	public void setLesson_type(String lesson_type) {
		this.lesson_type = lesson_type;
	}
	public String getLesson_title() {
		return lesson_title;
	}
	public void setLesson_title(String lesson_title) {
		this.lesson_title = lesson_title;
	}
	public String getLesson_price() {
		return lesson_price;
	}
	public void setLesson_price(String lesson_price) {
		this.lesson_price = lesson_price;
	}
	public String getLesson_location() {
		return lesson_location;
	}
	public void setLesson_location(String lesson_location) {
		this.lesson_location = lesson_location;
	}
	public String getLesson_content() {
		return lesson_content;
	}
	public void setLesson_content(String lesson_content) {
		this.lesson_content = lesson_content;
	}
	public int getBookmark() {
		return bookmark;
	}
	public void setBookmark(int bookmark) {
		this.bookmark = bookmark;
	}
	public String getLesson_chat() {
		return lesson_chat;
	}
	public void setLesson_chat(String lesson_chat) {
		this.lesson_chat = lesson_chat;
	}
	
	
}
