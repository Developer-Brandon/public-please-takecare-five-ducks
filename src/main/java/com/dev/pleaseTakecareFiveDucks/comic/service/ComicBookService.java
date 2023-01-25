package com.dev.pleaseTakecareFiveDucks.comic.service;

import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.SelectComicBookThumbnailDTO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookSerialStateVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.result.ComicBookListResultVO;

import java.util.ArrayList;
import java.util.List;

public interface ComicBookService {

    Integer selectComicBookTotalCnt() throws Exception;

    List<ComicBookSerialStateVO> selectComicBookSerialStateList() throws Exception;

    void removeAllComicBookInfoList() throws Exception;

    ComicBookListResultVO selectComicBookPaginationList(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO);

    List<ComicBookVO> selectAllComicBookInfoList();

    ComicBookVO selectComicBookInfo(SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO);

    void registerComicBookInfo(InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO) throws Exception;

    void modifyComicBookInfo(UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO) throws Exception;

    void removeComicBookInfo(DeleteComicBookInfoRequestDTO deleteComicBookInfoRequestDTO) throws Exception;

    ArrayList<RawImageThumbnailVO> selectImageThumbnailVOList(SelectComicBookThumbnailDTO selectComicBookThumbnailDTO) throws Exception;

    void registerComicBookViewCnt(InsertComicBookViewCntRequestDTO insertComicBookViewCntRequestDTO) throws Exception;
}
