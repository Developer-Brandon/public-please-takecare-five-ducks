package com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMovieInfoRequestDTO {

    Integer movieNo;

    Integer madeNatureNo;

    String title;

    String directorName;

    String link;

    Integer totalNumberOfEpisode;

    String webThumbnailUrl;

    String movieRegDt;
}
