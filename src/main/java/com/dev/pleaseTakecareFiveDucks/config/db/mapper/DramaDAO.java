package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.InsertDramaInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.SelectDramaInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.UpdateDramaInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.UpdateDramaStateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaVO;
import java.util.List;

public interface DramaDAO {

    int getDramaTotalCnt();

    int deleteAll();

    List<DramaVO> selectAllDramaList();

    List<DramaVO> selectDramaList(SelectDramaInfoRequestDTO selectDramaInfoRequestDTO);

    DramaVO selectDramaInfo(SelectDramaInfoRequestDTO selectDramaInfoRequestDTO);

    Integer insertDramaInfo(InsertDramaInfoRequestDTO insertDramaInfoRequestDTO);

    int updateDramaInfo(UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO);

    int updateDramaState(UpdateDramaStateRequestDTO updateDramaStateRequestDTO);

    int deleteDramaInfo(Integer bookNo);
}
