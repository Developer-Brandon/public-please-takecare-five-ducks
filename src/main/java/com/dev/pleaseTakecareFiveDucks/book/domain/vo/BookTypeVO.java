package com.dev.pleaseTakecareFiveDucks.book.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTypeVO {
    Integer bookTypeNo;
    String bookTypeKorean;
    String bookTypeEnglish;
    String hexCode;
}
