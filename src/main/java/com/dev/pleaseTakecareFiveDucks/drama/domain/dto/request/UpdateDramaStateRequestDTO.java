package com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.drama.util.DramaUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDramaStateRequestDTO {
    Integer dramaNo;
    DramaUseYnEnum dramaUseYnEnum;
}
