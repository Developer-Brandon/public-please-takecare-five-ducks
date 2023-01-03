package com.dev.pleaseTakecareFiveDucks.anime.domain.vo;

import com.dev.pleaseTakecareFiveDucks.anime.util.AnimeUseYnEnum;
import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeVO {
    Integer animeNo;
    Integer madeNatureNo;
    String animeTitle;
    String animeAuthor;
    String link;
    FinalizedYnEnum finalizedYnEnum;
    Integer animeBroadcastCnt;
    AnimeUseYnEnum animeUseYnEnum;
    Integer viewCnt;
    String filePullPath;
    String animeRegDt;
    String regDt;
}
