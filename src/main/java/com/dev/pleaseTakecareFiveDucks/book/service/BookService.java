package com.dev.pleaseTakecareFiveDucks.book.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.SelectBookThumbnailDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookTypeVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.result.BookListResultVO;

import java.util.List;

public interface BookService {

    List<BookTypeVO> selectBookTypeList() throws Exception;

    Integer selectBookTotalCnt();

    void removeAllBookInfoList() throws Exception;

    BookListResultVO selectBookPaginationList(SelectBookPaginationRequestDTO selectBookPaginationRequestDTO);

    List<BookVO> selectAllBookInfoList();

    BookVO selectBookInfo(SelectBookInfoRequestDTO selectBookInfoRequestDTO);

    void registerBookInfo(InsertBookInfoRequestDTO insertBookInfoRequestDTO) throws Exception;

    void modifyBookInfo(UpdateBookInfoRequestDTO updateBookInfoRequestDTO) throws Exception;

    void removeBookInfo(DeleteBookInfoRequestDTO deleteBookInfoRequestDTO) throws Exception;

    List<RawImageThumbnailVO> selectImageThumbnailVOList(SelectBookThumbnailDTO selectBookThumbnailDTO) throws Exception;

    void registerBookViewCnt(InsertBookViewCntRequestDTO insertBookViewCntRequestDTO) throws Exception;
}
