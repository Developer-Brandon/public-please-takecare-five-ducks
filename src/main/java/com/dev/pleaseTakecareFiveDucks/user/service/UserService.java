package com.dev.pleaseTakecareFiveDucks.user.service;

import com.dev.pleaseTakecareFiveDucks.user.domain.vo.UserTypeVO;

import java.util.List;

public interface UserService {
    List<UserTypeVO> selectUserTypeList() throws Exception;
}
