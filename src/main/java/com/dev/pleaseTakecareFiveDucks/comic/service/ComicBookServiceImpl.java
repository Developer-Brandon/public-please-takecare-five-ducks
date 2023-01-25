package com.dev.pleaseTakecareFiveDucks.comic.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.SelectBookThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.result.BookListResultVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.result.ComicBookListResultVO;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.ComicBookDAO;
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
public class ComicBookServiceImpl implements ComicBookService{

    private final ComicBookDAO comicBookDAO;

    @Override
    public Integer selectComicBookTotalCnt() {
        return comicBookDAO.getComicBookTotalCnt();
    }

    @Override
    public void removeAllComicBookInfoList() throws Exception {

        // 만약 comicBookTotalCnt 0개 초과로 있다면...
        int comicBookTotalCnt = comicBookDAO.getComicBookTotalCnt();

        if(comicBookTotalCnt > 0) {

            // 실제 삭제가 된 데이터들이 size와 일치해야 합니다.
            int removedBookCnt = comicBookDAO.deleteAll();

            // 만약, 일치하지 않는다면 Exception을 throw 합니다.
            if(comicBookTotalCnt != removedBookCnt) {
                throw new Exception();
            }
        }
    }

    @Override
    public ComicBookListResultVO selectComicBookPaginationList(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO) {

        int totalCnt = comicBookDAO.getTotalCntByCondition(selectComicBookPaginationRequestDTO);

        PageHandler pageHandler = new PageHandler(totalCnt, selectComicBookPaginationRequestDTO.getCurrentPage(), selectComicBookPaginationRequestDTO.getTitle());

        // 만약 현재의 페이지가.. 1 -> 0, 2 -> 1, 3 -> 2, 4 -> 3 대로 오프셋 설정
        Integer offset = selectComicBookPaginationRequestDTO.getCurrentPage() - 1;

        // PageSize는 DTO에서 기본으로 10으로 처리되어 있습니다.
        selectComicBookPaginationRequestDTO.setOffset(offset * selectComicBookPaginationRequestDTO.getPageSize());

        selectComicBookPaginationRequestDTO.setPageSize(selectComicBookPaginationRequestDTO.getPageSize());

        List<ComicBookVO> comicBookVOList = comicBookDAO.selectComicBookPaginationList(selectComicBookPaginationRequestDTO);

        return ComicBookListResultVO.builder()
                .pageHandler(pageHandler)
                .comicBookVOList(comicBookVOList)
                .build();
    }

    @Override
    public List<ComicBookVO> selectAllComicBookInfoList() {

        return comicBookDAO.selectAllComicBookList();
    }

    @Override
    public ComicBookVO selectComicBookInfo(SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO) {

        return comicBookDAO.selectComicBookInfo(selectComicBookInfoRequestDTO);
    }

    @Override
    public void registerComicBookInfo(InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO) throws Exception {

        // 단일 insert 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(comicBookDAO.insertComicBookInfo(insertComicBookInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String webThumbnailUrl = insertComicBookInfoRequestDTO.getWebThumbnailUrl();

        if(!webThumbnailUrl.isEmpty()) {

            // 썸네일을 삽입하기 위한 dto를 준비합니다.
            InsertComicBookThumbnailInfoRequestDTO insertComicBookThumbnailInfoRequestDTO = InsertComicBookThumbnailInfoRequestDTO.builder()
                    .bookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo())
                    .webThumbnailUrl(webThumbnailUrl)
                    .build();

            // 썸네일을 삽입합니다.
            if(comicBookDAO.insertComicBookThumbnailInfo(insertComicBookThumbnailInfoRequestDTO) != 1) {
                throw new Exception();
            }
        }
    }

    @Override
    public void modifyComicBookInfo(UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO) throws Exception {

        // 단일 update 이므로, 한개가 insert되지 않으면 excpetion을 일으킵니다.
        if(comicBookDAO.updateComicBookInfo(updateComicBookInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면....
        String webThumbnailUrl = updateComicBookInfoRequestDTO.getWebThumbnailUrl();

        if(!webThumbnailUrl.isEmpty()) {

            // 그리고 만약 썸네일 이 있다면 update를 하고, 없다면 insert를 합니다.
            // (사실상 말이 안되는 로직이긴 합니다, 왜냐하면 insert당시에 필수적으로 값을 밀어넣게끔 되어있기 때문에....)
            if(comicBookDAO.selectComicBookThumbnailImageCntByBookNo(updateComicBookInfoRequestDTO.getBookNo()) > 0) {

                // 썸네일을 update하기 위한 dto를 준비합니다.
                UpdateComicBookThumbnailInfoRequestDTO updateComicBookThumbnailInfoRequestDTO = UpdateComicBookThumbnailInfoRequestDTO.builder()
                        .bookNo(updateComicBookInfoRequestDTO.getBookNo())
                        .webThumbnailUrl(updateComicBookInfoRequestDTO.getWebThumbnailUrl())
                        .build();

                // 썸네일을 업데이트합니다.
                if (comicBookDAO.updateComicBookThumbnailInfo(updateComicBookThumbnailInfoRequestDTO) != 1) {
                    throw new Exception();
                }
            } else {

                // 썸네일을 삽입하기 위한 dto를 준비합니다.
                InsertComicBookThumbnailInfoRequestDTO insertComicBookThumbnailInfoRequestDTO = InsertComicBookThumbnailInfoRequestDTO.builder()
                        .bookNo(updateComicBookInfoRequestDTO.getBookNo())
                        .webThumbnailUrl(updateComicBookInfoRequestDTO.getWebThumbnailUrl())
                        .build();

                // 썸네일을 삽입합니다.
                if (comicBookDAO.insertComicBookThumbnailInfo(insertComicBookThumbnailInfoRequestDTO) != 1) {
                    throw new Exception();
                }
            }
        }
    }

    @Override
    public void removeComicBookInfo(DeleteComicBookInfoRequestDTO deleteComicBookInfoRequestDTO) throws Exception {

        // fk 문제 때문에, fk의 맨 마지막에 걸려있는 데이터부터 삭제해야 합니다.
        if(comicBookDAO.deleteComicBookInfo(deleteComicBookInfoRequestDTO.getBookNo()) != 1) {
            throw new Exception();
        }

        if(comicBookDAO.deleteComicBookThumbnailInfo(deleteComicBookInfoRequestDTO.getBookNo()) != 1) {
            throw new Exception();
        }

    }

    @Override
    public List<RawImageThumbnailVO> selectImageThumbnailVOList(SelectBookThumbnailImageUrlDTO selectBookThumbnailImageUrlDTO) throws Exception {

        String comicBookName = selectBookThumbnailImageUrlDTO.getBookName();

        JSONObject json = null;

        String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

        String SEARCH_API_URL = "https://www.googleapis.com/customsearch/v1";

        String API_KEY = "AIzaSyAw2NnlSIS-dj6-Rh-3FsvLwiUKP35b6Tc";

        String SEARCH_ENGINE_ID = "d1bf72817817d47b1";

        List<String> animeImageUrlList = new ArrayList<>();

        Connection.Response res = null;

        try {

            res = Jsoup.connect(SEARCH_API_URL + "?key=" + API_KEY + "&cx=" + SEARCH_ENGINE_ID + "&q=" + comicBookName)
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
    public void registerComicBookViewCnt(InsertComicBookViewCntRequestDTO insertComicBookViewCntRequestDTO) throws Exception {

        if(comicBookDAO.insertComicBookViewCnt(insertComicBookViewCntRequestDTO) != 1) {
            throw new Exception();
        }
    }
}
