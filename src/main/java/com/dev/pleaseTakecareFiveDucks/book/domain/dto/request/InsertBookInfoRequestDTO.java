package com.dev.pleaseTakecareFiveDucks.book.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertBookInfoRequestDTO {
    Integer madeNatureNo;
    String title;
    String author;
    Date bookRegDt;
}
