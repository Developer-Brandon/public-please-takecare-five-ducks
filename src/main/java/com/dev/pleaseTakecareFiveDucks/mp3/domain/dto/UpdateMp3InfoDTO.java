package com.dev.pleaseTakecareFiveDucks.mp3.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UpdateMp3InfoDTO {
    Integer mp3No;
    String title;
    String singer;
    Date songRegDt;
}
