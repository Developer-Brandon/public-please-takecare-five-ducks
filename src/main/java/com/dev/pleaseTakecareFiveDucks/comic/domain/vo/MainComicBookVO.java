package com.dev.pleaseTakecareFiveDucks.comic.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainComicBookVO {
    List<MainComicBookDetailVO> mainComicBookDetailVOList;
}
