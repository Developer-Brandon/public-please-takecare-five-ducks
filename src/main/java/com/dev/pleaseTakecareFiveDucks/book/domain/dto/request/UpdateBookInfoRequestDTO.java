package com.dev.pleaseTakecareFiveDucks.book.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UpdateBookInfoRequestDTO {
    Integer bookNo;
    Integer madeNatureNo;
    String title;
    String author;
    String bookRegDt;
}
