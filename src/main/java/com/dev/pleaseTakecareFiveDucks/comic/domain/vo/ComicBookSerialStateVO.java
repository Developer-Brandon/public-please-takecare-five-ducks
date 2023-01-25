package com.dev.pleaseTakecareFiveDucks.comic.domain.vo;

import com.dev.pleaseTakecareFiveDucks.comic.util.ComicBookSerialStateEnum;
import com.dev.pleaseTakecareFiveDucks.comic.util.ComicBookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicBookSerialStateVO {
    ComicBookSerialStateEnum comicBookSerialStateEnum;
}
