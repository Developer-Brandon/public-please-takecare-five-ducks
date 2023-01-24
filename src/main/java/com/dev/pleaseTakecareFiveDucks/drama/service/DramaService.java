package com.dev.pleaseTakecareFiveDucks.drama.service;


import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.SelectBookThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.SelectDramaThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaVO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.result.DramaListResultVO;

import java.util.List;

public interface DramaService {

    Integer selectDramaTotalCnt();

    void removeAllDramaInfoList() throws Exception;

    DramaListResultVO selectDramaPaginationList(SelectDramaPaginationDTO selectDramaPaginationDTO) throws Exception;

    List<DramaVO> selectAllDramaInfoList() throws Exception;

    DramaVO selectDramaInfo(SelectDramaInfoRequestDTO selectDramaInfoRequestDTO);

    void registerDramaInfo(InsertDramaInfoRequestDTO insertDramaInfoRequestDTO) throws Exception;

    void modifyDramaInfo(UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO) throws Exception;

    void modifyDramaState(UpdateDramaStateRequestDTO updateDramaStateRequestDTO) throws Exception;

    void removeDramaInfo(Integer No) throws Exception;

    List<RawImageThumbnailVO> selectImageThumbnailVOList(SelectDramaThumbnailImageUrlDTO selectDramaThumbnailImageUrlDTO) throws Exception;

    void registerDramaViewCnt(InsertDramaViewCntRequestDTO insertDramaViewCntRequestDTO) throws Exception;
}
