# 국가 코드는 나중에 진짜 국가코드(예를들어 한국이면 +82)를 가져와서 사용해야 하지만, 임시로...
# 아래와 같이 데이터를 삽입해놓고 추후에 날짜 코드들을 api같은 곳에서 불러와서 작업하는 식으로 코드를 작성합니다....

DELIMITER $$

DROP PROCEDURE IF EXISTS validate_data $$
CREATE PROCEDURE validate_data()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM plz_tc_fd.tb_contents_made_nature
                   WHERE m_n_no= 1)) THEN

        insert plz_tc_fd.tb_contents_made_nature(english_name, korean_name)
        values('korea', '한국'), ('japan', '일본');

END IF;

END $$

CALL validate_data() $$

DELIMITER ;
