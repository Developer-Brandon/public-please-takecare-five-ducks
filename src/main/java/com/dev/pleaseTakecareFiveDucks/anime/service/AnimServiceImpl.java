package com.dev.pleaseTakecareFiveDucks.anime.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;

import java.util.List;

public class AnimServiceImpl implements AnimeService{

    @Override
    public Integer selectAnimeTotalCnt() {
        return null;
    }

    @Override
    public void removeAllAnimeInfoList() {

    }

    @Override
    public List<AnimeVO> selectAnimeList(SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO) {
        return null;
    }

    @Override
    public List<AnimeVO> selectAllAnimeInfoList() {
        return null;
    }

    @Override
    public AnimeVO selectAnimeInfo(SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO) {
        return null;
    }

    @Override
    public void registerAnimeInfo(InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO) {

    }

    @Override
    public void modifyAnimeInfo(UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO) {

    }

    @Override
    public void removeAnimeInfo(RemoveAnimeInfoRequestDTO removeAnimeRequestDTO) {

    }
}
