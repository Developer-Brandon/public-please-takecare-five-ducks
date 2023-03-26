# 1. modify table의 컬럼 몇개를 수정합니다.
DELIMITER $$

DROP PROCEDURE IF EXISTS modify_table $$
CREATE PROCEDURE modify_table()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM INFORMATION_SCHEMA.COLUMNS
                   WHERE table_name = 'tb_main_banner_img'
                     AND table_schema = 'plz_tc_fd'
                     AND column_name = 'use_yn')) THEN
        alter table plz_tc_fd.tb_main_banner_img add use_yn enum('y','n') not null default 'y' comment '사용여부' after file_name;
    END IF;

END $$

CALL modify_table() $$

DELIMITER ;
