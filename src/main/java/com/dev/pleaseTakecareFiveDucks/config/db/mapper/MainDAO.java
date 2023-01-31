package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.main.domain.vo.MainBannerVO;
import com.dev.pleaseTakecareFiveDucks.main.domain.vo.UserTypeVO;

import java.util.List;

public interface MainDAO {

    List<MainBannerVO> selectMainBannerList();

    List<UserTypeVO> selectUserTypeList();
}
