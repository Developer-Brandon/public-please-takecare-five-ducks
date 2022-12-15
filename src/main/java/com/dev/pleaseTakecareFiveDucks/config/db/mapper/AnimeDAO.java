package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnimeDAO {

    int getAnimeTotalCnt();

    int deleteAll();

    List<AnimeVO> selectAllAnimeList();

    List<AnimeVO> selectAnimeList(SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO);

    AnimeVO selectAnimeInfo(SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO);

    Integer insertAnimeInfo(InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO);

    int updateAnimeInfo(UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO);

    int updateAnimeState(UpdateAnimeStateRequestDTO updateAnimeStateRequestDTO);

    int deleteAnimeInfo(Integer animeNo);
}
