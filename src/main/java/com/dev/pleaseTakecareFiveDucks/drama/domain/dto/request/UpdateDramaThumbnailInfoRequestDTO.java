package com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateDramaThumbnailInfoRequestDTO {
    Integer dramaNo;
    String filePath;
    String fileName;
}
