package com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.comic.util.ComicBookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateComicBookStateRequestDTO {
    Integer bookNo;
    ComicBookUseYnEnum comicBookUseYnEnum;
}
