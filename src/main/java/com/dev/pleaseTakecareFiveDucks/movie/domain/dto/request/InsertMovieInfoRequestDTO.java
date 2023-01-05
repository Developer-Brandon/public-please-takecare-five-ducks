package com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertMovieInfoRequestDTO {

    Integer insertedMovieNo;

    Integer madeNatureNo;

    String title;

    String directorName;

    String link;

    Integer totalNumberOfEpisode;

    String filePath;

    String fileName;

    String movieRegDt;
}
