package com.dev.pleaseTakecareFiveDucks.config.db.mapper;


import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;
import java.util.List;

public interface BookMapper {

    int getBookTotalCnt();

    int deleteAll();

    List<BookVO> selectAllBookList();

    List<BookVO> selectBookList(SelectBookPaginationRequestDTO selectBookPaginationRequestDTO);

    BookVO selectBookInfo(SelectBookInfoRequestDTO selectBookInfoRequestDTO);

    Integer insertBookInfo(InsertBookInfoRequestDTO insertBookInfoRequestDTO);

    int updateBookInfo(UpdateBookInfoRequestDTO updateBookInfoRequestDTO);

    int updateBookState(UpdateBookStateRequestDTO updateBookStateRequestDTO);

    int deleteBookInfo(Integer bookNo);
}
