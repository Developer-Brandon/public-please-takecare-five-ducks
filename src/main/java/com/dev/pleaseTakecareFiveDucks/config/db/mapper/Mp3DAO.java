package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request.InsertMp3InfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request.SelectMp3InfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request.UpdateMp3InfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request.UpdateMp3StateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.vo.Mp3VO;
import java.util.List;

// todo: Mp3DAO와 YotubeDAO는 추후 util 메뉴에 대해서 추가적으로 고민 후 개발 예정입니다
public interface Mp3DAO {

    int getMp3TotalCnt();

    int deleteAll();

    List<Mp3VO> selectAllMp3List();

    List<Mp3VO> selectMp3List();

    Mp3VO selectMp3Info(SelectMp3InfoRequestDTO selectMp3InfoRequestDTO);

    Integer insertMp3Info(InsertMp3InfoRequestDTO insertMp3InfoRequestDTO);

    int updateMp3Info(UpdateMp3InfoRequestDTO updateMp3InfoRequestDTO);

    int updateMp3State(UpdateMp3StateRequestDTO updateMp3StateRequestDTO);

    int deleteMp3Info(Integer bookNo);
}
