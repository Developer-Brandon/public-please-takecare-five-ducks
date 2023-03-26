# 만약, mod_dt가 없다면 mod_dt 컬럼을 table에 추가합니다.
DELIMITER $$

DROP PROCEDURE IF EXISTS modify_table $$
CREATE PROCEDURE modify_table()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM INFORMATION_SCHEMA.COLUMNS
                   WHERE table_name = 'tb_anime'
                     AND table_schema = 'plz_tc_fd'
                     AND column_name = 'mod_dt')) THEN
alter table plz_tc_fd.tb_anime add mod_dt datetime not null default current_timestamp comment '수정일자'  after reg_dt;

END IF;

END $$

CALL modify_table() $$

DELIMITER ;
