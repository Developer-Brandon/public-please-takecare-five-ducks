package com.dev.pleaseTakecareFiveDucks.main.service;

import com.dev.pleaseTakecareFiveDucks.main.domain.vo.MainPageVO;
import com.dev.pleaseTakecareFiveDucks.main.domain.vo.UserTypeVO;

import java.util.List;

public interface MainService {
    MainPageVO selectMainPageData() throws Exception;
    List<UserTypeVO> selectUserTypeList() throws Exception;
}
