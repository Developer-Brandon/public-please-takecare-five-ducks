package com.dev.pleaseTakecareFiveDucks.config.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BaseController {
    public static final String TEXT_PLAIN_FORMAT = "text/plain; charset=UTF-8";
    public static final String TEXT_HTML_FORMAT = "text/html; charset=UTF-8";
    public static final String JSON_FORMAT = MediaType.APPLICATION_JSON_UTF8_VALUE;
}
