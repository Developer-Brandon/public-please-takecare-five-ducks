package com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
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

    String animeTitle;

    String animeAuthor;

    String animeRegDt;

    String link;

    String filePath;

    String fileName;

    FinalizedYnEnum finalizedYnEnum;

    Integer animeBroadcastCnt;
}
