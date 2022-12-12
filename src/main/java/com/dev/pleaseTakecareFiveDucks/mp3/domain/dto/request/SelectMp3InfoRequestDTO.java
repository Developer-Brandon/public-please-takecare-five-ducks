package com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SelectMp3InfoRequestDTO {
    Integer mp3No;
}
