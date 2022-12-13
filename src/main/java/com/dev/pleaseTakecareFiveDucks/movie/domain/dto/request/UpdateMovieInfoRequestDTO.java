package com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UpdateMovieInfoRequestDTO {
    Integer movieNo;
    Integer madeNatureNo;
    String title;
    String directorName;
    Integer pagePerMovieCnt;
    String movieRegDt;
}
