package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AnimeMapperTests {

    private static final Logger logger = LoggerFactory.getLogger(AnimeMapperTests.class);

    @Autowired
    private AnimeMapper animeMapper;

    @Test
    public void getBlogMapperList() {

        // 1. 조회 전 삽입 테스트

//        InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO = InsertAnimeInfoRequestDTO.builder()
//                .madeNatureNo(1)
//                .title("타이의 대모험")
//                .author("이나다 코지")
//                .pagePerAnimeCnt(0)
//                .animeRegDt("2022-09-07 12:48:52")
//                .build();
//
//                Boolean insertedOrNot = animeMapper.insertAnimeInfo(insertAnimeInfoRequestDTO) == 1;
//        //
//                assertThat(insertedOrNot, is(true));
//                assertThat(insertAnimeInfoRequestDTO.getInsertedAnimeNo(), is(greaterThan(1)));
//        //
        //        ////////////////////////////////////////////////////////////////////////
        //
        //        // 2. 조회 테스트
        //
        //        List<BlogPostingVO> blogPostingVOList = blogMapper.selectAllBlogPostingList();
        //
        //        assertThat(blogPostingVOList, is(notNullValue()));
        //        assertThat(blogPostingVOList.size(), is(greaterThan(0)));
    }

    @Test
    public void insertBlog() {

        // 위에서 검증했으므로 생략하겠습니다.
    }

    @Test
    public void insertBlogList() {
        //
        //        // 1. 리스트 (대량) 삽입 테스트
        //        RawBlogPostingVO rawBlogPostingVO = RawBlogPostingVO.builder()
        //                .title("제목_테스트")
        //                .imgSrc("이미지경로_테스트")
        //                .postingRegDt("2022-09-07 12:48:52")
        //                .build();
        //
        //        RawBlogPostingVO rawBlogPostingVO2 = RawBlogPostingVO.builder()
        //                .title("제목_테스트2")
        //                .imgSrc("이미지경로_테스트2")
        //                .postingRegDt("2022-09-07 12:48:52")
        //                .build();
        //
        //        RawBlogPostingVO rawBlogPostingVO3 = RawBlogPostingVO.builder()
        //                .title("제목_테스트3")
        //                .imgSrc("이미지경로_테스트3")
        //                .postingRegDt("2022-09-07 12:48:52")
        //                .build();
        //
        //        //
        //        List<RawBlogPostingVO> insertBlogPostingDTOList = new ArrayList<>();
        //        insertBlogPostingDTOList.add(rawBlogPostingVO);
        //        insertBlogPostingDTOList.add(rawBlogPostingVO2);
        //        insertBlogPostingDTOList.add(rawBlogPostingVO3);
        //
        //        InsertBlogPostingListDTO insertBlogPostingListDTO = InsertBlogPostingListDTO.builder()
        //                .insertBlogPostingDTOList(insertBlogPostingDTOList)
        //                .build();
        //
        //        Boolean insertedOrNot = blogMapper.insertBlogPostingList(insertBlogPostingListDTO) == 3;
        //
        //        assertThat(insertedOrNot, is(true));
    }

}
