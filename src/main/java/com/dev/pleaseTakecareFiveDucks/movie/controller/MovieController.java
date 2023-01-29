package com.dev.pleaseTakecareFiveDucks.movie.controller;

import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.contents.service.ContentsMadeNatureService;
import com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request.SelectMoviePaginationRequestDTO;
import com.dev.pleaseTakecareFiveDucks.movie.domain.vo.result.MovieListResultVO;
import com.dev.pleaseTakecareFiveDucks.movie.service.MovieService;
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
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController extends BaseController {

    private final MovieService movieService;

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

        SelectMoviePaginationRequestDTO selectMoviePaginationRequestDTO = SelectMoviePaginationRequestDTO.builder()
                .currentPage(currentPage)
                .build();

        if (StringUtils.isEmpty(title)) {
            title = "";
        }

        selectMoviePaginationRequestDTO.setTitle(title);

        MovieListResultVO movieListResultVO = movieService.selectMoviePaginationList(selectMoviePaginationRequestDTO);

        model.addAttribute("movieListResultVO", movieListResultVO);

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

        return "/movie/main";
    }

    @GetMapping(value = "/register")
    public String goRegisterPage(
            HttpServletRequest request
            , Model model
    ) throws Exception {

        model.addAttribute("contentsMadeNatureInfoList", contentsMadeNatureService.selectContentsMadeNatureInfoList());

        return "/movie/register";
    }

    @GetMapping(value = "/modifier/{movieNo}")
    public String goModifierPage(
            HttpServletRequest request
            , Model model
            , @PathVariable
                    Integer movieNo
    ) throws Exception {

        model.addAttribute("movieNo", movieNo);

        model.addAttribute("contentsMadeNatureInfoList", contentsMadeNatureService.selectContentsMadeNatureInfoList());

        return "/movie/modifier";
    }
}
