package com.dev.pleaseTakecareFiveDucks.anime.controller;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.SelectAnimeThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.SelectAnimePaginationRequestDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.service.AnimeServiceImpl;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/anime")
@RequiredArgsConstructor
public class AnimeController extends BaseController {

    private final AnimeServiceImpl animeService;

    @GetMapping(value = "/main")
    public String goMainPage(
            @RequestParam(required = false)
                    Integer currentPage
            , HttpServletRequest request
            , Model model
    ) {

        if (StringUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }

        SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO = SelectAnimePaginationRequestDTO.builder()
                .currentPage(currentPage)
                .build();

        // todo: pagination logic 불러온 후 테스트 예정입니다. 그 전까지는 아래 vo list로 대체합니다.
        List<AnimeVO> animeVOList = animeService.selectAllAnimeInfoList();

        model.addAttribute("animeVOList", animeVOList);

        return "/anime/main";
    }

    @GetMapping(value = "/register")
    public String goRegisterPage(
            @RequestParam(required = false)
                    Integer currentPage
            , HttpServletRequest request
            , Model model
    ) {
        return "/anime/register";
    }

    @GetMapping(value = "/modifier")
    public String goModifierPage(
            @RequestParam(required = false)
                    Integer currentPage
            , HttpServletRequest request
            , Model model
    ) {
        return "/anime/modifier";
    }

    @GetMapping(value = "/search/image/thumbnail")
    public ModelAndView selectImageThumbnailVOList(
            @RequestParam
            String animeName
    ) throws Exception {

        SelectAnimeThumbnailImageUrlDTO selectAnimeThumbnailImageUrlDTO = SelectAnimeThumbnailImageUrlDTO.builder()
                .animeName(animeName)
                .build();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("anime/register");

        modelAndView.addObject("imageThumbnailVoList", animeService.selectImageThumbnailVOList(selectAnimeThumbnailImageUrlDTO));

        return modelAndView;
    }
}
