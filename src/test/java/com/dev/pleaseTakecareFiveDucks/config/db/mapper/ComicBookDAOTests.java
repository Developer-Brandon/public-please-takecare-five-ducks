package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.MainComicBookDetailVO;
import com.dev.pleaseTakecareFiveDucks.comic.util.ComicBookSerialStateEnum;
import com.dev.pleaseTakecareFiveDucks.comic.util.ComicBookUseYnEnum;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.StringContains;
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

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ComicBookDAOTests {

    private static final Logger logger = LoggerFactory.getLogger(ComicBookDAOTests.class);

    @Autowired
    private ComicBookDAO comicBookDAO;

    private Integer natureNo = 0;

    private InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO;

    private InsertComicBookThumbnailInfoRequestDTO insertComicBookThumbnailInfoRequestDTO;

    @Before
    public void init() {

        // 공통적으로 쓰일 natureNo 입니다. 1은 한국의 natureNo 입니다.
        natureNo = 1;

        // 공통적으로 쓰일 insertBookInfoRequestDTO 입니다.
        insertComicBookInfoRequestDTO = InsertComicBookInfoRequestDTO.builder()
                .madeNatureNo(natureNo)
                .title("귀멸의칼날")
                .author("코요게고로하루")
                .link("www.naver.com")
                .comicBookSerialStateEnum(ComicBookSerialStateEnum.finished)
                .comicBookRegDt("2016-02-15")
                .build();

        insertComicBookThumbnailInfoRequestDTO = InsertComicBookThumbnailInfoRequestDTO.builder()
                .webThumbnailUrl("default")
                .build();
    }

    @After
    public void destroy() {
        logger.debug("테스트 케이스 완료");
    }

    @Ignore
    @Test
    public void test1_GetComicBookTotalCnt() {

        // 1. 조회
        // given

        // when
        int comicBookTotalCnt = comicBookDAO.getComicBookTotalCnt();

        // then
        assertThat(comicBookTotalCnt, is(0));

        ////////////////////////////

        // 2. 삽입
        // given

        // when
        int insertedCnt = comicBookDAO.insertComicBookInfo(insertComicBookInfoRequestDTO);
        insertComicBookThumbnailInfoRequestDTO.setBookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo());
        int insertedCnt2 = comicBookDAO.insertComicBookThumbnailInfo(insertComicBookThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertComicBookInfoRequestDTO.getInsertedComicBookNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////

        // 3. 재조회
        // given & when
        int comicBookTotalCnt2 = comicBookDAO.getComicBookTotalCnt();

        // then
        assertThat(comicBookTotalCnt2, greaterThanOrEqualTo(1));
    }

    @Test
    public void test2_DeleteAll() {

        // 1. 삽입

        // given

        // when
        int insertedCnt = comicBookDAO.insertComicBookInfo(insertComicBookInfoRequestDTO);
        insertComicBookThumbnailInfoRequestDTO.setBookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo());
        int insertedCnt2 = comicBookDAO.insertComicBookThumbnailInfo(insertComicBookThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertComicBookInfoRequestDTO.getInsertedComicBookNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////

        // 2. 조회(확인)

        // given
        SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO = SelectComicBookInfoRequestDTO.builder()
                .bookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo())
                .build();

        // when
        ComicBookVO ComicBookVO = comicBookDAO.selectComicBookInfo(selectComicBookInfoRequestDTO);

        // then
        assertThat(ComicBookVO.getBookNo(), greaterThanOrEqualTo(1));

        ////////////////////////////

        // 3. 전체 삭제

        // given

        // when
        int deleteAllComicBookCnt = comicBookDAO.deleteAll();

        // then
        assertThat(deleteAllComicBookCnt, greaterThanOrEqualTo(1));

        /////////////////////////////////////////

        // 4. 전체 리스트 조회

        // given

        // when
        List<ComicBookVO> comicBookVOList = comicBookDAO.selectAllComicBookList();

        // then
        assertThat(comicBookVOList.size(), is(0));
    }

    @Test
    public void test3_SelectAllComicBookList() {

        // 1.삽입

        // given

        // when
        int insertedCnt = comicBookDAO.insertComicBookInfo(insertComicBookInfoRequestDTO);
        insertComicBookThumbnailInfoRequestDTO.setBookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo());
        int insertedCnt2 = comicBookDAO.insertComicBookThumbnailInfo(insertComicBookThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertComicBookInfoRequestDTO.getInsertedComicBookNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        /////////////////////////////////////////

        // 2.전체 조회해서,0번째 element를 조회합니다.

        // given

        // when
        List<ComicBookVO> comicBookVOList = comicBookDAO.selectAllComicBookList();

        // then
        assertThat(comicBookVOList.size(), is(greaterThan(1)));
        assertThat(comicBookVOList.get(0).getComicBookTitle(), is("귀멸의칼날"));
        assertThat(comicBookVOList.get(0).getComicBookAuthor(), is("코요게고로하루"));
    }

    // Pagination test입니다.
    @Test
    public void test4_SelectComicBookList() {

        // 1. 삽입

        // given

        // when

        // then

        /////////////////////////////////////////

        // 2. Pagination으로 조회해서, 해당 객체의 element를 검증합니다.
        // TODO: dao단에서의 pagination은 간단하기 때문에..service단에서 pagination에 대한 기능 테스트를 마치고 추후 테스트 케이스 작성 예정입니다.
    }

    @Test
    public void test5_SelectComicBookInfo() {

        // 1. 삽입

        // given
        int insertedCnt = comicBookDAO.insertComicBookInfo(insertComicBookInfoRequestDTO);
        insertComicBookThumbnailInfoRequestDTO.setBookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo());
        int insertedCnt2 = comicBookDAO.insertComicBookThumbnailInfo(insertComicBookThumbnailInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertComicBookInfoRequestDTO.getInsertedComicBookNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ///////////////////////////////////////////////////////

        // 2.단일 조회를 해서, 해당 객체의 element를 검증합니다.

        // given
        SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO = SelectComicBookInfoRequestDTO.builder()
                .bookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo())
                .build();

        // when
        ComicBookVO ComicBookVO = comicBookDAO.selectComicBookInfo(selectComicBookInfoRequestDTO);

        // then
        assertThat(ComicBookVO.getComicBookTitle(), CoreMatchers.is("귀멸의칼날"));
        assertThat(ComicBookVO.getComicBookAuthor(), StringContains.containsString("코요게고로하루"));
    }

    @Test
    public void test6_InsertComicBookInfo() {

        // 바로 위에서 실행한 test5_SelectComicBookInfo 메소드의 테스트와 일맥상통하므로 생략하겠습니다.

    }

    @Test
    public void test7_UpdateComicBookInfo() {
        // 1. 삽입

        // given
        int insertedCnt = comicBookDAO.insertComicBookInfo(insertComicBookInfoRequestDTO);
        insertComicBookThumbnailInfoRequestDTO.setBookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo());
        int insertedCnt2 = comicBookDAO.insertComicBookThumbnailInfo(insertComicBookThumbnailInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertComicBookInfoRequestDTO.getInsertedComicBookNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO = SelectComicBookInfoRequestDTO.builder()
                .bookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo())
                .build();

        ComicBookVO ComicBookVO = comicBookDAO.selectComicBookInfo(selectComicBookInfoRequestDTO);

        // when & then
        assertThat(ComicBookVO.getBookNo(), greaterThanOrEqualTo(insertComicBookInfoRequestDTO.getInsertedComicBookNo()));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO = UpdateComicBookInfoRequestDTO.builder()
                .madeNatureNo(natureNo)
                .bookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo())
                .title("드래곤볼GT")
                .author("나카츠루 카츠요시")
                .link("www.daum.net")
                .comicBookSerialStateEnum(ComicBookSerialStateEnum.finished)
                .comicBookRegDt("1996-12-20")
                .build();

        // when & then
        int updatedCnt = comicBookDAO.updateComicBookInfo(updateComicBookInfoRequestDTO);

        // 업데이트는 반드시 한번만 되어야 합니다.
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO2 = SelectComicBookInfoRequestDTO.builder()
                .bookNo(updateComicBookInfoRequestDTO.getBookNo())
                .build();

        // when
        // 다시 조회했을때, 3번에서 업데이트한 정보들이 조회가 되어야 합니다.
        ComicBookVO comicBookVO2 = comicBookDAO.selectComicBookInfo(selectComicBookInfoRequestDTO2);

        // then
        assertThat(comicBookVO2.getBookNo(), is(updateComicBookInfoRequestDTO.getBookNo()));
        assertThat(comicBookVO2.getComicBookTitle(), is("드래곤볼GT"));
        assertThat(comicBookVO2.getComicBookAuthor(), is("나카츠루 카츠요시"));
    }

    @Test
    public void test8_UpdateComicBookState() {

        // 1. 삽입

        // given
        int insertedCnt = comicBookDAO.insertComicBookInfo(insertComicBookInfoRequestDTO);
        insertComicBookThumbnailInfoRequestDTO.setBookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo());
        int insertedCnt2 = comicBookDAO.insertComicBookThumbnailInfo(insertComicBookThumbnailInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertComicBookInfoRequestDTO.getInsertedComicBookNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO = SelectComicBookInfoRequestDTO.builder()
                .bookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo())
                .build();

        ComicBookVO comicBookVO = comicBookDAO.selectComicBookInfo(selectComicBookInfoRequestDTO);

        // when & then
        assertThat(comicBookVO.getBookNo(), greaterThanOrEqualTo(insertComicBookInfoRequestDTO.getInsertedComicBookNo()));
        // 추가로, 책의 상태도 체크합니다.
        assertThat(comicBookVO.getComicBookUseYnEnum(), CoreMatchers.is(ComicBookUseYnEnum.Y));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateComicBookStateRequestDTO updateComicBookStateRequestDTO = UpdateComicBookStateRequestDTO.builder()
                .bookNo(comicBookVO.getBookNo())
                .comicBookUseYnEnum(ComicBookUseYnEnum.N)
                .build();

        // when
        int updatedCnt = comicBookDAO.updateComicBookState(updateComicBookStateRequestDTO);

        // then
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        // 상단에서 조회한 dto를 그대로 가져와서 조회합니다.

        // when & then
        ComicBookVO animVO2 = comicBookDAO.selectComicBookInfo(selectComicBookInfoRequestDTO);

        assertThat(animVO2, is(nullValue()));
    }

    @Test
    public void test9_DeleteComicBookInfo() {

        // 1. 삽입

        // given
        // 최상단에서 만들어진 dto를 그대로 가져와서 조회합니다.

        // when
        int insertedCnt = comicBookDAO.insertComicBookInfo(insertComicBookInfoRequestDTO);
        insertComicBookThumbnailInfoRequestDTO.setBookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo());
        int insertedCnt2 = comicBookDAO.insertComicBookThumbnailInfo(insertComicBookThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertComicBookInfoRequestDTO.getInsertedComicBookNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////////

        // 2. 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO = SelectComicBookInfoRequestDTO.builder()
                .bookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo())
                .build();

        // when
        ComicBookVO comicBookVO = comicBookDAO.selectComicBookInfo(selectComicBookInfoRequestDTO);

        // then
        assertThat(comicBookVO.getBookNo(), is(selectComicBookInfoRequestDTO.getBookNo()));

        ////////////////////////////////

        // 3. 삭제

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.

        // when
        int deletedCnt = comicBookDAO.deleteComicBookInfo(comicBookVO.getBookNo());

        // then
        assertThat(deletedCnt, is(1));

        ////////////////////////////////

        // 4. 다시 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO2 = SelectComicBookInfoRequestDTO.builder()
                .bookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo())
                .build();

        // when
        ComicBookVO comicBookVO2 = comicBookDAO.selectComicBookInfo(selectComicBookInfoRequestDTO2);

        // then
        assertThat(comicBookVO2, is(nullValue()));
    }

    @Test
    public void test10_selectMainComicBookList() {

        // 1. 삽입

        // given
        // 최상단에서 만들어진 dto를 그대로 가져와서 조회합니다.

        // when
        int insertedCnt = comicBookDAO.insertComicBookInfo(insertComicBookInfoRequestDTO);
        insertComicBookThumbnailInfoRequestDTO.setBookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo());
        int insertedCnt2 = comicBookDAO.insertComicBookThumbnailInfo(insertComicBookThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertComicBookInfoRequestDTO.getInsertedComicBookNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////////

        // 2. 조회

        // given & when
        MainComicBookRequestDTO mainComicBookRequestDTO = MainComicBookRequestDTO.builder()
                .startDt("2010")
                .endDt("2020")
                .build();

        List<MainComicBookDetailVO> mainComicBookVOList = comicBookDAO.selectMainComicBookList(mainComicBookRequestDTO);

        // then
        assertThat(mainComicBookVOList.size(), is(1));
        assertThat(mainComicBookVOList.get(0).getComicBookTitle(), is("귀멸의칼날"));
        assertThat(mainComicBookVOList.get(0).getComicBookAuthor(), is("코요게고로하루"));
    }
}
