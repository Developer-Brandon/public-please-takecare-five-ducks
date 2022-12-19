package com.dev.pleaseTakecareFiveDucks.book.service;

import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.BookDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookDAO bookDAO;

    @Override
    public Integer selectBookTotalCnt() {
        return bookDAO.getBookTotalCnt();
    }

    @Override
    public void removeAllBookInfoList() throws Exception{

        // 만약 book 0개 초과로 있다면...
        int bookCnt = bookDAO.getBookTotalCnt();

        if(bookCnt > 0) {

            // 실제 삭제가 된 데이터들이 size와 일치해야 합니다.
            int removedBookCnt = bookDAO.deleteAll();

            // 만약, 일치하지 않는다면 Exception을 throw 합니다.
            if(bookCnt != removedBookCnt) {
                throw new Exception();
            }
        }
    }

    // todo: 추후 jsp에서 어떻게 받을지 결정한 후에 개발하는 것으로 할게요.
    @Override
    public List<BookVO> selectBookList(SelectBookPaginationRequestDTO selectBookPaginationRequestDTO) {
        return null;
    }

    @Override
    public List<BookVO> selectAllBookInfoList() {

        return bookDAO.selectAllBookList();
    }

    @Override
    public BookVO selectBookInfo(SelectBookInfoRequestDTO selectBookInfoRequestDTO) {

        return bookDAO.selectBookInfo(selectBookInfoRequestDTO);
    }

    @Override
    public void registerBookInfo(InsertBookInfoRequestDTO insertBookInfoRequestDTO) throws Exception {

        // 단일 insert 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(bookDAO.insertBookInfo(insertBookInfoRequestDTO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void modifyBookInfo(UpdateBookInfoRequestDTO updateBookInfoRequestDTO) throws Exception {

        if(bookDAO.updateBookInfo(updateBookInfoRequestDTO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void removeBookInfo(RemoveBookInfoRequestDTO removeBookInfoRequestDTO) throws Exception {

        if(bookDAO.deleteBookInfo(removeBookInfoRequestDTO.getBookNo()) != 1) {
            throw new Exception();
        }
    }
}
