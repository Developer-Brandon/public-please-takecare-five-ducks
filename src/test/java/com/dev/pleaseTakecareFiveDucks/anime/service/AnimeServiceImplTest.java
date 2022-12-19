package com.dev.pleaseTakecareFiveDucks.anime.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.SelectAnimeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.util.AnimeUseYnEnum;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.AnimeDAO;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AnimeServiceImplTest extends TestCase {

    // https://hothoony.tistory.com/1301
    // https://myborn.tistory.com/16

    // Junit4 + Mockito
    // https://ellune.tistory.com/8

    // BDD 하는법
    // https://effortguy.tistory.com/145

    // TODO: 하기의 Mock관련 Annotation들에 대해 정리하기

    @InjectMocks
    AnimeServiceImpl animeService;

    @Mock
    AnimeDAO animeDAO;

    @Test
    public void test1_SelectAnimeTotalCnt() {

        // given
        Integer wholeAnimeCnt = 10;
        given(animeDAO.getAnimeTotalCnt()).willReturn(wholeAnimeCnt);

        // when
        animeService.selectAnimeTotalCnt();

        // then & verify
        then(animeDAO).should().getAnimeTotalCnt();
        verify(animeDAO, times(1)).getAnimeTotalCnt();
    }


    @Test
    public void test2_1_RemoveAllAnimeInfoList() throws Exception {

        // 1. animeCnt가 0개 초과로 존재할 경우
        // 2. 제대로 삭제로직이 실행 되었을 경우

        // given
        Integer wholeAnimeTotalCnt = 1;
        given(animeDAO.getAnimeTotalCnt()).willReturn(wholeAnimeTotalCnt);

        Integer wholeRemovedAnimeCnt = 1;
        given(animeDAO.deleteAll()).willReturn(wholeRemovedAnimeCnt);

        // when
        animeService.removeAllAnimeInfoList();

        // then & verify
        then(animeDAO).should().getAnimeTotalCnt();
        then(animeDAO).should().deleteAll();
        //
        verify(animeDAO, times(1)).getAnimeTotalCnt();
        verify(animeDAO, times(1)).deleteAll();
    }

    @Ignore
    @Test
    public void test2_2_RemoveAllAnimeInfoList() throws Exception {

        //        // 1. animeCnt가 0개 초과로 존재할 경우
        //        // 2. 제대로 삭제로직이 실행 되지 않았을 경우
        //
        //        // given
        //        Integer wholeAnimeTotalCnt = 1;
        //        given(animeDAO.getAnimeTotalCnt()).willReturn(wholeAnimeTotalCnt);
        //
        //        Integer wholeRemovedAnimeCnt = 0;
        //        given(animeDAO.deleteAll()).willReturn(wholeRemovedAnimeCnt);
        //
        //        // todo: doThrow를 사용할 것인지, thenThrow를 사용할 것인지 다시 살펴보기
        //        // 그리고 dao로 인해서 일어나는 exception이 아니기 때문에 이것을 어떻게 검증할 것인지에 대해서 고민하기
        //
        //        // doThrow(Exception.class).when(animeService).removeAllAnimeInfoList();
        //        // when(animeService.removeAllAnimeInfoList()).thenThrow(new Exception());
        //
        //        // when
        //        animeService.removeAllAnimeInfoList();
        //
        //        // then & verify & assertThat
        //        then(animeDAO).should().getAnimeTotalCnt();
        //        then(animeDAO).should().deleteAll();
        //        //
        //        verify(animeDAO, times(1)).getAnimeTotalCnt();
        //        verify(animeDAO, times(1)).deleteAll();
    }

    @Test
    public void test2_3_RemoveAllAnimeInfoList() throws Exception {

        // 1. animeCnt가 1개 미만으로 존재할 경우

        // given
        Integer wholeAnimeTotalCnt = 0;
        given(animeDAO.getAnimeTotalCnt()).willReturn(wholeAnimeTotalCnt);

        // when
        animeService.removeAllAnimeInfoList();

        // then & verify
        then(animeDAO).should().getAnimeTotalCnt();
        //
        verify(animeDAO, times(1)).getAnimeTotalCnt();
        verify(animeDAO, times(0)).deleteAll();
    }

    // todo: 추후 jsp에서 어떻게 받을지 결정한 후에 개발하는 것으로 할게요.
    @Test
    public void test3_SelectAnimeList() {

        // given

        // when

        // then & verify
    }

    @Test
    public void test4_SelectAllAnimeInfoList() {

        // given
        AnimeVO animeVO = mock(AnimeVO.class);
        animeVO.setAnimeNo(1);
        animeVO.setAnimeAuthor("테스트_작가");
        animeVO.setAnimeRegDt("2022-12-30");
        animeVO.setAnimeTitle("테스트_제목");
        animeVO.setAnimeUseYnEnum(AnimeUseYnEnum.Y);
        animeVO.setMadeNatureNo(1);
        animeVO.setPagePerAnimeCnt(0);

        AnimeVO animeVO2 = mock(AnimeVO.class);
        animeVO2.setAnimeNo(2);
        animeVO2.setAnimeAuthor("테스트_작가2");
        animeVO2.setAnimeRegDt("2022-12-30");
        animeVO2.setAnimeTitle("테스트_제목2");
        animeVO2.setAnimeUseYnEnum(AnimeUseYnEnum.Y);
        animeVO2.setMadeNatureNo(1);
        animeVO2.setPagePerAnimeCnt(0);

        AnimeVO animeVO3 = mock(AnimeVO.class);
        animeVO3.setAnimeNo(3);
        animeVO3.setAnimeAuthor("테스트_작가3");
        animeVO3.setAnimeRegDt("2022-12-30");
        animeVO3.setAnimeTitle("테스트_제목3");
        animeVO3.setAnimeUseYnEnum(AnimeUseYnEnum.Y);
        animeVO3.setMadeNatureNo(1);
        animeVO3.setPagePerAnimeCnt(0);

        List<AnimeVO> animeVOList = mock(ArrayList.class);
        animeVOList.add(animeVO);
        animeVOList.add(animeVO2);
        animeVOList.add(animeVO3);

        given(animeDAO.selectAllAnimeList()).willReturn(animeVOList);

        // when
        animeService.selectAllAnimeInfoList();

        // then & verify
        then(animeDAO).should().selectAllAnimeList();
        //
        verify(animeDAO, times(1)).selectAllAnimeList();
    }

    @Test
    public void test5_SelectAnimeInfo() {

        // given
        SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO = SelectAnimeInfoRequestDTO.builder()
                .build();

        AnimeVO animeVO = AnimeVO.builder()
                .animeNo(1)
                .animeTitle("테스트_제목")
                .animeAuthor("테스트_작가")
                .animeUseYnEnum(AnimeUseYnEnum.Y)
                .animeRegDt("2022-12-15")
                .pagePerAnimeCnt(1)
                .build();

        given(animeDAO.selectAnimeInfo(selectAnimeInfoRequestDTO)).willReturn(animeVO);

        // when
        animeDAO.selectAnimeInfo(selectAnimeInfoRequestDTO);

        // then & verify
        then(animeDAO).should().selectAnimeInfo(selectAnimeInfoRequestDTO);
        //
        verify(animeDAO, times(1)).selectAnimeInfo(selectAnimeInfoRequestDTO);
    }

    // todo: animation을 등록하는 로직 작성을 해야하는데... exception 처리에 대해서 공부 후에 테스트케이스 추가 작성 예정
    @Test
    public void test6_RegisterAnimeInfo() {

        // given

        // when

        // then & verify
    }

    // todo: exception 처리에 대해서 공부 후에 테스트케이스 추가 작성 예정
    public void testModifyAnimeInfo() {

        // given

        // when

        // then & verify
    }

    // todo: exception 처리에 대해서 공부 후에 테스트케이스 추가 작성 예정
    public void testRemoveAnimeInfo() {

        // given

        // when

        // then & verify
    }
}
