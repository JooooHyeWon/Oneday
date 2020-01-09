drop table teacher;
create table teacher (
	tea_id varchar2(15) constraint teacher_id_pk primary key,
	tea_pass varchar2(20) constraint teacher_pass_nn not null,
	tea_name varchar2(15) constraint teacher_name_nn not null,
	tea_phone varchar2(11) constraint teacher_phone_nn not null
				    constraint teacher_phone_uk unique,
	tea_email varchar2(40) constraint teacher_email_nn not null
				    constraint teacher_email_uk unique,
	user_type varchar2(1)	 default 1
);

insert into teacher values('teacher' ,'whdsmwl1','eunji','eunji','eunji@naver.com','1');
insert into teacher values('eunji','eunji','eunji','eunji','eunji@naver.com','1');

select * from teacher;

delete teacher;

commit