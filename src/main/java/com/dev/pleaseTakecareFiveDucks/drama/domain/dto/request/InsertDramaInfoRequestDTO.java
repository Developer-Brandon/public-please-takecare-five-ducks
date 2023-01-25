package com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.drama.util.BroadcastStateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertDramaInfoRequestDTO {
    Integer insertedDramaNo;
    Integer madeNatureNo;
    String title;
    String author;
    String link;
    String webThumbnailUrl;
    Integer viewCnt;
    BroadcastStateEnum broadcastStateEnum;
    String dramaRegDt;
}
