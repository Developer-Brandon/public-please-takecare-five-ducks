package com.dev.pleaseTakecareFiveDucks.book.service;

import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;

import java.util.List;

public interface BookService {

    Integer selectBookTotalCnt();

    void removeAllBookInfoList() throws Exception;

    List<BookVO> selectBookList(SelectBookPaginationRequestDTO selectBookPaginationRequestDTO);

    List<BookVO> selectAllBookInfoList();

    BookVO selectBookInfo(SelectBookInfoRequestDTO selectBookInfoRequestDTO);

    void registerBookInfo(InsertBookInfoRequestDTO insertBookInfoRequestDTO) throws Exception;

    void modifyBookInfo(UpdateBookInfoRequestDTO updateBookInfoRequestDTO) throws Exception;

    void removeBookInfo(RemoveBookInfoRequestDTO removeBookInfoRequestDTO) throws Exception;
}
