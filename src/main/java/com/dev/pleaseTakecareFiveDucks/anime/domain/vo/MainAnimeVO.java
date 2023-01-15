package com.dev.pleaseTakecareFiveDucks.anime.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainAnimeVO {
    Integer animeNo;
    String animeTitle;
    String animeAuthor;
    String link;
    Integer viewCnt;
    String fileFullPath;
    String animeRegDt;
    String regDt;
}
