package com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertAnimeInfoRequestDTO {

    Integer insertedAnimeNo;

    Integer madeNatureNo;

    String title;

    String author;

    String link;

    String webThumbnailUrl;

    FinalizedYnEnum finalizedYnEnum;

    Integer animeBroadcastCnt;

    String animeRegDt;
}
