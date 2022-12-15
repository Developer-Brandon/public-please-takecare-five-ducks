package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.InsertBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.SelectBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.UpdateBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.UpdateBookStateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;
import com.dev.pleaseTakecareFiveDucks.book.util.BookUseYnEnum;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookDAOTests {

    private static final Logger logger = LoggerFactory.getLogger(BookDAOTests.class);

    @Autowired
    private BookDAO bookDAO;

    private Integer natureNo = 0;

    private InsertBookInfoRequestDTO insertBookInfoRequestDTO;

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

    @After
    public void destroy() {
        logger.debug("테스트 케이스 완료");
    }

    @Ignore
    @Test
    public void test1_GetBookTotalCnt() {

        // 1. 최초 조회 시 0개(테스트 데이터 삽입 전에는 0개로 나와야 합니다, 추후 테스트 데이터가 채워지면 0개로 나올수 없습니다.)

        // given
        int bookTotalCnt = bookDAO.getBookTotalCnt();

        // when & then
        assertThat(bookTotalCnt, is(0));

        ////////////////////////////////////////

        // 2. 삽입

        // given
        int insertedCnt = bookDAO.insertBookInfo(insertBookInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        ////////////////////////////////////////

        // 3. 재조회

        // given
        int bookTotalCnt2 = bookDAO.getBookTotalCnt();

        // when & then
        assertThat(bookTotalCnt2, Matchers.greaterThanOrEqualTo(1));
    }

    @Test
    public void test2_DeleteAll() {

        // 1. 삽입

        // given

        // when & then
        int insertedCnt = bookDAO.insertBookInfo(insertBookInfoRequestDTO);

        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        ////////////////////////////////////////

        // 2. 확인

        // given
        SelectBookInfoRequestDTO selectBookInfoRequestDTO = SelectBookInfoRequestDTO.builder()
                .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                .build();
        // when & then
        BookVO bookVO = bookDAO.selectBookInfo(selectBookInfoRequestDTO);
        assertThat(bookVO.getBookNo(), Matchers.greaterThanOrEqualTo(insertBookInfoRequestDTO.getInsertedBookNo()));

        ////////////////////////////////////////

        // 3. 전체 삭제
        // (1개 이상 삭제외어야 합니다.)

        // given
        int deletedAllBookCnt = bookDAO.deleteAll();

        // when & then
        assertThat(deletedAllBookCnt, Matchers.greaterThanOrEqualTo(1));

        ////////////////////////////////////////

        // 4. 전체 리스트 조회
        // given
        List<BookVO> bookVOList = bookDAO.selectAllBookList();

        // when & then
        assertThat(bookVOList.size(), is(0));
    }

    @Test
    public void test3_SelectAllBookList() {

        // 1. 삽입

        // given
        int insertedCnt = bookDAO.insertBookInfo(insertBookInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        ///////////////////////////////////////////////////////////

        // 2. 전체 조회해서, 0번째 element를 조회합니다.

        // given

        // when
        List<BookVO> bookVOList = bookDAO.selectAllBookList();

        // then
        assertThat(bookVOList.size(), is(1));
        assertThat(bookVOList.get(0).getBookTitle(), is("해리포터"));
        assertThat(bookVOList.get(0).getBookAuthor(), is("JK롤링"));
    }

    // Pagination Test입니다.
    @Test
    public void test4_SelectBookList() {

        // 1. 삽입

        // given
        int insertedCnt = bookDAO.insertBookInfo(insertBookInfoRequestDTO);

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
    public void test5_SelectBookInfo() {

        // 1. 삽입

        // given
        int insertedCnt = bookDAO.insertBookInfo(insertBookInfoRequestDTO);

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
        BookVO bookVO = bookDAO.selectBookInfo(selectBookInfoRequestDTO);

        // then
        assertThat(bookVO.getBookTitle(), is("해리포터"));
        assertThat(bookVO.getBookAuthor(), containsString("롤링"));
    }

    @Test
    public void test6_InsertBookInfo() {

        // 바로 위에서 실행한 testSelectBookInfo 메소드의 테스트와 일맥상통하므로 생략하겠습니다.

    }

    @Test
    public void test7_UpdateBookInfo() {

        // 1. 삽입

        // given
        int insertedCnt = bookDAO.insertBookInfo(insertBookInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectBookInfoRequestDTO selectBookInfoRequestDTO = SelectBookInfoRequestDTO.builder()
                .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                .build();

        BookVO bookVO = bookDAO.selectBookInfo(selectBookInfoRequestDTO);

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
        int updatedCnt = bookDAO.updateBookInfo(updateBookInfoRequestDTO);

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
        BookVO bookVO2 = bookDAO.selectBookInfo(selectBookInfoRequestDTO2);

        // then
        assertThat(bookVO2.getBookNo(), is(insertBookInfoRequestDTO.getInsertedBookNo()));
        assertThat(bookVO2.getBookTitle(), is("나미야잡화점의기적"));
        assertThat(bookVO2.getBookAuthor(), is("히가시노게이고"));
    }

    @Test
    public void test8_UpdateBookState() {

        // 1. 삽입

        // given
        int insertedCnt = bookDAO.insertBookInfo(insertBookInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectBookInfoRequestDTO selectBookInfoRequestDTO = SelectBookInfoRequestDTO.builder()
                .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                .build();

        BookVO bookVO = bookDAO.selectBookInfo(selectBookInfoRequestDTO);

        // when & then
        assertThat(bookVO.getBookNo(), Matchers.greaterThanOrEqualTo(insertBookInfoRequestDTO.getInsertedBookNo()));
        // 추가로, 책의 상태도 체크합니다.
        assertThat(bookVO.getBookUseYnEnum(), is(BookUseYnEnum.Y));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateBookStateRequestDTO updateBookStateRequestDTO = UpdateBookStateRequestDTO.builder()
                .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                .bookUseYnEnum(BookUseYnEnum.N)
                .build();

        // when
        int updatedCnt = bookDAO.updateBookState(updateBookStateRequestDTO);

        // then
        assertThat(updatedCnt, is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        // 상단에서 조회한 dto를 그대로 가져와서 조회합니다.

        // when & then
        BookVO bookVO2 = bookDAO.selectBookInfo(selectBookInfoRequestDTO);

        assertThat(bookVO2.getBookUseYnEnum(), is(BookUseYnEnum.N));
    }

    @Test
    public void test9_DeleteBookInfo() {

        // 1. 삽입

        // given
        // 최상단에서 만들어진 dto를 그대로 가져와서 조회합니다.

        // when
        int insertedCnt = bookDAO.insertBookInfo(insertBookInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertBookInfoRequestDTO.getInsertedBookNo(), Matchers.greaterThanOrEqualTo(1));

        ////////////////////////////////

        // 2. 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectBookInfoRequestDTO selectBookInfoRequestDTO = SelectBookInfoRequestDTO.builder()
                .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                .build();

        // when
        BookVO bookVO = bookDAO.selectBookInfo(selectBookInfoRequestDTO);

        // then
        assertThat(bookVO.getBookNo(), is(selectBookInfoRequestDTO.getBookNo()));

        ////////////////////////////////

        // 3. 삭제

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.

        // when
        int deletedCnt = bookDAO.deleteBookInfo(bookVO.getBookNo());

        // then
        assertThat(deletedCnt, is(1));

        ////////////////////////////////

        // 4. 다시 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectBookInfoRequestDTO selectBookInfoRequestDTO2 = SelectBookInfoRequestDTO.builder()
                .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                .build();

        // when
        BookVO bookVO2 = bookDAO.selectBookInfo(selectBookInfoRequestDTO2);

        // then
        assertThat(bookVO2, is(nullValue()));
    }
}
