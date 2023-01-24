package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MainMovieVO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieVO;

import java.util.List;

public interface MovieDAO {

    int getMovieTotalCnt();

    int getTotalCntByCondition(SelectMoviePaginationRequestDTO selectMoviePaginationRequestDTO);

    int deleteAll();

    List<MovieVO> selectAllMovieList();

    List<MainMovieVO> selectMainMovieList();

    List<MovieVO> selectMoviePaginationList(SelectMoviePaginationRequestDTO selectMoviePaginationRequestDTO);

    int selectMovieThumbnailImageCntByMovieNo(Integer movieNo);

    List<MovieThumbnailVO> selectMovieThumbnailImageListByMovieNo(SelectMovieThumbnailImageListRequestDTO selectMovieThumbnailImageListRequestDTO);

    MovieVO selectMovieInfo(SelectMovieInfoRequestDTO selectMovieInfoRequestDTO);

    int insertMovieViewCnt(InsertMovieViewCntRequestDTO insertMovieViewCntRequestDTO);

    int insertMovieInfo(InsertMovieInfoRequestDTO insertMovieInfoRequestDTO);

    int insertMovieThumbnailInfo(InsertMovieThumbnailInfoRequestDTO insertMovieThumbnailInfoRequestDTO);

    int updateMovieInfo(UpdateMovieInfoRequestDTO updateMovieInfoRequestDTO);

    int updateMovieThumbnailInfo(UpdateMovieThumbnailInfoRequestDTO updateMovieThumbnailInfoRequestDTO);

    int updateMovieState(UpdateMovieStateRequestDTO updateMovieStateRequestDTO);

    int deleteMovieInfo(Integer movieNo);

    int deleteMovieThumbnailInfo(Integer movieNo);
}
