package com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertDramaInfoRequestDTO {
    Integer insertedDramaNo;
    Integer madeNatureNo;
    String title;
    String author;
    Integer pagePerDramaCnt;
    String dramaRegDt;
}
