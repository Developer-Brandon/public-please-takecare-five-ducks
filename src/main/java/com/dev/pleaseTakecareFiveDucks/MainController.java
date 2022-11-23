package com.dev.pleaseTakecareFiveDucks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class MainController{

    // 메인을 어떻게 꾸밀지 생각해보기

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goMainJsp(Locale locale, Model model) {

        return "main";
    }
}
