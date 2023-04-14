# 1. 만약 tb_drama table의 page_per_drama_cnt가 존재한다면 삭제합니다.
delimiter $$

drop procedure if exists modify_table $$
create procedure modify_table()

begin
    if not exists((select *
                   from information_schema.columns
                   where table_name = 'tb_movie'
                     and table_schema = 'plz_tc_fd'
                     and column_name = 'mod_dt')) then
        alter table plz_tc_fd.tb_movie add mod_dt datetime not null default current_timestamp comment '수정일자' after reg_dt;
    end if;
end $$

call modify_table() $$

delimiter ;

