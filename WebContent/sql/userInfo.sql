drop table userInfo;
create table userInfo (
	user_id varchar2(15) constraint userInfo_id_pk primary key,
	user_pass varchar2(20) constraint userInfo_pass_nn not null,
	user_name varchar2(15) constraint userInfo_name_nn not null,
	user_phone varchar2(11) constraint userInfo_phone_nn not null
				      constraint userInfo_phone_uk unique,
	user_email varchar2(40) constraint userInfo_email_nn not null
				      constraint userInfo_email_uk unique,
	user_type varchar2(1)	 default 0
);

delete userInfo;

select * from userInfo;

insert into userInfo values('admin', '1234', '관리자', '01000001111', 'admin.naver.com', 0)