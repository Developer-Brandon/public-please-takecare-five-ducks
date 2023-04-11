delimiter $$

drop procedure if exists update_table $$
create procedure update_table()

begin
    if exists((select *
               from information_schema.columns
               where table_name = 'tb_anime_thumbnail_img'
                 and table_schema = 'plz_tc_fd'
                 and column_name = 'file_path')) THEN
        alter table plz_tc_fd.tb_anime_thumbnail_img drop column file_path;
        alter table plz_tc_fd.tb_anime_thumbnail_img drop column file_name;
        alter table plz_tc_fd.tb_anime_thumbnail_img add web_thumbnail_url text not null comment '웹 썸네일 주소' after r_anime_no;
        alter table plz_tc_fd.tb_anime_thumbnail_img comment '애니 프로필(썸네일) 이미지';
    end if;
end $$

call update_table() $$

delimiter ;
