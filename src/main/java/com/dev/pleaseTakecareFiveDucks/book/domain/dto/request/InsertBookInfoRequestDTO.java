package com.dev.pleaseTakecareFiveDucks.book.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InsertBookInfoRequestDTO {
    Integer insertedBookNo;
    Integer madeNatureNo;
    String title;
    String author;
    String bookRegDt;
}
