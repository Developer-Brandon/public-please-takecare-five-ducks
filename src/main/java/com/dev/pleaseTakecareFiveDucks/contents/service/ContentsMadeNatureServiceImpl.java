package com.dev.pleaseTakecareFiveDucks.contents.service;

import com.dev.pleaseTakecareFiveDucks.config.db.mapper.ContentsMadeNatureDAO;
import com.dev.pleaseTakecareFiveDucks.contents.domain.dto.request.*;
import com.dev.pleaseTakecareFiveDucks.contents.domain.vo.ContentsMadeNatureInfoVO;
import com.dev.pleaseTakecareFiveDucks.contents.domain.vo.ContentsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentsMadeNatureServiceImpl implements ContentsMadeNatureService{

    private final ContentsMadeNatureDAO contentsMadeNatureDAO;

    @Override
    public List<ContentsMadeNatureInfoVO> selectContentsMadeNatureInfoList() throws Exception {
        return contentsMadeNatureDAO.selectContentsMadeNatureList();
    }

    @Override
    public Integer selectContentsMadeNatureTotalCnt() {
        return null;
    }

    @Override
    public void removeAllContentsMadeNatureInfoList() {
    }

    @Override
    public List<ContentsVO> selectContentsMadeNatureList(SelectContentsPaginationRequestDTO selectContentsPaginationRequestDTO) {
        return null;
    }

    @Override
    public List<ContentsVO> selectAllContentsMadeNatureInfoList() {
        return null;
    }

    @Override
    public ContentsVO selectContentsMadeNatureInfo(SelectContentsInfoRequestDTO selectContentsInfoRequestDTO) {
        return null;
    }

    @Override
    public void registerContentsMadeNatureInfo(InsertContentsInfoRequestDTO insertContentsInfoRequestDTO) {

    }

    @Override
    public void modifyContentsMadeNatureInfo(UpdateContentsInfoRequestDTO updateContentsInfoRequestDTO) {

    }

    @Override
    public void modifyContentsMadeNatureState(UpdateContentsStateRequestDTO updateContentsStateRequestDTO) {

    }

    @Override
    public void removeContentsMadeNatureInfo(Integer bookNo) {

    }
}
