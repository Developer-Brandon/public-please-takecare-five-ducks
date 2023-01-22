package com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.config.util.PageHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeListResultVO {

    PageHandler pageHandler;

    List<AnimeVO> animeVOList;
}
