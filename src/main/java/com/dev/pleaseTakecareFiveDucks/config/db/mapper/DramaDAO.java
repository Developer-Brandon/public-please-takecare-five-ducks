package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaVO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.MainDramaVO;

import java.util.List;

public interface DramaDAO {

    List<MainDramaVO> selectMainDramaList();

    int getDramaTotalCnt();

    int getTotalCntByCondition(SelectDramaPaginationDTO selectDramaPaginationDTO);

    List<DramaVO> selectAllDramaList();

    List<DramaVO> selectDramaPaginationList(SelectDramaPaginationDTO selectDramaPaginationDTO);

    List<DramaThumbnailVO> selectDramaThumbnailImageListByDramaNo(SelectDramaThumbnailImageListRequestDTO selectDramaThumbnailImageListRequestDTO);

    int selectDramaThumbnailImageCntByDramaNo(Integer dramaNo);

    DramaVO selectDramaInfo(SelectDramaInfoRequestDTO selectDramaInfoRequestDTO);

    int insertDramaViewCnt(InsertDramaViewCntRequestDTO insertDramaViewCntRequestDTO);

    int insertDramaThumbnailInfo(InsertDramaThumbnailInfoRequestDTO insertDramaThumbnailInfoRequestDTO);

    int insertDramaInfo(InsertDramaInfoRequestDTO insertDramaInfoRequestDTO);

    int updateDramaInfo(UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO);

    int updateDramaThumbnailInfo(UpdateDramaThumbnailInfoRequestDTO updateDramaThumbnailInfoRequestDTO);

    int updateDramaState(UpdateDramaStateRequestDTO updateDramaStateRequestDTO);

    int deleteAll();

    int deleteDramaInfo(Integer bookNo);

    int deleteDramaThumbnailInfo(Integer bookNo);
}
