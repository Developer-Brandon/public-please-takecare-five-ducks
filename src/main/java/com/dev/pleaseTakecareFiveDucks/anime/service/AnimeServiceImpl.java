package com.dev.pleaseTakecareFiveDucks.anime.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.SelectAnimeThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.AnimeListResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.AnimeDAO;
import com.dev.pleaseTakecareFiveDucks.config.util.PageHandler;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimeServiceImpl implements AnimeService{

    private final AnimeDAO animeDAO;

    @Override
    public List<FinalizedYnEnum> selectAnimeFinalizedList() throws Exception {

        List<FinalizedYnEnum> finalizedYnEnumList = new ArrayList<>();

        finalizedYnEnumList.add(FinalizedYnEnum.y);

        finalizedYnEnumList.add(FinalizedYnEnum.n);

        return finalizedYnEnumList;
    }

    @Override
    public Integer selectAnimeTotalCnt() throws Exception{
        return animeDAO.getTotalCnt();
    }

    @Override
    public void removeAllAnimeInfoList() throws Exception {

        // 만약 anime이 0개 초과로 있다면...
        int animeCnt = animeDAO.getTotalCnt();

        if(animeCnt > 0) {

            // 실제 삭제가 된 데이터들이 size와 일치해야 합니다.
            int removedAnimeCnt = animeDAO.deleteAll();

            // 만약, 일치하지 않는다면 Exception을 throw 합니다.
            if(animeCnt != removedAnimeCnt) {
                throw new Exception();
            }
        }
    }

    @Override
    public AnimeListResultVO selectAnimePaginationList(SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO) {

        int totalCnt = animeDAO.getTotalCntByCondition(selectAnimePaginationRequestDTO);

        PageHandler pageHandler = new PageHandler(totalCnt, selectAnimePaginationRequestDTO.getCurrentPage(), selectAnimePaginationRequestDTO.getTitle());

        // 만약 현재의 페이지가.. 1 -> 0, 2 -> 1, 3 -> 2, 4 -> 3 대로 오프셋 설정
        Integer offset = selectAnimePaginationRequestDTO.getCurrentPage() - 1;

        // PageSize는 DTO에서 기본으로 10으로 처리되어 있습니다.
        selectAnimePaginationRequestDTO.setOffset(offset * selectAnimePaginationRequestDTO.getPageSize());

        selectAnimePaginationRequestDTO.setPageSize(selectAnimePaginationRequestDTO.getPageSize());

        List<AnimeVO> animeVOList = animeDAO.selectAnimePaginationList(selectAnimePaginationRequestDTO);

        System.out.println("pageHandler" + pageHandler.toString());

        return AnimeListResultVO.builder()
                .pageHandler(pageHandler)
                .animeVOList(animeVOList)
                .build();
    }

    @Override
    public List<AnimeVO> selectAllAnimeInfoList() throws Exception {

        List<AnimeVO> animeVOList = animeDAO.selectAllAnimeList();

        List<Integer> animeNoList = animeVOList
                .stream()
                .map(AnimeVO::getAnimeNo)
                .collect(Collectors.toList());

        SelectAnimeThumbnailImageListRequestDTO selectAnimeThumbnailImageListRequestDTO = SelectAnimeThumbnailImageListRequestDTO.builder()
                .animeNoList(animeNoList)
                .build();

        List<AnimeThumbnailVO> animeThumbnailVOList = animeDAO.selectAnimeThumbnailImageListByAnimeNo(selectAnimeThumbnailImageListRequestDTO);

        // animeVOList를 순회하며, animeNo가 같은 요소들끼리 찾아서 fileFullPath를 set해줍니다.
        animeVOList.forEach(e -> {

                    //
                    animeThumbnailVOList.forEach(f -> {
                                if(e.getAnimeNo().equals(f.getAnimeNo())) {
                                    e.setWebThumbnailUrl(f.getWebThumbnailUrl());
                                }
                    });
        });

        return animeVOList;
    }

    @Override
    public AnimeVO selectAnimeInfo(SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO) {

        // 단일 조회 시, left outer join 으로 조회수와 썸네일을 조회합니다.
        // 리스트 같은경우 다르게 조회하므로 selectAllAnimeInfoList를 참고해주시면 됩니다.

        return animeDAO.selectAnimeInfo(selectAnimeInfoRequestDTO);
    }

    @Override
    public void registerAnimeInfo(InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO) throws Exception {

        // 단일 insert 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(animeDAO.insertAnimeInfo(insertAnimeInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String webThumbnailUrl = insertAnimeInfoRequestDTO.getWebThumbnailUrl();

        if(!webThumbnailUrl.isEmpty()) {

            // 썸네일을 삽입하기 위한 dto를 준비합니다.

            InsertAnimeThumbnailInfoRequestDTO insertAnimeThumbnailInfoRequestDTO = InsertAnimeThumbnailInfoRequestDTO.builder()
                    .animeNo(insertAnimeInfoRequestDTO.getInsertedAnimeNo())
                    .webThumbnailUrl(webThumbnailUrl)
                    .build();

            // 썸네일을 삽입합니다.
            if(animeDAO.insertAnimeThumbnailInfo(insertAnimeThumbnailInfoRequestDTO) != 1) {
                throw new Exception();
            }
        }
    }

    @Override
    public void modifyAnimeInfo(UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO) throws Exception {

        // 단일 update 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(animeDAO.updateAnimeInfo(updateAnimeInfoRequestDTO) != 1) {
           throw new Exception();
       }

        // 만약 썸네일을 첨부했다면...
        String webThumbnailUrl = updateAnimeInfoRequestDTO.getWebThumbnailUrl();

        if(!webThumbnailUrl.isEmpty()) {

            // 그리고 만약 썸네일 이 있다면 update를 하고, 없다면 insert를 합니다.
            // (혹시모를 예외처리)
            if(animeDAO.selectAnimeThumbnailImageByAnimeNo(updateAnimeInfoRequestDTO.getAnimeNo()) > 0) {

                // 썸네일을 update하기 위한 dto를 준비합니다.

                UpdateAnimeThumbnailInfoRequestDTO updateAnimeThumbnailInfoRequestDTO = UpdateAnimeThumbnailInfoRequestDTO.builder()
                        .animeNo(updateAnimeInfoRequestDTO.getAnimeNo())
                        .webThumbnailUrl(webThumbnailUrl)
                        .build();

                // 썸네일을 삽입합니다.
                if(animeDAO.updateAnimeThumbnailInfo(updateAnimeThumbnailInfoRequestDTO) != 1) {
                    throw new Exception();
                }
            } else {
                // 썸네일을 삽입하기 위한 dto를 준비합니다.

                InsertAnimeThumbnailInfoRequestDTO insertAnimeThumbnailInfoRequestDTO = InsertAnimeThumbnailInfoRequestDTO.builder()
                        .animeNo(updateAnimeInfoRequestDTO.getAnimeNo())
                        .webThumbnailUrl(webThumbnailUrl)
                        .build();

                // 썸네일을 삽입합니다.
                if(animeDAO.insertAnimeThumbnailInfo(insertAnimeThumbnailInfoRequestDTO) != 1) {
                    throw new Exception();
                }
            }

        }
    }

    @Override
    public void removeAnimeInfo(DeleteAnimeInfoRequestDTO removeAnimeRequestDTO) throws Exception {

        // fk 문제 때문에, fk의 맨 마지막에 걸려있는 데이터부터 삭제해야 합니다.
        if(animeDAO.deleteAnimeThumbnailInfo(removeAnimeRequestDTO.getAnimeNo()) != 1) {
            throw new Exception();
        }

        if(animeDAO.deleteAnimeInfo(removeAnimeRequestDTO.getAnimeNo()) != 1) {
            throw new Exception();
        }
    }

    @Override
    public List<RawImageThumbnailVO> selectImageThumbnailVOList(SelectAnimeThumbnailImageUrlDTO selectAnimeThumbnailImageUrlDTO) throws Exception {

        String animeName = selectAnimeThumbnailImageUrlDTO.getAnimeName();

        JSONObject json = null;

        String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

        String SEARCH_API_URL = "https://www.googleapis.com/customsearch/v1";

        String API_KEY = "AIzaSyAw2NnlSIS-dj6-Rh-3FsvLwiUKP35b6Tc";

        String SEARCH_ENGINE_ID = "d1bf72817817d47b1";

        List<String> animeImageUrlList = new ArrayList<>();

        Connection.Response res = null;

        try {

            res = Jsoup.connect(SEARCH_API_URL + "?key=" + API_KEY + "&cx=" + SEARCH_ENGINE_ID + "&q=" + animeName)
                    .ignoreContentType(true)
                    .userAgent(USER_AGENT)
                    .execute();
        } catch (IOException e) {

            e.printStackTrace();
        }

        json = new JSONObject(res.body());

        JSONArray jsonArray = json.getJSONArray("items");

        if (jsonArray.length() > 0) {

            jsonArray.forEach(e -> {

                try {

                    JSONObject jsonObject = ((JSONObject) e).getJSONObject("pagemap");

                    if(jsonObject.has("cse_thumbnail")) {

                        JSONArray cseThumbnail = jsonObject.getJSONArray("cse_thumbnail");

                        for(Integer i = 0; i < cseThumbnail.length(); i++) {

                            String innerImageUrl = cseThumbnail.getJSONObject(i).getString("src");

                            animeImageUrlList.add(innerImageUrl);
                        }
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                    animeImageUrlList.add("");
                }
            });

            return animeImageUrlList
                    .stream()
                    .filter(e -> !e.isEmpty())
                    .map(e2 -> RawImageThumbnailVO.builder()
                            .imageUrl(e2)
                            .build()
                    )
                    .collect(Collectors.toList());
        } else {

            return new ArrayList<>();
        }
    }

    @Override
    public void registerAnimeViewCnt(InsertAnimeViewCntRequestDTO insertAnimeViewCntRequestDTO) throws Exception {

        if(animeDAO.insertAnimeViewCnt(insertAnimeViewCntRequestDTO) != 1) {
            throw new Exception();
        }
    }
}
