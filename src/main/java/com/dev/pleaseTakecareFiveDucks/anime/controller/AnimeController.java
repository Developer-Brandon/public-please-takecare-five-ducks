package com.dev.pleaseTakecareFiveDucks.anime.controller;

import com.dev.pleaseTakecareFiveDucks.anime.domain.dto.request.SelectAnimeInfoRequestDTO;
import com.dev.pleaseTakecareFiveDucks.anime.service.AnimeServiceImpl;
import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/anime")
@RequiredArgsConstructor
public class AnimeController extends BaseController {

    private final AnimeServiceImpl animeService;

    @GetMapping(value ="/main")
    public String goMainPage(Model model) {

        return "/anime/index";
    }
}
