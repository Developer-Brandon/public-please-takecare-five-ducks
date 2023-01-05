package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.InsertYoutubeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.SelectYoutubeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.UpdateYoutubeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.dto.request.UpdateYoutubeStateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.youtube.domain.vo.YoutubeVO;

import java.util.List;

// todo: Mp3DAO와 YotubeDAO는 추후 util 메뉴에 대해서 추가적으로 고민 후 개발 예정입니다
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
