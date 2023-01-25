package com.dev.pleaseTakecareFiveDucks.book.controller;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.SelectAnimeThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.RawImageThumbnailResultVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.SelectBookThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookTypeVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.result.RawBookTypeResultVO;
import com.dev.pleaseTakecareFiveDucks.book.service.BookService;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookRestController extends BaseController {

    private final BookService bookService;

    private final ContentsMadeNatureService contentsMadeNatureService;

    // 등록페이지에서 썸네일을 찾는 api입니다.
    @GetMapping(value = "/search/image/thumbnail", produces = JSON_FORMAT)
    public RawImageThumbnailResultVO selectImageThumbnailList(
            @RequestParam
                    String bookName
    ) throws Exception {

        SelectBookThumbnailImageUrlDTO selectBookThumbnailImageUrlDTO = SelectBookThumbnailImageUrlDTO.builder()
                .bookName(bookName)
                .build();

        return RawImageThumbnailResultVO.builder()
                .rawImageThumbnailVOArrayList((ArrayList<RawImageThumbnailVO>) bookService.selectImageThumbnailVOList(selectBookThumbnailImageUrlDTO))
                .build();
    }

    // 책의 종류에 대해서 조회하는 api입니다.
    @GetMapping(value = "/type/list", produces = JSON_FORMAT)
    public RawBookTypeResultVO selectBookTypeList(
    ) throws Exception {

        return RawBookTypeResultVO.builder()
                .bookTypeVOArrayList((ArrayList<BookTypeVO>) bookService.selectBookTypeList())
                .build();
    }

    // 책 단일 조회 api 입니다.

    // 책 단일 삽입 api 입니다.

    // 책 단일 업데이트 api 입니다.

    // 책 단일 삭제 api 입니다.

    // 책 조회수 삽입 api 입니다.

    // (당장 사용하지 않는 api입니다) 책 리스트만 pagination으로 json 형식으로 반환하는 api 입니다.

}
