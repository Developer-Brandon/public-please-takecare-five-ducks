package com.dev.pleaseTakecareFiveDucks.anime.controller;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.SelectAnimeThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.InsertAnimeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.SelectAnimePaginationRequestDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.RawAnimeFinalizedResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.RawImageThumbnailResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.service.AnimeService;
import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
