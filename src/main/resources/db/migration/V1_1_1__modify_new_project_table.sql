# 1. table 수정 작업
# (1) 기획이 수정됨에 따라 table의 컬럼들을 추가합니다.
# (2) table의 comment가 잘못들어가있었던 부분 수정합니다.

### anime 관련 설정들 ####
# tb_anime 테이블 수정
DELIMITER $$

DROP PROCEDURE IF EXISTS modify_table $$
CREATE PROCEDURE modify_table()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM INFORMATION_SCHEMA.COLUMNS
                   WHERE table_name = 'tb_anime'
                     AND table_schema = 'web_app_test2'
                     AND column_name = 'anime_broadcast_cnt')) THEN
alter table web_app_test2.tb_anime modify title text not null comment '제목';
alter table web_app_test2.tb_anime modify author varchar(30) not null default 'noname' comment '작가이름';
alter table web_app_test2.tb_anime drop column page_per_anime_cnt;
alter table web_app_test2.tb_anime add link text not null comment '링크' after author;
alter table web_app_test2.tb_anime add finalized_yn enum('y','n') not null comment '완결여부' after link;
alter table web_app_test2.tb_anime add anime_broadcast_cnt int unsigned comment '방영회수' after finalized_yn;
END IF;

END $$

CALL modify_table() $$

DELIMITER ;

# tb_anime_thumbnail_img 테이블 추가
create table if not exists web_app_test2.tb_anime_thumbnail_img(
                                                                   anime_img_no int unsigned not null auto_increment comment '프로필 이미지 번호' primary key,
                                                                   r_anime_no int unsigned not null comment '애니 번호',
                                                                   file_path varchar(50) not null comment '파일 경로',
    file_name varchar(50) not null comment '파일 명',
    reg_dt datetime not null default current_timestamp comment '등록일자',
    mod_dt datetime not null default current_timestamp comment '수정일자'
    ) engine=innodb default charset=utf8mb4 comment '유저 프로필(썸네일) 이미지';

# tb_anime_thumbnail_img 외래키 설정
alter table web_app_test2.tb_anime_thumbnail_img add foreign key(r_anime_no)
    references web_app_test2.tb_anime (r_anime_no) on delete cascade;

# tb_anime_view 테이블 추가
create table if not exists web_app_test2.tb_anime_view(
                                                          view_no int unsigned not null auto_increment comment '조회수 번호' primary key,
                                                          user_no int unsigned not null comment '유저 번호',
                                                          r_anime_no int unsigned not null comment '애니 번호',
                                                          reg_dt datetime not null default current_timestamp comment '등록일자'
                                                      ) engine=innodb default charset=utf8mb4 comment '유저 프로필(썸네일) 이미지';

# tb_anime_view 외래키 설정
alter table web_app_test2.tb_anime_view add foreign key(user_no)
    references web_app_test2.tb_user (user_no) on delete cascade;

alter table web_app_test2.tb_anime_view add foreign key(r_anime_no)
    references web_app_test2.tb_anime(r_anime_no) on delete cascade;

# tb_anime_searching_keyword 테이블 추가
create table if not exists web_app_test2.tb_anime_searching_keyword(
                                                                       anime_keyword_no int unsigned not null auto_increment comment '애니 키워드 번호' primary key,
                                                                       user_no int unsigned not null comment '유저 번호',
                                                                       keyword varchar(40) not null comment '키워드',
    reg_dt datetime not null default current_timestamp comment '등록일자'
    ) engine=innodb default charset=utf8mb4 comment '유저 프로필(썸네일) 이미지';

# tb_anime_searching_keyword 외래키 설정
alter table web_app_test2.tb_anime_searching_keyword add foreign key(user_no)
    references web_app_test2.tb_user (user_no) on delete cascade;

### book 관련 설정들 ####
# tb_book_type 테이블 추가
create table if not exists web_app_test2.tb_book_type(
                                                         r_book_type_no int unsigned not null auto_increment comment '책의 종류 번호' primary key,
                                                         tag_korean_name varchar(30) not null comment '책의종류(한글)',
    tag_english_name varchar(30) not null comment '책의종류(영어)',
    reg_dt datetime not null default current_timestamp comment '등록일자',
    mod_dt datetime not null default current_timestamp comment '수정일자'
    ) engine=innodb default charset=utf8mb4 comment '책의 종류';

# tb_book 테이블 수정
alter table web_app_test2.tb_book modify title text not null comment '제목';
alter table web_app_test2.tb_book add r_book_type_no int unsigned comment '책의 종류 번호' after m_n_no;
alter table web_app_test2.tb_book add link int unsigned comment '링크' after author;
alter table web_app_test2.tb_book modify book_reg_dt datetime not null default current_timestamp comment '출간일자 혹은 컨텐츠 제작일자';
alter table web_app_test2.tb_book add mod_dt datetime not null default current_timestamp comment '수정일자' after reg_dt;

# tb_book_thumbnail_img 테이블 추가
create table if not exists web_app_test2.tb_book_thumbnail_img(
                                                                  book_img_no int unsigned not null auto_increment comment '책 이미지 번호' primary key,
                                                                  r_book_no int unsigned not null comment '책 번호',
                                                                  file_path varchar(50) not null comment '파일 경로',
    file_name varchar(50) not null comment '파일 명',
    reg_dt datetime not null default current_timestamp comment '등록일자',
    mod_dt datetime not null default current_timestamp comment '수정일자'
    ) engine=innodb default charset=utf8mb4 comment '책 썸네일 이미지';

# tb_book_thumbnail_img 외래키 설정
alter table web_app_test2.tb_book_thumbnail_img add foreign key(r_book_no)
    references web_app_test2.tb_book (r_book_no) on delete cascade;

# tb_book_view 테이블 추가
create table if not exists web_app_test2.tb_book_view(
                                                         view_no int unsigned not null auto_increment comment '조회수 번호' primary key,
                                                         user_no int unsigned not null comment '유저 번호',
                                                         r_book_no int unsigned not null comment '책 번호',
                                                         reg_dt datetime not null default current_timestamp comment '등록일자'
                                                     ) engine=innodb default charset=utf8mb4 comment '책 조회수';

# tb_book_view 외래키 설정
alter table web_app_test2.tb_book_view add foreign key(user_no)
    references web_app_test2.tb_user (user_no) on delete cascade;

alter table web_app_test2.tb_book_view add foreign key(r_book_no)
    references web_app_test2.tb_book(r_book_no) on delete cascade;

# tb_book_searching_keyword 테이블 추가
create table if not exists web_app_test2.tb_book_searching_keyword(
                                                                      book_keyword_no int unsigned not null auto_increment comment '책 키워드 번호' primary key,
                                                                      user_no int unsigned not null comment '유저 번호',
                                                                      keyword varchar(40) not null comment '키워드',
    reg_dt datetime not null default current_timestamp comment '등록일자'
    ) engine=innodb default charset=utf8mb4 comment '책 검색 키워드';

# tb_book_searching_keyword 외래키 설정
alter table web_app_test2.tb_book_searching_keyword add foreign key(user_no)
    references web_app_test2.tb_user (user_no) on delete cascade;

### comic book 관련 설정들 ####
# tb_comic_book 테이블 수정
DELIMITER $$

DROP PROCEDURE IF EXISTS modify_table $$
CREATE PROCEDURE modify_table()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM INFORMATION_SCHEMA.COLUMNS
                   WHERE table_name = 'tb_comic_book'
                     AND table_schema = 'web_app_test2'
                     AND column_name = 'serial_state')) THEN
alter table web_app_test2.tb_comic_book modify author varchar(30) not null default 'noname' comment '작가이름';
alter table web_app_test2.tb_comic_book add link text not null comment '링크' after author;
alter table web_app_test2.tb_comic_book add serial_state enum('finished','being','vacation') not null default 'finished' comment '연재상태' after link;
alter table web_app_test2.tb_comic_book add mod_dt datetime not null default current_timestamp comment '수정일자' after reg_dt;
END IF;

END $$

CALL modify_table() $$

DELIMITER ;

# tb_comic_book_thumbnail_img 테이블 추가
create table if not exists web_app_test2.tb_comic_book_thumbnail_img(
                                                                        book_img_no int unsigned not null auto_increment comment '책 이미지 번호' primary key,
                                                                        r_book_no int unsigned not null comment '책 번호',
                                                                        file_path varchar(50) not null comment '파일 경로',
    file_name varchar(50) not null comment '파일 명',
    reg_dt datetime not null default current_timestamp comment '등록일자',
    mod_dt datetime not null default current_timestamp comment '수정일자'
    ) engine=innodb default charset=utf8mb4 comment '만화책 썸네일 이미지';

# tb_comic_book_thumbnail_img 외래키 설정
alter table web_app_test2.tb_comic_book_thumbnail_img add foreign key(r_book_no)
    references web_app_test2.tb_comic_book (r_book_no) on delete cascade;

# tb_comic_book_view 테이블 추가
create table if not exists web_app_test2.tb_comic_book_view(
                                                               view_no int unsigned not null auto_increment comment '조회수 번호' primary key,
                                                               user_no int unsigned not null comment '유저 번호',
                                                               r_book_no int unsigned not null comment '책 번호',
                                                               reg_dt datetime not null default current_timestamp comment '등록일자'
                                                           ) engine=innodb default charset=utf8mb4 comment '만화책 조회수';

# tb_comic_book_view 외래키 설정
alter table web_app_test2.tb_comic_book_view add foreign key(user_no)
    references web_app_test2.tb_user (user_no) on delete cascade;

alter table web_app_test2.tb_comic_book_view add foreign key(r_book_no)
    references web_app_test2.tb_comic_book(r_book_no) on delete cascade;

# tb_comic_book_searching_keyword 테이블 추가
create table if not exists web_app_test2.tb_comic_book_searching_keyword(
                                                                            comic_book_keyword_no int unsigned not null auto_increment comment '만화책 키워드 번호' primary key,
                                                                            user_no int unsigned not null comment '유저 번호',
                                                                            keyword varchar(40) not null comment '키워드',
    reg_dt datetime not null default current_timestamp comment '등록일자'
    ) engine=innodb default charset=utf8mb4 comment '책 검색 키워드';

# tb_comic_book_searching_keyword 외래키 설정
alter table web_app_test2.tb_comic_book_searching_keyword add foreign key(user_no)
    references web_app_test2.tb_user (user_no) on delete cascade;

### drama 관련 설정들 ####
### tb_drama 관련 설정들(수정사항) ####
DELIMITER $$

DROP PROCEDURE IF EXISTS modify_table $$
CREATE PROCEDURE modify_table()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM INFORMATION_SCHEMA.COLUMNS
                   WHERE table_name = 'tb_drama'
                     AND table_schema = 'web_app_test2'
                     AND column_name = 'broadcast_state')) THEN
alter table web_app_test2.tb_drama modify title text not null comment '제목';
alter table web_app_test2.tb_drama modify author varchar(30) not null comment '작가이름';
alter table web_app_test2.tb_drama add link text not null comment '링크' after author;
alter table web_app_test2.tb_drama add broadcast_state enum('end','yet','early_end') not null default 'end' comment '방영상태';
alter table web_app_test2.tb_drama add mod_dt datetime not null default current_timestamp comment '수정일자' after reg_dt;
END IF;

END $$

CALL modify_table() $$

DELIMITER ;

# tb_drama_book_thumbnail_img 테이블 추가
create table if not exists web_app_test2.tb_drama_book_thumbnail_img(
                                                                        drama_img_no int unsigned not null auto_increment comment '드라마 이미지 번호' primary key,
                                                                        r_drama_no int unsigned not null comment '드라마 번호',
                                                                        file_path varchar(50) not null comment '파일 경로',
    file_name varchar(50) not null comment '파일 명',
    reg_dt datetime not null default current_timestamp comment '등록일자',
    mod_dt datetime not null default current_timestamp comment '수정일자'
    ) engine=innodb default charset=utf8mb4 comment '만화책 썸네일 이미지';

# tb_drama_book_thumbnail_img 외래키 설정
alter table web_app_test2.tb_drama_book_thumbnail_img add foreign key(r_drama_no)
    references web_app_test2.tb_drama (r_drama_no) on delete cascade;

# tb_comic_book_view 테이블 추가
create table if not exists web_app_test2.tb_drama_view(
                                                          view_no int unsigned not null auto_increment comment '조회수 번호' primary key,
                                                          user_no int unsigned not null comment '유저 번호',
                                                          r_drama_no int unsigned not null comment '드라마 번호',
                                                          reg_dt datetime not null default current_timestamp comment '등록일자'
                                                      ) engine=innodb default charset=utf8mb4 comment '만화책 조회수';

# tb_comic_book_view 외래키 설정
alter table web_app_test2.tb_drama_view add foreign key(user_no)
    references web_app_test2.tb_user (user_no) on delete cascade;

alter table web_app_test2.tb_drama_view add foreign key(r_drama_no)
    references web_app_test2.tb_drama(r_drama_no) on delete cascade;

# tb_drama_searching_keyword 테이블 추가
create table if not exists web_app_test2.tb_drama_searching_keyword(
                                                                       drama_keyword_no int unsigned not null auto_increment comment '드라마 키워드 번호' primary key,
                                                                       user_no int unsigned not null comment '유저 번호',
                                                                       keyword varchar(40) not null comment '키워드',
    reg_dt datetime not null default current_timestamp comment '등록일자'
    ) engine=innodb default charset=utf8mb4 comment '책 검색 키워드';

# tb_drama_searching_keyword 외래키 설정
alter table web_app_test2.tb_drama_searching_keyword add foreign key(user_no)
    references web_app_test2.tb_user (user_no) on delete cascade;

### movie 관련 설정들 ####
### tb_movie 관련 설정들(수정사항) ####
DELIMITER $$

DROP PROCEDURE IF EXISTS modify_table $$
CREATE PROCEDURE modify_table()
BEGIN

    IF NOT EXISTS((SELECT *
                   FROM INFORMATION_SCHEMA.COLUMNS
                   WHERE table_name = 'tb_movie'
                     AND table_schema = 'web_app_test2'
                     AND column_name = 'total_number_of_episode')) THEN
alter table web_app_test2.tb_movie modify title text not null comment '제목';
alter table web_app_test2.tb_movie modify director varchar(30) not null default 'noname' comment '감독이름';
alter table web_app_test2.tb_movie add link text not null comment '링크' after director;
alter table web_app_test2.tb_movie add total_number_of_episode tinyint(3) not null comment '총회차' after link;
END IF;

END $$

CALL modify_table() $$

DELIMITER ;

# tb_movie_thumbnail_img 테이블 추가
create table if not exists web_app_test2.tb_movie_thumbnail_img(
                                                                   movie_img_no int unsigned not null auto_increment comment '영화 이미지 번호' primary key,
                                                                   r_movie_no int unsigned not null comment '영화 번호',
                                                                   file_path varchar(50) not null comment '파일 경로',
    file_name varchar(50) not null comment '파일 명',
    reg_dt datetime not null default current_timestamp comment '등록일자',
    mod_dt datetime not null default current_timestamp comment '수정일자'
    ) engine=innodb default charset=utf8mb4 comment '영화 썸네일 이미지';

# tb_movie_thumbnail_img 외래키 설정
alter table web_app_test2.tb_movie_thumbnail_img add foreign key(r_movie_no)
    references web_app_test2.tb_movie (r_movie_no) on delete cascade;

# tb_movie_view 테이블 추가
create table if not exists web_app_test2.tb_movie_view(
                                                          view_no int unsigned not null auto_increment comment '조회수 번호' primary key,
                                                          user_no int unsigned not null comment '유저 번호',
                                                          r_movie_no int unsigned not null comment '영화 번호',
                                                          reg_dt datetime not null default current_timestamp comment '등록일자'
                                                      ) engine=innodb default charset=utf8mb4 comment '만화책 조회수';

# tb_movie_view 외래키 설정
alter table web_app_test2.tb_movie_view add foreign key(user_no)
    references web_app_test2.tb_user (user_no) on delete cascade;

alter table web_app_test2.tb_movie_view add foreign key(r_movie_no)
    references web_app_test2.tb_movie(r_movie_no) on delete cascade;

# tb_movie_searching_keyword 테이블 추가
create table if not exists web_app_test2.tb_movie_searching_keyword(
                                                                       movie_keyword_no int unsigned not null auto_increment comment '영화 키워드 번호' primary key,
                                                                       user_no int unsigned not null comment '유저 번호',
                                                                       keyword varchar(40) not null comment '키워드',
    reg_dt datetime not null default current_timestamp comment '등록일자'
    ) engine=innodb default charset=utf8mb4 comment '영화 검색 키워드';

# tb_movie_searching_keyword 외래키 설정
alter table web_app_test2.tb_movie_searching_keyword add foreign key(user_no)
    references web_app_test2.tb_user (user_no) on delete cascade;

# 2. tb_user에 딸린 각종 테이블 추가
create table if not exists web_app_test2.tb_user_profile_img(
                                                                profile_img_no int unsigned not null auto_increment comment '프로필 이미지 번호' primary key,
                                                                user_no int unsigned not null comment '유저 번호',
                                                                file_path varchar(50) not null comment '파일 경로',
    file_name varchar(50) not null comment '파일 명',
    reg_dt datetime not null default current_timestamp comment '등록일자',
    mod_dt datetime not null default current_timestamp comment '수정일자'
    ) engine=innodb default charset=utf8mb4 comment '유저 프로필(썸네일) 이미지';

alter table web_app_test2.tb_user_profile_img add foreign key(user_no)
    references web_app_test2.tb_user (user_no) on delete cascade;

# 3.컨텐츠 제작 국가 테이블 수정
alter table web_app_test2.tb_contents_made_nature add mod_dt datetime not null default current_timestamp comment '수정일자' after reg_dt;
