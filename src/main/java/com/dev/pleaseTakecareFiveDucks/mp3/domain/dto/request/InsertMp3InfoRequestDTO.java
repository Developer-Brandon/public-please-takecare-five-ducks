package com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertMp3InfoRequestDTO {
    Integer insertedMp3No;
    String title;
    String singer;
    String songRegDt;
}
