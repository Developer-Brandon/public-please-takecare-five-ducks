package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.MainComicBookDetailVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.MainComicBookVO;

import java.util.List;

public interface ComicBookDAO {

    int getComicBookTotalCnt();

    int getTotalCntByCondition(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO);

    List<MainComicBookDetailVO> selectMainComicBookList(MainComicBookRequestDTO mainComicBookRequestDTO);

    List<ComicBookVO> selectAllComicBookList();

    List<ComicBookVO> selectComicBookPaginationList(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO);

    int selectComicBookThumbnailImageCntByBookNo(Integer comicBookNo);

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

    int deleteAll();
}
