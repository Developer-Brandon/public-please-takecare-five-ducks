package com.dev.pleaseTakecareFiveDucks.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/base")
public class BaseConfigController {

    public static final String TEXT_HTML_FORMAT = "text/html; charset=UTF-8";
    public static final String TEXT_PLAIN_FORMAT = "text/plain; charset=UTF-8";
    public static final String JSON_FORMAT = MediaType.APPLICATION_JSON_VALUE;
}
