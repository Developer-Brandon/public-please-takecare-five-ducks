package com.dev.pleaseTakecareFiveDucks.movie.controller;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.SelectMovieThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieVO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.result.MovieListResultVO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.result.RawImageThumbnailResultVO;
import com.dev.pleaseTakecareFiveDucks.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieRestController extends BaseController {

    private final MovieService movieService;

    // 등록페이지에서 썸네일을 찾는 api입니다.
    @GetMapping(value = "/search/image/thumbnail", produces = JSON_FORMAT)
    public RawImageThumbnailResultVO selectImageThumbnailVOList(
            @RequestParam
            String movieName
    ) throws Exception {

        SelectMovieThumbnailImageUrlDTO selectmovieThumbnailImageUrlDTO = SelectMovieThumbnailImageUrlDTO.builder()
                .movieName(movieName)
                .build();

        return RawImageThumbnailResultVO.builder()
                .rawImageThumbnailVOArrayList((ArrayList<RawImageThumbnailVO>) movieService.selectImageThumbnailVOList(selectmovieThumbnailImageUrlDTO))
                .build();
    }

    // 드라마 단일 조회 api 입니다.
    @GetMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<MovieVO> selectMovieInfo(
            @RequestParam
            Integer movieNo
    ) throws Exception {

        SelectMovieInfoRequestDTO selectMovieInfoRequestDTO = SelectMovieInfoRequestDTO.builder()
                .movieNo(movieNo)
                .build();

        MovieVO movieVO = movieService.selectMovieInfo(selectMovieInfoRequestDTO);

        return ResponseEntity.ok(movieVO);
    }

    // 드라마 단일 삽입 api 입니다.
    @PostMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> insertMovieInfo(
            @RequestBody
                    InsertMovieInfoRequestDTO insertMovieInfoRequestDTO
    ) throws Exception {

        movieService.registerMovieInfo(insertMovieInfoRequestDTO);

        return ResponseEntity.ok(insertMovieInfoRequestDTO.getInsertedMovieNo());
    }

    // 드라마 단일 업데이트 api 입니다.
    @PutMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> updateMovieInfo(
            @RequestBody
                    UpdateMovieInfoRequestDTO updateMovieInfoRequestDTO
    ) throws Exception {

        movieService.modifyMovieInfo(updateMovieInfoRequestDTO);

        return ResponseEntity.ok(updateMovieInfoRequestDTO.getMovieNo());
    }

    // 드라마 단일 삭제 api 입니다.
    @DeleteMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> deleteMovieInfo(
            @RequestBody
                    DeleteMovieInfoRequestDTO deleteMovieInfoRequestDTO
    ) throws Exception {

        movieService.removeMovieInfo(deleteMovieInfoRequestDTO.getMovieNo());

        return ResponseEntity.ok(deleteMovieInfoRequestDTO.getMovieNo());
    }

    // 드라마 조회수 삽입 api 입니다.
    @PostMapping(value = "/info/view", produces = JSON_FORMAT)
    public ResponseEntity<Integer> insertMovieInfoView(
            @RequestBody
                    InsertMovieViewCntRequestDTO insertMovieViewCntRequestDTO
    ) throws Exception {

        movieService.registerMovieViewCnt(insertMovieViewCntRequestDTO);

        return ResponseEntity.ok(insertMovieViewCntRequestDTO.getMovieNo());
    }

    // (당장 사용하지 않는 api입니다) 애니 리스트만 pagination으로 json 형식으로 반환하는 api 입니다.
    @GetMapping(value = "/info/list", produces = JSON_FORMAT)
    public ResponseEntity<MovieListResultVO> selectMovieInfoList(
            @RequestParam(required = false, defaultValue = "1")
            Integer currentPage
            , @RequestParam(required = false, defaultValue = "10")
            Integer pageSize
    ) throws Exception {

        SelectMoviePaginationRequestDTO selectmoviePaginationRequestDTO = SelectMoviePaginationRequestDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();

        MovieListResultVO movieListResultVO = movieService.selectMoviePaginationList(selectmoviePaginationRequestDTO);

        return ResponseEntity.ok(movieListResultVO);
    }
}
