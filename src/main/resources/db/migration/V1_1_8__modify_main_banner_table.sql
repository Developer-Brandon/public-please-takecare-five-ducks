# 1. modify table의 컬럼 몇개를 수정합니다.
delimiter $$

drop procedure if exists modify_table $$
create procedure modify_table()

begin
    if not exists((select *
                   from information_schema.columns
                   where table_name = 'tb_main_banner_img'
                     and table_schema = 'plz_tc_fd'
                     and column_name = 'use_yn')) THEN
        alter table plz_tc_fd.tb_main_banner_img add use_yn enum('y','n') not null default 'y' comment '사용여부' after file_name;
    end if;
end $$

call modify_table() $$

delimiter ;
