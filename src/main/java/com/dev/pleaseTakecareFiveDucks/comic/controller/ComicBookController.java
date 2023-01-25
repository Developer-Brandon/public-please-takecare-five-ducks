package com.dev.pleaseTakecareFiveDucks.comic.controller;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.SelectAnimePaginationRequestDTO;
import com.dev.pleaseTakecareFiveDucks.anime.domain.vo.result.AnimeListResultVO;
import com.dev.pleaseTakecareFiveDucks.anime.service.AnimeService;
import com.dev.pleaseTakecareFiveDucks.comic.domain.dto.request.SelectComicBookPaginationRequestDTO;
import com.dev.pleaseTakecareFiveDucks.comic.domain.vo.result.ComicBookListResultVO;
import com.dev.pleaseTakecareFiveDucks.comic.service.ComicBookService;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/comic")
@RequiredArgsConstructor
public class ComicBookController extends BaseController {

    private final ComicBookService comicBookService;

    private final ContentsMadeNatureService contentsMadeNatureService;

    @GetMapping(value = "/main", produces = TEXT_HTML_FORMAT)
    public String goMainPage(
            @RequestParam(required = false)
                    Integer currentPage
            , @RequestParam(required = false)
                    String title
            , Model model
            , HttpServletRequest request
            , HttpServletResponse response
    ) throws Exception {

        if (StringUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }

        SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO = SelectComicBookPaginationRequestDTO.builder()
                .currentPage(currentPage)
                .build();

        if (StringUtils.isEmpty(title)) {
            title = "";
        }

        selectComicBookPaginationRequestDTO.setTitle(title);

        ComicBookListResultVO comicBookListResultVO = comicBookService.selectComicBookPaginationList(selectComicBookPaginationRequestDTO);

        model.addAttribute("comicBookListResultVO", comicBookListResultVO);

        // TODO: 추후 게시판 검색창에 쿠키개념 도입 예정
        //        https://velog.io/@max9106/JSP-Cookie%EC%BF%A0%ED%82%A4-p4k5b1auzs
        //        https://soonggi.tistory.com/66
        //        Cookie[] cookies = request.getCookies();
        //
        //        if(cookies != null) {
        //            for(Cookie cookie : cookies){
        //
        //                // 만약 쿠키가 비어있다면?
        //                if(!cookie.getName().equals("title")) {
        //
        //                    // title에 관련된 쿠키만 저장할 것이므로, title 파라미터로 부터 title 값을 가져옵니다.
        //                    String searchTitle = title;
        //
        //                    System.out.println("searchTitle값: " + searchTitle);
        //
        //                    // 쿠키를 굽고
        //                    Cookie searchKeywordCookie = new Cookie("title", searchTitle);
        //
        //                    // 쿠키의 시간을 지정합니다.
        //                    searchKeywordCookie.setMaxAge(60*60*24);
        //
        //                    // 응답 값에 쿠키를 더합니다.
        //                    response.addCookie(searchKeywordCookie);
        //                }
        //            }
        //        }

        return "/comic/main";
    }

    @GetMapping(value = "/register")
    public String goRegisterPage(
            HttpServletRequest request
            , Model model
    ) throws Exception {

        model.addAttribute("comicBookSerialStateList", comicBookService.selectComicBookSerialStateList());

        model.addAttribute("contentsMadeNatureInfoList", contentsMadeNatureService.selectContentsMadeNatureInfoList());

        return "/comic/register";
    }

    @GetMapping(value = "/modifier/{comicBookNo}")
    public String goModifierPage(
            HttpServletRequest request
            , Model model
            , @PathVariable
                    Integer comicBookNo
    ) throws Exception {

        model.addAttribute("comicBookNo", comicBookNo);

        model.addAttribute("comicBookSerialStateList", comicBookService.selectComicBookSerialStateList());

        model.addAttribute("contentsMadeNatureInfoList", contentsMadeNatureService.selectContentsMadeNatureInfoList());

        return "/comic/modifier";
    }
}
