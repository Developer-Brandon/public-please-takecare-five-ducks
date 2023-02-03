package com.dev.pleaseTakecareFiveDucks.main.controller;

import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.main.domain.vo.MainPageVO;
import com.dev.pleaseTakecareFiveDucks.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController extends BaseController {

    private final MainService mainService;

    @GetMapping(value = "/", produces = TEXT_HTML_FORMAT)
    public String goMainJsp(Model model) throws Exception {

        // TODO: 로그인 화면으로 이동하는 api입니다.
        // 만약 session이 물려있으면 메인화면으로 이동, session이 물려있지 않으면 로그인 화면으로 이동하게끔 개발합니다.

        MainPageVO mainPageVO = mainService.selectMainPageData();

        model.addAttribute("mainBannerVOList", mainPageVO.getMainBannerVOList());
        model.addAttribute("mainAnimeVOList", mainPageVO.getMainAnimeVOList().stream().limit(15).collect(Collectors.toList()));
        model.addAttribute("mainBookVOList", mainPageVO.getMainBookVOList());
        model.addAttribute("mainComicBookVOMap", mainPageVO.getMainComicBookVOMap());
        model.addAttribute("mainEntertainVOList", mainPageVO.getMainEntertainVOList().stream().limit(3).collect(Collectors.toList()));

        return "main";
    }

    @GetMapping(value = "/error307", produces = TEXT_HTML_FORMAT)
    public String goError307(Model model) throws Exception {

        return "error/error307";
    }

    @GetMapping(value = "/error400", produces = TEXT_HTML_FORMAT)
    public String goError400(Model model) throws Exception {

        return "error/error400";
    }

    @GetMapping(value = "/error401", produces = TEXT_HTML_FORMAT)
    public String goError401(Model model) throws Exception {

        return "error/error401";
    }

    @GetMapping(value = "/error404", produces = TEXT_HTML_FORMAT)
    public String goError404(Model model) throws Exception {

        return "error/error404";
    }

    @GetMapping(value = "/error405", produces = TEXT_HTML_FORMAT)
    public String goError405(Model model) throws Exception {

        return "error/error405";
    }

    @GetMapping(value = "/error500", produces = TEXT_HTML_FORMAT)
    public String goError500(Model model) throws Exception {

        return "error/error500";
    }

    @GetMapping(value = "/error503", produces = TEXT_HTML_FORMAT)
    public String goError503(Model model) throws Exception {

        return "error/error503";
    }
}
