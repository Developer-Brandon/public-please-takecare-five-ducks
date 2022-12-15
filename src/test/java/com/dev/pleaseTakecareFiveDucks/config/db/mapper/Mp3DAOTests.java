package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request.InsertMp3InfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request.SelectMp3InfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request.UpdateMp3InfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request.UpdateMp3StateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.vo.Mp3VO;
import com.dev.pleaseTakecareFiveDucks.mp3.util.Mp3UseYnEnum;
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
public class Mp3DAOTests {

    private static final Logger logger = LoggerFactory.getLogger(Mp3DAOTests.class);

    @Autowired
    private Mp3DAO mp3DAO;

    private Integer natureNo = 0;

    private InsertMp3InfoRequestDTO insertMp3InfoRequestDTO;

    @Before
    public void init() {

        // 공통적으로 쓰일 natureNo 입니다. 1은 한국의 natureNo 입니다.
        natureNo = 1;

        // 공통적으로 쓰일 insertBookInfoRequestDTO 입니다.
        insertMp3InfoRequestDTO = InsertMp3InfoRequestDTO.builder()
                .title("대부")
                .singer("돈꼴레오네")
                .songRegDt("1980-11-20")
                .build();
    }

    @After
    public void destroy() {
        logger.debug("테스트 케이스 완료");
    }

    @Ignore
    @Test
    public void test1_GetMp3TotalCnt() {

        // 1. 조회
        // given

        // when
        int movieTotalCnt = mp3DAO.getMp3TotalCnt();

        // then
        assertThat(movieTotalCnt, is(0));

        ////////////////////////////

        // 2. 삽입
        // given

        // when
        int insertedCnt = mp3DAO.insertMp3Info(insertMp3InfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertMp3InfoRequestDTO.getInsertedMp3No(), greaterThanOrEqualTo(1));

        ////////////////////////////

        // 3. 재조회
        // given

        // when
        int movieTotalCnt2 = mp3DAO.getMp3TotalCnt();

        // then
        assertThat(movieTotalCnt2, greaterThanOrEqualTo(1));
    }

    @Test
    public void test2_DeleteAll() {

        // 1. 삽입

        // given

        // when
        int insertedCnt = mp3DAO.insertMp3Info(insertMp3InfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertMp3InfoRequestDTO.getInsertedMp3No(), greaterThanOrEqualTo(1));

        ////////////////////////////

        // 2. 조회(확인)

        // given
        SelectMp3InfoRequestDTO selectMp3InfoRequestDTO = SelectMp3InfoRequestDTO.builder()
                .mp3No(insertMp3InfoRequestDTO.getInsertedMp3No())
                .build();

        // when
        Mp3VO Mp3VO = mp3DAO.selectMp3Info(selectMp3InfoRequestDTO);

        // then
        assertThat(Mp3VO.getMp3No(), greaterThanOrEqualTo(1));

        ////////////////////////////

        // 3. 전체 삭제

        // given

        // when
        int deleteAllMp3Cnt = mp3DAO.deleteAll();

        // then
        assertThat(deleteAllMp3Cnt, greaterThanOrEqualTo(1));

        /////////////////////////////////////////

        // 4. 전체 리스트 조회

        // given

        // when
        List<Mp3VO> movieVOList = mp3DAO.selectAllMp3List();

        // then
        assertThat(movieVOList.size(), is(0));
    }

    @Test
    public void test3_SelectAllMp3List() {

        // 1.삽입

        // given

        // when
        int insertedCnt = mp3DAO.insertMp3Info(insertMp3InfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertMp3InfoRequestDTO.getInsertedMp3No(), greaterThanOrEqualTo(1));

        /////////////////////////////////////////

        // 2.전체 조회해서,0번째 element를 조회합니다.

        // given

        // when
        List<Mp3VO> movieVOList = mp3DAO.selectAllMp3List();

        // then
        assertThat(movieVOList.size(), is(1));
        assertThat(movieVOList.get(0).getMp3Title(), is("대부"));
        assertThat(movieVOList.get(0).getSinger(), is("돈꼴레오네"));

    }

    // Pagination test입니다.
    @Test
    public void test4_SelectMp3List() {

        // 1. 삽입

        // given

        // when

        // then

        /////////////////////////////////////////

        // 2. Pagination으로 조회해서, 해당 객체의 element를 검증합니다.
        // TODO: dao단에서의 pagination은 간단하기 때문에..service단에서 pagination에 대한 기능 테스트를 마치고 추후 테스트 케이스 작성 예정입니다.
    }

    @Test
    public void test5_SelectMp3Info() {

        // 1. 삽입

        // given
        int insertedCnt = mp3DAO.insertMp3Info(insertMp3InfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertMp3InfoRequestDTO.getInsertedMp3No(), greaterThanOrEqualTo(1));

        ///////////////////////////////////////////////////////

        // 2.단일 조회를 해서, 해당 객체의 element를 검증합니다.

        // given
        SelectMp3InfoRequestDTO selectMp3InfoRequestDTO = SelectMp3InfoRequestDTO.builder()
                .mp3No(insertMp3InfoRequestDTO.getInsertedMp3No())
                .build();

        // when
        Mp3VO Mp3VO = mp3DAO.selectMp3Info(selectMp3InfoRequestDTO);

        // then
        assertThat(Mp3VO.getMp3Title(), CoreMatchers.is("대부"));
        assertThat(Mp3VO.getSinger(), containsString("돈꼴레오네"));
    }

    @Test
    public void test6_InsertMp3Info() {

        // 바로 위에서 실행한 test5_SelectMp3Info 메소드의 테스트와 일맥상통하므로 생략하겠습니다.

    }

    @Test
    public void test7_UpdateMp3Info() {
        // 1. 삽입

        // given
        int insertedCnt = mp3DAO.insertMp3Info(insertMp3InfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertMp3InfoRequestDTO.getInsertedMp3No(), greaterThanOrEqualTo(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectMp3InfoRequestDTO selectMp3InfoRequestDTO = SelectMp3InfoRequestDTO.builder()
                .mp3No(insertMp3InfoRequestDTO.getInsertedMp3No())
                .build();

        Mp3VO Mp3VO = mp3DAO.selectMp3Info(selectMp3InfoRequestDTO);

        // when & then
        assertThat(Mp3VO.getMp3No(), greaterThanOrEqualTo(insertMp3InfoRequestDTO.getInsertedMp3No()));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateMp3InfoRequestDTO updateMp3InfoRequestDTO = UpdateMp3InfoRequestDTO.builder()
                .mp3No(insertMp3InfoRequestDTO.getInsertedMp3No())
                .title("대부2")
                .singer("돈꼴레오네")
                .songRegDt("1996-12-20")
                .build();

        // when & then
        int updatedCnt = mp3DAO.updateMp3Info(updateMp3InfoRequestDTO);

        // 업데이트는 반드시 한번만 되어야 합니다.
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        SelectMp3InfoRequestDTO selectMp3InfoRequestDTO2 = SelectMp3InfoRequestDTO.builder()
                .mp3No(updateMp3InfoRequestDTO.getMp3No())
                .build();

        // when
        // 다시 조회했을때, 3번에서 업데이트한 정보들이 조회가 되어야 합니다.
        Mp3VO movieVO2 = mp3DAO.selectMp3Info(selectMp3InfoRequestDTO2);

        // then
        assertThat(movieVO2.getMp3No(), is(updateMp3InfoRequestDTO.getMp3No()));
        assertThat(movieVO2.getMp3Title(), is("대부2"));
        assertThat(movieVO2.getSinger(), is("돈꼴레오네"));
    }

    @Test
    public void test8_UpdateMp3State() {

        // 1. 삽입

        // given
        int insertedCnt = mp3DAO.insertMp3Info(insertMp3InfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertMp3InfoRequestDTO.getInsertedMp3No(), greaterThanOrEqualTo(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectMp3InfoRequestDTO selectMp3InfoRequestDTO = SelectMp3InfoRequestDTO.builder()
                .mp3No(insertMp3InfoRequestDTO.getInsertedMp3No())
                .build();

        Mp3VO movieVO = mp3DAO.selectMp3Info(selectMp3InfoRequestDTO);

        // when & then
        assertThat(movieVO.getMp3No(), greaterThanOrEqualTo(insertMp3InfoRequestDTO.getInsertedMp3No()));
        // 추가로, 책의 상태도 체크합니다.
        assertThat(movieVO.getMp3UseYnEnum(), CoreMatchers.is(Mp3UseYnEnum.Y));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateMp3StateRequestDTO updateMp3StateRequestDTO = UpdateMp3StateRequestDTO.builder()
                .mp3No(movieVO.getMp3No())
                .mp3UseYnEnum(Mp3UseYnEnum.N)
                .build();

        // when
        int updatedCnt = mp3DAO.updateMp3State(updateMp3StateRequestDTO);

        // then
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        // 상단에서 조회한 dto를 그대로 가져와서 조회합니다.

        // when & then
        Mp3VO animVO2 = mp3DAO.selectMp3Info(selectMp3InfoRequestDTO);

        assertThat(animVO2.getMp3UseYnEnum(), is(Mp3UseYnEnum.N));
    }

    @Test
    public void test9_DeleteMp3Info() {

        // 1. 삽입

        // given
        // 최상단에서 만들어진 dto를 그대로 가져와서 조회합니다.

        // when
        int insertedCnt = mp3DAO.insertMp3Info(insertMp3InfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertMp3InfoRequestDTO.getInsertedMp3No(), greaterThanOrEqualTo(1));

        ////////////////////////////////

        // 2. 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectMp3InfoRequestDTO selectMp3InfoRequestDTO = SelectMp3InfoRequestDTO.builder()
                .mp3No(insertMp3InfoRequestDTO.getInsertedMp3No())
                .build();

        // when
        Mp3VO movieVO = mp3DAO.selectMp3Info(selectMp3InfoRequestDTO);

        // then
        assertThat(movieVO.getMp3No(), is(selectMp3InfoRequestDTO.getMp3No()));

        ////////////////////////////////

        // 3. 삭제

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.

        // when
        int deletedCnt = mp3DAO.deleteMp3Info(movieVO.getMp3No());

        // then
        assertThat(deletedCnt, is(1));

        ////////////////////////////////

        // 4. 다시 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectMp3InfoRequestDTO selectMp3InfoRequestDTO2 = SelectMp3InfoRequestDTO.builder()
                .mp3No(insertMp3InfoRequestDTO.getInsertedMp3No())
                .build();

        // when
        Mp3VO movieVO2 = mp3DAO.selectMp3Info(selectMp3InfoRequestDTO2);

        // then
        assertThat(movieVO2, is(nullValue()));
    }
}
