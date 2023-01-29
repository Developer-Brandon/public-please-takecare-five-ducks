package com.dev.pleaseTakecareFiveDucks.drama.controller;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.SelectDramaThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.result.RawImageThumbnailResultVO;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaVO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.result.DramaListResultVO;
import com.dev.pleaseTakecareFiveDucks.drama.service.DramaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/drama")
@RequiredArgsConstructor
public class DramaRestController extends BaseController {

    private final DramaService dramaService;

    private final ContentsMadeNatureService contentsMadeNatureService;

    // 등록페이지에서 썸네일을 찾는 api입니다.
    @GetMapping(value = "/search/image/thumbnail", produces = JSON_FORMAT)
    public RawImageThumbnailResultVO selectImageThumbnailVOList(
            @RequestParam
            String dramaName
    ) throws Exception {

        SelectDramaThumbnailImageUrlDTO selectDramaThumbnailImageUrlDTO = SelectDramaThumbnailImageUrlDTO.builder()
                .dramaName(dramaName)
                .build();

        return RawImageThumbnailResultVO.builder()
                .rawImageThumbnailVOArrayList((ArrayList<RawImageThumbnailVO>) dramaService.selectImageThumbnailVOList(selectDramaThumbnailImageUrlDTO))
                .build();
    }

    // 드라마 단일 조회 api 입니다.
    @GetMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<DramaVO> selectDramaInfo(
            @RequestParam
            Integer dramaNo
    ) throws Exception {

        SelectDramaInfoRequestDTO selectDramaInfoRequestDTO = SelectDramaInfoRequestDTO.builder()
                .dramaNo(dramaNo)
                .build();

        DramaVO dramaVO = dramaService.selectDramaInfo(selectDramaInfoRequestDTO);

        return ResponseEntity.ok(dramaVO);
    }

    // 드라마 단일 삽입 api 입니다.
    @PostMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> insertDramaInfo(
            @RequestBody
            InsertDramaInfoRequestDTO insertDramaInfoRequestDTO
    ) throws Exception {

        dramaService.registerDramaInfo(insertDramaInfoRequestDTO);

        return ResponseEntity.ok(insertDramaInfoRequestDTO.getInsertedDramaNo());
    }

    // 드라마 단일 업데이트 api 입니다.
    @PutMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> updateDramaInfo(
            @RequestBody
            UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO
    ) throws Exception {

        dramaService.modifyDramaInfo(updateDramaInfoRequestDTO);

        return ResponseEntity.ok(updateDramaInfoRequestDTO.getDramaNo());
    }

    // 드라마 단일 삭제 api 입니다.
    @DeleteMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> deleteDramaInfo(
            @RequestBody
            DeleteDramaInfoRequestDTO deleteDramaInfoRequestDTO
    ) throws Exception {

        dramaService.removeDramaInfo(deleteDramaInfoRequestDTO.getDramaNo());

        return ResponseEntity.ok(deleteDramaInfoRequestDTO.getDramaNo());
    }

    // 드라마 조회수 삽입 api 입니다.
    @PostMapping(value = "/info/view", produces = JSON_FORMAT)
    public ResponseEntity<Integer> insertDramaInfoView(
            @RequestBody
            InsertDramaViewCntRequestDTO insertDramaViewCntRequestDTO
    ) throws Exception {

        dramaService.registerDramaViewCnt(insertDramaViewCntRequestDTO);

        return ResponseEntity.ok(insertDramaViewCntRequestDTO.getDramaNo());
    }

    // (당장 사용하지 않는 api입니다) 애니 리스트만 pagination으로 json 형식으로 반환하는 api 입니다.
    @GetMapping(value = "/info/list", produces = JSON_FORMAT)
    public ResponseEntity<DramaListResultVO> selectDramaInfoList(
            @RequestParam(required = false, defaultValue = "1")
            Integer currentPage
            , @RequestParam(required = false, defaultValue = "10")
            Integer pageSize
    ) throws Exception {

        SelectDramaPaginationRequestDTO selectDramaPaginationRequestDTO = SelectDramaPaginationRequestDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();

        DramaListResultVO dramaListResultVO = dramaService.selectDramaPaginationList(selectDramaPaginationRequestDTO);

        return ResponseEntity.ok(dramaListResultVO);
    }
}
