package com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SelectYoutubeInfoRequestDTO {
    Integer youtubeNo;
}
