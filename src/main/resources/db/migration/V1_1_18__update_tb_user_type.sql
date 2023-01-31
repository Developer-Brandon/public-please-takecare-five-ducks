delimiter $$

drop procedure if exists update_data $$
create procedure update_data()
begin

    if exists((select use_yn
               from web_app_test2.tb_user_type
               where use_yn = 'y')) then
    update web_app_test2.tb_user_type
    set use_yn = 'y'
    where use_yn = 'Y';

end if;
end $$

call update_data() $$

delimiter ;
