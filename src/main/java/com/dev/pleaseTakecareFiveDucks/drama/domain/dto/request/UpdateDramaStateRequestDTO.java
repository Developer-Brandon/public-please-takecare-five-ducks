package com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.drama.util.DramaUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateDramaStateRequestDTO {
    Integer dramaNo;
    DramaUseYnEnum dramaUseYnEnum;
}
