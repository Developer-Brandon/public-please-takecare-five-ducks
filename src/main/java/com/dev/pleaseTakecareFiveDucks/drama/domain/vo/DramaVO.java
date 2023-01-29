package com.dev.pleaseTakecareFiveDucks.drama.domain.vo;

import com.dev.pleaseTakecareFiveDucks.drama.util.BroadcastStateEnum;
import com.dev.pleaseTakecareFiveDucks.drama.util.DramaUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DramaVO {
    Integer dramaNo;
    String dramaTitle;
    String dramaAuthor;
    String link;
    BroadcastStateEnum broadcastStateEnum;
    DramaUseYnEnum dramaUseYnEnum;
    Integer madeNatureNo;
    String madeKoreanName;
    Integer viewCnt;
    String webThumbnailUrl;
    String dramaRegDt;
    String regDt;
}
