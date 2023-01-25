package com.dev.pleaseTakecareFiveDucks.book.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.SelectBookThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookTypeVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.result.BookListResultVO;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.BookDAO;
import com.dev.pleaseTakecareFiveDucks.config.util.PageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookDAO bookDAO;

    @Override
    public List<BookTypeVO> selectBookTypeList() throws Exception {
        return bookDAO.selectBookTypeList();
    }

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

    @Override
    public BookListResultVO selectBookPaginationList(SelectBookPaginationRequestDTO selectBookPaginationRequestDTO) {

        int totalCnt = bookDAO.getTotalCntByCondition(selectBookPaginationRequestDTO);

        PageHandler pageHandler = new PageHandler(totalCnt, selectBookPaginationRequestDTO.getCurrentPage(), selectBookPaginationRequestDTO.getTitle());

        // 만약 현재의 페이지가.. 1 -> 0, 2 -> 1, 3 -> 2, 4 -> 3 대로 오프셋 설정
        Integer offset = selectBookPaginationRequestDTO.getCurrentPage() - 1;

        // PageSize는 DTO에서 기본으로 10으로 처리되어 있습니다.
        selectBookPaginationRequestDTO.setOffset(offset * selectBookPaginationRequestDTO.getPageSize());

        selectBookPaginationRequestDTO.setPageSize(selectBookPaginationRequestDTO.getPageSize());

        List<BookVO> bookVOList = bookDAO.selectBookPaginationList(selectBookPaginationRequestDTO);

        return BookListResultVO.builder()
                .pageHandler(pageHandler)
                .bookVOList(bookVOList)
                .build();
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

        // 만약 썸네일을 첨부했다면...
        String webThumbnailUrl = insertBookInfoRequestDTO.getWebThumbnailUrl();

        if(!webThumbnailUrl.isEmpty()) {

            // 썸네일을 삽입하기 위한 dto를 준비합니다.

            InsertBookThumbnailInfoRequestDTO insertBookThumbnailInfoRequestDTO = InsertBookThumbnailInfoRequestDTO.builder()
                    .bookNo(insertBookInfoRequestDTO.getInsertedBookNo())
                    .webThumbnailUrl(webThumbnailUrl)
                    .build();

            // 썸네일을 삽입합니다.
            if(bookDAO.insertBookThumbnailInfo(insertBookThumbnailInfoRequestDTO) != 1) {
                throw new Exception();
            }
        }
    }

    @Override
    public void modifyBookInfo(UpdateBookInfoRequestDTO updateBookInfoRequestDTO) throws Exception {

        // 단일 update 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(bookDAO.updateBookInfo(updateBookInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String webThumbnailUrl = updateBookInfoRequestDTO.getWebThumbnailUrl();

        if(!webThumbnailUrl.isEmpty()) {

            // 그리고 만약 썸네일 이 있다면 update를 하고, 없다면 insert를 합니다.
            // (혹시모를 예외처리입니다)
            if(bookDAO.selectBookThumbnailImageByBookNo(updateBookInfoRequestDTO.getBookNo()) > 0) {

                // 썸네일을 update하기 위한 dto를 준비합니다.
                UpdateBookThumbnailInfoRequestDTO updateBookThumbnailInfoRequestDTO = UpdateBookThumbnailInfoRequestDTO.builder()
                        .bookNo(updateBookInfoRequestDTO.getBookNo())
                        .webThumbnailUrl(webThumbnailUrl)
                        .build();

                // 썸네일을 삽입합니다.
                if(bookDAO.updateBookThumbnailInfo(updateBookThumbnailInfoRequestDTO) != 1) {
                    throw new Exception();
                }
            } else {

                // 썸네일을 삽입하기 위한 dto를 준비합니다.
                InsertBookThumbnailInfoRequestDTO insertBookThumbnailInfoRequestDTO = InsertBookThumbnailInfoRequestDTO.builder()
                        .bookNo(updateBookInfoRequestDTO.getBookNo())
                        .webThumbnailUrl(webThumbnailUrl)
                        .build();

                // 썸네일을 삽입합니다.
                if(bookDAO.insertBookThumbnailInfo(insertBookThumbnailInfoRequestDTO) != 1) {
                    throw new Exception();
                }
            }
        }
    }

    @Override
    public void removeBookInfo(DeleteBookInfoRequestDTO deleteBookInfoRequestDTO) throws Exception {

        // fk 문제 때문에, fk의 맨 마지막에 걸려있는 데이터부터 삭제해야 합니다.
        if(bookDAO.deleteBookThumbnailInfo(deleteBookInfoRequestDTO.getBookNo()) != 1) {
            throw new Exception();
        }

        if(bookDAO.deleteBookInfo(deleteBookInfoRequestDTO.getBookNo()) != 1) {
            throw new Exception();
        }
    }

    @Override
    public List<RawImageThumbnailVO> selectImageThumbnailVOList(SelectBookThumbnailImageUrlDTO selectBookThumbnailImageUrlDTO) throws Exception {

        // todo: 2023-01-24 기준에 book module에 썸네일이 없기 때문에 우선 썸네일을 선택하는 메소드를 비워두고...
        // 입력단에서 default값을 넣어주고, 선택하는 솔루션은 추후에 작업합니다.

        return null;
    }

    @Override
    public void registerBookViewCnt(InsertBookViewCntRequestDTO insertBookViewCntRequestDTO) throws Exception {

        if(bookDAO.insertBookViewCnt(insertBookViewCntRequestDTO) != 1) {
            throw new Exception();
        }
    }
}
