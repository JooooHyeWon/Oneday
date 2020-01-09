drop table schedule;
create table schedule (
	lesson_code number(10) constraint schedule_lesson_code_fk references lesson(lesson_code) on delete cascade,
	lesson_date varchar2(20) constraint lesson_date_nn not null,
	lesson_start varchar2(6) constraint lesson_start_nn not null,
	lesson_end varchar2(6) constraint lesson_end_nn not null,
	max_user number(10) constraint lesson_max_nn not null,
	user_count number(10),
	lesson_de_code varchar2(20) primary key
);


select * from schedule;

delete schedule;

insert into schedule values (500, '2019/12/20', '11:00', '15:00', 6, 0, '1-2019/12/20');

insert into schedule values (500, '2019/12/24', '13:00', '17:00', 4, 0, '1-2019/12/24');

insert into schedule values (500, '2019/12/31', '13:00', '17:00', 5, 0, '1-2019/12/31');

insert into schedule values (500, '2020/01/02', '13:00', '17:00', 5, 0, '1-2020/01/02');