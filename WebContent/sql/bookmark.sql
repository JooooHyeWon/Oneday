drop table bookmark;

create table bookmark (
	user_id varchar2(15),
	lesson_code number(10) constraint bookmart_lesson_code_fk references lesson(lesson_code) on delete cascade ,
	is_bookmark number(10) default 0,
	constraint bookmark_combo_pk primary key(user_id, lesson_code)
);


select * from bookmark;