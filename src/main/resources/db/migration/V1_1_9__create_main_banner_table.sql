# 1. tb_contents_made_nature table의 컬럼 몇개를 수정합니다.
DELIMITER $$

DROP PROCEDURE IF EXISTS modify_table $$
CREATE PROCEDURE modify_table()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM INFORMATION_SCHEMA.COLUMNS
                   WHERE table_name = 'tb_contents_made_nature'
                     AND table_schema = 'plz_tc_fd'
                     AND column_name = 'public_code_number')) THEN
        alter table plz_tc_fd.tb_contents_made_nature add public_code_number int unsigned not null comment '실제 국가 코드 숫자' after m_n_no;
        alter table plz_tc_fd.tb_contents_made_nature add public_code_alphabet int unsigned not null comment '실제 국가 코드 영문(2자리)' after public_code_number;
        alter table plz_tc_fd.tb_contents_made_nature modify english_name varchar(30) not null comment '제작 국가 이름 영문';
        alter table plz_tc_fd.tb_contents_made_nature modify korean_name varchar(30) not null comment '제작 국가 이름 국문';
    END IF;

END $$

CALL modify_table() $$

DELIMITER ;

# 2. tb_main_banner_img 테이블을 추가합니다.
create table if not exists plz_tc_fd.tb_main_banner_img(
    banner_no int unsigned not null auto_increment comment '배너 번호' primary key,
    expose_order tinyint not null comment '배너노출순서',
    file_path varchar(50) not null comment '파일 경로',
    file_name varchar(50) not null comment '파일 명',
    reg_dt datetime not null default current_timestamp comment '등록일자',
    mod_dt datetime not null default current_timestamp comment '수정일자'
) engine=innodb default charset=utf8mb4 comment '메인 배너 이미지';
