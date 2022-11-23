package com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.comic.util.ComicBookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateComicBookStateRequestDTO {
    Integer bookNo;
    ComicBookUseYnEnum comicBookUseYnEnum;
}
