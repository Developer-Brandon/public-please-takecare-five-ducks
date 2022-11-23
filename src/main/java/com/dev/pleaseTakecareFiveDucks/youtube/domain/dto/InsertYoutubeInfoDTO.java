package com.dev.pleaseTakecareFiveDucks.youtube.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertYoutubeInfoDTO {
    Integer insertedYoutubeNo;
    String title;
    String youtuberName;
    Integer likeCnt;
    Integer dislikeCnt;
    Integer viewCnt;
    Date youtubeRegDt;
}
