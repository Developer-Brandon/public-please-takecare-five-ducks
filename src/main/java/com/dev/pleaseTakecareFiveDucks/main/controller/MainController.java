package com.dev.pleaseTakecareFiveDucks.main.controller;

import com.dev.pleaseTakecareFiveDucks.main.domain.vo.MainPageVO;
import com.dev.pleaseTakecareFiveDucks.main.domain.vo.UserTypeVO;
import com.dev.pleaseTakecareFiveDucks.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController{

    private final MainService mainService;

    @GetMapping(value = "/")
    public String goMainJsp(Model model) throws Exception {

        // TODO: 로그인 화면으로 이동하는 api입니다.
        // 만약 session이 물려있으면 메인화면으로 이동, session이 물려있지 않으면 로그인 화면으로 이동하게끔 개발합니다.

        MainPageVO mainPageVO = mainService.selectMainPageData();

        model.addAttribute("mainBannerVOList", mainPageVO.getMainBannerVOList());
        model.addAttribute("mainAnimeVOList", mainPageVO.getMainAnimeVOList().stream().limit(15).collect(Collectors.toList()));
        model.addAttribute("mainBookVOList", mainPageVO.getMainBookVOList());
        model.addAttribute("mainComicBookVOMap", mainPageVO.getMainComicBookVOMap());
        model.addAttribute("mainEntertainVOList", mainPageVO.getMainEntertainVOList().stream().limit(3).collect(Collectors.toList()));

        return "sign_in";
    }

    @GetMapping(value = "/sign-in")
    public String goSignInPage(Model model) throws Exception {

        return "sign_in";
    }

    @GetMapping(value = "/sign-up")
    public String goSignUpPage(Model model) throws Exception {

        List<UserTypeVO> userTypeVOList = mainService.selectUserTypeList();

        model.addAttribute("userTypeVOList", userTypeVOList);

        return "sign_up";
    }
}
