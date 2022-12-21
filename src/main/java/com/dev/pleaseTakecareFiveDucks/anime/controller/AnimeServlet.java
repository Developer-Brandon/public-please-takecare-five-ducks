package com.dev.pleaseTakecareFiveDucks.anime.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
@WebServlet(urlPatterns = {"/anime", "/anime/*"}, loadOnStartup = 1) // @Controller + @RequestMapping을 합친것이 @WebServlet 입니다.
public class AnimeServlet extends BaseHttpServlet {

    @Override
    public void init() throws ServletException {

        // 서블릿 초기화 - 서블릿이 생성 또는 리로딩 때, 단 한번만 수행됨
        System.out.println("init() 실행됨!");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 호출될때마다 반복적으로 수행됩니다.

        // 1. 입력
        // 2. 처리
        // 3. 출력
        System.out.println("req: " + req);
        System.out.println("resp: " + resp);
    }

    @Override
    public void destroy() {

        // 뒷정리 작업 - 서블릿이 unload될 때, 단 한번만 수행됨.
        System.out.println("destory() 실행됨!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  req.getRequestDispatcher("/anime/index.jsp");
    }
}
*/
