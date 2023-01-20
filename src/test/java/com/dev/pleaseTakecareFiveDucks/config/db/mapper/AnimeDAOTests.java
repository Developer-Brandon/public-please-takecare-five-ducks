package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.MainAnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.util.AnimeUseYnEnum;
import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
import org.hamcrest.CoreMatchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AnimeDAOTests {

    private static final Logger logger = LoggerFactory.getLogger(AnimeDAOTests.class);

    @Autowired
    private AnimeDAO animeDAO;

    private Integer natureNo = 0;

    private InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO;

    private InsertAnimeThumbnailInfoRequestDTO insertAnimeThumbnailInfoRequestDTO;

    @Before
    public void init() {

        // 공통적으로 쓰일 natureNo 입니다. 1은 한국의 natureNo 입니다.
        natureNo = 1;

        // 공통적으로 쓰일 insertBookInfoRequestDTO 입니다.
        insertAnimeInfoRequestDTO = InsertAnimeInfoRequestDTO.builder()
                .madeNatureNo(natureNo)
                .title("귀멸의칼날")
                .author("코요게고로하루")
                .link("http://www.naver.com")
                .finalizedYnEnum(FinalizedYnEnum.y)
                .animeBroadcastCnt(64)
                .animeRegDt("2016-02-15")
                .build();

        insertAnimeThumbnailInfoRequestDTO = InsertAnimeThumbnailInfoRequestDTO.builder()
                .filePath("anime")
                .fileName("no_name.png")
                .build();
    }

    @After
    public void destroy() {
        logger.debug("테스트 케이스 완료");
    }

    // 상품 조회 -> 삽입 -> 재조회로 검증
    @Ignore
    @Test
    public void test1_GetAnimeTotalCnt() {

        // 1. 조회
        // given

        // when
        int animeTotalCnt = animeDAO.getAnimeTotalCnt();

        // then
        assertThat(animeTotalCnt, is(0));

        ////////////////////////////

        // 2. 삽입
        // given
        int insertedCnt = animeDAO.insertAnimeInfo(insertAnimeInfoRequestDTO);
        insertAnimeThumbnailInfoRequestDTO.setAnimeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo());
        int insertedCnt2 = animeDAO.insertAnimeThumbnailInfo(insertAnimeThumbnailInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, is(1));
        assertThat(insertAnimeInfoRequestDTO.getInsertedAnimeNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////

        // 3. 재조회
        // given

        // when
        int animeTotalCnt2 = animeDAO.getAnimeTotalCnt();

        // then
        assertThat(animeTotalCnt2, greaterThanOrEqualTo(1));
    }

    // 애니 삽입 -> 조회 -> 전체삭제 -> 재조회로 검증
    @Test
    public void test2_DeleteAll() {

        // 1. 삽입

        // given

        // when
        // anime info , anime thumbnail 삽입
        int insertedCnt = animeDAO.insertAnimeInfo(insertAnimeInfoRequestDTO);
        insertAnimeThumbnailInfoRequestDTO.setAnimeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo());
        int insertedCnt2 = animeDAO.insertAnimeThumbnailInfo(insertAnimeThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertAnimeInfoRequestDTO.getInsertedAnimeNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////

        // 2. 조회(확인)

        // given
        SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO = SelectAnimeInfoRequestDTO.builder()
                .animeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo())
                .build();

        // when & then
        AnimeVO animeVO = animeDAO.selectAnimeInfo(selectAnimeInfoRequestDTO);
        assertThat(animeVO.getAnimeNo(), greaterThanOrEqualTo(1));
        assertThat(animeVO.getFileFullPath(), is(notNullValue()));

        ////////////////////////////

        // 3. 전체 삭제
        // (1개 이상일시에만 삭제되어야 합니다.)

        // given

        int deleteAllAnimeCnt = animeDAO.deleteAll();

        // when & then
        assertThat(deleteAllAnimeCnt, greaterThanOrEqualTo(1));

        /////////////////////////////////////////

        // 4. 전체 리스트 조회

        // given
        List<AnimeVO> animeVOList = animeDAO.selectAllAnimeList();

        // when & then
        assertThat(animeVOList.size(), is(0));
    }

    // 애니 삽입 -> 리스트 조회(0번째 item을 조회하는 것으로..)로 검증
    @Test
    public void test3_SelectAllAnimeList() {

        // 1.삽입

        // given

        // when
        int insertedCnt = animeDAO.insertAnimeInfo(insertAnimeInfoRequestDTO);
        insertAnimeThumbnailInfoRequestDTO.setAnimeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo());
        int insertedCnt2 = animeDAO.insertAnimeThumbnailInfo(insertAnimeThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, is(1));
        assertThat(insertAnimeInfoRequestDTO.getInsertedAnimeNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        /////////////////////////////////////////

        // 2.전체 조회해서,0번째 element를 조회합니다.

        // given

        // when
        List<AnimeVO> animeVOList = animeDAO.selectAllAnimeList();

        // then
        assertThat(animeVOList.size(), is(1));
        assertThat(animeVOList.get(0).getAnimeTitle(), is("귀멸의칼날"));
        assertThat(animeVOList.get(0).getAnimeAuthor(), is("코요게고로하루"));

    }

    // Pagination test입니다.
    @Test
    public void test4_SelectAnimeList() {

        // 1. 삽입

        // given

        // when

        // then

        /////////////////////////////////////////

        // 2. Pagination으로 조회해서, 해당 객체의 element를 검증합니다.
        // TODO: dao단에서의 pagination은 간단하기 때문에..service단에서 pagination에 대한 기능 테스트를 마치고 추후 테스트 케이스 작성 예정입니다.
    }

    // 애니 삽입 -> 조회로 검증
    @Test
    public void test5_SelectAnimeInfo() {

        // 1. 삽입

        // given
        int insertedCnt = animeDAO.insertAnimeInfo(insertAnimeInfoRequestDTO);
        insertAnimeThumbnailInfoRequestDTO.setAnimeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo());
        int insertedCnt2 = animeDAO.insertAnimeThumbnailInfo(insertAnimeThumbnailInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertAnimeInfoRequestDTO.getInsertedAnimeNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ///////////////////////////////////////////////////////

        // 2.단일 조회를 해서, 해당 객체의 element를 검증합니다.

        // given
        SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO = SelectAnimeInfoRequestDTO.builder()
                .animeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo())
                .build();

        // when
        AnimeVO animeVO = animeDAO.selectAnimeInfo(selectAnimeInfoRequestDTO);

        // then
        assertThat(animeVO.getAnimeTitle(), CoreMatchers.is("귀멸의칼날"));
        assertThat(animeVO.getAnimeAuthor(), containsString("코요게고로하루"));
        assertThat(animeVO.getFileFullPath(), CoreMatchers.is(notNullValue()));
    }

    // 생략
    @Test
    public void test6_InsertAnimeInfo() {

        // 바로 위에서 실행한 test5_SelectAnimeInfo 메소드의 테스트와 일맥상통하므로 생략하겠습니다.
    }

    // 애니 삽입 -> 조회 -> 정보 업데이트 -> 재조회로 검증
    @Test
    public void test7_UpdateAnimeInfo() {
        // 1. 삽입

        // given
        int insertedCnt = animeDAO.insertAnimeInfo(insertAnimeInfoRequestDTO);
        insertAnimeThumbnailInfoRequestDTO.setAnimeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo());
        int insertedCnt2 = animeDAO.insertAnimeThumbnailInfo(insertAnimeThumbnailInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertAnimeInfoRequestDTO.getInsertedAnimeNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO = SelectAnimeInfoRequestDTO.builder()
                .animeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo())
                .build();

        AnimeVO animeVO = animeDAO.selectAnimeInfo(selectAnimeInfoRequestDTO);

        // when & then
        assertThat(animeVO.getAnimeNo(), greaterThanOrEqualTo(insertAnimeInfoRequestDTO.getInsertedAnimeNo()));

        //////////////////////////////////

        // 3. 업데이트

        // given
        UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO = UpdateAnimeInfoRequestDTO.builder()
                .madeNatureNo(natureNo)
                .animeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo())
                .animeTitle("드래곤볼GT")
                .animeAuthor("나카츠루 카츠요시")
                .link("https://www.daum.net")
                .finalizedYnEnum(FinalizedYnEnum.y)
                .animeBroadcastCnt(64)
                .animeRegDt("1996-12-20")
                .build();

        // when & then
        int updatedCnt = animeDAO.updateAnimeInfo(updateAnimeInfoRequestDTO);

        // 업데이트는 반드시 한번만 되어야 합니다.
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO2 = SelectAnimeInfoRequestDTO.builder()
                .animeNo(updateAnimeInfoRequestDTO.getAnimeNo())
                .build();

        // when
        // 다시 조회했을때, 3번에서 업데이트한 정보들이 조회가 되어야 합니다.
        AnimeVO animeVO2 = animeDAO.selectAnimeInfo(selectAnimeInfoRequestDTO2);

        // then
        assertThat(animeVO2.getAnimeNo(), is(updateAnimeInfoRequestDTO.getAnimeNo()));
        assertThat(animeVO2.getAnimeTitle(), is("드래곤볼GT"));
        assertThat(animeVO2.getAnimeAuthor(), is("나카츠루 카츠요시"));
    }

    // 애니 삽입 -> 조회 -> 상태 업데이트 -> 재조회로 검증
    @Test
    public void test8_UpdateAnimeState() {

        // 1. 삽입

        // given
        int insertedCnt = animeDAO.insertAnimeInfo(insertAnimeInfoRequestDTO);
        insertAnimeThumbnailInfoRequestDTO.setAnimeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo());
        int insertedCnt2 = animeDAO.insertAnimeThumbnailInfo(insertAnimeThumbnailInfoRequestDTO);

        // when & then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertAnimeInfoRequestDTO.getInsertedAnimeNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        //////////////////////////////////

        // 2. 조회

        // given
        SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO = SelectAnimeInfoRequestDTO.builder()
                .animeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo())
                .build();

        AnimeVO animeVO = animeDAO.selectAnimeInfo(selectAnimeInfoRequestDTO);

        // when & then
        assertThat(animeVO.getAnimeNo(), greaterThanOrEqualTo(insertAnimeInfoRequestDTO.getInsertedAnimeNo()));
        // 추가로, 책의 상태도 체크합니다.
        assertThat(animeVO.getAnimeUseYnEnum(), CoreMatchers.is(AnimeUseYnEnum.Y));

        //////////////////////////////////

        // 3. 상태 업데이트

        // given
        UpdateAnimeStateRequestDTO updateAnimeStateRequestDTO = UpdateAnimeStateRequestDTO.builder()
                .animeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo())
                .animeUseYnEnum(AnimeUseYnEnum.N)
                .build();

        // when
        int updatedCnt = animeDAO.updateAnimeState(updateAnimeStateRequestDTO);

        // then
        assertThat(updatedCnt, CoreMatchers.is(1));

        //////////////////////////////////

        // 4. 조회

        // given
        // 상단에서 조회한 dto를 그대로 가져와서 조회합니다.

        // when & then
        AnimeVO animVO2 = animeDAO.selectAnimeInfo(selectAnimeInfoRequestDTO);

        assertThat(animVO2, CoreMatchers.is(nullValue()));
    }

    // 애니 삽입 -> 조회 -> 삭제 -> 재조회로 검증
    @Test
    public void test9_DeleteAnimeInfo() {

        // 1. 삽입

        // given
        // 최상단에서 만들어진 dto를 그대로 가져와서 조회합니다.

        // when
        int insertedCnt = animeDAO.insertAnimeInfo(insertAnimeInfoRequestDTO);
        insertAnimeThumbnailInfoRequestDTO.setAnimeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo());
        int insertedCnt2 = animeDAO.insertAnimeThumbnailInfo(insertAnimeThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertAnimeInfoRequestDTO.getInsertedAnimeNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////////

        // 2. 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO = SelectAnimeInfoRequestDTO.builder()
                .animeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo())
                .build();

        // when
        AnimeVO animeVO = animeDAO.selectAnimeInfo(selectAnimeInfoRequestDTO);

        // then
        assertThat(animeVO.getAnimeNo(), is(selectAnimeInfoRequestDTO.getAnimeNo()));

        ////////////////////////////////

        // 3. 삭제

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.

        // when
        int deletedCnt = animeDAO.deleteAnimeInfo(animeVO.getAnimeNo());

        // then
        assertThat(deletedCnt, is(1));

        ////////////////////////////////

        // 4. 다시 조회

        // given
        // 상단에서 삽입한 dto를 그대로 가져와서 조회합니다.
        SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO2 = SelectAnimeInfoRequestDTO.builder()
                .animeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo())
                .build();

        // when
        AnimeVO animeVO2 = animeDAO.selectAnimeInfo(selectAnimeInfoRequestDTO2);

        // then
        assertThat(animeVO2, is(nullValue()));
    }

    // 애니 삽입 -> 조회로 검증
    @Test
    public void test10_selectMainAnimationList() {

        // 1. 삽입

        // given
        // 최상단에서 만들어진 dto를 그대로 가져와서 조회합니다.

        // when
        int insertedCnt = animeDAO.insertAnimeInfo(insertAnimeInfoRequestDTO);
        insertAnimeThumbnailInfoRequestDTO.setAnimeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo());
        int insertedCnt2 = animeDAO.insertAnimeThumbnailInfo(insertAnimeThumbnailInfoRequestDTO);

        // then
        assertThat(insertedCnt, CoreMatchers.is(1));
        assertThat(insertAnimeInfoRequestDTO.getInsertedAnimeNo(), greaterThanOrEqualTo(1));
        assertThat(insertedCnt2, is(1));

        ////////////////////////////////

        // 2. 조회
        // given & when
        List<MainAnimeVO> mainAnimeVOList = animeDAO.selectMainAnimationList();

        // then
        assertThat(mainAnimeVOList.size(), is(1));
        assertThat(mainAnimeVOList.get(0).getAnimeTitle(), is("귀멸의칼날"));
    }

    @Test
    public void test11_selectAnimeThumbnail() {

        String animeName = "드래곤볼Z";

        JSONObject json = null;

        String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

        String SEARCH_API_URL = "https://www.googleapis.com/customsearch/v1";

        String API_KEY = "AIzaSyAw2NnlSIS-dj6-Rh-3FsvLwiUKP35b6Tc";

        String SEARCH_ENGINE_ID = "d1bf72817817d47b1";

        List<String> animeImageUrlList = new ArrayList<>();

        Connection.Response res = null;

        try {

            res = Jsoup.connect(SEARCH_API_URL + "?key=" + API_KEY + "&cx=" + SEARCH_ENGINE_ID + "&q=" + animeName)
                    .ignoreContentType(true)
                    .userAgent(USER_AGENT)
                    .execute();
        } catch (IOException e) {

            e.printStackTrace();
        }

        json = new JSONObject(res.body());

        JSONArray jsonArray = json.getJSONArray("items");

        if (jsonArray.length() > 0) {

            jsonArray.forEach(e -> {

                try {

                    JSONArray cseThumbnail = ((JSONObject) e).getJSONObject("pagemap").getJSONArray("cse_thumbnail");

                    System.out.println("cseThumbnail.length()" + cseThumbnail.length());

                    for(Integer i = 0; i < cseThumbnail.length(); i++) {

                        String innerImageUrl = cseThumbnail.getJSONObject(i).getString("src");

                        animeImageUrlList.add(innerImageUrl);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    animeImageUrlList.add("");
                }
            });
        }

        List<String> filteredAnimeImageUrlList = animeImageUrlList
                .stream()
                .filter(e -> !e.isEmpty())
                .collect(Collectors.toList());

        filteredAnimeImageUrlList.forEach(e -> System.out.println("e: " + e));
    }
}
