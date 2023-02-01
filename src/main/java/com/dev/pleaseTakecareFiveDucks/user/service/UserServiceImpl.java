package com.dev.pleaseTakecareFiveDucks.user.service;

import com.dev.pleaseTakecareFiveDucks.config.db.mapper.*;
import com.dev.pleaseTakecareFiveDucks.user.domain.vo.UserTypeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MainDAO mainDAO;

    private final AnimeDAO animeDAO;

    private final BookDAO bookDAO;

    private final ComicBookDAO comicBookDAO;

    private final DramaDAO dramaDAO;

    private final MovieDAO movieDAO;

    @Override
    public List<UserTypeVO> selectUserTypeList() throws Exception {
        return mainDAO.selectUserTypeList();
    }
}
