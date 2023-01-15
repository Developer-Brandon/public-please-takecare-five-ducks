package com.dev.pleaseTakecareFiveDucks.drama.service;

import com.dev.pleaseTakecareFiveDucks.config.db.mapper.DramaDAO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.drama.domain.vo.DramaVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DramaServiceImpl implements DramaService{

    private final DramaDAO dramaDAO;

    private boolean validateFileAttachedOrNot(String filePath, String fileName) {
        if(!filePath.isEmpty() && !fileName.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer selectDramaTotalCnt() {
        return dramaDAO.getDramaTotalCnt();
    }

    @Override
    public void removeAllDramaInfoList() throws Exception {

        // 만약 drama가 0개 초과로 있다면....
        int dramaCnt = dramaDAO.getDramaTotalCnt();

        if(dramaCnt > 0) {

            // 실제 삭제가 된 데이터들이 size와 일치해야 합니다.
            int removedDramaCnt = dramaDAO.deleteAll();

            // 만약, 일치하지 않는다면 Exception을 throw 합니다.
            if(dramaCnt != removedDramaCnt) {
                throw new Exception();
            }
        }
    }

    // todo: 추후 jsp에서 어떻게 받을지 결정한 후에 개발하는 것으로 할게요.
    @Override
    public List<DramaVO> selectDramaList() {
        return null;
    }

    @Override
    public List<DramaVO> selectAllDramaInfoList() {

        List<DramaVO> dramaVOList = dramaDAO.selectAllDramaList();

        List<Integer> dramaNoList = dramaVOList
                .stream()
                .map(DramaVO::getDramaNo)
                .collect(Collectors.toList());

        SelectDramaThumbnailImageListRequestDTO selectDramaThumbnailImageListRequestDTO = SelectDramaThumbnailImageListRequestDTO.builder()
                .dramaNoList(dramaNoList)
                .build();

        List<DramaThumbnailVO> dramaThumbnailVOList = dramaDAO.selectDramaThumbnailImageListByDramaNo(selectDramaThumbnailImageListRequestDTO);

        // dramaVOList를 순회하며, dramaNo가 같은 요소들끼리 찾아서 fileFullPath를 set해줍니다.
        dramaVOList.forEach(e -> {

            //
            dramaThumbnailVOList.forEach(f -> {
                if(e.getDramaNo().equals(f.getDramaNo())) {
                    e.setFileFullPath(f.getFileFullPath());
                }
            });
        });

        return dramaVOList;
    }

    @Override
    public DramaVO selectDramaInfo(SelectDramaInfoRequestDTO selectDramaInfoRequestDTO) {

        // 단일 조회 시, left outer join 으로 조회수와 썸네일을 조회합니다.
        // 리스트 같은경우 다르게 조회하므로 selectAllDramaInfoList를 참고해주시면 됩니다.

        return dramaDAO.selectDramaInfo(selectDramaInfoRequestDTO);
    }

    @Override
    public void registerDramaInfo(InsertDramaInfoRequestDTO insertDramaInfoRequestDTO) throws Exception {


        // 단일 insert 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(dramaDAO.insertDramaInfo(insertDramaInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String filePath = insertDramaInfoRequestDTO.getFilePath();
        String fileName = insertDramaInfoRequestDTO.getFileName();

        if(validateFileAttachedOrNot(filePath, fileName)) {

            // 썸네일을 삽입하기 위한 dto를 준비합니다.

            InsertDramaThumbnailInfoRequestDTO insertDramaThumbnailInfoRequestDTO = InsertDramaThumbnailInfoRequestDTO.builder()
                    .dramaNo(insertDramaInfoRequestDTO.getInsertedDramaNo())
                    .filePath(filePath)
                    .fileName(fileName)
                    .build();

            // 썸네일을 삽입합니다.
            if(dramaDAO.insertDramaThumbnailInfo(insertDramaThumbnailInfoRequestDTO) != 1) {
                throw new Exception();
            }
        }
    }

    @Override
    public void modifyDramaInfo(UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO) throws Exception {

        // 단일 update 이므로, 한개가 insert되지 않으면 exception을 일으킵니다.
        if(dramaDAO.updateDramaInfo(updateDramaInfoRequestDTO) != 1) {
            throw new Exception();
        }

        // 만약 썸네일을 첨부했다면...
        String filePath = updateDramaInfoRequestDTO.getFilePath();
        String fileName = updateDramaInfoRequestDTO.getFileName();

        if(validateFileAttachedOrNot(filePath, fileName)) {

            // 썸네일을 update하기 위한 dto를 준비합니다.

            UpdateDramaThumbnailInfoRequestDTO updateDramaThumbnailInfoRequestDTO = UpdateDramaThumbnailInfoRequestDTO.builder()
                    .dramaNo(updateDramaInfoRequestDTO.getDramaNo())
                    .filePath(filePath)
                    .fileName(fileName)
                    .build();

            // 썸네일을 삽입합니다.
            if(dramaDAO.updateDramaThumbnailInfo(updateDramaThumbnailInfoRequestDTO) != 1) {
                throw new Exception();
            }
        }
    }

    @Override
    public void modifyDramaState(UpdateDramaStateRequestDTO updateDramaStateRequestDTO) throws Exception {

        if(dramaDAO.updateDramaState(updateDramaStateRequestDTO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void removeDramaInfo(Integer dramaNo) throws Exception {

        if(dramaDAO.deleteDramaInfo(dramaNo) != 1) {
            throw new Exception();
        }

        if(dramaDAO.deleteDramaThumbnailInfo(dramaNo) != 1) {
            throw new Exception();
        }
    }
}
