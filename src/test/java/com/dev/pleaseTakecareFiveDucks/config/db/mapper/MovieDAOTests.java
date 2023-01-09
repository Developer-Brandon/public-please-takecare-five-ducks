package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MainMovieVO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieVO;
import com.dev.pleaseTakecareFiveDucks.movie.util.MovieUseYnEnum;
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
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MovieDAOTests {

    private static final Logger logger = LoggerFactory.getLogger(MovieDAOTests.class);

    @Autowired
    private MovieDAO movieDAO;

    private Integer natureNo = 0;

    private InsertMovieInfoRequestDTO insertMovieInfoRequestDTO;

    private InsertMovieThumbnailInfoRequestDTO insertMovieThumbnailInfoRequestDTO;

    @Before
    public void init() {

        // 공통적으로 쓰일 natureNo 입니다. 1은 한국의 natureNo 입니다.
        natureNo = 1;

        // 공통적으로 쓰일 insertBookInfoRequestDTO 입니다.
        insertMovieInfoRequestDTO = InsertMovieInfoRequestDTO.builder()
                .madeNatureNo(natureNo)
                .title("대부")
                .directorName("돈꼴레오네")
                .link("www.naver.com")
                .totalNumberOfEpisode(3)
                .movieRegDt("1980-11-20")
                .build();

        insertMovieThumbnailInfoRequestDTO = InsertMovieThumbnailInfoRequestDTO.builder()
                .fileName("movie")
                .filePath("no_name.png")
                .build();
    }

    @After
    public void destroy() {
        logger.debug("테스트 케이스 완료");
    }

    @Ignore
    @Test
    public void test1_GetMovieTotalCnt() {

        // 1. 조회
        // given

        // when
        int movieTotalCnt = movieDAO.getMovieTotalCnt();

        // then
        assertThat(movieTotalCnt, is(0));

        ////////////////////////////

        // 2. 삽입
        // given

        // when
        int insertedCnt = movieDAO.insertMovieInfo(insertMovieInfoRequestDTO);
        insertMovieThumbnailInfoRequestDTO.setMovieNo(insertMovieInfoRequestDTO.getInsertedMovieNo());
        int insertedCnt2 = movieDAO.insertMovieThumbnailInfo(insertMovieThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertMovieInfoRequestDTO.getInsertedMovieNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////

        // 3. 재조회
        // given

        // when
        int movieTotalCnt2 = movieDAO.getMovieTotalCnt();

        // then
        assertThat(movieTotalCnt2, greaterThanOrEqualTo(1));
    }

    @Test
    public void test2_DeleteAll() {

        // 1. 삽입

        // given

        // when
        int insertedCnt = movieDAO.insertMovieInfo(insertMovieInfoRequestDTO);
        insertMovieThumbnailInfoRequestDTO.setMovieNo(insertMovieInfoRequestDTO.getInsertedMovieNo());
        int insertedCnt2 = movieDAO.insertMovieThumbnailInfo(insertMovieThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertMovieInfoRequestDTO.getInsertedMovieNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////

        // 2. 조회(확인)

        // given
        SelectMovieInfoRequestDTO selectMovieInfoRequestDTO = SelectMovieInfoRequestDTO.builder()
                .movieNo(insertMovieInfoRequestDTO.getInsertedMovieNo())
                .build();

        // when
        MovieVO MovieVO = movieDAO.selectMovieInfo(selectMovieInfoRequestDTO);

        // then
        assertThat(MovieVO.getMovieNo(), greaterThanOrEqualTo(1));

        ////////////////////////////

        // 3. 전체 삭제

        // given

        // when
        int deleteAllMovieCnt = movieDAO.deleteAll();

        // then
        assertThat(deleteAllMovieCnt, greaterThanOrEqualTo(1));

        /////////////////////////////////////////

        // 4. 전체 리스트 조회

        // given

        // when
        List<MovieVO> movieVOList = movieDAO.selectAllMovieList();

        // then
        assertThat(movieVOList.size(), is(0));
    }

    @Test
    public void test3_SelectAllMovieList() {

        // 1.삽입

        // given

        // when
        int insertedCnt = movieDAO.insertMovieInfo(insertMovieInfoRequestDTO);
        insertMovieThumbnailInfoRequestDTO.setMovieNo(insertMovieInfoRequestDTO.getInsertedMovieNo());
        int insertedCnt2 = movieDAO.insertMovieThumbnailInfo(insertMovieThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertMovieInfoRequestDTO.getInsertedMovieNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        /////////////////////////////////////////

        // 2.전체 조회해서,0번째 element를 조회합니다.

        // given

        // when
        List<MovieVO> movieVOList = movieDAO.selectAllMovieList();

        // then
        assertThat(movieVOList.size(), is(1));
        assertThat(movieVOList.get(0).getMovieTitle(), is("대부"));
        assertThat(movieVOList.get(0).getDirectorName(), is("돈꼴레오네"));

    }

    // Pagination test입니다.
    @Test
    public void test4_SelectMovieList() {

        // 1. 삽입

        // given

        // when

        // then

        /////////////////////////////////////////

        // 2. Pagination으로 조회해서, 해당 객체의 element를 검증합니다.
        // TODO: dao단에서의 pagination은 간단하기 때문에..service단에서 pagination에 대한 기능 테스트를 마치고 추후 테스트 케이스 작성 예정입니다.
    }

    @Test
    public void test5_SelectMovieInfo() {

        // 1. 삽입

        // given
        int insertedCnt = movieDAO.insertMovieInfo(insertMovieInfoRequestDTO);
        insertMovieThumbnailInfoRequestDTO.setMovieNo(insertMovieInfoRequestDTO.getInsertedMovieNo());
        int insertedCnt2 = movieDAO.insertMovieThumbnailInfo(insertMovieThumbnailInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertMovieInfoRequestDTO.getInsertedMovieNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ///////////////////////////////////////////////////////

        // 2.단일 조회를 해서, 해당 객체의 element를 검증합니다.

        // given
        SelectMovieInfoRequestDTO selectMovieInfoRequestDTO = SelectMovieInfoRequestDTO.builder()
                .movieNo(insertMovieInfoRequestDTO.getInsertedMovieNo())
                .build();

        // when
        MovieVO MovieVO = movieDAO.selectMovieInfo(selectMovieInfoRequestDTO);

        // then
        assertThat(MovieVO.getMovieTitle(), CoreMatchers.is("대부"));
        assertThat(MovieVO.getDirectorName(), StringContains.containsString("돈꼴레오네"));
    }

    @Test
    public void test6_InsertMovieInfo() {

        // 바로 위에서 실행한 test5_SelectMovieInfo 메소드의 테스트와 일맥상통하므로 생략하겠습니다.

    }

    @Test
    public void test7_UpdateMovieInfo() {
        // 1. 삽입

        // given
        int insertedCnt = movieDAO.insertMovieInfo(insertMovieInfoRequestDTO);
        insertMovieThumbnailInfoRequestDTO.setMovieNo(insertMovieInfoRequestDTO.getInsertedMovieNo());
        int insertedCnt2 = movieDAO.insertMovieThumbnailInfo(insertMovieThumbnailInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertMovieInfoRequestDTO.getInsertedMovieNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectMovieInfoRequestDTO selectMovieInfoRequestDTO = SelectMovieInfoRequestDTO.builder()
                .movieNo(insertMovieInfoRequestDTO.getInsertedMovieNo())
                .build();

        MovieVO MovieVO = movieDAO.selectMovieInfo(selectMovieInfoRequestDTO);

        // when & then
        assertThat(MovieVO.getMovieNo(), greaterThanOrEqualTo(insertMovieInfoRequestDTO.getInsertedMovieNo()));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateMovieInfoRequestDTO updateMovieInfoRequestDTO = UpdateMovieInfoRequestDTO.builder()
                .madeNatureNo(natureNo)
                .movieNo(insertMovieInfoRequestDTO.getInsertedMovieNo())
                .title("대부2")
                .directorName("돈꼴레오네")
                .link("www.naver.com")
                .totalNumberOfEpisode(3)
                .movieRegDt("1996-12-20")
                .build();

        // when & then
        int updatedCnt = movieDAO.updateMovieInfo(updateMovieInfoRequestDTO);

        // 업데이트는 반드시 한번만 되어야 합니다.
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        SelectMovieInfoRequestDTO selectMovieInfoRequestDTO2 = SelectMovieInfoRequestDTO.builder()
                .movieNo(updateMovieInfoRequestDTO.getMovieNo())
                .build();

        // when
        // 다시 조회했을때, 3번에서 업데이트한 정보들이 조회가 되어야 합니다.
        MovieVO movieVO2 = movieDAO.selectMovieInfo(selectMovieInfoRequestDTO2);

        // then
        assertThat(movieVO2.getMovieNo(), is(updateMovieInfoRequestDTO.getMovieNo()));
        assertThat(movieVO2.getMovieTitle(), is("대부2"));
        assertThat(movieVO2.getDirectorName(), is("돈꼴레오네"));
    }

    @Test
    public void test8_UpdateMovieState() {

        // 1. 삽입

        // given
        int insertedCnt = movieDAO.insertMovieInfo(insertMovieInfoRequestDTO);
        insertMovieThumbnailInfoRequestDTO.setMovieNo(insertMovieInfoRequestDTO.getInsertedMovieNo());
        int insertedCnt2 = movieDAO.insertMovieThumbnailInfo(insertMovieThumbnailInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertMovieInfoRequestDTO.getInsertedMovieNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectMovieInfoRequestDTO selectMovieInfoRequestDTO = SelectMovieInfoRequestDTO.builder()
                .movieNo(insertMovieInfoRequestDTO.getInsertedMovieNo())
                .build();

        MovieVO movieVO = movieDAO.selectMovieInfo(selectMovieInfoRequestDTO);

        // when & then
        assertThat(movieVO.getMovieNo(), greaterThanOrEqualTo(insertMovieInfoRequestDTO.getInsertedMovieNo()));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateMovieStateRequestDTO updateMovieStateRequestDTO = UpdateMovieStateRequestDTO.builder()
                .movieNo(movieVO.getMovieNo())
                .movieUseYnEnum(MovieUseYnEnum.N)
                .build();

        // when
        int updatedCnt = movieDAO.updateMovieState(updateMovieStateRequestDTO);

        // then
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        // 상단에서 조회한 dto를 그대로 가져와서 조회합니다.

        // when & then
        MovieVO animVO2 = movieDAO.selectMovieInfo(selectMovieInfoRequestDTO);

        assertThat(animVO2, is(nullValue()));
    }

    @Test
    public void test9_DeleteMovieInfo() {

        // 1. 삽입

        // given
        // 최상단에서 만들어진 dto를 그대로 가져와서 조회합니다.

        // when
        int insertedCnt = movieDAO.insertMovieInfo(insertMovieInfoRequestDTO);
        insertMovieThumbnailInfoRequestDTO.setMovieNo(insertMovieInfoRequestDTO.getInsertedMovieNo());
        int insertedCnt2 = movieDAO.insertMovieThumbnailInfo(insertMovieThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertMovieInfoRequestDTO.getInsertedMovieNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////////

        // 2. 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectMovieInfoRequestDTO selectMovieInfoRequestDTO = SelectMovieInfoRequestDTO.builder()
                .movieNo(insertMovieInfoRequestDTO.getInsertedMovieNo())
                .build();

        // when
        MovieVO movieVO = movieDAO.selectMovieInfo(selectMovieInfoRequestDTO);

        // then
        assertThat(movieVO.getMovieNo(), is(selectMovieInfoRequestDTO.getMovieNo()));

        ////////////////////////////////

        // 3. 삭제

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.

        // when
        int deletedCnt = movieDAO.deleteMovieInfo(movieVO.getMovieNo());

        // then
        assertThat(deletedCnt, is(1));

        ////////////////////////////////

        // 4. 다시 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectMovieInfoRequestDTO selectMovieInfoRequestDTO2 = SelectMovieInfoRequestDTO.builder()
                .movieNo(movieVO.getMovieNo())
                .build();

        // when
        MovieVO movieVO2 = movieDAO.selectMovieInfo(selectMovieInfoRequestDTO2);

        // then
        assertThat(movieVO2, is(nullValue()));
    }

    @Test
    public void tes10_selectMainMovieList() {

        // 1. 삽입

        // given
        // 최상단에서 만들어진 dto를 그대로 가져와서 조회합니다.

        // when
        int insertedCnt = movieDAO.insertMovieInfo(insertMovieInfoRequestDTO);
        insertMovieThumbnailInfoRequestDTO.setMovieNo(insertMovieInfoRequestDTO.getInsertedMovieNo());
        int insertedCnt2 = movieDAO.insertMovieThumbnailInfo(insertMovieThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertMovieInfoRequestDTO.getInsertedMovieNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////////

        List<MainMovieVO> mainMovieVOList = movieDAO.selectMainMovieList();

        assertThat(mainMovieVOList.size(), is(1));
    }
}
