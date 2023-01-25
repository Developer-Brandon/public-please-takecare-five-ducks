package com.dev.pleaseTakecareFiveDucks.comic.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SelectComicBookThumbnailDTO {
    String comicBookName;
}
