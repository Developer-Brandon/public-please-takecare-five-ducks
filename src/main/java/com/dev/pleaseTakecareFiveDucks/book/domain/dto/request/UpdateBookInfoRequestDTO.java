package com.dev.pleaseTakecareFiveDucks.book.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookInfoRequestDTO {

    Integer bookNo;

    Integer madeNatureNo;

    Integer bookTypeNo;

    String title;

    String author;

    String link;

    // todo: 2023-01-24 기준에 book module에 썸네일이 없기 때문에 기본값을 넣어두고, 추후에 썸네일 관련되서 작업하게 되면 추가하도록 합니다.
    @Builder.Default
    String webThumbnailUrl = "default";

    String bookRegDt;
}
