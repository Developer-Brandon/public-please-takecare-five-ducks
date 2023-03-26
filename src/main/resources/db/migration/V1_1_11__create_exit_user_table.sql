# 1. tb_exit_user 테이블을 추가합니다.
create table if not exists plz_tc_fd.tb_exit_user(
   user_no int unsigned not null auto_increment comment '탈퇴 유저 번호' primary key,
   email varchar(40) not null comment '유저 이메일',
   password varchar(40) not null comment '유저 패스워드',
   user_name varchar(20) not null comment '유저 이름',
   use_yn enum('Y','N') not null default 'Y' comment '사용여부',
   reg_dt datetime not null default current_timestamp comment '등록일자',
   mod_dt datetime not null default current_timestamp comment '수정일자'
) engine=innodb default charset=utf8mb4 comment '탈퇴유저(어드민)';
