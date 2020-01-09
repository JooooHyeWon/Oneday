drop table user_lesson;
create table user_lesson (
	user_id varchar2(15)  constraint user_lesson_user_id_fk references userInfo(user_id) on delete cascade,
	lesson_code number(10) constraint user_lesson_lesson_code_fk references lesson(lesson_code) on delete cascade,
	lesson_de_code varchar2(20) ,
);

select * from user_lesson;

insert into user_lesson values('eunji1234',119,'119-2019/12/16');
insert into user_lesson values('eunji12345',119,'119-2019/12/16');
insert into user_lesson values('eunji2345',119,'119-2019/12/16');
insert into user_lesson values('eunji23456',119,'119-2019/12/16');