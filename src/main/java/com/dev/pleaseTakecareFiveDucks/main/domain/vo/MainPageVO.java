package com.dev.pleaseTakecareFiveDucks.main.domain.vo;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.MainAnimeVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.MainBookVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.MainComicBookDetailVO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class MainPageVO {

    List<MainBannerVO> mainBannerVOList;

    List<MainAnimeVO> mainAnimeVOList;

    List<MainBookVO> mainBookVOList;

    Map<String, List<MainComicBookDetailVO>> mainComicBookVOMap;

    List<MainEntertainVO> mainEntertainVOList;
}
