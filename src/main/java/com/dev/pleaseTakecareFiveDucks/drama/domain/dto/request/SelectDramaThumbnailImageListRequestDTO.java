package com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SelectDramaThumbnailImageListRequestDTO {
    List<Integer> dramaNoList;
}
