package com.dev.pleaseTakecareFiveDucks.anime.controller;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.SelectAnimeThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.AnimeListResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.RawAnimeFinalizedResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.RawImageThumbnailResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.service.AnimeService;
import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/anime")
@RequiredArgsConstructor
public class AnimeRestController extends BaseController {

    private final AnimeService animeService;

    private final ContentsMadeNatureService contentsMadeNatureService;

    @GetMapping(value = "/search/image/thumbnail", produces = JSON_FORMAT)
    public RawImageThumbnailResultVO selectImageThumbnailVOList(
            @RequestParam
            String animeName
    ) throws Exception {

        SelectAnimeThumbnailImageUrlDTO selectAnimeThumbnailImageUrlDTO = SelectAnimeThumbnailImageUrlDTO.builder()
                .animeName(animeName)
                .build();

        return RawImageThumbnailResultVO.builder()
                .rawImageThumbnailVOArrayList((ArrayList<RawImageThumbnailVO>) animeService.selectImageThumbnailVOList(selectAnimeThumbnailImageUrlDTO))
                .build();
    }

    @GetMapping(value = "/finalized/list", produces = JSON_FORMAT)
    public RawAnimeFinalizedResultVO selectAnimeFinalizedList(
    ) throws Exception {

        return RawAnimeFinalizedResultVO.builder()
                .finalizedYnEnumArrayList((ArrayList<FinalizedYnEnum>) animeService.selectAnimeFinalizedList())
                .build();
    }

    // 애니 단일 조회 api 입니다.
    @GetMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<AnimeVO> selectAnimeInfo(
            @RequestParam
            Integer animeNo
    ) throws Exception {

        SelectAnimeInfoRequestDTO animeInfoRequestDTO = SelectAnimeInfoRequestDTO.builder()
                .animeNo(animeNo)
                .build();

        AnimeVO animeVO = animeService.selectAnimeInfo(animeInfoRequestDTO);

        return ResponseEntity.ok(animeVO);
    }

    // 애니 단일 삽입 api 입니다.
    @PostMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> insertAnimeInfo(
            @RequestBody
                    InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO
    ) throws Exception {

        animeService.registerAnimeInfo(insertAnimeInfoRequestDTO);

        return ResponseEntity.ok(insertAnimeInfoRequestDTO.getInsertedAnimeNo());
    }

    // 애니 단일 업데이트 api 입니다.
    @PutMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> updateAnimeInfo(
            @RequestBody
            UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO
    ) throws Exception {

        animeService.modifyAnimeInfo(updateAnimeInfoRequestDTO);

        return ResponseEntity.ok(updateAnimeInfoRequestDTO.getAnimeNo());
    }

    // 애니 단일 삭제 api 입니다.
    @DeleteMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> deleteAnimeInfo(
            @RequestBody
            DeleteAnimeInfoRequestDTO deleteComicBookInfoRequestDTO
    ) throws Exception {

        animeService.removeAnimeInfo(deleteComicBookInfoRequestDTO);

        return ResponseEntity.ok(deleteComicBookInfoRequestDTO.getAnimeNo());
    }

    // 애니 리스트만 json형식으로 불러오는 api 입니다.
    @GetMapping(value = "/info/list", produces = JSON_FORMAT)
    public ResponseEntity<AnimeListResultVO> getNoticeList(
            @RequestParam(required = false, defaultValue = "1")
                    Integer currentPage
            , @RequestParam(required = false, defaultValue = "10")
                    Integer pageSize
    ) throws Exception {

        SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO = SelectAnimePaginationRequestDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();

        AnimeListResultVO animeListResultVO = animeService.selectAnimePaginationList(selectAnimePaginationRequestDTO);

        return ResponseEntity.ok(animeListResultVO);
    }

}
