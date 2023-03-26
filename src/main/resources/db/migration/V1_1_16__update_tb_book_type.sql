DELIMITER $$

DROP PROCEDURE IF EXISTS update_table $$
CREATE PROCEDURE update_table()
BEGIN

    IF NOT EXISTS((SELECT *
               FROM INFORMATION_SCHEMA.COLUMNS
               WHERE table_name = 'tb_book_type'
                 AND table_schema = 'plz_tc_fd'
                 AND column_name = 'hex_code')) THEN

            # 1. tb_book_type에 hex_code 컬럼 추가
            alter table plz_tc_fd.tb_book_type add hex_code varchar(15) not null after tag_english_name;

            # 2. hex_code에 관한 데이터 추가
            update plz_tc_fd.tb_book_type
            set hex_code = '#FF0000'
            where tag_english_name = 'SCRIPT';

            update plz_tc_fd.tb_book_type
            set hex_code = '#0500FF'
            where tag_english_name = 'BOOK_SUMMARY';

            update plz_tc_fd.tb_book_type
            set hex_code = '#FFF500'
            where tag_english_name = 'BOOK_PDF';

            update plz_tc_fd.tb_book_type
            set hex_code = '#04CF00'
            where tag_english_name = 'STUDY_SUMMARY';

            update plz_tc_fd.tb_book_type
            set hex_code = '#7CA800'
            where tag_english_name = 'LECTURE_SUMMARY';

            update plz_tc_fd.tb_book_type
            set hex_code = '#CF0095'
            where tag_english_name = 'NOVEL';
    END IF;
END $$

CALL update_table() $$

DELIMITER ;
