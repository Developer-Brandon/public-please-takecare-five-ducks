package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.InsertBookInfoRequestDTO;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(BookMapperTest.class);

    @Autowired
    private BookMapper bookMapper;

    @Ignore
    @Test
    public void testGetBookTotalCnt() {

        // 1. 최초 조회 시 0개(테스트 데이터 삽입 전에는 0개로 나와야 합니다, 추후 테스트 데이터가 채워지면 0개로 나올수 없습니다.)

        // given
        int bookTotalCnt = bookMapper.getBookTotalCnt();

        // when & then
        assertThat(bookTotalCnt, is(0));

        ////////////////////////////////////////

        // 2. 삽입
        int natureNo = 1;

        // given
        InsertBookInfoRequestDTO insertBookInfoRequestDTO = InsertBookInfoRequestDTO.builder()
                .madeNatureNo(natureNo)
                .title("해리포터")
                .author("JK롤링")
                .bookRegDt("2022-12-21")
                .build();

        int insertedCnt = bookMapper.insertBookInfo(insertBookInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        ////////////////////////////////////////

        // 3. 재조회

        // given
        int bookTotalCnt2 = bookMapper.getBookTotalCnt();

        // when & then
        assertThat(bookTotalCnt2, Matchers.greaterThanOrEqualTo(1));
    }

    @Test
    public void testDeleteAll() {

        // 1. 삽입

        // 2. 확인

        // 3. 삭제

        //
    }

    @Test
    public void testSelectAllBookList() {
    }

    @Test
    public void testSelectBookList() {
    }

    @Test
    public void testSelectBookInfo() {
    }

    @Test
    public void testInsertBookInfo() {
    }

    @Test
    public void testUpdateBookInfo() {
    }

    @Test
    public void testUpdateBookState() {
    }

    @Test
    public void testDeleteBookInfo() {
    }
}
