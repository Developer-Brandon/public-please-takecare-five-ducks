DELIMITER $$

DROP PROCEDURE IF EXISTS update_table $$
CREATE PROCEDURE update_table()
BEGIN

    IF EXISTS((SELECT *
               FROM INFORMATION_SCHEMA.COLUMNS
               WHERE table_name = 'tb_book_thumbnail_img'
                 AND table_schema = 'web_app_test2'
                 AND column_name = 'file_path')) THEN
        # 1. tb_book_thumbnail_img
        alter table web_app_test2.tb_book_thumbnail_img drop column file_path;
        alter table web_app_test2.tb_book_thumbnail_img drop column file_name;
        alter table web_app_test2.tb_book_thumbnail_img add web_thumbnail_url text not null comment '웹 썸네일 주소' after r_book_no;

        # 2. tb_comic_book_thumbnail_img
        alter table web_app_test2.tb_comic_book_thumbnail_img drop column file_path;
        alter table web_app_test2.tb_comic_book_thumbnail_img drop column file_name;
        alter table web_app_test2.tb_comic_book_thumbnail_img add web_thumbnail_url text not null comment '웹 썸네일 주소' after r_book_no;

        # 3. tb_drama_thumbnail_img
        alter table web_app_test2.tb_drama_thumbnail_img drop column file_path;
        alter table web_app_test2.tb_drama_thumbnail_img drop column file_name;
        alter table web_app_test2.tb_drama_thumbnail_img add web_thumbnail_url text not null comment '웹 썸네일 주소' after r_drama_no;

        # 4. tb_movie_thumbnail_img
        alter table web_app_test2.tb_movie_thumbnail_img drop column file_path;
        alter table web_app_test2.tb_movie_thumbnail_img drop column file_name;
        alter table web_app_test2.tb_movie_thumbnail_img add web_thumbnail_url text not null comment '웹 썸네일 주소' after r_movie_no;

    END IF;
END $$

CALL update_table() $$

DELIMITER ;
