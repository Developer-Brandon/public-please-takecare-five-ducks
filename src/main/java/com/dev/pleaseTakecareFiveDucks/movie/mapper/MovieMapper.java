package com.dev.pleaseTakecareFiveDucks.movie.mapper;

import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.InsertMovieInfoDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.UpdateMovieInfoDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.UpdateMovieStateDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.SelectMoviePaginationDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieVO;

import java.util.List;

public interface MovieMapper {

    int getMovieTotalCnt();

    int deleteAll();

    List<MovieVO> selectAllMovieList();

    List<MovieVO> selectMovieList(SelectMoviePaginationDTO selectMoviePaginationDTO);

    Integer insertMovieInfo(InsertMovieInfoDTO insertMovieInfoDTO);

    int updateMovieInfo(UpdateMovieInfoDTO updateMovieInfoDTO);

    int updateMovieState(UpdateMovieStateDTO updateMovieStateDTO);

    int deleteMovieInfo(Integer movieNo);
}
