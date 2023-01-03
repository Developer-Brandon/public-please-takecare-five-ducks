# user_type 삽입
DELIMITER $$

DROP PROCEDURE IF EXISTS validate_data $$
CREATE PROCEDURE validate_data()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM web_app_test2.tb_user_type
                   WHERE type = 'GUEST')) THEN

        insert web_app_test2.tb_user_type(type)
        values('GUEST')
             ,('ADMIN')
             ,('SUPER_ADMIN');

    END IF;

END $$

CALL validate_data() $$

DELIMITER ;

# book_type 삽입
DELIMITER $$

DROP PROCEDURE IF EXISTS validate_data $$
CREATE PROCEDURE validate_data()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM web_app_test2.tb_book_type
                   WHERE tag_korean_name = '스크립트')) THEN

        insert web_app_test2.tb_book_type(tag_korean_name, tag_english_name)
        values('스크립트', 'SCRIPT')
             , ('책요약본', 'BOOK_SUMMARY')
             ,('책PDF', 'BOOK_PDF')
             ,('공부요약본', 'STUDY_SUMMARY')
             ,('강의요약본', 'LECTURE_SUMMARY')
             ,('소설', 'NOVEL');

    END IF;

END $$

CALL validate_data() $$

DELIMITER ;
