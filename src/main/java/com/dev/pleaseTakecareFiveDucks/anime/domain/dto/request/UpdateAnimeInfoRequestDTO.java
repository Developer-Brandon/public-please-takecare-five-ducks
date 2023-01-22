package com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAnimeInfoRequestDTO {

    Integer animeNo;

    Integer madeNatureNo;

    String title;

    String author;

    String animeRegDt;

    String link;

    String webThumbnailUrl;

    FinalizedYnEnum finalizedYnEnum;

    Integer animeBroadcastCnt;
}
