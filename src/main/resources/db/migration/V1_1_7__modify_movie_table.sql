# 1. 만약 tb_drama table의 page_per_drama_cnt가 존재한다면 삭제합니다.
DELIMITER $$

DROP PROCEDURE IF EXISTS modify_table $$
CREATE PROCEDURE modify_table()
BEGIN

    IF EXISTS((SELECT *
                   FROM INFORMATION_SCHEMA.COLUMNS
                   WHERE table_name = 'tb_movie'
                     AND table_schema = 'plz_tc_fd'
                     AND column_name = 'page_per_movie_cnt')) THEN
        alter table plz_tc_fd.tb_movie drop column page_per_movie_cnt;

    END IF;

END $$

CALL modify_table() $$

DELIMITER ;

