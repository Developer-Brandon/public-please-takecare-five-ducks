package com.dev.pleaseTakecareFiveDucks.contents.mapper;

import com.dev.pleaseTakecareFiveDucks.contents.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.contents.domain.vo.ContentsVO;

import java.util.List;

public interface ContentsMadeNatureMapper {

    int getContentsTotalCnt();

    int deleteAll();

    List<ContentsVO> selectAllContentsList();

    List<ContentsVO> selectContentsList(SelectContentsPaginationRequestDTO selectContentsPaginationRequestDTO);

    ContentsVO selectContents(SelectContentsInfoRequestDTO selectContentsInfoRequestDTO);

    Integer insertContentsInfo(InsertContentsInfoRequestDTO insertContentsInfoRequestDTO);

    int updateContentsInfo(UpdateContentsInfoRequestDTO updateContentsInfoRequestDTO);

    int updateContentsState(UpdateContentsStateRequestDTO updateContentsStateRequestDTO);

    int deleteContentsInfo(Integer contentsNo);
}
