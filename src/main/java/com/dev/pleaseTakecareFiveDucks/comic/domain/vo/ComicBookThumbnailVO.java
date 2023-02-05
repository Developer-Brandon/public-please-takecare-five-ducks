package com.dev.pleaseTakecareFiveDucks.comic.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicBookThumbnailVO {
    Integer bookNo;
    String filePullPath;
}
