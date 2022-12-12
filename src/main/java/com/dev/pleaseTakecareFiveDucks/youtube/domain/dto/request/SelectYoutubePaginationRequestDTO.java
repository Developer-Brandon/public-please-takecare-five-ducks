package com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectYoutubePaginationRequestDTO {

    @Builder.Default
    Integer currentPage = 1;

    Integer offset;

    @Builder.Default
    Integer pageSize = 10;
}
