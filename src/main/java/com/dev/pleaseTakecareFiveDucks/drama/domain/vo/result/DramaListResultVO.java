package com.dev.pleaseTakecareFiveDucks.drama.domain.vo.result;

import com.dev.pleaseTakecareFiveDucks.config.util.PageHandler;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DramaListResultVO {
    PageHandler pageHandler;
    List<DramaVO> dramaVOList;
}
