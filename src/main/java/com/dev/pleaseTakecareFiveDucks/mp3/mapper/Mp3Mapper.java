package com.dev.pleaseTakecareFiveDucks.mp3.mapper;

import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.InsertMp3InfoDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.UpdateMp3InfoDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.dto.UpdateMp3StateDTO;
import com.dev.pleaseTakecareFiveDucks.mp3.domain.vo.Mp3VO;
import java.util.List;

public interface Mp3Mapper {

    int getMp3TotalCnt();

    int deleteAll();

    List<Mp3VO> selectAllMp3List();

    List<Mp3VO> selectMp3List();

    Integer insertMp3Info(InsertMp3InfoDTO insertMp3InfoDTO);

    int updateMp3Info(UpdateMp3InfoDTO updateMp3InfoDTO);

    int updateMp3State(UpdateMp3StateDTO updateMp3StateDTO);

    int deleteMp3Info(Integer bookNo);
}
