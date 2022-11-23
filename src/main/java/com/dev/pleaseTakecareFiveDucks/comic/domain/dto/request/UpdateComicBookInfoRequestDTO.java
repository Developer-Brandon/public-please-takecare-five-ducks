package com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UpdateComicBookInfoRequestDTO {
    Integer bookNo;
    Integer madeNatureNo;
    String title;
    String author;
    Date comicBookRegDt;
}
