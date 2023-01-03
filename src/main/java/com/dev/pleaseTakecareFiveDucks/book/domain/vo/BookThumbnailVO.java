package com.dev.pleaseTakecareFiveDucks.book.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookThumbnailVO {
    Integer bookNo;
    String filePullPath;
}
