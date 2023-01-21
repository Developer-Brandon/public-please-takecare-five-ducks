package com.dev.pleaseTakecareFiveDucks.anime.controller;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.SelectAnimeThumbnailImageUrlDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.SelectAnimePaginationRequestDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.AnimeVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.RawImageThumbnailVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.RawAnimeFinalizedResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.RawImageThumbnailResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.service.AnimeService;
import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import com.dev.pleaseTakecareFiveDucks.main.service.MainService;
import com.dev.pleaseTakecareFiveDucks.main.service.MainServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
            @RequestParam(required = false)
                    Integer currentPage
            , HttpServletRequest request
            , Model model
    ) throws Exception {

        model.addAttribute("animeFinalizedList", animeService.selectAnimeFinalizedList());

        model.addAttribute("contentsMadeNatureInfoList", contentsMadeNatureService.selectContentsMadeNatureInfoList());

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

    @ResponseBody
    @GetMapping(value = "/search/image/thumbnail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RawImageThumbnailResultVO selectImageThumbnailVOList(
            @RequestParam
            String animeName
    ) throws Exception {

        SelectAnimeThumbnailImageUrlDTO selectAnimeThumbnailImageUrlDTO = SelectAnimeThumbnailImageUrlDTO.builder()
                .animeName(animeName)
                .build();

        return RawImageThumbnailResultVO.builder()
                .rawImageThumbnailVOArrayList((ArrayList<RawImageThumbnailVO>) animeService.selectImageThumbnailVOList(selectAnimeThumbnailImageUrlDTO))
                .build();
    }

    @ResponseBody
    @GetMapping(value = "/finalized/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RawAnimeFinalizedResultVO selectAnimeFinalizedList(
    ) throws Exception {

        return RawAnimeFinalizedResultVO.builder()
                .finalizedYnEnumArrayList((ArrayList<FinalizedYnEnum>) animeService.selectAnimeFinalizedList())
                .build();
    }
}
