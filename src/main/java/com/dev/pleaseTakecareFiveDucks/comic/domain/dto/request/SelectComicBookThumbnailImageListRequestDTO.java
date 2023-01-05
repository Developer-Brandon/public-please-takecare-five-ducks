package com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SelectComicBookThumbnailImageListRequestDTO {
    List<Integer> comicBookNoList;
}
