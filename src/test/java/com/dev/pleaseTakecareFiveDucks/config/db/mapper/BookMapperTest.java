package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.InsertBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.SelectBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.UpdateBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(BookMapperTest.class);

    @Autowired
    private BookMapper bookMapper;

    private static Integer natureNo = 0;

    private static InsertBookInfoRequestDTO insertBookInfoRequestDTO;

    @Before
    public void init() {

        // 공통적으로 쓰일 natureNo 입니다. 1은 한국의 natureNo 입니다.
        natureNo = 1;

        // 공통적으로 쓰일 insertBookInfoRequestDTO 입니다.
        insertBookInfoRequestDTO = InsertBookInfoRequestDTO.builder()
                .madeNatureNo(natureNo)
                .title("해리포터")
                .author("JK롤링")
                .bookRegDt("2022-12-21")
                .build();
    }

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

        // given
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

        // given
        int insertedCnt = bookMapper.insertBookInfo(insertBookInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        ////////////////////////////////////////

        // 2. 확인

        // given
        SelectBookInfoRequestDTO selectBookInfoRequestDTO = SelectBookInfoRequestDTO.builder()
                .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                .build();

        BookVO bookVO = bookMapper.selectBookInfo(selectBookInfoRequestDTO);

        // when & then
        assertThat(bookVO.getBookNo(), Matchers.greaterThanOrEqualTo(insertBookInfoRequestDTO.getInsertedBookNo()));

        ////////////////////////////////////////

        // 3. 전체 삭제
        // (1개 이상 삭제외어야 합니다.)

        // given
        int deletedAllBookCnt = bookMapper.deleteAll();

        // when & then
        assertThat(deletedAllBookCnt, Matchers.greaterThanOrEqualTo(1));

        ////////////////////////////////////////

        // 4. 전체 리스트 조회
        // given
        List<BookVO> bookVOList = bookMapper.selectAllBookList();

        // when & then
        assertThat(bookVOList.size(), is(0));
    }

    @Test
    public void testSelectAllBookList() {

        // 1. 삽입

        // given
        int insertedCnt = bookMapper.insertBookInfo(insertBookInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        ///////////////////////////////////////////////////////////

        // 2. 전체 조회해서, 0번째 element를 조회합니다.

        // given
        List<BookVO> bookVOList = bookMapper.selectAllBookList();

        // when & then
        assertThat(bookVOList.size(), is(1));
        assertThat(bookVOList.get(0).getBookTitle(), is("해리포터"));
        assertThat(bookVOList.get(0).getBookAuthor(), is("JK롤링"));
    }

    // Pagination Test입니다.
    @Test
    public void testSelectBookList() {

        // 1. 삽입

        // given
        int insertedCnt = bookMapper.insertBookInfo(insertBookInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        ///////////////////////////////////////////////////////////

        // 2. Pagination으로 조회해서, 해당 객체의 element를 검증합니다.

        // given
        // TODO: dao단에서의 pagination은 간단하기 때문에..service단에서 pagination에 대한 기능 테스트를 마치고 추후 테스트 케이스 작성 예정입니다.

        // when & then
    }

    @Test
    public void testSelectBookInfo() {

        // 1. 삽입

        // given
        int insertedCnt = bookMapper.insertBookInfo(insertBookInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        ///////////////////////////////////////////////////////

        // 2.단일 조회를 해서, 해당 객체의 element를 검증합니다.

        // given
        SelectBookInfoRequestDTO selectBookInfoRequestDTO = SelectBookInfoRequestDTO.builder()
                .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                .build();

        // when
        BookVO bookVO = bookMapper.selectBookInfo(selectBookInfoRequestDTO);

        // then
        assertThat(bookVO.getBookTitle(), is("해리포터"));
        assertThat(bookVO.getBookAuthor(), containsString("롤링"));
    }

    @Test
    public void testInsertBookInfo() {

        // 바로 위에서 실행한 testSelectBookInfo 메소드의 테스트와 일맥상통하므로 생략하겠습니다.

    }

    @Test
    public void testUpdateBookInfo() {

        // 1. 삽입

        // given
        int insertedCnt = bookMapper.insertBookInfo(insertBookInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectBookInfoRequestDTO selectBookInfoRequestDTO = SelectBookInfoRequestDTO.builder()
                .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                .build();

        BookVO bookVO = bookMapper.selectBookInfo(selectBookInfoRequestDTO);

        // when & then
        assertThat(bookVO.getBookNo(), Matchers.greaterThanOrEqualTo(insertBookInfoRequestDTO.getInsertedBookNo()));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateBookInfoRequestDTO updateBookInfoRequestDTO = UpdateBookInfoRequestDTO.builder()
                .madeNatureNo(natureNo)
                .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                .title("나미야잡화점의기적")
                .author("히가시노게이고")
                .bookRegDt("2010-12-20")
                .build();

        // when & then
        int updatedCnt = bookMapper.updateBookInfo(updateBookInfoRequestDTO);

        // 업데이트는 반드시 한번만 되어야 합니다.
        assertThat(updatedCnt, is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        SelectBookInfoRequestDTO selectBookInfoRequestDTO2 = SelectBookInfoRequestDTO.builder()
                .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                .build();

        // when
        // 다시 조회했을때, 3번에서 업데이트한 정보들이 조회가 되어야 합니다.
        BookVO bookVO2 = bookMapper.selectBookInfo(selectBookInfoRequestDTO2);

        // then
        assertThat(bookVO2.getBookNo(), is(insertBookInfoRequestDTO.getInsertedBookNo()));
        assertThat(bookVO2.getBookTitle(), is("나미야잡화점의기적"));
        assertThat(bookVO2.getBookAuthor(), is("히가시노게이고"));
    }

    @Test
    public void testUpdateBookState() {

        // 1. 삽입

        // given

        // when & then

        //////////////////////////////////

        // 2. 조회

        // given

        // when & then

        //////////////////////////////////

        // 3. 업데이트

        // given

        // when & then

        //////////////////////////////////

        // 4. 조회

        // given

        // when & then
    }

    @Test
    public void testDeleteBookInfo() {


    }
}
