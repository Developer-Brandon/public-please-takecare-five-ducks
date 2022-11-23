# 1. web_app_test2 스키마 생성
create schema if not exists web_app_test2;

#2. 테이블 생성
create table if not exists web_app_test2.tb_book(
    r_book_no int unsigned not null auto_increment comment '책 번호' primary key,
    m_n_no int unsigned not null comment '제작 국가 번호',
    title text not null comment '책 제목',
    author varchar(30) not null default 'noname' comment '작가',
    use_yn enum('Y','N') not null default 'Y' comment '사용여부',
    book_reg_dt datetime not null default current_timestamp comment '출간일자',
    reg_dt datetime not null default current_timestamp comment '등록일자'
) engine=innodb default charset=utf8mb4 comment '책';

create table if not exists web_app_test2.tb_comic_book(
    r_book_no int unsigned not null auto_increment comment '책 번호' primary key,
    m_n_no int unsigned not null comment '제작 국가 번호',
    title text not null comment '책 제목',
    author varchar(30) not null default 'noname' comment '작가',
    use_yn enum('Y','N') not null default 'Y' comment '사용여부',
    book_reg_dt datetime not null default current_timestamp comment '출간일자',
    reg_dt datetime not null default current_timestamp comment '등록일자'
) engine=innodb default charset=utf8mb4 comment '만화책';

create table if not exists web_app_test2.tb_anime(
    r_anime_no int unsigned not null auto_increment comment '애니 번호' primary key,
    m_n_no int unsigned not null comment '제작 국가 번호',
    title text not null comment '애니 제목',
    author varchar(30) not null default 'noname' comment '작가',
    page_per_anime_cnt int unsigned not null comment '페이지 당 애니의 개수',
    use_yn enum('Y','N') not null default 'Y' comment '사용여부',
    anime_reg_dt datetime not null default current_timestamp comment '출시일자',
    reg_dt datetime not null default current_timestamp comment '등록일자'
) engine=innodb default charset=utf8mb4 comment '애니';

create table if not exists web_app_test2.tb_drama(
    r_drama_no int unsigned not null auto_increment comment '드라마 번호' primary key,
    m_n_no int unsigned not null comment '제작 국가 번호',
    title text not null comment '드라마 제목',
    author varchar(30) not null default 'noname' comment '작가',
    page_per_drama_cnt int unsigned not null comment '페이지 당 드라마의 개수',
    use_yn enum('Y','N') not null default 'Y' comment '사용여부',
    drama_reg_dt datetime not null default current_timestamp comment '방영 시작 일자',
    reg_dt datetime not null default current_timestamp comment '등록일자'
) engine=innodb default charset=utf8mb4 comment '드라마';

create table if not exists web_app_test2.tb_movie(
    r_movie_no int unsigned not null auto_increment comment '영화 번호' primary key,
    m_n_no int unsigned not null comment '제작 국가 번호',
    title text not null comment '영화 제목',
    director varchar(30) not null default 'noname' comment '감독',
    page_per_movie_cnt int unsigned not null comment '페이지 당 영화의 개수',
    use_yn enum('Y','N') not null default 'Y' comment '사용여부',
    movie_reg_dt datetime not null default current_timestamp comment '영화 개봉 일자',
    reg_dt datetime not null default current_timestamp comment '등록일자'
) engine=innodb default charset=utf8mb4 comment '영화';

create table if not exists web_app_test2.tb_contents_made_nature(
    m_n_no int unsigned not null auto_increment comment '제작 국가 번호' primary key,
    english_name varchar(30) not null default '?' comment '(영문)제작 국가 이름',
    korean_name varchar(30) not null default '?' comment '(국문)제작 국가 이름',
    use_yn enum('Y','N') not null default 'Y' comment '사용여부',
    reg_dt datetime not null default current_timestamp comment '등록일자'
) engine=innodb default charset=utf8mb4 comment '컨텐츠 제작 국가';

# 3. FK 처리
alter table web_app_test2.tb_book add foreign key(m_n_no)
    references web_app_test2.tb_contents_made_nature(m_n_no) on delete cascade;

alter table web_app_test2.tb_comic_book add foreign key(m_n_no)
    references web_app_test2.tb_contents_made_nature(m_n_no) on delete cascade;

alter table web_app_test2.tb_anime add foreign key(m_n_no)
    references web_app_test2.tb_contents_made_nature(m_n_no) on delete cascade;

alter table web_app_test2.tb_drama add foreign key(m_n_no)
    references web_app_test2.tb_contents_made_nature(m_n_no) on delete cascade;

alter table web_app_test2.tb_movie add foreign key(m_n_no)
    references web_app_test2.tb_contents_made_nature(m_n_no) on delete cascade;

# 4. FK 처리가  필요없는 테이블
create table if not exists web_app_test2.tb_mp3(
    r_mp3_no int unsigned not null auto_increment comment 'mp3 번호' primary key,
    title text not null comment 'mp3 제목',
    singer varchar(30) not null default 'noname' comment '가수',
    use_yn enum('Y','N') not null default 'Y' comment '사용여부',
    song_reg_dt datetime not null default current_timestamp comment '음원 출시 일자',
    reg_dt datetime not null default current_timestamp comment '등록일자'
) engine=innodb default charset=utf8mb4 comment 'mp3';

create table if not exists web_app_test2.tb_youtube(
    r_youtube_no int unsigned not null auto_increment comment 'youtube 번호' primary key,
    title text not null comment 'youtube 영상 제목',
    youtuber_name varchar(30) not null default 'noname' comment '유투버 이름',
    like_cnt int unsigned not null default 0 comment '좋아요 수',
    dislike_cnt int unsigned not null default 0 comment '싫어요 수',
    view_cnt int unsigned not null default 0 comment '조회수',
    use_yn enum('Y','N') not null default 'Y' comment '사용여부',
    youtube_reg_dt datetime not null default current_timestamp comment '(유투브 자체의)영상 등록일자',
    reg_dt datetime not null default current_timestamp comment '등록일자'
) engine=innodb default charset=utf8mb4 comment 'youtube';


# 5. 회원가입 관련 테이블
create table if not exists web_app_test2.tb_user(
    user_no int unsigned not null auto_increment comment '유저 번호' primary key,
    user_type_no int unsigned not null comment '유저 타입 번호',
    email varchar(40) not null comment '유저 이메일',
    password varchar(30) not null comment '유저 패스워드',
    user_name varchar(20) not null comment '유저 이름',
    use_yn enum('Y','N') not null default 'Y' comment '사용여부',
    reg_dt datetime not null default current_timestamp comment '등록일자',
    mod_dt datetime not null default current_timestamp comment '수정일자'
) engine=innodb default charset=utf8mb4 comment '유저';

create table if not exists web_app_test2.tb_user_type(
    user_type_no int unsigned not null auto_increment comment '유저 타입 번호' primary key,
    type varchar(40) not null comment '유저 타입',
    use_yn enum('Y','N') not null default 'Y' comment '사용여부',
    reg_dt datetime not null default current_timestamp comment '등록일자',
    mod_dt datetime not null default current_timestamp comment '수정일자'
) engine=innodb default charset=utf8mb4 comment '유저타입';

alter table web_app_test2.tb_user add foreign key(user_type_no)
    references web_app_test2.tb_user_type(user_type_no) on delete cascade;
