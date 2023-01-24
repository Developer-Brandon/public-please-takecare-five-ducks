package com.dev.pleaseTakecareFiveDucks.drama.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DramaThumbnailVO {
    Integer dramaNo;
    String webThumbnailUrl;
}
