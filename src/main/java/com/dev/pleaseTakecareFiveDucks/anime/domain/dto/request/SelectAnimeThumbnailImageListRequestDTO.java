package com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SelectAnimeThumbnailImageListRequestDTO {
    List<Integer> animeNoList;
}
