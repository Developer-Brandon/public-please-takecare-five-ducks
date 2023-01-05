package com.dev.pleaseTakecareFiveDucks.movie.service;


import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.InsertMovieInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.SelectMovieInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.UpdateMovieInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.UpdateMovieStateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieVO;

import java.util.List;

public interface MovieService {

    Integer selectMovieTotalCnt();

    void removeAllMovieInfoList() throws Exception;

    List<MovieVO> selectMovieList();

    List<MovieVO> selectAllMovieInfoList();

    MovieVO selectMovieInfo(SelectMovieInfoRequestDTO selectMovieInfoRequestDTO);

    void registerMovieInfo(InsertMovieInfoRequestDTO insertMovieInfoRequestDTO) throws Exception;

    void modifyMovieInfo(UpdateMovieInfoRequestDTO updateMovieInfoRequestDTO) throws Exception;

    void modifyMovieState(UpdateMovieStateRequestDTO updateMovieStateRequestDTO) throws Exception;

    void removeMovieInfo(Integer movieNo) throws Exception;
}
