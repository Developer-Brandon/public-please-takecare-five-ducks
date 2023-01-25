package com.dev.pleaseTakecareFiveDucks.book.controller;

import com.dev.pleaseTakecareFiveDucks.book.domain.dto.SelectBookThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.result.BookListResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.RawImageThumbnailResultVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.SelectBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookTypeVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.result.RawBookTypeResultVO;
import com.dev.pleaseTakecareFiveDucks.book.service.BookService;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    @GetMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<BookVO> selectBookInfo(
            @RequestParam
                    Integer bookNo
    ) throws Exception {

        SelectBookInfoRequestDTO BookInfoRequestDTO = SelectBookInfoRequestDTO.builder()
                .bookNo(bookNo)
                .build();

        BookVO BookVO = bookService.selectBookInfo(BookInfoRequestDTO);

        return ResponseEntity.ok(BookVO);
    }

    // 책 단일 삽입 api 입니다.
    @PostMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> insertBookInfo(
            @RequestBody
                    InsertBookInfoRequestDTO insertBookInfoRequestDTO
    ) throws Exception {

        bookService.registerBookInfo(insertBookInfoRequestDTO);

        return ResponseEntity.ok(insertBookInfoRequestDTO.getInsertedBookNo());
    }

    // 책 단일 업데이트 api 입니다.
    @PutMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> updateBookInfo(
            @RequestBody
                    UpdateBookInfoRequestDTO updateBookInfoRequestDTO
    ) throws Exception {

        bookService.modifyBookInfo(updateBookInfoRequestDTO);

        return ResponseEntity.ok(updateBookInfoRequestDTO.getBookNo());
    }

    // 책 단일 삭제 api 입니다.
    @DeleteMapping(value = "/info", produces = JSON_FORMAT)
    public ResponseEntity<Integer> deleteBookInfo(
            @RequestBody
            DeleteBookInfoRequestDTO deleteBookInfoRequestDTO
    ) throws Exception {

        bookService.removeBookInfo(deleteBookInfoRequestDTO);

        return ResponseEntity.ok(deleteBookInfoRequestDTO.getBookNo());
    }

    // 책 조회수 삽입 api 입니다.
    @PostMapping(value = "/info/view", produces = JSON_FORMAT)
    public ResponseEntity<Integer> insertBookInfoView(
            @RequestBody
                    InsertBookViewCntRequestDTO insertBookViewCntRequestDTO
    ) throws Exception {

        bookService.registerBookViewCnt(insertBookViewCntRequestDTO);

        return ResponseEntity.ok(insertBookViewCntRequestDTO.getBookNo());
    }

    // (당장 사용하지 않는 api입니다) 책 리스트만 pagination으로 json 형식으로 반환하는 api 입니다.
    @GetMapping(value = "/info/list", produces = JSON_FORMAT)
    public ResponseEntity<BookListResultVO> selectBookInfoList(
            @RequestParam(required = false, defaultValue = "1")
                    Integer currentPage
            , @RequestParam(required = false, defaultValue = "10")
                    Integer pageSize
    ) throws Exception {

        SelectBookPaginationRequestDTO selectBookPaginationRequestDTO = SelectBookPaginationRequestDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();

        BookListResultVO BookListResultVO = bookService.selectBookPaginationList(selectBookPaginationRequestDTO);

        return ResponseEntity.ok(BookListResultVO);
    }
}
