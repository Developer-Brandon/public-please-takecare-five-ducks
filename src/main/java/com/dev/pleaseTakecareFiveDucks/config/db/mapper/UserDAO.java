package com.dev.pleaseTakecareFiveDucks.config.db.mapper;

import com.dev.pleaseTakecareFiveDucks.user.domain.vo.UserTypeVO;

import java.util.List;

public interface UserDAO {

    List<UserTypeVO> selectUserTypeList();
}
