package com.dev.pleaseTakecareFiveDucks.comic.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainComicBookDetailVO {
    Integer comicBookNo;
    String comicBookTitle;
    String comicBookAuthor;
    String link;
    String fileFullPath;
}
