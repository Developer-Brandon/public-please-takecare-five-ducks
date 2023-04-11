# 1. 만약 tb_drama table의 page_per_drama_cnt가 존재한다면 삭제합니다.
delimiter $$

drop procedure if exists modify_table $$
create procedure modify_table()

begin
    if exists((select *
                   from information_schema.columns
                   where table_name = 'tb_drama'
                     and table_schema = 'plz_tc_fd'
                     and column_name = 'page_per_drama_cnt')) then
        alter table plz_tc_fd.tb_drama drop column page_per_drama_cnt;
    end if;
end $$

call modify_table() $$

delimiter ;

# 2. tb_drama table의 broadcast_state를 link 컬럼 뒤로 보냅니다.
alter table plz_tc_fd.tb_drama modify broadcast_state enum('end','yet','early_end') not null default 'end' comment '방영상태' after link;

# 3. 만약 tb_drama_book_thumnail_img가 존재하면 테이블 이름을 변경합니다.
delimiter $$

drop procedure if exists modify_table $$
create procedure modify_table()

begin
    if exists((select *
                   from information_schema.columns
                   where table_name = 'tb_drama_book_thumbnail_img'
                     and table_schema = 'plz_tc_fd')) then
        alter table plz_tc_fd.tb_drama_book_thumbnail_img rename plz_tc_fd.tb_drama_thumbnail_img;
    end if;
end $$

call modify_table() $$

delimiter ;
