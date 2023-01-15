DELIMITER $$

DROP PROCEDURE IF EXISTS validate_data $$
CREATE PROCEDURE validate_data()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM web_app_test2.tb_comic_book
                   WHERE r_book_no = 100)) THEN

        # 만화책 가짜 데이터 삽입

        # tb_comic_book
        insert into web_app_test2.tb_comic_book(r_book_no, m_n_no, title, author, link, serial_state, book_reg_dt)
        values(100, 2, '헌터X헌터', '토가시 요시히로', '', 'being', '1998-03-03')
             ,(101, 2, '원피스', '오다 에이치로', '', 'being', '1997-07-20');

        # tb_comic_book_view
        insert into web_app_test2.tb_comic_book_view(view_no, user_no, r_book_no)
        values(100, 100, 100)
             ,(101, 100, 101);

        # tb_comic_book_thumbnail_img
        insert into web_app_test2.tb_comic_book_thumbnail_img(book_img_no, r_book_no, file_path, file_name)
        values(100, 100, 'local','hunter_x_hunger.png')
             , (101, 101, 'local','onepiece.png');

        # tb_comic_book_searching_keyword
        insert into web_app_test2.tb_comic_book_searching_keyword(comic_book_keyword_no, user_no, keyword)
        values(100, 100, '원피')
             ,(101, 100, '헌터')
             ,(102, 100, '원');
    END IF;

END $$

CALL validate_data() $$

DELIMITER ;
