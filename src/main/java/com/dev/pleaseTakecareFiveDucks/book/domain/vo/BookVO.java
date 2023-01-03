package com.dev.pleaseTakecareFiveDucks.book.domain.vo;

import com.dev.pleaseTakecareFiveDucks.book.util.BookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
    Integer bookNo;
    Integer madeNatureNo;
    String bookTypeKorean;
    String bookTypeEnglish;
    String bookTitle;
    String bookAuthor;
    String link;
    BookUseYnEnum bookUseYnEnum;
    Integer viewCnt;
    String filePullPath;
    String bookRegDt;
    String regDt;
}
