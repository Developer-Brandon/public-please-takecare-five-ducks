package com.dev.pleaseTakecareFiveDucks.movie.service;

import com.dev.pleaseTakecareFiveDucks.config.db.mapper.MovieDAO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.InsertMovieThumbnailInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.SelectMovieThumbnailImageListRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.UpdateMovieThumbnailInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.MovieVO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.InsertMovieInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.SelectMovieInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.UpdateMovieInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.UpdateMovieStateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieDAO movieDAO;

    private boolean validateFileAttachedOrNot(String filePath, String fileName) {
        if(!filePath.isEmpty() && !fileName.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer selectMovieTotalCnt() {
        return movieDAO.getMovieTotalCnt();
    }

    @Override
    public void removeAllMovieInfoList() throws Exception {

        // 만약 movie가 0개 초과로 있다면....
        int movieCnt = movieDAO.getMovieTotalCnt();

        if(movieCnt > 0) {

            // 실제 삭제가 된 데이터들이 size와 일치해야 합니다.
            int removedMovieCnt = movieDAO.deleteAll();

            // 만약, 일치하지 않는다면 Exception을 throw 합니다.
            if(movieCnt != removedMovieCnt) {
                throw new Exception();
            }
        }
    }

    // todo: 추후 jsp에서 어떻게 받을지 결정한 후에 개발하는 것으로 할게요.
    @Override
    public List<MovieVO> selectMovieList() {
        return null;
    }

    @Override
    public List<MovieVO> selectAllMovieInfoList() {

        List<MovieVO> movieVOList = movieDAO.selectAllMovieList();

        List<Integer> movieNoList = movieVOList
                .stream()
                .map(MovieVO::getMovieNo)
                .collect(Collectors.toList());

        SelectMovieThumbnailImageListRequestDTO selectMovieThumbnailImageListRequestDTO = SelectMovieThumbnailImageListRequestDTO.builder()
                .movieNoList(movieNoList)
                .build();

        List<MovieThumbnailVO> movieThumbnailVOList = movieDAO.selectMovieThumbnailImageListByMovieNo(selectMovieThumbnailImageListRequestDTO);

        // movieVOList를 순회하며, movieNo가 같은 요소들끼리 찾아서 filePullPath를 set해줍니다.
        movieVOList.forEach(e -> {

            //
            movieThumbnailVOList.forEach(f -> {
                if(e.getMovieNo().equals(f.getMovieNo())) {
                    e.setFilePullPath(f.getFilePullPath());
                }
            });
        });

        return movieVOList;
    }

    @Override
    public MovieVO selectMovieInfo(SelectMovieInfoRequestDTO selectMovieInfoRequestDTO) {

        // 단일 조회 시, left outer join 으로 조회수와 썸네일을 조회합니다.
        // 리스트 같은경우 다르게 조회하므로 selectAllMovieInfoList를 참고해주시면 됩니다.

        return movieDAO.selectMovieInfo(selectMovieInfoRequestDTO);
    }

    @Override
    public void registerMovieInfo(InsertMovieInfoRequestDTO insertMovieInfoRequestDTO) throws Exception {


        // 단일 insert 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(movieDAO.insertMovieInfo(insertMovieInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String filePath = insertMovieInfoRequestDTO.getFilePath();
        String fileName = insertMovieInfoRequestDTO.getFileName();

        if(validateFileAttachedOrNot(filePath, fileName)) {

            // 썸네일을 삽입하기 위한 dto를 준비합니다.

            InsertMovieThumbnailInfoRequestDTO insertMovieThumbnailInfoRequestDTO = InsertMovieThumbnailInfoRequestDTO.builder()
                    .movieNo(insertMovieInfoRequestDTO.getInsertedMovieNo())
                    .filePath(filePath)
                    .fileName(fileName)
                    .build();

            // 썸네일을 삽입합니다.
            if(movieDAO.insertMovieThumbnailInfo(insertMovieThumbnailInfoRequestDTO) != 1) {
                throw new Exception();
            }
        }
    }

    @Override
    public void modifyMovieInfo(UpdateMovieInfoRequestDTO updateMovieInfoRequestDTO) throws Exception {

        // 단일 update 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(movieDAO.updateMovieInfo(updateMovieInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String filePath = updateMovieInfoRequestDTO.getFilePath();
        String fileName = updateMovieInfoRequestDTO.getFileName();

        if(validateFileAttachedOrNot(filePath, fileName)) {

            // 썸네일을 update하기 위한 dto를 준비합니다.

            UpdateMovieThumbnailInfoRequestDTO updateMovieThumbnailInfoRequestDTO = UpdateMovieThumbnailInfoRequestDTO.builder()
                    .movieNo(updateMovieInfoRequestDTO.getMovieNo())
                    .filePath(filePath)
                    .fileName(fileName)
                    .build();

            // 썸네일을 삽입합니다.
            if(movieDAO.updateMovieThumbnailInfo(updateMovieThumbnailInfoRequestDTO) != 1) {
                throw new Exception();
            }
        }
    }

    @Override
    public void modifyMovieState(UpdateMovieStateRequestDTO updateMovieStateRequestDTO) throws Exception {

        if(movieDAO.updateMovieState(updateMovieStateRequestDTO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void removeMovieInfo(Integer movieNo) throws Exception {


        if(movieDAO.deleteMovieInfo(movieNo) != 1) {
            throw new Exception();
        }

        if(movieDAO.deleteMovieThumbnailInfo(movieNo) != 1) {
            throw new Exception();
        }
    }
}
