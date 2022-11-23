package com.dev.pleaseTakecareFiveDucks.contents.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InsertContentsInfoRequestDTO {
    Integer insertedContentsNo;
    String englishName;
    String koreanName;
}
