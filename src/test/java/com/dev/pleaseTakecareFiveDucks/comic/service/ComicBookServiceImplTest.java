package com.dev.pleaseTakecareFiveDucks.comic.service;

import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
import com.dev.pleaseTakecareFiveDucks.comic.service.ComicBookServiceImpl;
import com.dev.pleaseTakecareFiveDucks.comic.util.ComicBookUseYnEnum;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.ComicBookDAO;
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
import static org.mockito.Mockito.times;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ComicBookServiceImplTest extends TestCase {

    @InjectMocks
    ComicBookServiceImpl comicBookService;

    @Mock
    ComicBookDAO comicBookDAO;

    @Test
    public void test1_SelectComicBookTotalCnt() {

        // given
        Integer wholeComicBookCnt = 10;
        given(comicBookDAO.getComicBookTotalCnt()).willReturn(wholeComicBookCnt);

        // when
        comicBookService.selectComicBookTotalCnt();

        // then & verify
        then(comicBookDAO).should().getComicBookTotalCnt();
        verify(comicBookDAO, times(1)).getComicBookTotalCnt();
    }

    @Test
    public void test2_1_RemoveAllComicBookInfoList() throws Exception {

        // 1. comicBookCnt가 0개 초과로 존재할 경우
        // 2. 제대로 삭제로직이 실행 되었을 경우

        // given
        Integer wholeComicBookTotalCnt = 1;
        given(comicBookDAO.getComicBookTotalCnt()).willReturn(wholeComicBookTotalCnt);

        Integer wholeRemovedComicBookCnt = 1;
        given(comicBookDAO.deleteAll()).willReturn(wholeRemovedComicBookCnt);

        // when
        comicBookService.removeAllComicBookInfoList();

        // then & verify
        then(comicBookDAO).should().getComicBookTotalCnt();
        then(comicBookDAO).should().deleteAll();
        //
        verify(comicBookDAO, times(1)).getComicBookTotalCnt();
        verify(comicBookDAO, times(1)).deleteAll();
    }

    @Test
    public void test2_2_RemoveAllComicBookInfoList() {

        // 1. comicBookCnt가 0개 초과로 존재할 경우
        // 2. 제대로 삭제로직이 실행 되지 않았을 경우

        // todo: doThrow를 사용할 것인지, thenThrow를 사용할 것인지 다시 살펴보기
    }

    @Test
    public void test2_3_RemoveAllComicBookInfoList() throws Exception {

        // 1. comicBookCnt가 1개 미만으로 존재할 경우

        // given
        Integer wholeComicBookTotalCnt = 0;
        given(comicBookDAO.getComicBookTotalCnt()).willReturn(wholeComicBookTotalCnt);

        // when
        comicBookService.removeAllComicBookInfoList();

        // then & verify
        then(comicBookDAO).should().getComicBookTotalCnt();
        //
        verify(comicBookDAO, times(1)).getComicBookTotalCnt();
        verify(comicBookDAO, times(0)).deleteAll();
    }

    // todo: 추후 jsp에서 어떻게 받을지 결정한 후에 개발하는 것으로 할게요.
    @Test
    public void test3_SelectComicBookList() {

        // given

        // when

        // then & verify
    }

    @Test
    public void test4_SelectAllComicBookInfoList() {

        // given
        ComicBookVO comicBookVO = mock(ComicBookVO.class);
        comicBookVO.setComicBookNo(1);
        comicBookVO.setComicBookAuthor("테스트_저자");
        comicBookVO.setComicBookRegDt("2022-12-30");
        comicBookVO.setComicBookTitle("테스트_제목");
        comicBookVO.setComicBookUseYnEnum(ComicBookUseYnEnum.Y);
        comicBookVO.setMadeNatureNo(1);
        comicBookVO.setRegDt("2022-12-30");

        ComicBookVO comicBookVO2 = mock(ComicBookVO.class);
        comicBookVO2.setComicBookNo(1);
        comicBookVO2.setComicBookAuthor("테스트_저자");
        comicBookVO2.setComicBookRegDt("2022-12-30");
        comicBookVO2.setComicBookTitle("테스트_제목");
        comicBookVO2.setComicBookUseYnEnum(ComicBookUseYnEnum.Y);
        comicBookVO2.setMadeNatureNo(1);
        comicBookVO2.setRegDt("2022-12-30");

        ComicBookVO comicBookVO3 = mock(ComicBookVO.class);
        comicBookVO3.setComicBookNo(1);
        comicBookVO3.setComicBookAuthor("테스트_저자");
        comicBookVO3.setComicBookRegDt("2022-12-30");
        comicBookVO3.setComicBookTitle("테스트_제목");
        comicBookVO3.setComicBookUseYnEnum(ComicBookUseYnEnum.Y);
        comicBookVO3.setMadeNatureNo(1);
        comicBookVO3.setRegDt("2022-12-30");

        List<ComicBookVO> comicBookVOList = mock(ArrayList.class);
        comicBookVOList.add(comicBookVO);
        comicBookVOList.add(comicBookVO2);
        comicBookVOList.add(comicBookVO3);

        given(comicBookDAO.selectAllComicBookList()).willReturn(comicBookVOList);

        // when
        comicBookService.selectAllComicBookInfoList();

        // then & verify
        then(comicBookDAO).should().selectAllComicBookList();

        verify(comicBookDAO, times(1)).selectAllComicBookList();
    }

    @Test
    public void test5_SelectComicBookInfo() {

        // given

        // when

        // then & verify

    }

    // todo: comicBook을 등록하는 로직 작성을 해야하는데... exception 처리에 대해서 공부 후에 테스트케이스 추가 작성 예정
    @Test
    public void test6_RegisterComicBookInfo() {

        // given

        // when

        // then & verify
    }

    // todo: exception 처리에 대해서 공부 후에 테스트케이스 추가 작성 예정
    @Test
    public void test7_ModifyComicBookInfo() {

        // given

        // when

        // then & verify
    }

    // todo: exception 처리에 대해서 공부 후에 테스트케이스 추가 작성 예정
    @Test
    public void test8_RemoveComicBookInfo() {

        // given

        // when

        // then & verify
    }
}
