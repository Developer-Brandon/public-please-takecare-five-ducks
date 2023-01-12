<%--
  Created by IntelliJ IDEA.
  User: dklee
  Date: 2022-12-19
  Time: 오후 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/util/component/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css">
    </head>
    <body>
        <nav class="menu-bar">
            <div class="menu-bar__inner">
                <div class="wrap-logo">
                    <div class="wrap-logo__inner">
                        <img id="logo"
                             src="${pageContext.request.contextPath}/resources/images/main-logo-icon.png"
                             alt="로고 이미지입니다" />
                    </div>
                </div>
                <div class="wrap-menu">
                    <div class="normal-menu">
                        <ul class="menu-list-ul">
                            <li class="menu-list-li">
                                <a href="/anime/main">애니</a>
                            </li>
                            <li class="menu-list-li">
                                <a href="/book/main">책</a>
                            </li>
                            <li class="menu-list-li">
                                <a href="/comicBook/main">만화책</a>
                            </li>
                            <li class="menu-list-li">
                                <a href="/drama/main">드라마</a>
                            </li>
                            <li class="menu-list-li">
                                <a href="/movie/main">영화</a>
                            </li>
                            <li class="menu-list-li">
                                <a href="/util/main">유틸</a>
                            </li>
                        </ul>
                    </div>
                    <div class="auth-menu">
                        <ul class="my-page-menu-list-ul">
                            <li class="my-page-menu-list-li">
                                <a href="/mypage/main">내정보</a>
                            </li>
                            <li class="my-page-menu-list-li">
                                <a href="/logout">로그아웃</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </body>
</html>

