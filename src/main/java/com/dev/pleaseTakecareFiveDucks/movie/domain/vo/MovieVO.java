package com.dev.pleaseTakecareFiveDucks.movie.domain.vo;

import com.dev.pleaseTakecareFiveDucks.movie.util.MovieUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieVO {
    Integer movieNo;
    Integer madeNatureNo;
    String movieTitle;
    String directorName;
    String link;
    Integer totalNumberOfEpisode;
    Integer viewCnt;
    String filePullPath;
    MovieUseYnEnum movieUseYnEnum;
    String movieRegDt;
    String regDt;
}
