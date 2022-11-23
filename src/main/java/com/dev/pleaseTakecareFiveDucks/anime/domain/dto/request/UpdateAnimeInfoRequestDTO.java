package com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UpdateAnimeInfoRequestDTO {
    Integer animeNo;
    Integer madeNatureNo;
    String title;
    String author;
    Integer pagePerAnimeCnt;
    Date animeRegDt;
}
