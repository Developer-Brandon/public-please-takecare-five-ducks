package com.dev.pleaseTakecareFiveDucks.book.controller;

import com.dev.pleaseTakecareFiveDucks.book.domain.dto.request.SelectBookPaginationRequestDTO;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.result.BookListResultVO;
import com.dev.pleaseTakecareFiveDucks.book.service.BookService;
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
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController extends BaseController {

    private final BookService bookService;

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

        SelectBookPaginationRequestDTO selectBookPaginationRequestDTO = SelectBookPaginationRequestDTO.builder()
                .currentPage(currentPage)
                .build();

        if (StringUtils.isEmpty(title)) {
            title = "";
        }

        selectBookPaginationRequestDTO.setTitle(title);

        BookListResultVO bookListResultVO = bookService.selectBookPaginationList(selectBookPaginationRequestDTO);

        model.addAttribute("bookListResultVO", bookListResultVO);

        return "/book/main";
    }

    @GetMapping(value = "/register")
    public String goRegisterPage(
            HttpServletRequest request
            , Model model
    ) throws Exception {

        model.addAttribute("bookTypeList", bookService.selectBookTypeList());

        model.addAttribute("contentsMadeNatureInfoList", contentsMadeNatureService.selectContentsMadeNatureInfoList());

        return "/anime/register";
    }

    @GetMapping(value = "/modifier/{bookNo}")
    public String goModifierPage(
            HttpServletRequest request
            , Model model
            , @PathVariable
            Integer bookNo
    ) throws Exception {

        model.addAttribute("bookNo", bookNo);

        model.addAttribute("bookTypeList", bookService.selectBookTypeList());

        model.addAttribute("contentsMadeNatureInfoList", contentsMadeNatureService.selectContentsMadeNatureInfoList());

        return "/anime/modifier";
    }
}
