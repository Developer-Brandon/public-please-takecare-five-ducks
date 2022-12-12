package com.dev.pleaseTakecareFiveDucks.book.domain.vo;

import com.dev.pleaseTakecareFiveDucks.book.util.BookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
    Integer bookNo;
    Integer madeNatureNo;
    String bookTitle;
    String bookAuthor;
    BookUseYnEnum bookUseYnEnum;
    String bookRegDt;
    String regDt;
}
