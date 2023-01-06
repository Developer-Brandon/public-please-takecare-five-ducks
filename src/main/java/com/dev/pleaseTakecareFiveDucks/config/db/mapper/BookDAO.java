package com.dev.pleaseTakecareFiveDucks.config.db.mapper;


import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.MainBookVO;

import java.util.List;

public interface BookDAO {

    List<MainBookVO> selectMainBookList();

    int getBookTotalCnt();

    int deleteAll();

    List<BookVO> selectAllBookList();

    List<BookVO> selectBookList(SelectBookPaginationRequestDTO selectBookPaginationRequestDTO);

    BookVO selectBookInfo(SelectBookInfoRequestDTO selectBookInfoRequestDTO);

    int insertBookViewCnt(InsertBookViewCntRequestDTO insertBookViewCntRequestDTO);

    int insertBookInfo(InsertBookInfoRequestDTO insertBookInfoRequestDTO);

    int insertBookThumbnailInfo(InsertBookThumbnailInfoRequestDTO insertBookThumbnailInfoRequestDTO);

    int updateBookInfo(UpdateBookInfoRequestDTO updateBookInfoRequestDTO);

    int updateBookThumbnailInfo(UpdateBookThumbnailInfoRequestDTO updateBookThumbnailInfoRequestDTO);

    int updateBookState(UpdateBookStateRequestDTO updateBookStateRequestDTO);

    int deleteBookInfo(Integer bookNo);

    int deleteBookThumbnailInfo(Integer bookNo);
}
