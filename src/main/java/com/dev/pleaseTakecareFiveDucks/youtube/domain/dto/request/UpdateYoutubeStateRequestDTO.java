package com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.youtube.util.YoutubeUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateYoutubeStateRequestDTO {
    Integer youtubeNo;
    YoutubeUseYnEnum youtubeUseYnEnum;
}
