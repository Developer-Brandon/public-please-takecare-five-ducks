# 1. 만약 tb_drama table의 page_per_drama_cnt가 존재한다면 삭제합니다.
DELIMITER $$

DROP PROCEDURE IF EXISTS modify_table $$
CREATE PROCEDURE modify_table()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM INFORMATION_SCHEMA.COLUMNS
                   WHERE table_name = 'tb_movie'
                     AND table_schema = 'web_app_test2'
                     AND column_name = 'mod_dt')) THEN
        alter table web_app_test2.tb_movie add mod_dt datetime not null default current_timestamp comment '수정일자' after reg_dt;

    END IF;

END $$

CALL modify_table() $$

DELIMITER ;

