package com.dev.pleaseTakecareFiveDucks.book.service;

import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.InsertBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.SelectBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.SelectBookPaginationRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.UpdateBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;

import java.util.List;

public interface BookService {

    Integer selectBookTotalCnt();

    void removeAllBookInfoList();

    List<BookVO> selectBookList(SelectBookPaginationRequestDTO selectBookPaginationRequestDTO);

    List<BookVO> selectAllBookInfoList();

    BookVO selectBookInfo(SelectBookInfoRequestDTO selectBookInfoRequestDTO);

    void registerBookInfo(InsertBookInfoRequestDTO insertBookInfoRequestDTO);

    void modifyBookInfo(UpdateBookInfoRequestDTO updateBookInfoRequestDTO);

    void removeBookInfo(Integer bookNo);
}
