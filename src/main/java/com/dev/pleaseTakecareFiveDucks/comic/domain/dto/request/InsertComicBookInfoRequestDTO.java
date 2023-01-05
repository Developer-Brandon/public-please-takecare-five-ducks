package com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.comic.util.ComicBookSerialStateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertComicBookInfoRequestDTO {

    Integer insertedComicBookNo;

    Integer madeNatureNo;

    String title;

    String author;

    String link;

    String filePath;

    String fileName;

    ComicBookSerialStateEnum comicBookSerialStateEnum;

    String comicBookRegDt;
}
