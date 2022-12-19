package com.dev.pleaseTakecareFiveDucks.book.service;

import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;
import com.dev.pleaseTakecareFiveDucks.book.util.BookUseYnEnum;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.BookDAO;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookServiceImplTest extends TestCase {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookDAO bookDAO;

    @Test
    public void test1_SelectBookTotalCnt() {

        // given
        Integer wholeBookCnt = 10;
        given(bookDAO.getBookTotalCnt()).willReturn(wholeBookCnt);

        // when
        bookService.selectBookTotalCnt();

        // then & verify
        then(bookDAO).should().getBookTotalCnt();
        verify(bookDAO, times(1)).getBookTotalCnt();
    }

    @Test
    public void test2_1_RemoveAllBookInfoList() throws Exception {

        // 1. bookCnt가 0개 초과로 존재할 경우
        // 2. 제대로 삭제로직이 실행 되었을 경우

        // given
        Integer wholeBookTotalCnt = 1;
        given(bookDAO.getBookTotalCnt()).willReturn(wholeBookTotalCnt);

        Integer wholeRemovedBookCnt = 1;
        given(bookDAO.deleteAll()).willReturn(wholeRemovedBookCnt);

        // when
        bookService.removeAllBookInfoList();

        // then & verify
        then(bookDAO).should().getBookTotalCnt();
        then(bookDAO).should().deleteAll();
        //
        verify(bookDAO, times(1)).getBookTotalCnt();
        verify(bookDAO, times(1)).deleteAll();
    }

    @Test
    public void test2_2_RemoveAllBookInfoList() {

        // 1. bookCnt가 0개 초과로 존재할 경우
        // 2. 제대로 삭제로직이 실행 되지 않았을 경우

        // todo: doThrow를 사용할 것인지, thenThrow를 사용할 것인지 다시 살펴보기
    }

    @Test
    public void test2_3_RemoveAllBookInfoList() throws Exception {

        // 1. bookCnt가 1개 미만으로 존재할 경우

        // given
        Integer wholeBookTotalCnt = 0;
        given(bookDAO.getBookTotalCnt()).willReturn(wholeBookTotalCnt);

        // when
        bookService.removeAllBookInfoList();

        // then & verify
        then(bookDAO).should().getBookTotalCnt();
        //
        verify(bookDAO, times(1)).getBookTotalCnt();
        verify(bookDAO, times(0)).deleteAll();
    }

    // todo: 추후 jsp에서 어떻게 받을지 결정한 후에 개발하는 것으로 할게요.
    @Test
    public void test3_SelectBookList() {

        // given

        // when

        // then & verify
    }

    @Test
    public void test4_SelectAllBookInfoList() {

        // given
        BookVO bookVO = mock(BookVO.class);
        bookVO.setBookNo(1);
        bookVO.setBookAuthor("테스트_저자");
        bookVO.setBookRegDt("2022-12-30");
        bookVO.setBookTitle("테스트_제목");
        bookVO.setBookUseYnEnum(BookUseYnEnum.Y);
        bookVO.setMadeNatureNo(1);
        bookVO.setRegDt("2022-12-30");

        BookVO bookVO2 = mock(BookVO.class);
        bookVO2.setBookNo(1);
        bookVO2.setBookAuthor("테스트_저자");
        bookVO2.setBookRegDt("2022-12-30");
        bookVO2.setBookTitle("테스트_제목");
        bookVO2.setBookUseYnEnum(BookUseYnEnum.Y);
        bookVO2.setMadeNatureNo(1);
        bookVO2.setRegDt("2022-12-30");

        BookVO bookVO3 = mock(BookVO.class);
        bookVO3.setBookNo(1);
        bookVO3.setBookAuthor("테스트_저자");
        bookVO3.setBookRegDt("2022-12-30");
        bookVO3.setBookTitle("테스트_제목");
        bookVO3.setBookUseYnEnum(BookUseYnEnum.Y);
        bookVO3.setMadeNatureNo(1);
        bookVO3.setRegDt("2022-12-30");

        List<BookVO> bookVOList = mock(ArrayList.class);
        bookVOList.add(bookVO);
        bookVOList.add(bookVO2);
        bookVOList.add(bookVO3);

        given(bookDAO.selectAllBookList()).willReturn(bookVOList);

        // when
        bookService.selectAllBookInfoList();

        // then & verify
        then(bookDAO).should().selectAllBookList();

        verify(bookDAO, times(1)).selectAllBookList();
    }

    @Test
    public void test5_SelectBookInfo() {

        // given

        // when

        // then & verify

    }

    // todo: book을 등록하는 로직 작성을 해야하는데... exception 처리에 대해서 공부 후에 테스트케이스 추가 작성 예정
    @Test
    public void test6_RegisterBookInfo() {

        // given

        // when

        // then & verify
    }

    // todo: exception 처리에 대해서 공부 후에 테스트케이스 추가 작성 예정
    @Test
    public void test7_ModifyBookInfo() {

        // given

        // when

        // then & verify
    }

    // todo: exception 처리에 대해서 공부 후에 테스트케이스 추가 작성 예정
    @Test
    public void test8_RemoveBookInfo() {

        // given

        // when

        // then & verify
    }
}
