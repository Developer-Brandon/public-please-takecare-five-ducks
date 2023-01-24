package com.dev.pleaseTakecareFiveDucks.anime.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.SelectAnimeThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.AnimeListResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;

import java.util.List;

public interface AnimeService {

    public List<FinalizedYnEnum> selectAnimeFinalizedList() throws Exception;

    public Integer selectAnimeTotalCnt() throws Exception;

    public void removeAllAnimeInfoList() throws Exception;

    public AnimeListResultVO selectAnimePaginationList(SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO);

    public List<AnimeVO> selectAllAnimeInfoList() throws Exception;

    public AnimeVO selectAnimeInfo(SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO);

    public void registerAnimeInfo(InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO) throws Exception;

    public void modifyAnimeInfo(UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO) throws Exception;

    public void removeAnimeInfo(DeleteAnimeInfoRequestDTO removeAnimeRequestDTO) throws Exception;

    public List<RawImageThumbnailVO> selectImageThumbnailVOList(SelectAnimeThumbnailImageUrlDTO selectAnimeThumbnailImageUrlDTO) throws Exception;

    public void registerAnimeViewCnt(InsertAnimeViewCntRequestDTO insertAnimeViewCntRequestDTO) throws Exception;
}
