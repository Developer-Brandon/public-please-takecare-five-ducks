package com.dev.pleaseTakecareFiveDucks.contents.domain.vo;

import com.dev.pleaseTakecareFiveDucks.contents.util.ContentsMadeNatureUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentsVO {
    Integer madeNatureNo;
    String englishName;
    String koreanName;
    ContentsMadeNatureUseYnEnum contentsMadeNatureUseYnEnum;
    Date regDt;
}
