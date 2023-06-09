package com.dev.pleaseTakecareFiveDucks.user.controller;

import com.dev.pleaseTakecareFiveDucks.config.controller.BaseController;
import com.dev.pleaseTakecareFiveDucks.user.domain.vo.UserTypeVO;
import com.dev.pleaseTakecareFiveDucks.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping(value = "/sign-in", produces = TEXT_HTML_FORMAT)
    public String goSignInPage(Model model) throws Exception {

        return "sign_in";
    }

    @GetMapping(value = "/sign-up", produces = TEXT_HTML_FORMAT)
    public String goSignUpPage(Model model) throws Exception {

        List<UserTypeVO> userTypeVOList = userService.selectUserTypeList();

        model.addAttribute("userTypeVOList", userTypeVOList);

        return "sign_up";
    }
}
