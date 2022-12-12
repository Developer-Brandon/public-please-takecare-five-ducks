package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;

import java.util.List;

public interface ComicBookMapper {

    int getComicBookTotalCnt();

    int deleteAll();

    List<ComicBookVO> selectAllComicBookList();

    List<ComicBookVO> selectComicBookList(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO);

    ComicBookVO selectComicBookInfo(SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO);

    Integer insertComicBookInfo(InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO);

    int updateComicBookInfo(UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO);

    int updateComicBookState(UpdateComicBookStateRequestDTO updateComicBookStateRequestDTO);

    int deleteComicBookInfo(Integer bookNo);
}
