package com.dev.pleaseTakecareFiveDucks.book.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainBookVO {
    Integer bookNo;
    String bookTitle;
    String bookAuthor;
    String link;
    String bookTypeKorean;
    String bookTypeEnglish;
    Integer viewCnt;
    String fileFullPath;
    String bookRegDt;
    String regDt;
}
