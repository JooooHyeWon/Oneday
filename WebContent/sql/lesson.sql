drop table lesson;
create table lesson (
	lesson_code number(10) constraint lesson_code_pk primary key,
	tea_id varchar2(15) constraint lesson_tea_id_fk references teacher(tea_id) on delete cascade,
	lesson_type varchar2(20) constraint lesson_type_nn not null,
	lesson_title varchar2(50) constraint lesson_title_nn not null,
	lesson_price varchar2(10) constraint lesson_price_nn not null,
	lesson_location varchar2(200) constraint lesson_location_nn not null,
	lesson_content varchar2(1000) constraint lesson_content_nn not null,
	bookmark number(10),
	lesson_chat varchar2(40) constraint lesson_chat_nn not null,
	tea_info varchar2(1000) constraint lesson_tea_info_nn not null,
	lesson_info varchar2(300)  constraint lesson_lesson_info_nn not null
);


insert into lesson values(500, 'sooteacher', 'diy', '������������������������', '50', '��õ������ �ֽ·� 223, 103�� 1006ȣ', '���������Դϴٴٴٴٴٴ�', 0 , 'www', '������ �Ұ��Դϴٴٴٴݴٴ�', '���� �Ұ�');

delete from lesson where lesson_code = 500;

create sequence lesson_seq;

drop sequence lesson_seq;

select * from lesson;

delete lesson;

select count(*) from lesson where tea_id = 'teacher';

select lesson_code from lesson where tea_id = 'teacher';

update lesson set lesson_type = 'art' where lesson_code = 37;