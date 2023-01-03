package com.dev.pleaseTakecareFiveDucks.book.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateBookThumbnailInfoRequestDTO {
    Integer bookNo;
    String filePath;
    String fileName;
}