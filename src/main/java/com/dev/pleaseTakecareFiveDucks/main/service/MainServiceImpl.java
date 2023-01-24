package com.dev.pleaseTakecareFiveDucks.main.service;

import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.MainComicBookRequestDTO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.MainComicBookDetailVO;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.*;
import com.dev.pleaseTakecareFiveDucks.main.domain.vo.MainEntertainVO;
import com.dev.pleaseTakecareFiveDucks.main.domain.vo.MainPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService{

    private final MainDAO mainDAO;

    private final AnimeDAO animeDAO;

    private final BookDAO bookDAO;

    private final ComicBookDAO comicBookDAO;

    private final DramaDAO dramaDAO;

    private final MovieDAO movieDAO;

    @Override
    public MainPageVO selectMainPageData() throws Exception {

        // 1. 만화책 관련된 처리를 합니다.
        MainComicBookRequestDTO mainComicBookRequestDTO = MainComicBookRequestDTO.builder()
                .startDt("1990")
                .endDt("1999")
                .build();

        List<MainComicBookDetailVO> firstMainComicBookVOList = comicBookDAO.selectMainComicBookList(mainComicBookRequestDTO);

        mainComicBookRequestDTO.setStartDt("2000");
        mainComicBookRequestDTO.setEndDt("2009");

        List<MainComicBookDetailVO> secondMainComicBookVOList = comicBookDAO.selectMainComicBookList(mainComicBookRequestDTO);

        mainComicBookRequestDTO.setStartDt("2010");
        mainComicBookRequestDTO.setEndDt("2019");

        List<MainComicBookDetailVO> thirdMainComicBookVOList = comicBookDAO.selectMainComicBookList(mainComicBookRequestDTO);

        Map<String, List<MainComicBookDetailVO>> mainComicBookMap = new HashMap<>();

        mainComicBookMap.put("1990", firstMainComicBookVOList);
        mainComicBookMap.put("2000", secondMainComicBookVOList);
        mainComicBookMap.put("2010", thirdMainComicBookVOList);

        // 2. drama와 movie 관련 처리를 해줍니다.
        List<MainEntertainVO> mainEntertainVOList = new ArrayList<>();

        dramaDAO.selectMainDramaList()
                .forEach(e -> mainEntertainVOList.add(
                    MainEntertainVO.builder()
                    .title(e.getDramaTitle())
                    .link(e.getLink())
                    .webThumbnailUrl(e.getWebThumbnailUrl())
                    .viewCnt(e.getViewCnt())
                    .build()
                )
            );


        movieDAO.selectMainMovieList()
                .forEach(e -> mainEntertainVOList.add(
                    MainEntertainVO.builder()
                    .title(e.getTitle())
                    .link(e.getLink())
                    .webThumbnailUrl(e.getWebThumbnailUrl())
                    .viewCnt(e.getViewCnt())
                    .build()
                )
            );

        mainEntertainVOList
                .stream()
                .limit(3);

        return MainPageVO.builder()
                .mainBannerVOList(mainDAO.selectMainBannerList())
                .mainAnimeVOList(animeDAO.selectMainAnimationList())
                .mainBookVOList(bookDAO.selectMainBookList())
                .mainComicBookVOMap(mainComicBookMap)
                .mainEntertainVOList(mainEntertainVOList)
                .build();
    }
}
