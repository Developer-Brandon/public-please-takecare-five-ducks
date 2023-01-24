package com.dev.pleaseTakecareFiveDucks.movie.domain.vo.result;

import com.dev.pleaseTakecareFiveDucks.config.util.PageHandler;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieListResultVO {
    PageHandler pageHandler;
    List<MovieVO> movieVOList;
}
