package com.dev.pleaseTakecareFiveDucks.mp3.domain.dto;

import com.dev.pleaseTakecareFiveDucks.mp3.util.Mp3UseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateMp3StateDTO {
    Integer mp3No;
    Mp3UseYnEnum mp3UseYnEnum;
}
