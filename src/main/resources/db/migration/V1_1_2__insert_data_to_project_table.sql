# user_type 삽입
insert web_app_test2.tb_user_type(type)
values('GUEST')
,('ADMIN')
,('SUPER_ADMIN');

# user_type 삽입
insert web_app_test2.tb_book_type(tag_korean_name, tag_english_name)
values('스크립트', 'SCRIPT')
, ('책요약본', 'BOOK_SUMMARY')
,('책PDF', 'BOOK_PDF')
,('공부요약본', 'STUDY_SUMMARY')
,('강의요약본', 'LECTURE_SUMMARY')
,('소설', 'NOVEL');
