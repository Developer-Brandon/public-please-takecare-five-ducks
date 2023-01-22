package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.MainAnimeVO;

import java.util.List;

public interface AnimeDAO {

    List<MainAnimeVO> selectMainAnimationList();

    int getTotalCnt();

    int deleteAll();

    List<AnimeVO> selectAllAnimeList();

    List<AnimeVO> selectAnimePaginationList(SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO);

    AnimeVO selectAnimeInfo(SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO);

    Integer insertAnimeInfo(InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO);

    int updateAnimeInfo(UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO);

    int updateAnimeState(UpdateAnimeStateRequestDTO updateAnimeStateRequestDTO);

    int deleteAnimeInfo(Integer animeNo);

    int insertAnimeThumbnailInfo(InsertAnimeThumbnailInfoRequestDTO insertAnimeThumbnailInfoRequestDTO);

    int updateAnimeThumbnailInfo(UpdateAnimeThumbnailInfoRequestDTO updateAnimeThumbnailInfoRequestDTO);

    List<AnimeThumbnailVO> selectAnimeThumbnailImageListByAnimeNo(SelectAnimeThumbnailImageListRequestDTO selectAnimeThumbnailImageListRequestDTO);

    int deleteAnimeThumbnailInfo(Integer animeNo);

    int selectAnimeThumbnailImageByAnimeNo(Integer animeNo);
}
