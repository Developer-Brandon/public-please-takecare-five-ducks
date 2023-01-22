package com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InsertAnimeThumbnailInfoRequestDTO {
    Integer animeNo;
    String webThumbnailUrl;
}
