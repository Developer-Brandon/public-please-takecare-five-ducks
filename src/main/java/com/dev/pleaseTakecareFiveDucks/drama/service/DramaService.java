package com.dev.pleaseTakecareFiveDucks.drama.service;


import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.InsertDramaInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.SelectDramaInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.UpdateDramaInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.UpdateDramaStateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaVO;

import java.util.List;

public interface DramaService {

    Integer selectDramaTotalCnt();

    void removeAllDramaInfoList();

    List<DramaVO> selectDramaList();

    List<DramaVO> selectAllDramaInfoList();

    DramaVO selectDramaInfo(SelectDramaInfoRequestDTO selectDramaInfoRequestDTO);

    void registerDramaInfo(InsertDramaInfoRequestDTO insertDramaInfoRequestDTO);

    void modifyDramaInfo(UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO);

    void modifyDramaState(UpdateDramaStateRequestDTO updateDramaStateRequestDTO);

    void removeDramaInfo(Integer No);
}
