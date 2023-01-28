package com.dev.pleaseTakecareFiveDucks.drama.domain.vo;

import com.dev.pleaseTakecareFiveDucks.drama.util.DramaSerialStateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DramaSerialStateVO {
    DramaSerialStateEnum dramaSerialStateEnum;
}
