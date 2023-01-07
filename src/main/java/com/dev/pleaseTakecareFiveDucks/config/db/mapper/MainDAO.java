package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.main.domain.vo.MainBannerVO;

import java.util.List;

public interface MainDAO {

    List<MainBannerVO> selectMainBannerList();
}
