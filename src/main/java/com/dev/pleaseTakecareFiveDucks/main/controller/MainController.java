package com.dev.pleaseTakecareFiveDucks.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class MainController{

    @GetMapping(value = "/")
    public String goMainJsp(Locale locale, Model model) {

        // TODO: 로그인 화면으로 꾸미기
        //


        return "main";
    }
}
