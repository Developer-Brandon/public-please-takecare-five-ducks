# 만약, mod_dt가 없다면 mod_dt 컬럼을 table에 추가합니다.
delimiter $$

drop procedure if exists modify_table $$
create procedure modify_table()

begin

    if not exists((select *
                   from information_schema.columns
                   where table_name = 'tb_anime'
                     and table_schema = 'plz_tc_fd'
                     and column_name = 'mod_dt')) then
alter table plz_tc_fd.tb_anime add mod_dt datetime not null default current_timestamp comment '수정일자'  after reg_dt;

end if;
end $$

call modify_table() $$

delimiter ;
