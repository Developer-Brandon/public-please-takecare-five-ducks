# 1. 만약 tb_drama table의 page_per_drama_cnt가 존재한다면 삭제합니다.
DELIMITER $$

DROP PROCEDURE IF EXISTS modify_table $$
CREATE PROCEDURE modify_table()
BEGIN

    IF EXISTS((SELECT *
                   FROM INFORMATION_SCHEMA.COLUMNS
                   WHERE table_name = 'tb_drama'
                     AND table_schema = 'plz_tc_fd'
                     AND column_name = 'page_per_drama_cnt')) THEN
        alter table plz_tc_fd.tb_drama drop column page_per_drama_cnt;

    END IF;

END $$

CALL modify_table() $$

DELIMITER ;

# 2. tb_drama table의 broadcast_state를 link 컬럼 뒤로 보냅니다.
alter table plz_tc_fd.tb_drama modify broadcast_state enum('end','yet','early_end') not null default 'end' comment '방영상태' after link;

# 3. 만약 tb_drama_book_thumnail_img가 존재하면 테이블 이름을 변경합니다.
DELIMITER $$

DROP PROCEDURE IF EXISTS modify_table $$
CREATE PROCEDURE modify_table()
BEGIN

    IF EXISTS((SELECT *
                   FROM INFORMATION_SCHEMA.COLUMNS
                   WHERE table_name = 'tb_drama_book_thumbnail_img'
                     AND table_schema = 'plz_tc_fd')) THEN
        alter table plz_tc_fd.tb_drama_book_thumbnail_img rename plz_tc_fd.tb_drama_thumbnail_img;

    END IF;

END $$

CALL modify_table() $$

DELIMITER ;
