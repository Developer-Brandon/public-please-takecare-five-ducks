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
    <script type="text/javascript">
     function goAnimeDetailPage(animeNo) {
      console.log(animeNo);
     }

     function goAnimePage() {
      console.log("애니 페이지로 이동하기")
     }

     function goBookPage(){
      console.log("책 페이지로 이동하기")
     }
    </script>
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

    <%--    --%>
    <!-- 가장 핫한 애니 시작 -->
    <div class="hot-anime-list">
        <div class="title">
            <p class="main-title">가장 핫한 애니 추천</p>
            <p class="sub-title">장르 불문 인기 애니를 만나보세요~</p>
        </div>
        <div class="anime-thumbnail-list">
            <c:forEach var="anime" items="${mainAnimeVOList}">
                <div class="thumbnail-box">
                    <div class="thumbnail-box__inner" onclick="goAnimeDetailPage(${anime.animeNo})">
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
            <button class="see-more-anime-button" onclick="goAnimePage()">애니 더 보기</button>
        </div>
    </div>
    <!--  -->

    <!--  -->
    <!-- 각종 도서(책) 모음 시작 -->
    <div class="some-book-list">
        <div class="title">
            <p class="main-title">각종 도서(책) 모음</p>
            <p class="sub-title">강의 요약 스크립트 / 기술서적 / 인터넷 소설 등등</p>
        </div>
        <div class="some-book-list">
            <div class="some-book-list__inner">
                <c:forEach var="book" items="${mainBookVOList}">
                    <div class="book-item">
                        <div class="book-item__left">
                            <img src="${pageContext.request.contextPath}/resources/images/book-icon.png"
                                 alt="책 아이콘"
                                 class="book-icon">
                        </div>
                        <div class="book-item__right">
                            <div class="top-title">
                                <p id="book-title"><c:out value="${book.bookTitle}"/></p>
                                <p id="book-type">
                                    <span>[</span>
                                        <c:out value="${book.bookTypeEnglish}"/>
                                    <span>]</span>
                                </p>
                                <p id="book-reg-dt"><c:out value="${book.bookRegDt}"/></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="wrap-more-book-button">
                <button class="see-more-book-button" onclick="goBookPage()">도서(책) 더보기</button>
            </div>
        </div>
    </div>
    <%--    <div class="comic-list">--%>
    <%--        <div class="comic-list__inner">--%>
    <%--            <div class="title">--%>
    <%--                <p class="title-content">--%>
    <%--                    <img src="${pageContext.request.contextPath}/resources/images/book-icon.png"--%>
    <%--                         style="width:13px; height:13px;margin-right:4px;"--%>
    <%--                    />일반 만화 리스트</p>--%>
    <%--            </div>--%>
    <%--            <div class="content">--%>
    <%--                <div class="content__inner"></div>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--    </div>--%>
    <!--  -->

    <!--  -->
    <!-- ETC 시작 -->
    <%--    <div class="etc-list">--%>
    <%--        <div class="etc-list__inner">--%>
    <%--            <!-- 애니 시작 -->--%>
    <%--            <div class="anime-box">--%>
    <%--                <div class="anime-list">--%>
    <%--                    <div class="anime-list__inner">--%>
    <%--                        <div class="title">--%>
    <%--                            <p class="title-content">--%>
    <%--                                <img--%>
    <%--                                        src="${pageContext.request.contextPath}/resources/images/anime-icon.png"--%>
    <%--                                        style="width:9px; height:9px;margin-right:4px;"--%>
    <%--                                />애니 리스트</p>--%>
    <%--                            <!-- <div id="ptcfd-border"></div> -->--%>
    <%--                        </div>--%>
    <%--                        <div class="content">--%>
    <%--                            <div class="content__inner"></div>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            </div>--%>

    <%--            <!-- 게임 시작 -->--%>
    <%--            <div class="game-box">--%>
    <%--                <div class="game-list">--%>
    <%--                    <div class="game-list__inner">--%>
    <%--                        <div class="title">--%>
    <%--                            <p class="title-content">--%>
    <%--                                <img--%>
    <%--                                        src="${pageContext.request.contextPath}/resources/images/game-icon.png"--%>
    <%--                                        style="width:9px; height:9px;margin-right:4px;"--%>
    <%--                                />게임 리스트</p>--%>
    <%--                            <!-- <div id="ptcfd-border"></div> -->--%>
    <%--                        </div>--%>
    <%--                        <div class="content">--%>
    <%--                            <div class="content__inner"></div>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            </div>--%>

    <%--            <!-- 유틸 시작 -->--%>
    <%--            <div class="util-box">--%>
    <%--                <div class="util-list">--%>
    <%--                    <div class="util-list__inner">--%>
    <%--                        <div class="title">--%>
    <%--                            <p class="title-content">--%>
    <%--                                <img--%>
    <%--                                        src="${pageContext.request.contextPath}/resources/images/util-icon.png"--%>
    <%--                                        style="width:9px; height:9px;margin-right:4px;"--%>
    <%--                                />유틸 리스트</p>--%>
    <%--                            <!-- <div id="ptcfd-border"></div> -->--%>
    <%--                        </div>--%>
    <%--                        <div class="content">--%>
    <%--                            <div class="content__inner"></div>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--    </div>--%>
    <!--  -->

    <!-- 메인 끝 -->

    <!-- footer 시작 -->
    <%@ include file="footer.jsp" %>
    <!-- footer 끝 -->
</body>
</html>
