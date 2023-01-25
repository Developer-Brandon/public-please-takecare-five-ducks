package com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.drama.util.BroadcastStateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDramaInfoRequestDTO {
    Integer dramaNo;
    Integer madeNatureNo;
    String title;
    String author;
    String link;
    String webThumbnailUrl;
    BroadcastStateEnum broadcastStateEnum;
    String dramaRegDt;
}
