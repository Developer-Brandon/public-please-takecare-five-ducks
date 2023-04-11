# 1. 만약 tb_drama table의 page_per_drama_cnt가 존재한다면 삭제합니다.
delimiter $$

drop procedure if exists modify_table $$
create procedure modify_table()
begin
    if exists((select *
                   from information_schema.columns
                   where table_name = 'tb_movie'
                     and table_schema = 'plz_tc_fd'
                     and column_name = 'page_per_movie_cnt')) then
        alter table plz_tc_fd.tb_movie drop column page_per_movie_cnt;
    end if;
end $$

call modify_table() $$

delimiter ;

