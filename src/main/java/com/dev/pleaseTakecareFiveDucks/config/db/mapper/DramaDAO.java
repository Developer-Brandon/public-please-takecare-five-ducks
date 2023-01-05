package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaVO;
import java.util.List;

public interface DramaDAO {

    int getDramaTotalCnt();

    int deleteAll();

    List<DramaVO> selectAllDramaList();

    List<DramaVO> selectDramaList(SelectDramaInfoRequestDTO selectDramaInfoRequestDTO);

    List<DramaThumbnailVO> selectDramaThumbnailImageListByDramaNo(SelectDramaThumbnailImageListRequestDTO selectDramaThumbnailImageListRequestDTO);

    DramaVO selectDramaInfo(SelectDramaInfoRequestDTO selectDramaInfoRequestDTO);

    int insertDramaViewCnt(InsertDramaViewCntRequestDTO insertDramaViewCntRequestDTO);

    int insertDramaThumbnailInfo(InsertDramaThumbnailInfoRequestDTO insertDramaThumbnailInfoRequestDTO);

    int insertDramaInfo(InsertDramaInfoRequestDTO insertDramaInfoRequestDTO);

    int updateDramaInfo(UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO);

    int updateDramaThumbnailInfo(UpdateDramaThumbnailInfoRequestDTO updateDramaThumbnailInfoRequestDTO);

    int updateDramaState(UpdateDramaStateRequestDTO updateDramaStateRequestDTO);

    int deleteDramaInfo(Integer bookNo);

    int deleteDramaThumbnailInfo(Integer bookNo);
}
