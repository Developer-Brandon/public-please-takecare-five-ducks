package com.dev.pleaseTakecareFiveDucks.book.domain.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SelectBookThumbnailImageListRequestDTO {
    List<Integer> bookNoList;
}
