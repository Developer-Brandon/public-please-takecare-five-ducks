package com.dev.pleaseTakecareFiveDucks.movie.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UpdateMovieInfoDTO {
    Integer movieNo;
    Integer madeNatureNo;
    String title;
    String directorName;
    Integer pagePerMovieCnt;
    Date movieRegDt;
}
