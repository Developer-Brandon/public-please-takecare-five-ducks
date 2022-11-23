package com.dev.pleaseTakecareFiveDucks.youtube.mapper;

import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.InsertYoutubeInfoDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.UpdateYoutubeInfoDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.UpdateYoutubeStateDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.vo.YoutubeVO;

import java.util.List;

public interface YoutubeMapper {

    int getYoutubeTotalCnt();

    int deleteAll();

    List<YoutubeVO> selectAllYoutubeList();

    List<YoutubeVO> selectYoutubeList();

    Integer insertYoutubeInfo(InsertYoutubeInfoDTO insertYoutubeInfoDTO);

    int updateYoutubeInfo(UpdateYoutubeInfoDTO updateYoutubeInfoDTO);

    int updateYoutubeState(UpdateYoutubeStateDTO updateYoutubeStateDTO);

    int deleteYoutubeInfo(Integer bookNo);
}
