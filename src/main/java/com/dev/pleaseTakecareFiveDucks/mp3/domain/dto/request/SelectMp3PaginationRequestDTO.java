package com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectMp3PaginationRequestDTO {

    @Builder.Default
    Integer currentPage = 1;

    Integer offset;

    @Builder.Default
    Integer pageSize = 10;
}
