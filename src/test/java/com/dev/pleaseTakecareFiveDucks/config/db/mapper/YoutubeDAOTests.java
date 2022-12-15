package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.InsertYoutubeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.SelectYoutubeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.UpdateYoutubeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.UpdateYoutubeStateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.vo.YoutubeVO;
import com.dev.pleaseTakecareFiveDucks.youtube.util.YoutubeUseYnEnum;
import org.hamcrest.CoreMatchers;
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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class YoutubeDAOTests {

    private static final Logger logger = LoggerFactory.getLogger(YoutubeDAOTests.class);

    @Autowired
    private YoutubeDAO youtubeDAO;

    private Integer natureNo = 0;

    private InsertYoutubeInfoRequestDTO insertYoutubeInfoRequestDTO;

    @Before
    public void init() {

        // 공통적으로 쓰일 natureNo 입니다. 1은 한국의 natureNo 입니다.
        natureNo = 1;

        // 공통적으로 쓰일 insertBookInfoRequestDTO 입니다.
        insertYoutubeInfoRequestDTO = InsertYoutubeInfoRequestDTO.builder()
                .title("돈잘버는법")
                .youtuberName("심사임당")
                .likeCnt(10)
                .dislikeCnt(1)
                .viewCnt(100)
                .youtubeRegDt("1980-11-20")
                .build();
    }

    @After
    public void destroy() {
        logger.debug("테스트 케이스 완료");
    }

    @Ignore
    @Test
    public void test1_GetYoutubeTotalCnt() {

        // 1. 조회
        // given

        // when
        int movieTotalCnt = youtubeDAO.getYoutubeTotalCnt();

        // then
        assertThat(movieTotalCnt, is(0));

        ////////////////////////////

        // 2. 삽입
        // given

        // when
        int insertedCnt = youtubeDAO.insertYoutubeInfo(insertYoutubeInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo(), greaterThanOrEqualTo(1));

        ////////////////////////////

        // 3. 재조회
        // given

        // when
        int movieTotalCnt2 = youtubeDAO.getYoutubeTotalCnt();

        // then
        assertThat(movieTotalCnt2, greaterThanOrEqualTo(1));
    }

    @Test
    public void test2_DeleteAll() {

        // 1. 삽입

        // given

        // when
        int insertedCnt = youtubeDAO.insertYoutubeInfo(insertYoutubeInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo(), greaterThanOrEqualTo(1));

        ////////////////////////////

        // 2. 조회(확인)

        // given
        SelectYoutubeInfoRequestDTO selectYoutubeInfoRequestDTO = SelectYoutubeInfoRequestDTO.builder()
                .youtubeNo(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo())
                .build();

        // when
        YoutubeVO YoutubeVO = youtubeDAO.selectYoutubeInfo(selectYoutubeInfoRequestDTO);

        // then
        assertThat(YoutubeVO.getYoutubeNo(), greaterThanOrEqualTo(1));

        ////////////////////////////

        // 3. 전체 삭제

        // given

        // when
        int deleteAllYoutubeCnt = youtubeDAO.deleteAll();

        // then
        assertThat(deleteAllYoutubeCnt, greaterThanOrEqualTo(1));

        /////////////////////////////////////////

        // 4. 전체 리스트 조회

        // given

        // when
        List<YoutubeVO> movieVOList = youtubeDAO.selectAllYoutubeList();

        // then
        assertThat(movieVOList.size(), is(0));
    }

    @Test
    public void test3_SelectAllYoutubeList() {

        // 1.삽입

        // given

        // when
        int insertedCnt = youtubeDAO.insertYoutubeInfo(insertYoutubeInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo(), greaterThanOrEqualTo(1));

        /////////////////////////////////////////

        // 2.전체 조회해서,0번째 element를 조회합니다.

        // given

        // when
        List<YoutubeVO> movieVOList = youtubeDAO.selectAllYoutubeList();

        // then
        assertThat(movieVOList.size(), is(1));
        assertThat(movieVOList.get(0).getYoutubeTitle(), is("돈잘버는법"));
        assertThat(movieVOList.get(0).getYoutuberName(), is("심사임당"));

    }

    // Pagination test입니다.
    @Test
    public void test4_SelectYoutubeList() {

        // 1. 삽입

        // given

        // when

        // then

        /////////////////////////////////////////

        // 2. Pagination으로 조회해서, 해당 객체의 element를 검증합니다.
        // TODO: dao단에서의 pagination은 간단하기 때문에..service단에서 pagination에 대한 기능 테스트를 마치고 추후 테스트 케이스 작성 예정입니다.
    }

    @Test
    public void test5_SelectYoutubeInfo() {

        // 1. 삽입

        // given
        int insertedCnt = youtubeDAO.insertYoutubeInfo(insertYoutubeInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo(), greaterThanOrEqualTo(1));

        ///////////////////////////////////////////////////////

        // 2.단일 조회를 해서, 해당 객체의 element를 검증합니다.

        // given
        SelectYoutubeInfoRequestDTO selectYoutubeInfoRequestDTO = SelectYoutubeInfoRequestDTO.builder()
                .youtubeNo(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo())
                .build();

        // when
        YoutubeVO YoutubeVO = youtubeDAO.selectYoutubeInfo(selectYoutubeInfoRequestDTO);

        // then
        assertThat(YoutubeVO.getYoutubeTitle(), CoreMatchers.is("돈잘버는법"));
        assertThat(YoutubeVO.getYoutuberName(), containsString("심사임당"));
    }

    @Test
    public void test6_InsertYoutubeInfo() {

        // 바로 위에서 실행한 test5_SelectYoutubeInfo 메소드의 테스트와 일맥상통하므로 생략하겠습니다.

    }

    @Test
    public void test7_UpdateYoutubeInfo() {
        // 1. 삽입

        // given
        int insertedCnt = youtubeDAO.insertYoutubeInfo(insertYoutubeInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo(), greaterThanOrEqualTo(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectYoutubeInfoRequestDTO selectYoutubeInfoRequestDTO = SelectYoutubeInfoRequestDTO.builder()
                .youtubeNo(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo())
                .build();

        YoutubeVO YoutubeVO = youtubeDAO.selectYoutubeInfo(selectYoutubeInfoRequestDTO);

        // when & then
        assertThat(YoutubeVO.getYoutubeNo(), greaterThanOrEqualTo(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo()));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateYoutubeInfoRequestDTO updateYoutubeInfoRequestDTO = UpdateYoutubeInfoRequestDTO.builder()
                .youtubeNo(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo())
                .title("돈잘버는법2")
                .youtuberName("심사임당2")
                .likeCnt(10)
                .dislikeCnt(1)
                .viewCnt(100)
                .youtubeRegDt("1996-12-20")
                .build();

        // when & then
        int updatedCnt = youtubeDAO.updateYoutubeInfo(updateYoutubeInfoRequestDTO);

        // 업데이트는 반드시 한번만 되어야 합니다.
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        SelectYoutubeInfoRequestDTO selectYoutubeInfoRequestDTO2 = SelectYoutubeInfoRequestDTO.builder()
                .youtubeNo(updateYoutubeInfoRequestDTO.getYoutubeNo())
                .build();

        // when
        // 다시 조회했을때, 3번에서 업데이트한 정보들이 조회가 되어야 합니다.
        YoutubeVO movieVO2 = youtubeDAO.selectYoutubeInfo(selectYoutubeInfoRequestDTO2);

        // then
        assertThat(movieVO2.getYoutubeNo(), is(updateYoutubeInfoRequestDTO.getYoutubeNo()));
        assertThat(movieVO2.getYoutubeTitle(), is("돈잘버는법2"));
        assertThat(movieVO2.getYoutuberName(), is("심사임당2"));
    }

    @Test
    public void test8_UpdateYoutubeState() {

        // 1. 삽입

        // given
        int insertedCnt = youtubeDAO.insertYoutubeInfo(insertYoutubeInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo(), greaterThanOrEqualTo(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectYoutubeInfoRequestDTO selectYoutubeInfoRequestDTO = SelectYoutubeInfoRequestDTO.builder()
                .youtubeNo(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo())
                .build();

        YoutubeVO movieVO = youtubeDAO.selectYoutubeInfo(selectYoutubeInfoRequestDTO);

        // when & then
        assertThat(movieVO.getYoutubeNo(), greaterThanOrEqualTo(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo()));
        // 추가로, 책의 상태도 체크합니다.
        assertThat(movieVO.getYoutubeUseYnEnum(), CoreMatchers.is(YoutubeUseYnEnum.Y));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateYoutubeStateRequestDTO updateYoutubeStateRequestDTO = UpdateYoutubeStateRequestDTO.builder()
                .youtubeNo(movieVO.getYoutubeNo())
                .youtubeUseYnEnum(YoutubeUseYnEnum.N)
                .build();

        // when
        int updatedCnt = youtubeDAO.updateYoutubeState(updateYoutubeStateRequestDTO);

        // then
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        // 상단에서 조회한 dto를 그대로 가져와서 조회합니다.

        // when & then
        YoutubeVO animVO2 = youtubeDAO.selectYoutubeInfo(selectYoutubeInfoRequestDTO);

        assertThat(animVO2.getYoutubeUseYnEnum(), is(YoutubeUseYnEnum.N));
    }

    @Test
    public void test9_DeleteYoutubeInfo() {

        // 1. 삽입

        // given
        // 최상단에서 만들어진 dto를 그대로 가져와서 조회합니다.

        // when
        int insertedCnt = youtubeDAO.insertYoutubeInfo(insertYoutubeInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo(), greaterThanOrEqualTo(1));

        ////////////////////////////////

        // 2. 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectYoutubeInfoRequestDTO selectYoutubeInfoRequestDTO = SelectYoutubeInfoRequestDTO.builder()
                .youtubeNo(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo())
                .build();

        // when
        YoutubeVO movieVO = youtubeDAO.selectYoutubeInfo(selectYoutubeInfoRequestDTO);

        // then
        assertThat(movieVO.getYoutubeNo(), is(selectYoutubeInfoRequestDTO.getYoutubeNo()));

        ////////////////////////////////

        // 3. 삭제

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.

        // when
        int deletedCnt = youtubeDAO.deleteYoutubeInfo(movieVO.getYoutubeNo());

        // then
        assertThat(deletedCnt, is(1));

        ////////////////////////////////

        // 4. 다시 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectYoutubeInfoRequestDTO selectYoutubeInfoRequestDTO2 = SelectYoutubeInfoRequestDTO.builder()
                .youtubeNo(insertYoutubeInfoRequestDTO.getInsertedYoutubeNo())
                .build();

        // when
        YoutubeVO movieVO2 = youtubeDAO.selectYoutubeInfo(selectYoutubeInfoRequestDTO2);

        // then
        assertThat(movieVO2, is(nullValue()));
    }
}
