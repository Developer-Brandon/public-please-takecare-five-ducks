package com.dev.pleaseTakecareFiveDucks.drama.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.SelectBookThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.result.ComicBookListResultVO;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.DramaDAO;
import com.dev.pleaseTakecareFiveDucks.config.util.PageHandler;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.SelectDramaThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaVO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.result.DramaListResultVO;
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
public class DramaServiceImpl implements DramaService{

    private final DramaDAO dramaDAO;

    @Override
    public Integer selectDramaTotalCnt() {
        return dramaDAO.getDramaTotalCnt();
    }

    @Override
    public void removeAllDramaInfoList() throws Exception {

        // 만약 drama가 0개 초과로 있다면....
        int dramaCnt = dramaDAO.getDramaTotalCnt();

        if(dramaCnt > 0) {

            // 실제 삭제가 된 데이터들이 size와 일치해야 합니다.
            int removedDramaCnt = dramaDAO.deleteAll();

            // 만약, 일치하지 않는다면 Exception을 throw 합니다.
            if(dramaCnt != removedDramaCnt) {
                throw new Exception();
            }
        }
    }

    @Override
    public DramaListResultVO selectDramaPaginationList(SelectDramaPaginationDTO selectDramaPaginationDTO) throws Exception {

        int totalCnt = dramaDAO.getTotalCntByCondition(selectDramaPaginationDTO);

        PageHandler pageHandler = new PageHandler(totalCnt, selectDramaPaginationDTO.getCurrentPage(), selectDramaPaginationDTO.getTitle());

        // 만약 현재의 페이지가.. 1 -> 0, 2 -> 1, 3 -> 2, 4 -> 3 대로 오프셋 설정
        Integer offset = selectDramaPaginationDTO.getCurrentPage() - 1;

        // PageSize는 DTO에서 기본으로 10으로 처리되어 있습니다.
        selectDramaPaginationDTO.setOffset(offset * selectDramaPaginationDTO.getPageSize());

        selectDramaPaginationDTO.setPageSize(selectDramaPaginationDTO.getPageSize());

        List<DramaVO> dramaVOList = dramaDAO.selectDramaPaginationList(selectDramaPaginationDTO);

        return DramaListResultVO.builder()
                .pageHandler(pageHandler)
                .dramaVOList(dramaVOList)
                .build();
    }

    @Override
    public List<DramaVO> selectAllDramaInfoList() throws Exception{

        return  dramaDAO.selectAllDramaList();
    }

    @Override
    public DramaVO selectDramaInfo(SelectDramaInfoRequestDTO selectDramaInfoRequestDTO) {

        // 단일 조회 시, left outer join 으로 조회수와 썸네일을 조회합니다.
        // 리스트 같은경우 다르게 조회하므로 selectAllDramaInfoList를 참고해주시면 됩니다.

        return dramaDAO.selectDramaInfo(selectDramaInfoRequestDTO);
    }

    @Override
    public void registerDramaInfo(InsertDramaInfoRequestDTO insertDramaInfoRequestDTO) throws Exception {


        // 단일 insert 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(dramaDAO.insertDramaInfo(insertDramaInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String webThumbnailUrl = insertDramaInfoRequestDTO.getWebThumbnailUrl();

        if(!webThumbnailUrl.isEmpty()) {

            // 썸네일을 삽입하기 위한 dto를 준비합니다.

            InsertDramaThumbnailInfoRequestDTO insertDramaThumbnailInfoRequestDTO = InsertDramaThumbnailInfoRequestDTO.builder()
                    .dramaNo(insertDramaInfoRequestDTO.getInsertedDramaNo())
                    .webThumbnailUrl(insertDramaInfoRequestDTO.getWebThumbnailUrl())
                    .build();

            // 썸네일을 삽입합니다.
            if(dramaDAO.insertDramaThumbnailInfo(insertDramaThumbnailInfoRequestDTO) != 1) {
                throw new Exception();
            }
        }
    }

    @Override
    public void modifyDramaInfo(UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO) throws Exception {

        // 단일 update 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(dramaDAO.updateDramaInfo(updateDramaInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String webThumbnailUrl = updateDramaInfoRequestDTO.getWebThumbnailUrl();

        if(!webThumbnailUrl.isEmpty()) {

            // 그리고 만약 썸네일 이 있다면 update를 하고, 없다면 insert를 합니다.
            // (사실상 말이 안되는 로직이긴 합니다, 왜냐하면 insert당시에 필수적으로 값을 밀어넣게끔 되어있기 때문에....)
            if(dramaDAO.selectDramaThumbnailImageCntByDramaNo(updateDramaInfoRequestDTO.getDramaNo()) > 0) {

                // 썸네일을 update하기 위한 dto를 준비합니다.

                UpdateDramaThumbnailInfoRequestDTO updateDramaThumbnailInfoRequestDTO = UpdateDramaThumbnailInfoRequestDTO.builder()
                        .dramaNo(updateDramaInfoRequestDTO.getDramaNo())
                        .webThumbnailUrl(updateDramaInfoRequestDTO.getWebThumbnailUrl())
                        .build();

                // 썸네일을 업데이트합니다.
                if(dramaDAO.updateDramaThumbnailInfo(updateDramaThumbnailInfoRequestDTO) != 1) {
                    throw new Exception();
                }
            } else {

                // 썸네일을 삽입하기 위한 dto를 준비합니다.
                InsertDramaThumbnailInfoRequestDTO insertDramaThumbnailInfoRequestDTO = InsertDramaThumbnailInfoRequestDTO.builder()
                        .dramaNo(updateDramaInfoRequestDTO.getDramaNo())
                        .webThumbnailUrl(updateDramaInfoRequestDTO.getWebThumbnailUrl())
                        .build();

                // 썸네일을 삽입합니다.
                if (dramaDAO.insertDramaThumbnailInfo(insertDramaThumbnailInfoRequestDTO) != 1) {
                    throw new Exception();
                }
            }
        }
    }

    @Override
    public void modifyDramaState(UpdateDramaStateRequestDTO updateDramaStateRequestDTO) throws Exception {

        if(dramaDAO.updateDramaState(updateDramaStateRequestDTO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void removeDramaInfo(Integer dramaNo) throws Exception {

        // fk 문제 때문에, fk의 맨 마지막에 걸려있는 데이터부터 삭제해야 합니다.
        if(dramaDAO.deleteDramaThumbnailInfo(dramaNo) != 1) {
            throw new Exception();
        }

        if(dramaDAO.deleteDramaInfo(dramaNo) != 1) {
            throw new Exception();
        }
    }

    @Override
    public List<RawImageThumbnailVO> selectImageThumbnailVOList(SelectDramaThumbnailImageUrlDTO selectDramaThumbnailImageUrlDTO) throws Exception {

        String dramaName = selectDramaThumbnailImageUrlDTO.getDramaName();

        JSONObject json = null;

        String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

        String SEARCH_API_URL = "https://www.googleapis.com/customsearch/v1";

        String API_KEY = "AIzaSyAw2NnlSIS-dj6-Rh-3FsvLwiUKP35b6Tc";

        String SEARCH_ENGINE_ID = "d1bf72817817d47b1";

        List<String> animeImageUrlList = new ArrayList<>();

        Connection.Response res = null;

        try {

            res = Jsoup.connect(SEARCH_API_URL + "?key=" + API_KEY + "&cx=" + SEARCH_ENGINE_ID + "&q=" + dramaName)
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
    public void registerDramaViewCnt(InsertDramaViewCntRequestDTO insertDramaViewCntRequestDTO) throws Exception {

        if(dramaDAO.insertDramaViewCnt(insertDramaViewCntRequestDTO) != 1) {
            throw new Exception();
        }
    }
}
