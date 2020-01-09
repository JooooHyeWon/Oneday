drop table review;

create table review (
	review_code number(10) constraint review_code_pk primary key,
	lesson_code number(10) constraint review_lesson_code_fk references lesson(lesson_code) on delete cascade,
	user_id varchar2(15) constraint review_user_id_fk references userInfo(user_id) on delete cascade,
	review_date date,
	review_content varchar2(1000) constraint review_content_nn not null,
	review_star  number not null
);


select * from review;

create sequence review_seq;

drop sequence review_seq;