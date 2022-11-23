package com.dev.pleaseTakecareFiveDucks.youtube.domain.vo;

import com.dev.pleaseTakecareFiveDucks.youtube.util.YoutubeUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YoutubeVO {
    Integer youtubeNo;
    String youtubeTitle;
    String youtuberName;
    Integer likeCnt;
    Integer dislikeCnt;
    Integer viewCnt;
    YoutubeUseYnEnum youtubeUseYnEnum;
    Date youtubeRegDt;
    Date regDt;
}
