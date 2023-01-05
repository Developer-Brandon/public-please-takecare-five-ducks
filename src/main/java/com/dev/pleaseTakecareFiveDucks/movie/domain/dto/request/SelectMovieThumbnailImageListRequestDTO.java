package com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SelectMovieThumbnailImageListRequestDTO {
    List<Integer> movieNoList;
}
