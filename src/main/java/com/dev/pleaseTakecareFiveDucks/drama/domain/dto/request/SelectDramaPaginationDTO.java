package com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectDramaPaginationDTO {

    @Builder.Default
    Integer currentPage = 1;

    Integer offset;

    @Builder.Default
    Integer pageSize = 10;
}
