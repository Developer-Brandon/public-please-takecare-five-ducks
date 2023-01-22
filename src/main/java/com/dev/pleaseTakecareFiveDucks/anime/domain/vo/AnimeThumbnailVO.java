package com.dev.pleaseTakecareFiveDucks.anime.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeThumbnailVO {
    Integer animeNo;
    String webThumbnailUrl;
}
