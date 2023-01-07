package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.MainComicBookDetailVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.MainComicBookVO;

import java.util.List;

public interface ComicBookDAO {

    List<MainComicBookDetailVO> selectMainComicBookList(MainComicBookRequestDTO mainComicBookRequestDTO);

    int getComicBookTotalCnt();

    int deleteAll();

    List<ComicBookVO> selectAllComicBookList();

    List<ComicBookVO> selectComicBookList(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO);

    List<ComicBookThumbnailVO> selectComicBookThumbnailImageListByComicBookNo(SelectComicBookThumbnailImageListRequestDTO selectComicBookThumbnailImageListRequestDTO);

    ComicBookVO selectComicBookInfo(SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO);

    int insertComicBookViewCnt(InsertComicBookViewCntRequestDTO insertComicBookViewCntRequestDTO);

    int insertComicBookInfo(InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO);

    int insertComicBookThumbnailInfo(InsertComicBookThumbnailInfoRequestDTO insertComicBookThumbnailInfoRequestDTO);

    int updateComicBookInfo(UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO);

    int updateComicBookThumbnailInfo(UpdateComicBookThumbnailInfoRequestDTO  updateComicBookThumbnailInfoRequestDTO);

    int updateComicBookState(UpdateComicBookStateRequestDTO updateComicBookStateRequestDTO);

    int deleteComicBookInfo(Integer bookNo);

    int deleteComicBookThumbnailInfo(Integer bookNo);
}
