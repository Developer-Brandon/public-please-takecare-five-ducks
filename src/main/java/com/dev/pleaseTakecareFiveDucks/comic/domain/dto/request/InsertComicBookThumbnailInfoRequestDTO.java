package com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InsertComicBookThumbnailInfoRequestDTO {
    Integer bookNo;
    String webThumbnailUrl;
}
