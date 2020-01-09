drop table gallery;
create table gallery (
	lesson_code number(10) constraint gallery_lesson_code_fk references lesson(lesson_code) on delete cascade,
	pic_num number(10) constraint gallery_num_nn not null,
	pic_name varchar2(1000) constraint gallery_name_nn not null
);




select * from gallery;

delete gallery;

insert into gallery values(500, 1, 'gallery/flower1.png');
insert into gallery values(500, 2, 'gallery/kakao.jpg');