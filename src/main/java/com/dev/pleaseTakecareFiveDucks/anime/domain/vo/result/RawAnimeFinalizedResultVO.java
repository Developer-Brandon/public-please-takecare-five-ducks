package com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Builder
@Getter
@Setter
public class RawAnimeFinalizedResultVO {

    ArrayList<FinalizedYnEnum> finalizedYnEnumArrayList;
}
