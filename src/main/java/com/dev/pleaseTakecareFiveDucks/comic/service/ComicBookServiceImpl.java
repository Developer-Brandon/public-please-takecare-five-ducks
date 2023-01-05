package com.dev.pleaseTakecareFiveDucks.comic.service;

import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.ComicBookVO;
import com.dev.pleaseTakecareFiveDucks.config.db.mapper.ComicBookDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComicBookServiceImpl implements ComicBookService{

    private final ComicBookDAO comicBookDAO;

    private boolean validateFileAttachedOrNot(String filePath, String fileName) {
        if(!filePath.isEmpty() && !fileName.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

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

        List<ComicBookVO> comicBookVOList = comicBookDAO.selectAllComicBookList();

        List<Integer> comicBookNoList = comicBookVOList
                .stream()
                .map(ComicBookVO::getBookNo)
                .collect(Collectors.toList());

        SelectComicBookThumbnailImageListRequestDTO comicBookThumbnailImageListRequestDTO = SelectComicBookThumbnailImageListRequestDTO.builder()
                .comicBookNoList(comicBookNoList)
                .build();

        List<ComicBookThumbnailVO> comicBookThumbnailVOList = comicBookDAO.selectComicBookThumbnailImageListByComicBookNo(comicBookThumbnailImageListRequestDTO);

        // comicBookVOList를 순회하며, animeNo가 같은 요소들끼리 찾아서 filePullPath를 set해줍니다.
        comicBookVOList.forEach(e -> {

            //
            comicBookThumbnailVOList.forEach(f -> {
                if(e.getBookNo().equals(f.getBookNo())) {
                    e.setFileFullPath(f.getFilePullPath());
                }
            });
        });

        return comicBookVOList;
    }

    @Override
    public ComicBookVO selectComicBookInfo(SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO) {

        // 단일 조회 시, left outer join 으로 조회수와 썸네일을 조회합니다.
        // 리스트 같은경우 다르게 조회하므로 selectAllComicBookInfoList를 참고해주시면 됩니다.

        return comicBookDAO.selectComicBookInfo(selectComicBookInfoRequestDTO);
    }

    @Override
    public void registerComicBookInfo(InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO) throws Exception {

        // 단일 insert 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(comicBookDAO.insertComicBookInfo(insertComicBookInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String filePath = insertComicBookInfoRequestDTO.getFilePath();
        String fileName = insertComicBookInfoRequestDTO.getFileName();

        if(validateFileAttachedOrNot(filePath, fileName)) {

            // 썸네일을 삽입하기 위한 dto를 준비합니다.

            InsertComicBookThumbnailInfoRequestDTO insertComicBookThumbnailInfoRequestDTO = InsertComicBookThumbnailInfoRequestDTO.builder()
                    .bookNo(insertComicBookInfoRequestDTO.getInsertedComicBookNo())
                    .filePath(filePath)
                    .fileName(fileName)
                    .build();

            // 썸네일을 삽입합니다.
            if(comicBookDAO.insertComicBookThumbnailInfo(insertComicBookThumbnailInfoRequestDTO) != 1) {
                throw new Exception();
            }
        }
    }

    @Override
    public void modifyComicBookInfo(UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO) throws Exception {

        // 단일 update 이므로, 한개가 insert되지 않으면 excpetion을 일으킵니다.
        if(comicBookDAO.updateComicBookInfo(updateComicBookInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면....
        String filePath = updateComicBookInfoRequestDTO.getFilePath();
        String fileName = updateComicBookInfoRequestDTO.getFileName();

        if(validateFileAttachedOrNot(filePath, fileName)) {

            // 썸네일을 update하기 위한 dto를 준비합니다.

            UpdateComicBookThumbnailInfoRequestDTO updateComicBookThumbnailInfoRequestDTO = UpdateComicBookThumbnailInfoRequestDTO.builder()
                    .bookNo(updateComicBookInfoRequestDTO.getBookNo())
                    .filePath(filePath)
                    .fileName(fileName)
                    .build();

            // 썸네일을 삽입합니다.
            if(comicBookDAO.updateComicBookThumbnailInfo(updateComicBookThumbnailInfoRequestDTO) != 1) {
                throw new Exception();
            }
        }
    }

    @Override
    public void removeComicBookInfo(DeleteComicBookInfoRequestDTO deleteComicBookInfoRequestDTO) throws Exception {

        if(comicBookDAO.deleteComicBookInfo(deleteComicBookInfoRequestDTO.getBookNo()) != 1) {
            throw new Exception();
        }

        if(comicBookDAO.deleteComicBookThumbnailInfo(deleteComicBookInfoRequestDTO.getBookNo()) != 1) {
            throw new Exception();
        }

    }
}
