package com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertAnimeViewCntRequestDTO {
    Integer userNo;
    Integer animeNo;
}
