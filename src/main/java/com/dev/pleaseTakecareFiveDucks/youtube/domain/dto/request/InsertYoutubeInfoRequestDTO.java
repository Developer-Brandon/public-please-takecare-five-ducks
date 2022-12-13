package com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertYoutubeInfoRequestDTO {
    Integer insertedYoutubeNo;
    String title;
    String youtuberName;
    Integer likeCnt;
    Integer dislikeCnt;
    Integer viewCnt;
    String youtubeRegDt;
}
