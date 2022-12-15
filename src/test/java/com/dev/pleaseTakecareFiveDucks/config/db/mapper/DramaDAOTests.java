package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.SelectDramaInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.UpdateDramaInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.UpdateDramaStateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaVO;
import com.dev.pleaseTakecareFiveDucks.drama.util.DramaUseYnEnum;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.InsertDramaInfoRequestDTO;
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
public class DramaDAOTests {

    private static final Logger logger = LoggerFactory.getLogger(DramaDAOTests.class);

    @Autowired
    private DramaDAO dramaDAO;

    private Integer natureNo = 0;

    private InsertDramaInfoRequestDTO insertDramaInfoRequestDTO;

    @Before
    public void init() {

        // 공통적으로 쓰일 natureNo 입니다. 1은 한국의 natureNo 입니다.
        natureNo = 1;

        // 공통적으로 쓰일 insertBookInfoRequestDTO 입니다.
        insertDramaInfoRequestDTO = InsertDramaInfoRequestDTO.builder()
                .madeNatureNo(natureNo)
                .title("올인")
                .author("최완규")
                .dramaRegDt("2003-01-15")
                .pagePerDramaCnt(0)
                .build();
    }

    @After
    public void destroy() {
        logger.debug("테스트 케이스 완료");
    }

    @Ignore
    @Test
    public void test1_GetDramaTotalCnt() {

        // 1. 조회
        // given

        // when
        int dramaTotalCnt = dramaDAO.getDramaTotalCnt();

        // then
        assertThat(dramaTotalCnt, is(0));

        ////////////////////////////

        // 2. 삽입
        // given

        // when
        int insertedCnt = dramaDAO.insertDramaInfo(insertDramaInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertDramaInfoRequestDTO.getInsertedDramaNo(), greaterThanOrEqualTo(1));

        ////////////////////////////

        // 3. 재조회
        // given

        // when
        int dramaTotalCnt2 = dramaDAO.getDramaTotalCnt();

        // then
        assertThat(dramaTotalCnt2, greaterThanOrEqualTo(1));
    }

    @Test
    public void test2_DeleteAll() {

        // 1. 삽입

        // given

        // when
        int insertedCnt = dramaDAO.insertDramaInfo(insertDramaInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertDramaInfoRequestDTO.getInsertedDramaNo(), greaterThanOrEqualTo(1));

        ////////////////////////////

        // 2. 조회(확인)

        // given
        SelectDramaInfoRequestDTO selectDramaInfoRequestDTO = SelectDramaInfoRequestDTO.builder()
                .dramaNo(insertDramaInfoRequestDTO.getInsertedDramaNo())
                .build();

        // when
        DramaVO DramaVO = dramaDAO.selectDramaInfo(selectDramaInfoRequestDTO);

        // then
        assertThat(DramaVO.getDramaNo(), greaterThanOrEqualTo(1));

        ////////////////////////////

        // 3. 전체 삭제

        // given

        // when
        int deleteAllDramaCnt = dramaDAO.deleteAll();

        // then
        assertThat(deleteAllDramaCnt, greaterThanOrEqualTo(1));

        /////////////////////////////////////////

        // 4. 전체 리스트 조회

        // given

        // when
        List<DramaVO> dramaVOList = dramaDAO.selectAllDramaList();

        // then
        assertThat(dramaVOList.size(), is(0));
    }

    @Test
    public void test3_SelectAllDramaList() {

        // 1.삽입

        // given

        // when
        int insertedCnt = dramaDAO.insertDramaInfo(insertDramaInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertDramaInfoRequestDTO.getInsertedDramaNo(), greaterThanOrEqualTo(1));

        /////////////////////////////////////////

        // 2.전체 조회해서,0번째 element를 조회합니다.

        // given

        // when
        List<DramaVO> dramaVOList = dramaDAO.selectAllDramaList();

        // then
        assertThat(dramaVOList.size(), is(1));
        assertThat(dramaVOList.get(0).getDramaTitle(), is("올인"));
        assertThat(dramaVOList.get(0).getDramaAuthor(), is("최완규"));

    }

    // Pagination test입니다.
    @Test
    public void test4_SelectDramaList() {

        // 1. 삽입

        // given

        // when

        // then

        /////////////////////////////////////////

        // 2. Pagination으로 조회해서, 해당 객체의 element를 검증합니다.
        // TODO: dao단에서의 pagination은 간단하기 때문에..service단에서 pagination에 대한 기능 테스트를 마치고 추후 테스트 케이스 작성 예정입니다.
    }

    @Test
    public void test5_SelectDramaInfo() {

        // 1. 삽입

        // given
        int insertedCnt = dramaDAO.insertDramaInfo(insertDramaInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertDramaInfoRequestDTO.getInsertedDramaNo(), greaterThanOrEqualTo(1));

        ///////////////////////////////////////////////////////

        // 2.단일 조회를 해서, 해당 객체의 element를 검증합니다.

        // given
        SelectDramaInfoRequestDTO selectDramaInfoRequestDTO = SelectDramaInfoRequestDTO.builder()
                .dramaNo(insertDramaInfoRequestDTO.getInsertedDramaNo())
                .build();

        // when
        DramaVO DramaVO = dramaDAO.selectDramaInfo(selectDramaInfoRequestDTO);

        // then
        assertThat(DramaVO.getDramaTitle(), CoreMatchers.is("올인"));
        assertThat(DramaVO.getDramaAuthor(), StringContains.containsString("최완규"));
    }

    @Test
    public void test6_InsertDramaInfo() {

        // 바로 위에서 실행한 test5_SelectDramaInfo 메소드의 테스트와 일맥상통하므로 생략하겠습니다.

    }

    @Test
    public void test7_UpdateDramaInfo() {
        // 1. 삽입

        // given
        int insertedCnt = dramaDAO.insertDramaInfo(insertDramaInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertDramaInfoRequestDTO.getInsertedDramaNo(), greaterThanOrEqualTo(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectDramaInfoRequestDTO selectDramaInfoRequestDTO = SelectDramaInfoRequestDTO.builder()
                .dramaNo(insertDramaInfoRequestDTO.getInsertedDramaNo())
                .build();

        DramaVO DramaVO = dramaDAO.selectDramaInfo(selectDramaInfoRequestDTO);

        // when & then
        assertThat(DramaVO.getDramaNo(), greaterThanOrEqualTo(insertDramaInfoRequestDTO.getInsertedDramaNo()));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO = UpdateDramaInfoRequestDTO.builder()
                .madeNatureNo(natureNo)
                .dramaNo(insertDramaInfoRequestDTO.getInsertedDramaNo())
                .title("올인2")
                .author("?")
                .dramaRegDt("1996-12-20")
                .pagePerDramaCnt(0)
                .build();

        // when & then
        int updatedCnt = dramaDAO.updateDramaInfo(updateDramaInfoRequestDTO);

        // 업데이트는 반드시 한번만 되어야 합니다.
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        SelectDramaInfoRequestDTO selectDramaInfoRequestDTO2 = SelectDramaInfoRequestDTO.builder()
                .dramaNo(updateDramaInfoRequestDTO.getDramaNo())
                .build();

        // when
        // 다시 조회했을때, 3번에서 업데이트한 정보들이 조회가 되어야 합니다.
        DramaVO dramaVO2 = dramaDAO.selectDramaInfo(selectDramaInfoRequestDTO2);

        // then
        assertThat(dramaVO2.getDramaNo(), is(updateDramaInfoRequestDTO.getDramaNo()));
        assertThat(dramaVO2.getDramaTitle(), is("올인2"));
        assertThat(dramaVO2.getDramaAuthor(), is("?"));
    }

    @Test
    public void test8_UpdateDramaState() {

        // 1. 삽입

        // given
        int insertedCnt = dramaDAO.insertDramaInfo(insertDramaInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertDramaInfoRequestDTO.getInsertedDramaNo(), greaterThanOrEqualTo(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectDramaInfoRequestDTO selectDramaInfoRequestDTO = SelectDramaInfoRequestDTO.builder()
                .dramaNo(insertDramaInfoRequestDTO.getInsertedDramaNo())
                .build();

        DramaVO dramaVO = dramaDAO.selectDramaInfo(selectDramaInfoRequestDTO);

        // when & then
        assertThat(dramaVO.getDramaNo(), greaterThanOrEqualTo(insertDramaInfoRequestDTO.getInsertedDramaNo()));
        // 추가로, 책의 상태도 체크합니다.
        assertThat(dramaVO.getDramaUseYnEnum(), CoreMatchers.is(DramaUseYnEnum.Y));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateDramaStateRequestDTO updateDramaStateRequestDTO = UpdateDramaStateRequestDTO.builder()
                .dramaNo(dramaVO.getDramaNo())
                .dramaUseYnEnum(DramaUseYnEnum.N)
                .build();

        // when
        int updatedCnt = dramaDAO.updateDramaState(updateDramaStateRequestDTO);

        // then
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        // 상단에서 조회한 dto를 그대로 가져와서 조회합니다.

        // when & then
        DramaVO animVO2 = dramaDAO.selectDramaInfo(selectDramaInfoRequestDTO);

        assertThat(animVO2.getDramaUseYnEnum(), is(DramaUseYnEnum.N));
    }

    @Test
    public void test9_DeleteDramaInfo() {

        // 1. 삽입

        // given
        // 최상단에서 만들어진 dto를 그대로 가져와서 조회합니다.

        // when
        int insertedCnt = dramaDAO.insertDramaInfo(insertDramaInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertDramaInfoRequestDTO.getInsertedDramaNo(), greaterThanOrEqualTo(1));

        ////////////////////////////////

        // 2. 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectDramaInfoRequestDTO selectDramaInfoRequestDTO = SelectDramaInfoRequestDTO.builder()
                .dramaNo(insertDramaInfoRequestDTO.getInsertedDramaNo())
                .build();

        // when
        DramaVO dramaVO = dramaDAO.selectDramaInfo(selectDramaInfoRequestDTO);

        // then
        assertThat(dramaVO.getDramaNo(), is(selectDramaInfoRequestDTO.getDramaNo()));

        ////////////////////////////////

        // 3. 삭제

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.

        // when
        int deletedCnt = dramaDAO.deleteDramaInfo(dramaVO.getDramaNo());

        // then
        assertThat(deletedCnt, is(1));

        ////////////////////////////////

        // 4. 다시 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectDramaInfoRequestDTO selectDramaInfoRequestDTO2 = SelectDramaInfoRequestDTO.builder()
                .dramaNo(insertDramaInfoRequestDTO.getInsertedDramaNo())
                .build();

        // when
        DramaVO dramaVO2 = dramaDAO.selectDramaInfo(selectDramaInfoRequestDTO2);

        // then
        assertThat(dramaVO2, is(nullValue()));
    }
}
