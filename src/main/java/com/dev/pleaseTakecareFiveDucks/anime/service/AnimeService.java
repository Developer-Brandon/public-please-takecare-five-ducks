package com.dev.pleaseTakecareFiveDucks.anime.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;

import java.util.List;

public interface AnimeService {

    Integer selectAnimeTotalCnt();

    void removeAllAnimeInfoList() throws Exception;

    List<AnimeVO> selectAnimeList(SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO);

    List<AnimeVO> selectAllAnimeInfoList();

    AnimeVO selectAnimeInfo(SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO);

    void registerAnimeInfo(InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO);

    void modifyAnimeInfo(UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO);

    void removeAnimeInfo(RemoveAnimeInfoRequestDTO removeAnimeRequestDTO);
}
