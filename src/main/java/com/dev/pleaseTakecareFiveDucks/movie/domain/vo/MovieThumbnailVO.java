package com.dev.pleaseTakecareFiveDucks.movie.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieThumbnailVO {
    Integer movieNo;
    String fileFullPath;
}
