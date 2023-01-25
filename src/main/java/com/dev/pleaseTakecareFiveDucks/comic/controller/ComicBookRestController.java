package com.dev.pleaseTakecareFiveDucks.comic.controller;

import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.SelectComicBookThumbnailDTO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.result.ComicBookListResultVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.result.RawImageThumbnailResultVO;
import com.dev.pleaseTakecareFiveDucks.comic.service.ComicBookService;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comic")
@RequiredArgsConstructor
public class ComicBookRestController extends BaseController {

    private final ComicBookService comicBookService;

    private final ContentsMadeNatureService contentsMadeNatureService;

    // 등록페이지에서 썸네일을 찾는 api입니다.
    @GetMapping(value = "/search/image/thumbnail", produces = JSON_FORMAT)
    public RawImageThumbnailResultVO selectImageThumbnailVOList(
            @RequestParam
            String comicBookName
    ) throws Exception {

        SelectComicBookThumbnailDTO selectComicBookThumbnailDTO = SelectComicBookThumbnailDTO.builder()
                .comicBookName(comicBookName)
                .build();

        return RawImageThumbnailResultVO.builder()
                .rawImageThumbnailVOArrayList(comicBookService.selectImageThumbnailVOList(selectComicBookThumbnailDTO))
                .build();
    }

    // 만화책 단일 조회 api 입니다.
    @GetMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<ComicBookVO> selectComicBookInfo(
            @RequestParam
            Integer bookNo
    ) throws Exception {

        SelectComicBookInfoRequestDTO comicBookInfoRequestDTO = SelectComicBookInfoRequestDTO.builder()
                .bookNo(bookNo)
                .build();

        ComicBookVO comicBookVO = comicBookService.selectComicBookInfo(comicBookInfoRequestDTO);

        return ResponseEntity.ok(comicBookVO);
    }

    // 만화책 단일 삽입 api 입니다.
    @PostMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> insertComicBookInfo(
            @RequestBody
                    InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO
    ) throws Exception {

        comicBookService.registerComicBookInfo(insertComicBookInfoRequestDTO);

        return ResponseEntity.ok(insertComicBookInfoRequestDTO.getInsertedComicBookNo());
    }

    // 만화책 단일 업데이트 api 입니다.
    @PutMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> updateComicBookInfo(
            @RequestBody
            UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO
    ) throws Exception {

        comicBookService.modifyComicBookInfo(updateComicBookInfoRequestDTO);

        return ResponseEntity.ok(updateComicBookInfoRequestDTO.getBookNo());
    }

    // 만화책 단일 삭제 api 입니다.
    @DeleteMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> deleteComicBookInfo(
            @RequestBody
            DeleteComicBookInfoRequestDTO deleteComicBookInfoRequestDTO
    ) throws Exception {

        comicBookService.removeComicBookInfo(deleteComicBookInfoRequestDTO);

        return ResponseEntity.ok(deleteComicBookInfoRequestDTO.getBookNo());
    }

    // 만화책 조회수 삽입 api 입니다.
    @PostMapping(value = "/info/view", produces = JSON_FORMAT)
    public ResponseEntity<Integer> insertComicBookInfoView(
            @RequestBody
                    InsertComicBookViewCntRequestDTO insertComicBookViewCntRequestDTO
    ) throws Exception {

        comicBookService.registerComicBookViewCnt(insertComicBookViewCntRequestDTO);

        return ResponseEntity.ok(insertComicBookViewCntRequestDTO.getBookNo());
    }

    // (당장 사용하지 않는 api입니다) 애니 리스트만 pagination으로 json 형식으로 반환하는 api 입니다.
    @GetMapping(value = "/info/list", produces = JSON_FORMAT)
    public ResponseEntity<ComicBookListResultVO> selectComicBookInfoList(
            @RequestParam(required = false, defaultValue = "1")
                    Integer currentPage
            , @RequestParam(required = false, defaultValue = "10")
                    Integer pageSize
    ) throws Exception {

        SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO = SelectComicBookPaginationRequestDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();

        ComicBookListResultVO comicBookListResultVO = comicBookService.selectComicBookPaginationList(selectComicBookPaginationRequestDTO);

        return ResponseEntity.ok(comicBookListResultVO);
    }
}
