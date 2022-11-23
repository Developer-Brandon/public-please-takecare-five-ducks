package com.dev.pleaseTakecareFiveDucks.contents.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.contents.util.ContentsMadeNatureUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateContentsStateRequestDTO {
    Integer contentsNo;
    ContentsMadeNatureUseYnEnum contentsMadeNatureUseYnEnum;
}
