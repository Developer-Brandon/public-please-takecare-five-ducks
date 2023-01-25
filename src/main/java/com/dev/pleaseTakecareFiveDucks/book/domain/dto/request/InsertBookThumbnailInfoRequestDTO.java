package com.dev.pleaseTakecareFiveDucks.book.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertBookThumbnailInfoRequestDTO {
    Integer bookNo;
    String webThumbnailUrl;
}
