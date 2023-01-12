DELIMITER $$

DROP PROCEDURE IF EXISTS validate_data $$
CREATE PROCEDURE validate_data()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM web_app_test2.tb_user
                   WHERE user_no = 100)) THEN
        # tb_anime
        insert into web_app_test2.tb_user(user_no, user_type_no, email, password, user_name)
            value(100, 3, 'brandon5959somi@gmail.com', 'test123', '이도겸');

        # tb_anime
        insert into web_app_test2.tb_anime(r_anime_no, m_n_no, title, author, link, finalized_yn, anime_broadcast_cnt, anime_reg_dt)
        values(100, 2,'디지몬 어드벤쳐 1~10화','혼고 아키요시','https://pond-play-786.notion.site/1-10-04e9ae6de0ae44d0b2b0679f4d5f5dbe','y','54','1997-06-26')
             ,(101, 2, '디지몬 어드벤쳐 11~20화','혼고 아키요시','https://pond-play-786.notion.site/11-20-cc15ad1d77144b63a1e5ad5b2184772c','y','54','1997-06-26')
             ,(102, 2, '디지몬 어드벤쳐 21~30화','혼고 아키요시','https://pond-play-786.notion.site/21-30-87515e28ebfd4bf8a6fcd5d60509ca8a','y','54','1997-06-26')
             ,(103, 2, '디지몬 어드벤쳐 31~40화','혼고 아키요시','https://pond-play-786.notion.site/31-40-5787bed9656845e8a120500654cdda82','y','54','1997-06-26')
             ,(104, 2, '디지몬 어드벤쳐 41~54화','혼고 아키요시','https://pond-play-786.notion.site/41-54-66dda09a637641f4b19ec5bb1422e2f5','y','54','1997-06-26');

        # tb_anime_view
        insert into web_app_test2.tb_anime_view(view_no, user_no, r_anime_no)
        values(100, 100, 100)
             ,(101, 100, 100)
             ,(102, 100, 100)
             ,(103, 100, 100)
             ,(104, 100, 101)
             ,(105, 100, 101)
             ,(106, 100, 103)
             ,(107, 100, 104)
             ,(108, 100, 104)
             ,(109, 100, 104);

        # tb_anime_thumbnail_img
        insert into web_app_test2.tb_anime_thumbnail_img(anime_img_no, r_anime_no, file_path, file_name)
        values(100, 100, 'local','digimon.png')
             , (101, 101, 'local','digimon.png')
             , (102, 102, 'local','digimon.png')
             , (103, 103, 'local','digimon.png')
             , (104, 104, 'local','digimon.png');

        # tb_anime_searching_keyword
        insert into web_app_test2.tb_anime_searching_keyword(anime_keyword_no, user_no, keyword)
        values(100, 100, '디지')
             ,(101, 100, '디지몬')
             ,(102, 100, '어드');

                #############################################

        # tb_book
        insert into web_app_test2.tb_book(r_book_no, m_n_no, r_book_type_no, title, author, link)
        values(100, 1, 4, '스프링 용어/개념 및 어노테이션 정리', '이도겸', 'https://pond-play-786.notion.site/00862c50454c417c92e4853974ce1173')
             , (101, 1, 4, 'JSP의 모든것', '이도겸', 'https://pond-play-786.notion.site/JSP-054e852f13d04205be427569134f0899')
             , (102, 1, 4, 'Spring 오류/에러 정리', '이도겸', 'https://pond-play-786.notion.site/Spring-c8d3904a2a414c7da1f5186060420b1d')
             , (103, 1, 4, 'Client/Server pagination 총정리', '이도겸', 'https://pond-play-786.notion.site/Client-Server-Pagination-7e029e1152ff499f8713a6718804b385')
             , (104, 1, 4, 'Mybatis 개념/정리/태그 모음', '이도겸', 'https://pond-play-786.notion.site/Mybatis-0aa267a91c794da797f4aa88e42fde32');

        # tb_book_view
        insert into web_app_test2.tb_book_view(view_no, user_no, r_book_no)
        values(100, 100, 100)
             ,(101, 100, 100)
             ,(102, 100, 101)
             ,(103, 100, 101)
             ,(104, 100, 101)
             ,(105, 100, 101)
             ,(106, 100, 103)
             ,(107, 100, 104)
             ,(108, 100, 104)
             ,(109, 100, 104);

        # tb_book_thumbnail_img
        insert into web_app_test2.tb_book_thumbnail_img(book_img_no, r_book_no, file_path, file_name)
        values(100, 100, 'local','digimon.png')
             , (101, 101, 'local','digimon.png')
             , (102, 102, 'local','digimon.png')
             , (103, 103, 'local','digimon.png')
             , (104, 104, 'local','digimon.png');

        # tb_book_searching_keyword
        insert into web_app_test2.tb_book_searching_keyword(book_keyword_no, user_no, keyword)
        values(100, 100, '스프링')
             ,(101, 100, 'JSP')
             ,(102, 100, '오류');

        #############################################

        # TODO
        # 만화책, 드라마는 아직 로우 데이터가 준비가 안되서, 로우데이터 업로드 후에 추가적으로 진행하는 것으로 결정되었습니다.
        # 또한 국가코드에 대해서 고민해보아야 합니다, 우선은 일본에 해당하는 정보가 아니면 전부 한국것으로 국가코드 지정하였습니다.
        # 국가코드중에서는, 더미데이터 중에서는 tb_movie만 유심히 보면됩니다.

        #############################################

        # tb_movie
        insert into web_app_test2.tb_movie(r_movie_no, m_n_no, title, director, link, total_number_of_episode, movie_reg_dt)
        values(100, 1, '8마일', '커티스핸슨', 'https://pond-play-786.notion.site/8-e2c903ed56134f1398a3ebce83a03869', 1, '2003-02-21')
             ,(101, 1, '가위손', '팀버튼', 'https://pond-play-786.notion.site/770dd86a90f34e89b6cd9250a72c6f36', 1, '1991-06-29');

        # tb_movie_view
        insert into web_app_test2.tb_movie_view(view_no, user_no, r_movie_no)
        values(100, 100, 100)
             ,(101, 100, 100);

        # tb_movie_thumbnail_img
        insert into web_app_test2.tb_movie_thumbnail_img(movie_img_no, r_movie_no, file_path, file_name)
        values(100, 100, 'local','8mile.png')
             , (101, 101, 'local','scissorhands.png');

        # tb_movie_searching_keyword
        insert into web_app_test2.tb_movie_searching_keyword(movie_keyword_no, user_no, keyword)
        values(100, 100, '가위')
             ,(101, 100, '손')
             ,(102, 100, '8');
    END IF;

END $$

CALL validate_data() $$

DELIMITER ;
