package com.dev.pleaseTakecareFiveDucks.anime.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.SelectAnimeThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;

import java.util.List;

public interface AnimeService {

    List<FinalizedYnEnum> selectAnimeFinalizedList() throws Exception;

    Integer selectAnimeTotalCnt() throws Exception;

    void removeAllAnimeInfoList() throws Exception;

    List<AnimeVO> selectAnimeList(SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO);

    List<AnimeVO> selectAllAnimeInfoList();

    AnimeVO selectAnimeInfo(SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO);

    void registerAnimeInfo(InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO) throws Exception;

    void modifyAnimeInfo(UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO) throws Exception;

    void removeAnimeInfo(RemoveAnimeInfoRequestDTO removeAnimeRequestDTO) throws Exception;

    List<RawImageThumbnailVO> selectImageThumbnailVOList(SelectAnimeThumbnailImageUrlDTO selectAnimeThumbnailImageUrlDTO) throws Exception;
}
