<%--
  Created by IntelliJ IDEA.
  User: dklee
  Date: 2022-12-19
  Time: 오후 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<nav class="menu-bar">
    <div class="menu-bar__inner">
        <div class="logo-title">
            <div class="wrap-logo">
                <img id="logo"
                     src="${pageContext.request.contextPath}/resources/images/rubber-duck.png"
                     alt="로고 이미지입니다" />
            </div>
            <div class="wrap-title">
                <div class="wrap-title__inner">
                    <h1 id="main-title">오덕을 부탁해</h1>
                    <p id="main-description">Please, take care 5ducks</p>
                </div>
            </div>
        </div>
        <div class="menu-list">
            <ul class="menu-list-ul">
                <li class="menu-list-li">
<%--                    ${pageContext.request.contextPath}/WEB-INF/views/comic/index.jsp--%>
                    <a href="">Comic</a>
                </li>
                <li class="menu-list-li">
                    <a href="/anime/main">Anime</a>
                </li>
                <li class="menu-list-li">
                    <a href="movie/index.jsp">Movie</a>
                </li>
                <li class="menu-list-li">
                    <a href="game/index.jsp">Game</a>
                </li>
                <li class="menu-list-li">
                    <a href="util/index.jsp">Util</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

