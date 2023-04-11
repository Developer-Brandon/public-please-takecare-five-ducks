delimiter $$

drop procedure if exists update_table $$
create procedure update_table()

begin
    if exists((select *
               from information_schema.columns
               where table_name = 'tb_book_thumbnail_img'
                 and table_schema = 'plz_tc_fd'
                 and column_name = 'file_path')) then
        # 1. tb_book_thumbnail_img
        alter table plz_tc_fd.tb_book_thumbnail_img drop column file_path;
        alter table plz_tc_fd.tb_book_thumbnail_img drop column file_name;
        alter table plz_tc_fd.tb_book_thumbnail_img add web_thumbnail_url text not null comment '웹 썸네일 주소' after r_book_no;

        # 2. tb_comic_book_thumbnail_img
        alter table plz_tc_fd.tb_comic_book_thumbnail_img drop column file_path;
        alter table plz_tc_fd.tb_comic_book_thumbnail_img drop column file_name;
        alter table plz_tc_fd.tb_comic_book_thumbnail_img add web_thumbnail_url text not null comment '웹 썸네일 주소' after r_book_no;

        # 3. tb_drama_thumbnail_img
        alter table plz_tc_fd.tb_drama_thumbnail_img drop column file_path;
        alter table plz_tc_fd.tb_drama_thumbnail_img drop column file_name;
        alter table plz_tc_fd.tb_drama_thumbnail_img add web_thumbnail_url text not null comment '웹 썸네일 주소' after r_drama_no;

        # 4. tb_movie_thumbnail_img
        alter table plz_tc_fd.tb_movie_thumbnail_img drop column file_path;
        alter table plz_tc_fd.tb_movie_thumbnail_img drop column file_name;
        alter table plz_tc_fd.tb_movie_thumbnail_img add web_thumbnail_url text not null comment '웹 썸네일 주소' after r_movie_no;
    end if;
end $$

call update_table() $$

delimiter ;
