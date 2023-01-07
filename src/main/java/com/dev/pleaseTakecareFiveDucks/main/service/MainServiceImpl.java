package com.dev.pleaseTakecareFiveDucks.main.service;

import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.MainComicBookRequestDTO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.MainComicBookDetailVO;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.*;
import com.dev.pleaseTakecareFiveDucks.main.domain.vo.MainPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public MainPageVO selectMainPageData() {

        MainComicBookRequestDTO mainComicBookRequestDTO = MainComicBookRequestDTO.builder()
                .startDt("1990")
                .startDt("1999")
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

        return MainPageVO.builder()
                .mainBannerVOList(mainDAO.selectMainBannerList())
                .mainAnimeVOList(animeDAO.selectMainAnimationList())
                .mainBookVOList(bookDAO.selectMainBookList())
                .mainComicBookVOMap(mainComicBookMap)
                .mainDramaVOList(dramaDAO.selectMainDramaList())
                .mainMovieVOList(movieDAO.selectMainMovieList())
                .build();
    }
}
