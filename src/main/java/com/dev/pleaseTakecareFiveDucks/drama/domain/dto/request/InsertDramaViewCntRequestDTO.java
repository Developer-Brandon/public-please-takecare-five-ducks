package com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InsertDramaViewCntRequestDTO {
    Integer userNo;
    Integer dramaNo;
}
