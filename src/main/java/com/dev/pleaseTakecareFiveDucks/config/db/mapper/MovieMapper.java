package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.InsertMovieInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.UpdateMovieInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.UpdateMovieStateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.SelectMoviePaginationRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieVO;

import java.util.List;

public interface MovieMapper {

    int getMovieTotalCnt();

    int deleteAll();

    List<MovieVO> selectAllMovieList();

    List<MovieVO> selectMovieList(SelectMoviePaginationRequestDTO selectMoviePaginationRequestDTO);

    Integer insertMovieInfo(InsertMovieInfoRequestDTO insertMovieInfoRequestDTO);

    int updateMovieInfo(UpdateMovieInfoRequestDTO updateMovieInfoRequestDTO);

    int updateMovieState(UpdateMovieStateRequestDTO updateMovieStateRequestDTO);

    int deleteMovieInfo(Integer movieNo);
}
