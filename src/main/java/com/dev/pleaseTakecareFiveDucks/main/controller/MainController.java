package com.dev.pleaseTakecareFiveDucks.main.controller;

import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.MainComicBookDetailVO;
import com.dev.pleaseTakecareFiveDucks.main.domain.vo.MainPageVO;
import com.dev.pleaseTakecareFiveDucks.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class MainController{

    private final MainService mainService;

    @GetMapping(value = "/")
    public String goMainJsp(Locale locale, Model model) throws Exception {

        // TODO: 로그인 화면으로 이동하는 api입니다.
        // 만약 session이 물려있으면 메인화면으로 이동, session이 물려있지 않으면 로그인 화면으로 이동하게끔 개발합니다.

        MainPageVO mainPageVO = mainService.selectMainPageData();

        model.addAttribute("mainBannerVOList", mainPageVO.getMainBannerVOList());
        model.addAttribute("mainAnimeVOList", mainPageVO.getMainAnimeVOList());
        model.addAttribute("mainBookVOList", mainPageVO.getMainBookVOList());
        model.addAttribute("mainComicBookVOMap", mainPageVO.getMainComicBookVOMap());
        model.addAttribute("mainEntertainVOList", mainPageVO.getMainEntertainVOList());

        return "main";
    }
}
