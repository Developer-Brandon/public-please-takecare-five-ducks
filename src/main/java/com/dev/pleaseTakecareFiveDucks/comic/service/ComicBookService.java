package com.dev.pleaseTakecareFiveDucks.comic.service;

import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.InsertComicBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.SelectComicBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.SelectComicBookPaginationRequestDTO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.UpdateComicBookInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;

import java.util.List;

public interface ComicBookService {

    Integer selectComicBookTotalCnt();

    void removeAllComicBookInfoList();

    List<ComicBookVO> selectComicBookList(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO);

    List<ComicBookVO> selectAllComicBookInfoList();

    ComicBookVO selectComicBookInfo(SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO);

    void registerComicBookInfo(InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO);

    void modifyComicBookInfo(UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO);

    void removeComicBookInfo(Integer bookNo);
}
