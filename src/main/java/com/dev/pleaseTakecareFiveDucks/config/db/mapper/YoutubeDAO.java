package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.InsertYoutubeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.SelectYoutubeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.UpdateYoutubeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.UpdateYoutubeStateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.vo.YoutubeVO;

import java.util.List;

public interface YoutubeDAO {

    int getYoutubeTotalCnt();

    int deleteAll();

    List<YoutubeVO> selectAllYoutubeList();

    List<YoutubeVO> selectYoutubeList();

    YoutubeVO selectYoutubeInfo(SelectYoutubeInfoRequestDTO selectYoutubeInfoRequestDTO);

    Integer insertYoutubeInfo(InsertYoutubeInfoRequestDTO insertYoutubeInfoDTO);

    int updateYoutubeInfo(UpdateYoutubeInfoRequestDTO updateYoutubeInfoRequestDTO);

    int updateYoutubeState(UpdateYoutubeStateRequestDTO updateYoutubeStateRequestDTO);

    int deleteYoutubeInfo(Integer bookNo);
}
