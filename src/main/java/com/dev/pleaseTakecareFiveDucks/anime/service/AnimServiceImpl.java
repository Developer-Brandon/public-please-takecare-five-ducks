package com.dev.pleaseTakecareFiveDucks.anime.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.AnimeDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimServiceImpl implements AnimeService{

    private final AnimeDAO animeDAO;

    @Override
    public Integer selectAnimeTotalCnt() {
        return animeDAO.getAnimeTotalCnt();
    }

    @Override
    public void removeAllAnimeInfoList() throws Exception {

        // 만약 anime이 0개 초과로 있다면...
        int animeCnt = animeDAO.selectAllAnimeList().size();

        if(animeCnt > 0) {

            // 실제 삭제가 된 데이터들이 size와 일치해야 합니다.
            int removedAnimeCnt = animeDAO.deleteAll();

            // 만약, 일치하지 않는다면 Exception을 throw 합니다.
            if(animeCnt != removedAnimeCnt) {
                throw new Exception();
            }
        }
    }

    // todo: 추후 jsp에서 어떻게 받을지 결정한 후에 개발하는 것으로 할게요.
    @Override
    public List<AnimeVO> selectAnimeList(SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO) {
        return null;
    }

    @Override
    public List<AnimeVO> selectAllAnimeInfoList() {
        return null;
    }

    @Override
    public AnimeVO selectAnimeInfo(SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO) {
        return null;
    }

    @Override
    public void registerAnimeInfo(InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO) {

    }

    @Override
    public void modifyAnimeInfo(UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO) {

    }

    @Override
    public void removeAnimeInfo(RemoveAnimeInfoRequestDTO removeAnimeRequestDTO) {

    }
}
