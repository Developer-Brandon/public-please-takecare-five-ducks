package com.dev.pleaseTakecareFiveDucks.anime.controller;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.SelectAnimeThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.RawAnimeFinalizedResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.RawImageThumbnailResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.service.AnimeService;
import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
import com.dev.pleaseTakecareFiveDucks.config.VoidResponse;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/anime")
@RequiredArgsConstructor
public class AnimeRestController extends BaseController {

    private final AnimeService animeService;

    private final ContentsMadeNatureService contentsMadeNatureService;

    @GetMapping(value = "/search/image/thumbnail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @GetMapping(value = "/finalized/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RawAnimeFinalizedResultVO selectAnimeFinalizedList(
    ) throws Exception {

        return RawAnimeFinalizedResultVO.builder()
                .finalizedYnEnumArrayList((ArrayList<FinalizedYnEnum>) animeService.selectAnimeFinalizedList())
                .build();
    }

    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @PostMapping(value = "/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Integer> insertAnimeInfo(
            @RequestBody
                    InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO
    ) throws Exception {

        animeService.registerAnimeInfo(insertAnimeInfoRequestDTO);

        return ResponseEntity.ok(insertAnimeInfoRequestDTO.getInsertedAnimeNo());
    }

    @PutMapping(value = "/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Integer> updateAnimeInfo(
            @RequestBody
            UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO
    ) throws Exception {

        animeService.modifyAnimeInfo(updateAnimeInfoRequestDTO);

        return ResponseEntity.ok(updateAnimeInfoRequestDTO.getAnimeNo());
    }
}
