package com.dev.pleaseTakecareFiveDucks.main.controller;

import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.main.domain.vo.MainPageVO;
import com.dev.pleaseTakecareFiveDucks.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController extends BaseController {

    private final MainService mainService;

    @GetMapping(value = "/", produces = TEXT_HTML_FORMAT)
    public String goSignInPage(Model model) throws Exception {

        return "sign_in";
    }

    @GetMapping(value = "/main", produces = TEXT_HTML_FORMAT)
    public String goMainPage(Model model) throws Exception {

        MainPageVO mainPageVO = mainService.selectMainPageData();

        model.addAttribute("mainBannerVOList", mainPageVO.getMainBannerVOList());
        model.addAttribute("mainAnimeVOList", mainPageVO.getMainAnimeVOList().stream().limit(15).collect(Collectors.toList()));
        model.addAttribute("mainBookVOList", mainPageVO.getMainBookVOList());
        model.addAttribute("mainComicBookVOMap", mainPageVO.getMainComicBookVOMap());
        model.addAttribute("mainEntertainVOList", mainPageVO.getMainEntertainVOList().stream().limit(3).collect(Collectors.toList()));

        return "main";
    }

    @PostMapping(value = "/login", produces = TEXT_HTML_FORMAT)
    public String goLoginOkLogic(Model model) throws Exception {
        return "sign_in_request";
    }

    @GetMapping(value = "/error307", produces = TEXT_HTML_FORMAT)
    public String goError307Page(Model model) throws Exception {

        return "error/error307";
    }

    @GetMapping(value = "/error400", produces = TEXT_HTML_FORMAT)
    public String goError400Page(Model model) throws Exception {

        return "error/error400";
    }

    @GetMapping(value = "/error401", produces = TEXT_HTML_FORMAT)
    public String goError401Page(Model model) throws Exception {

        return "error/error401";
    }

    @GetMapping(value = "/error404", produces = TEXT_HTML_FORMAT)
    public String goError404Page(Model model) throws Exception {

        return "error/error404";
    }

    @GetMapping(value = "/error405", produces = TEXT_HTML_FORMAT)
    public String goError405Page(Model model) throws Exception {

        return "error/error405";
    }

    @GetMapping(value = "/error500", produces = TEXT_HTML_FORMAT)
    public String goError500Page(Model model) throws Exception {

        return "error/error500";
    }

    @GetMapping(value = "/error503", produces = TEXT_HTML_FORMAT)
    public String goError503Page(Model model) throws Exception {

        return "error/error503";
    }

    @GetMapping(value = "/exception", produces = TEXT_HTML_FORMAT)
    public String goExceptionPage(Model model) throws Exception {

        return "error/exception";
    }
}
