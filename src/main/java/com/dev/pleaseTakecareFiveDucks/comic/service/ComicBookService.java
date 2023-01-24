package com.dev.pleaseTakecareFiveDucks.comic.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.book.domain.dto.SelectBookThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.result.ComicBookListResultVO;

import java.util.List;

public interface ComicBookService {

    Integer selectComicBookTotalCnt();

    void removeAllComicBookInfoList() throws Exception;

    ComicBookListResultVO selectComicBookPaginationList(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO);

    List<ComicBookVO> selectAllComicBookInfoList();

    ComicBookVO selectComicBookInfo(SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO);

    void registerComicBookInfo(InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO) throws Exception;

    void modifyComicBookInfo(UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO) throws Exception;

    void removeComicBookInfo(DeleteComicBookInfoRequestDTO deleteComicBookInfoRequestDTO) throws Exception;

    List<RawImageThumbnailVO> selectImageThumbnailVOList(SelectBookThumbnailImageUrlDTO selectBookThumbnailImageUrlDTO) throws Exception;

    void registerComicBookViewCnt(InsertComicBookViewCntRequestDTO insertComicBookViewCntRequestDTO) throws Exception;
}
