package com.dev.pleaseTakecareFiveDucks.comic.service;

import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;

import java.util.List;

public interface ComicBookService {

    Integer selectComicBookTotalCnt();

    void removeAllComicBookInfoList() throws Exception;

    List<ComicBookVO> selectComicBookList(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO);

    List<ComicBookVO> selectAllComicBookInfoList();

    ComicBookVO selectComicBookInfo(SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO);

    void registerComicBookInfo(InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO) throws Exception;

    void modifyComicBookInfo(UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO) throws Exception;

    void removeComicBookInfo(DeleteComicBookInfoRequestDTO deleteComicBookInfoRequestDTO) throws Exception;
}
