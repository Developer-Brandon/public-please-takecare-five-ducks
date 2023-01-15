package com.dev.pleaseTakecareFiveDucks.main.domain.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MainEntertainVO {

    String title;

    String fileFullPath;

    Integer viewCnt;
}
