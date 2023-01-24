package com.dev.pleaseTakecareFiveDucks.comic.domain.vo.result;

import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
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
public class ComicBookListResultVO {
    PageHandler pageHandler;
    List<ComicBookVO> comicBookVOList;
}
