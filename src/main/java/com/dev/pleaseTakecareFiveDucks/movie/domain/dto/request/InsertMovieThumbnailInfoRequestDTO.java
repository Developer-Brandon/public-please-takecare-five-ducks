package com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InsertMovieThumbnailInfoRequestDTO {
    Integer movieNo;
    String webThumbnailUrl;
}
