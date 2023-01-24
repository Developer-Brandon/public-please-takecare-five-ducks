package com.dev.pleaseTakecareFiveDucks.book.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SelectBookThumbnailImageUrlDTO {

    String comicBookName;
}
