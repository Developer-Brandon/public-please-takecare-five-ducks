package com.dev.pleaseTakecareFiveDucks.movie.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.MovieDAO;
import com.dev.pleaseTakecareFiveDucks.config.util.PageHandler;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.SelectMovieThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieVO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.result.MovieListResultVO;
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
public class MovieServiceImpl implements MovieService {

    private final MovieDAO movieDAO;

    @Override
    public Integer selectMovieTotalCnt() {
        return movieDAO.getMovieTotalCnt();
    }

    @Override
    public void removeAllMovieInfoList() throws Exception {

        // 만약 movie가 0개 초과로 있다면....
        int movieCnt = movieDAO.getMovieTotalCnt();

        if (movieCnt > 0) {

            // 실제 삭제가 된 데이터들이 size와 일치해야 합니다.
            int removedMovieCnt = movieDAO.deleteAll();

            // 만약, 일치하지 않는다면 Exception을 throw 합니다.
            if (movieCnt != removedMovieCnt) {
                throw new Exception();
            }
        }
    }

    @Override
    public MovieListResultVO selectMoviePaginationList(SelectMoviePaginationRequestDTO selectMoviePaginationRequestDTO) throws Exception {

        int totalCnt = movieDAO.getTotalCntByCondition(selectMoviePaginationRequestDTO);

        PageHandler pageHandler = new PageHandler(totalCnt, selectMoviePaginationRequestDTO.getCurrentPage(), selectMoviePaginationRequestDTO.getTitle());

        // 만약 현재의 페이지가.. 1 -> 0, 2 -> 1, 3 -> 2, 4 -> 3 대로 오프셋 설정
        Integer offset = selectMoviePaginationRequestDTO.getCurrentPage() - 1;

        // PageSize는 DTO에서 기본으로 10으로 처리되어 있습니다.
        selectMoviePaginationRequestDTO.setOffset(offset * selectMoviePaginationRequestDTO.getPageSize());

        selectMoviePaginationRequestDTO.setPageSize(selectMoviePaginationRequestDTO.getPageSize());

        List<MovieVO> movieVOList = movieDAO.selectMoviePaginationList(selectMoviePaginationRequestDTO);

        return MovieListResultVO.builder()
                .pageHandler(pageHandler)
                .movieVOList(movieVOList)
                .build();
    }

    @Override
    public List<MovieVO> selectAllMovieInfoList() {

        return movieDAO.selectAllMovieList();
    }

    @Override
    public MovieVO selectMovieInfo(SelectMovieInfoRequestDTO selectMovieInfoRequestDTO) {

        // 단일 조회 시, left outer join 으로 조회수와 썸네일을 조회합니다.
        // 리스트 같은경우 다르게 조회하므로 selectAllMovieInfoList를 참고해주시면 됩니다.

        return movieDAO.selectMovieInfo(selectMovieInfoRequestDTO);
    }

    @Override
    public void registerMovieInfo(InsertMovieInfoRequestDTO insertMovieInfoRequestDTO) throws Exception {


        // 단일 insert 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if (movieDAO.insertMovieInfo(insertMovieInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String webThumbnailUrl = insertMovieInfoRequestDTO.getWebThumbnailUrl();

        if (!webThumbnailUrl.isEmpty()) {

            // 썸네일을 삽입하기 위한 dto를 준비합니다.

            InsertMovieThumbnailInfoRequestDTO insertMovieThumbnailInfoRequestDTO = InsertMovieThumbnailInfoRequestDTO.builder()
                    .movieNo(insertMovieInfoRequestDTO.getInsertedMovieNo())
                    .webThumbnailUrl(webThumbnailUrl)
                    .build();

            // 썸네일을 삽입합니다.
            if (movieDAO.insertMovieThumbnailInfo(insertMovieThumbnailInfoRequestDTO) != 1) {
                throw new Exception();
            }
        }
    }

    @Override
    public void modifyMovieInfo(UpdateMovieInfoRequestDTO updateMovieInfoRequestDTO) throws Exception {

        // 단일 update 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if (movieDAO.updateMovieInfo(updateMovieInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String webThumbnailUrl = updateMovieInfoRequestDTO.getWebThumbnailUrl();

        if (!webThumbnailUrl.isEmpty()) {

            if (movieDAO.selectMovieThumbnailImageCntByMovieNo(updateMovieInfoRequestDTO.getMovieNo()) > 0) {

                // 썸네일을 update하기 위한 dto를 준비합니다.
                UpdateMovieThumbnailInfoRequestDTO updateMovieThumbnailInfoRequestDTO = UpdateMovieThumbnailInfoRequestDTO.builder()
                        .movieNo(updateMovieInfoRequestDTO.getMovieNo())
                        .webThumbnailUrl(webThumbnailUrl)
                        .build();

                // 썸네일을 삽입합니다.
                if (movieDAO.updateMovieThumbnailInfo(updateMovieThumbnailInfoRequestDTO) != 1) {
                    throw new Exception();
                }
            } else {

                // 썸네일을 삽입하기 위한 dto를 준비합니다.
                InsertMovieThumbnailInfoRequestDTO insertMovieThumbnailInfoRequestDTO = InsertMovieThumbnailInfoRequestDTO.builder()
                        .movieNo(updateMovieInfoRequestDTO.getMovieNo())
                        .webThumbnailUrl(updateMovieInfoRequestDTO.getWebThumbnailUrl())
                        .build();

                // 썸네일을 삽입합니다.
                if (movieDAO.insertMovieThumbnailInfo(insertMovieThumbnailInfoRequestDTO) != 1) {
                    throw new Exception();
                }
            }
        }
    }

    @Override
    public void modifyMovieState(UpdateMovieStateRequestDTO updateMovieStateRequestDTO) throws Exception {

        if (movieDAO.updateMovieState(updateMovieStateRequestDTO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void removeMovieInfo(Integer movieNo) throws Exception {

        // fk 문제 때문에, fk의 맨 마지막에 걸려있는 데이터부터 삭제해야 합니다.
        if (movieDAO.deleteMovieThumbnailInfo(movieNo) != 1) {
            throw new Exception();
        }

        if (movieDAO.deleteMovieInfo(movieNo) != 1) {
            throw new Exception();
        }

    }

    @Override
    public List<RawImageThumbnailVO> selectImageThumbnailVOList(SelectMovieThumbnailImageUrlDTO selectMovieThumbnailImageUrlDTO) throws Exception {

        String movieName = selectMovieThumbnailImageUrlDTO.getMovieName();

        JSONObject json = null;

        String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

        String SEARCH_API_URL = "https://www.googleapis.com/customsearch/v1";

        String API_KEY = "AIzaSyAw2NnlSIS-dj6-Rh-3FsvLwiUKP35b6Tc";

        String SEARCH_ENGINE_ID = "d1bf72817817d47b1";

        List<String> animeImageUrlList = new ArrayList<>();

        Connection.Response res = null;

        try {

            res = Jsoup.connect(SEARCH_API_URL + "?key=" + API_KEY + "&cx=" + SEARCH_ENGINE_ID + "&q=" + movieName)
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

                    if (jsonObject.has("cse_thumbnail")) {

                        JSONArray cseThumbnail = jsonObject.getJSONArray("cse_thumbnail");

                        for (Integer i = 0; i < cseThumbnail.length(); i++) {

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
    public void registerMovieViewCnt(InsertMovieViewCntRequestDTO insertMovieViewCntRequestDTO) throws Exception {

        if (movieDAO.insertMovieViewCnt(insertMovieViewCntRequestDTO) != 1) {
            throw new Exception();
        }
    }
}
