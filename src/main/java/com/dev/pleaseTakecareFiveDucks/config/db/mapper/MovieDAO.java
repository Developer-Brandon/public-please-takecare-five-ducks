package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieVO;

import java.util.List;

public interface MovieDAO {

    int getMovieTotalCnt();

    int deleteAll();

    List<MovieVO> selectAllMovieList();

    List<MovieVO> selectMovieList(SelectMoviePaginationRequestDTO selectMoviePaginationRequestDTO);

    MovieVO selectMovieInfo(SelectMovieInfoRequestDTO selectMovieInfoRequestDTO);

    Integer insertMovieInfo(InsertMovieInfoRequestDTO insertMovieInfoRequestDTO);

    int updateMovieInfo(UpdateMovieInfoRequestDTO updateMovieInfoRequestDTO);

    int updateMovieState(UpdateMovieStateRequestDTO updateMovieStateRequestDTO);

    int deleteMovieInfo(Integer movieNo);
}
