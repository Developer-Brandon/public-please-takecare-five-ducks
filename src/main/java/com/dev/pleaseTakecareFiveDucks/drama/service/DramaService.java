package com.dev.pleaseTakecareFiveDucks.drama.service;


import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.InsertDramaInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.SelectDramaInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.UpdateDramaInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.UpdateDramaStateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaVO;

import java.util.List;

public interface DramaService {

    Integer selectDramaTotalCnt();

    void removeAllDramaInfoList() throws Exception;

    List<DramaVO> selectDramaList();

    List<DramaVO> selectAllDramaInfoList();

    DramaVO selectDramaInfo(SelectDramaInfoRequestDTO selectDramaInfoRequestDTO);

    void registerDramaInfo(InsertDramaInfoRequestDTO insertDramaInfoRequestDTO) throws Exception;

    void modifyDramaInfo(UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO) throws Exception;

    void modifyDramaState(UpdateDramaStateRequestDTO updateDramaStateRequestDTO) throws Exception;

    void removeDramaInfo(Integer No) throws Exception;
}
