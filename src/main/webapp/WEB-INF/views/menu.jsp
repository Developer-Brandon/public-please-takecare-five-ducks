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
        <script>
            function getRootUrl() {
             return '${pageContext.request.contextPath}'
            }
        </script>
        <script src="${pageContext.request.contextPath}/resources/js/menu.js"></script>
    </head>
    <body class="ptcfd-body">
        <nav class="menu-bar">
            <div class="menu-bar__inner">
                <div class="wrap-logo">
                    <div class="wrap-logo__inner">
                        <img id="logo"
                             onclick="goMainPage(getRootUrl())"
                             src="${pageContext.request.contextPath}/resources/images/main-logo-icon.png"
                             alt="로고 이미지입니다" />
                    </div>
                </div>
                <div class="wrap-menu">
                    <div class="normal-menu">
                        <ul class="menu-list-ul">
                            <li class="menu-list-li">
                                <a href="${pageContext.request.contextPath}/anime/main">애니</a>
                            </li>
                            <li class="menu-list-li">
                                <a href="${pageContext.request.contextPath}/book/main">책</a>
                            </li>
                            <li class="menu-list-li">
                                <a href="${pageContext.request.contextPath}/comic/main">만화책</a>
                            </li>
                            <li class="menu-list-li">
                                <a href="${pageContext.request.contextPath}/drama/main">드라마</a>
                            </li>
                            <li class="menu-list-li">
                                <a href="${pageContext.request.contextPath}/movie/main">영화</a>
                            </li>
                            <%--<li class="menu-list-li">--%>
                            <%--    <a href="/util/main">유틸</a>--%>
                            <%--</li>--%>
                        </ul>
                    </div>
                    <div class="auth-menu">
                        <ul class="my-page-menu-list-ul">
                            <%--<li class="my-page-menu-list-li">--%>
                            <%--    <a href="${pageContext.request.contextPath}/mypage/main">내정보</a>--%>
                            <%--</li>--%>
                            <li class="my-page-menu-list-li">
                                <a class="logout">로그아웃</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </body>
</html>

