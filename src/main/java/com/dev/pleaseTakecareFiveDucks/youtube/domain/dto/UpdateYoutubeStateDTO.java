package com.dev.pleaseTakecareFiveDucks.youtube.domain.dto;

import com.dev.pleaseTakecareFiveDucks.youtube.util.YoutubeUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateYoutubeStateDTO {
    Integer youtubeNo;
    YoutubeUseYnEnum youtubeUseYnEnum;
}
