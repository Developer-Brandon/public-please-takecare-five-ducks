package com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.anime.util.AnimeUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateAnimeStateRequestDTO {
    Integer animeNo;
    AnimeUseYnEnum animeUseYnEnum;
}
