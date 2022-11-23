package com.dev.pleaseTakecareFiveDucks.anime.domain.vo;

import com.dev.pleaseTakecareFiveDucks.anime.util.AnimeUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeVO {
    Integer animeNo;
    Integer madeNatureNo;
    String animeTitle;
    String animeAuthor;
    Integer pagePerAnimeCnt;
    AnimeUseYnEnum animeUseYnEnum;
    Date animeRegDt;
    Date regDt;
}
