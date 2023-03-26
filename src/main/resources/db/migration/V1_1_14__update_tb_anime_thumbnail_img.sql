DELIMITER $$

DROP PROCEDURE IF EXISTS update_table $$
CREATE PROCEDURE update_table()
BEGIN

    IF EXISTS((SELECT *
               FROM INFORMATION_SCHEMA.COLUMNS
               WHERE table_name = 'tb_anime_thumbnail_img'
                 AND table_schema = 'plz_tc_fd'
                 AND column_name = 'file_path')) THEN
        alter table plz_tc_fd.tb_anime_thumbnail_img drop column file_path;
        alter table plz_tc_fd.tb_anime_thumbnail_img drop column file_name;
        alter table plz_tc_fd.tb_anime_thumbnail_img add web_thumbnail_url text not null comment '웹 썸네일 주소' after r_anime_no;
        alter table plz_tc_fd.tb_anime_thumbnail_img comment '애니 프로필(썸네일) 이미지';
    END IF;
END $$

CALL update_table() $$

DELIMITER ;
