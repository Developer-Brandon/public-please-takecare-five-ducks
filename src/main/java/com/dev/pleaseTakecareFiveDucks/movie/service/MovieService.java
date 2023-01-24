package com.dev.pleaseTakecareFiveDucks.movie.service;


import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.SelectDramaThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.SelectMovieThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieVO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.result.MovieListResultVO;

import java.util.List;

public interface MovieService {

    Integer selectMovieTotalCnt();

    void removeAllMovieInfoList() throws Exception;

    MovieListResultVO selectMoviePaginationList(SelectMoviePaginationRequestDTO selectMoviePaginationRequestDTO) throws Exception;

    List<MovieVO> selectAllMovieInfoList();

    MovieVO selectMovieInfo(SelectMovieInfoRequestDTO selectMovieInfoRequestDTO);

    void registerMovieInfo(InsertMovieInfoRequestDTO insertMovieInfoRequestDTO) throws Exception;

    void modifyMovieInfo(UpdateMovieInfoRequestDTO updateMovieInfoRequestDTO) throws Exception;

    void modifyMovieState(UpdateMovieStateRequestDTO updateMovieStateRequestDTO) throws Exception;

    void removeMovieInfo(Integer movieNo) throws Exception;

    List<RawImageThumbnailVO> selectImageThumbnailVOList(SelectMovieThumbnailImageUrlDTO selectMovieThumbnailImageUrlDTO) throws Exception;

    void registerDramaViewCnt(InsertMovieViewCntRequestDTO insertMovieViewCntRequestDTO) throws Exception;
}
