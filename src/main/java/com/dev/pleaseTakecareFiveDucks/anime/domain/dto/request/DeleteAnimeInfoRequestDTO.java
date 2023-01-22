package com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAnimeInfoRequestDTO {
    Integer animeNo;
}
