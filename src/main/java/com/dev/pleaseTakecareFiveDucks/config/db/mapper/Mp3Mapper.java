package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request.InsertMp3InfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request.UpdateMp3InfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.request.UpdateMp3StateRequestDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.vo.Mp3VO;
import java.util.List;

public interface Mp3Mapper {

    int getMp3TotalCnt();

    int deleteAll();

    List<Mp3VO> selectAllMp3List();

    List<Mp3VO> selectMp3List();

    Integer insertMp3Info(InsertMp3InfoRequestDTO insertMp3InfoRequestDTO);

    int updateMp3Info(UpdateMp3InfoRequestDTO updateMp3InfoRequestDTO);

    int updateMp3State(UpdateMp3StateRequestDTO updateMp3StateRequestDTO);

    int deleteMp3Info(Integer bookNo);
}
