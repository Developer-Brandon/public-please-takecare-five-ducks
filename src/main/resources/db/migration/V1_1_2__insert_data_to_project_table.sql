# user_type 삽입
delimiter $$

drop procedure if exists validate_data $$
create procedure validate_data()
begin
    if not exists((select *
                   from plz_tc_fd.tb_user_type
                   where type = 'GUEST')) then

        insert plz_tc_fd.tb_user_type(type)
        values('GUEST')
             ,('ADMIN')
             ,('SUPER_ADMIN');
    end if;
end $$

call validate_data() $$

delimiter ;

# book_type 삽입
delimiter $$

drop procedure if exists validate_data $$
create procedure validate_data()

begin
    if not exists((select *
                   from plz_tc_fd.tb_book_type
                   where tag_korean_name = '스크립트')) then

        insert plz_tc_fd.tb_book_type(tag_korean_name, tag_english_name)
        values('스크립트', 'SCRIPT')
             , ('책요약본', 'BOOK_SUMMARY')
             ,('책PDF', 'BOOK_PDF')
             ,('공부요약본', 'STUDY_SUMMARY')
             ,('강의요약본', 'LECTURE_SUMMARY')
             ,('소설', 'NOVEL');
    end if;
end $$

call validate_data() $$

delimiter ;
