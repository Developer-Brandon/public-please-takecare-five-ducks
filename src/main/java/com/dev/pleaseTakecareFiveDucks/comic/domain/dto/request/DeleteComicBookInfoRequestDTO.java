package com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteComicBookInfoRequestDTO {
    Integer bookNo;
}
