package com.dev.pleaseTakecareFiveDucks.anime.domain.vo;

import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
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
    FinalizedYnEnum finalizedYnEnum;
    String link;
    String animeAuthor;
    Integer animeBroadcastCnt;
    Integer viewCnt;
    String fileFullPath;
    String animeRegDt;
    String regDt;
}
