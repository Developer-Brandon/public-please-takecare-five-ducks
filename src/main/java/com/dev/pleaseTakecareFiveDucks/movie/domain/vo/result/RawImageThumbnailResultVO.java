package com.dev.pleaseTakecareFiveDucks.movie.domain.vo.result;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Builder
@Getter
@Setter
public class RawImageThumbnailResultVO {

    ArrayList<RawImageThumbnailVO> rawImageThumbnailVOArrayList;
}
