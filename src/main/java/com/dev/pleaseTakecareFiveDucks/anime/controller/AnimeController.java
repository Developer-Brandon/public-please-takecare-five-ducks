package com.dev.pleaseTakecareFiveDucks.anime.controller;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.SelectAnimePaginationRequestDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.AnimeListResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.service.AnimeService;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/anime")
@RequiredArgsConstructor
public class AnimeController extends BaseController {

    private final AnimeService animeService;

    private final ContentsMadeNatureService contentsMadeNatureService;

    @GetMapping(value = "/main")
    public String goMainPage(
            @RequestParam(required = false)
                    Integer currentPage
            , Model model
    ) throws Exception{

        if (StringUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }

        SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO = SelectAnimePaginationRequestDTO.builder()
                .currentPage(currentPage)
                .build();

        AnimeListResultVO animeListResultVO = animeService.selectAnimePaginationList(selectAnimePaginationRequestDTO);

        model.addAttribute("animeListResultVO", animeListResultVO);

        return "/anime/main";
    }

    @GetMapping(value = "/register")
    public String goRegisterPage(
            HttpServletRequest request
            , Model model
    ) throws Exception {

        model.addAttribute("animeFinalizedList", animeService.selectAnimeFinalizedList());

        model.addAttribute("contentsMadeNatureInfoList", contentsMadeNatureService.selectContentsMadeNatureInfoList());

        return "/anime/register";
    }

    @GetMapping(value = "/modifier/{animeNo}")
    public String goModifierPage(
            HttpServletRequest request
            , Model model
            , @PathVariable
            Integer animeNo
    ) throws Exception {

        model.addAttribute("animeNo", animeNo);

        model.addAttribute("animeFinalizedList", animeService.selectAnimeFinalizedList());

        model.addAttribute("contentsMadeNatureInfoList", contentsMadeNatureService.selectContentsMadeNatureInfoList());

        return "/anime/modifier";
    }
}
