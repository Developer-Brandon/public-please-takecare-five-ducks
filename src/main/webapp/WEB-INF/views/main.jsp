<html>
<!-- jsp reload 하는 법 -->
<!-- https://velog.io/@coreminw/Intellij%EC%97%90%EC%84%9C-JSP-%EC%88%98%EC%A0%95%EC%8B%9C-%EC%9E%90%EB%8F%99-%EB%A6%AC%EB%A1%9C%EB%93%9C-%EB%90%98%EB%8F%84%EB%A1%9D-%EC%84%A4%EC%A0%95 -->
<!-- reload시 주의점: css의 reloading을 고려하여 ctrl + shift + R로 새로고침을 실행해야합니다.-->
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="page_header.jsp" %>
    <!-- -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/util/component/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body class="custom-body">
<!-- 메뉴 시작 -->
<%@ include file="menu.jsp" %>
<!-- 메뉴 끝 -->

<!-- 메인 시작 -->
<div class="image-box">
    <img class="goku"
         src="${pageContext.request.contextPath}/resources/images/goku.png"
         alt="손오공 유년시절">
</div>
<div class="body__inner">
    <!-- 인기 만화 시작 -->
    <div class="hot-anime-list">
        <div class="title">
            <p id="main-title">가장 핫한 애니 추천</p>
            <p id="sub-title">장르 불문 인기 애니를 만나보세요~</p>
        </div>
        <div class="anime-thumbnail-list">
            <c:forEach var="anime" items="${mainAnimeVOList}">
                <div class="thumbnail-box">
                    <%--<p><c:out value="${anime.animeNo}"/></p>--%>
                    <div class="thumbnail-box__inner">
                        <div class="thumbnail">
                            <p><c:out value="${anime.animeTitle}"/></p>
                            <p><c:out value="${anime.animeAuthor}"/></p>
                            <p><c:out value="${anime.animeRegDt}"/></p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="wrap-more-anime-button">
            <button class="see-more-anime-button">애니 더 보기</button>
        </div>
    </div>
    <!--  -->

    <!--  -->
    <!-- 만화 시작 -->
    <div class="comic-list">
        <div class="comic-list__inner">
            <div class="title">
                <p class="title-content">
                    <img
                            src="${pageContext.request.contextPath}/resources/images/book-icon.png"
                            style="width:13px; height:13px;margin-right:4px;"
                    />일반 만화 리스트</p>
                <!-- <div id="ptcfd-border"></div> -->
            </div>
            <div class="content">
                <div class="content__inner"></div>
            </div>
        </div>
    </div>
    <!--  -->

    <!--  -->
    <!-- ETC 시작 -->
    <div class="etc-list">
        <div class="etc-list__inner">
            <!-- 애니 시작 -->
            <div class="anime-box">
                <div class="anime-list">
                    <div class="anime-list__inner">
                        <div class="title">
                            <p class="title-content">
                                <img
                                        src="${pageContext.request.contextPath}/resources/images/anime-icon.png"
                                        style="width:9px; height:9px;margin-right:4px;"
                                />애니 리스트</p>
                            <!-- <div id="ptcfd-border"></div> -->
                        </div>
                        <div class="content">
                            <div class="content__inner"></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 게임 시작 -->
            <div class="game-box">
                <div class="game-list">
                    <div class="game-list__inner">
                        <div class="title">
                            <p class="title-content">
                                <img
                                        src="${pageContext.request.contextPath}/resources/images/game-icon.png"
                                        style="width:9px; height:9px;margin-right:4px;"
                                />게임 리스트</p>
                            <!-- <div id="ptcfd-border"></div> -->
                        </div>
                        <div class="content">
                            <div class="content__inner"></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 유틸 시작 -->
            <div class="util-box">
                <div class="util-list">
                    <div class="util-list__inner">
                        <div class="title">
                            <p class="title-content">
                                <img
                                        src="${pageContext.request.contextPath}/resources/images/util-icon.png"
                                        style="width:9px; height:9px;margin-right:4px;"
                                />유틸 리스트</p>
                            <!-- <div id="ptcfd-border"></div> -->
                        </div>
                        <div class="content">
                            <div class="content__inner"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--  -->

    <!-- 메인 끝 -->

    <!-- footer 시작 -->
    <%@ include file="footer.jsp" %>
    <!-- footer 끝 -->
</body>
</html>
