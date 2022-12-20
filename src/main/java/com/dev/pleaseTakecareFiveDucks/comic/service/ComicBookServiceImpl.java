package com.dev.pleaseTakecareFiveDucks.comic.service;

import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.ComicBookDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComicBookServiceImpl implements ComicBookService{

    private final ComicBookDAO comicBookDAO;

    @Override
    public Integer selectComicBookTotalCnt() {
        return comicBookDAO.getComicBookTotalCnt();
    }

    @Override
    public void removeAllComicBookInfoList() throws Exception {

        // 만약 comicBookTotalCnt 0개 초과로 있다면...
        int comicBookTotalCnt = comicBookDAO.getComicBookTotalCnt();

        if(comicBookTotalCnt > 0) {

            // 실제 삭제가 된 데이터들이 size와 일치해야 합니다.
            int removedBookCnt = comicBookDAO.deleteAll();

            // 만약, 일치하지 않는다면 Exception을 throw 합니다.
            if(comicBookTotalCnt != removedBookCnt) {
                throw new Exception();
            }
        }
    }

    // todo: 추후 jsp에서 어떻게 받을지 결정한 후에 개발하는 것으로 할게요.
    @Override
    public List<ComicBookVO> selectComicBookList(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO) {
        return null;
    }

    @Override
    public List<ComicBookVO> selectAllComicBookInfoList() {

        return comicBookDAO.selectAllComicBookList();
    }

    @Override
    public ComicBookVO selectComicBookInfo(SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO) {

        return comicBookDAO.selectComicBookInfo(selectComicBookInfoRequestDTO);
    }

    @Override
    public void registerComicBookInfo(InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO) throws Exception {

        // 단일 insert 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(comicBookDAO.insertComicBookInfo(insertComicBookInfoRequestDTO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void modifyComicBookInfo(UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO) throws Exception {

        if(comicBookDAO.updateComicBookInfo(updateComicBookInfoRequestDTO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void removeComicBookInfo(DeleteComicBookInfoRequestDTO deleteComicBookInfoRequestDTO) throws Exception {

        if(comicBookDAO.deleteComicBookInfo(deleteComicBookInfoRequestDTO.getBookNo()) != 1) {
            throw new Exception();
        }

    }
}
