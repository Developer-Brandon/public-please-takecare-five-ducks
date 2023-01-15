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
     function goNotionPage(link) {
      window.open(link)
     }

     function goAnimePage() {
      location.href = './anime/main'
     }

     function goBookPage() {
      location.href = './book/main'
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

    <!--    -->
    <!-- 가장 핫한 애니 시작 -->
    <div class="hot-anime-list">
        <div class="title">
            <p class="main-title">가장 핫한 애니 추천</p>
            <p class="sub-title">장르 불문 인기 애니를 만나보세요~</p>
        </div>
        <div class="anime-thumbnail-list">
            <c:forEach var="anime" items="${mainAnimeVOList}">
                <div class="thumbnail-box">
                    <div class="thumbnail-box__inner" onclick="goNotionPage('${anime.link}')">
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
    <!-- 가장 핫한 애니 끝 -->
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
                    <div class="book-item" onclick="goNotionPage('${book.link}')">
                        <div class="book-item__left">
                            <img src="${pageContext.request.contextPath}/resources/images/book-icon.png"
                                 alt="책 아이콘"
                                 class="book-icon">
                        </div>
                        <div class="book-item__right">
                            <div class="top-title">
                                <p id="book-title"><c:out value="${book.bookTitle}"/></p>
                                <p id="book-type" class="<c:choose>
                                        <c:when test="${book.bookTypeEnglish == 'SCRIPT'}">book-type_script</c:when>
                                        <c:when test="${book.bookTypeEnglish == 'STUDY_SUMMARY'}">book-type_study-summary</c:when>
                                        <c:when test="${book.bookTypeEnglish == 'BOOK_SUMMARY'}">book-type_book-summary</c:when>
                                        <c:when test="${book.bookTypeEnglish == 'LECTURE_SUMMARY'}">book-type_lecture_summary</c:when>
                                        <c:when test="${book.bookTypeEnglish == 'NOVEL'}">book-type_novel</c:when>
                                        <c:otherwise>book-type_default</c:otherwise>
                                    </c:choose>">
                                    <span style="font-weight:800 !important;">[</span>
                                    <c:out value="${book.bookTypeEnglish}"/>
                                    <span style="font-weight:800 !important;">]</span>
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
    <!-- 각종 도서(책) 모음 끝 -->
    <!--  -->

<%--        title 빼놓고는 css 선언되어 있지 않음, 추후 데이터 insert한 후에 마저 완성할 것 --%>
    <%--        <div class="comic-book-list">--%>
    <%--            <div class="title">--%>
    <%--                <p class="main-title">내가 찾던 바로 그 만화책!</p>--%>
    <%--                <p class="sub-title">옛날 고전 만화부터, 최신 만화까지 다양하게 즐겨보세요~</p>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--        <div class="comic-book-cover">--%>
    <%--            <div class="comic-book-cover__inner">--%>
    <%--                <c:forEach var="comic" items="${mainComicBookVOMap.get('1990')}">--%>
    <%--                    <div class="comic-book-section">--%>
    <%--                        <img src="" alt="" class="comic-thumbnail"/>--%>
    <%--                        <div class="introduce">--%>
    <%--                            <div class="introduce__inner">--%>
    <%--                                <div class="title">comic.comicBookTitle</div>--%>
    <%--                                <div class="author">comic.comicBookAuthor</div></div>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>
    <%--                </c:forEach>--%>
    <%--                <c:forEach var="comic" items="${mainComicBookVOMap.get('2000')}">--%>
    <%--                    <div class="comic-book-section">--%>
    <%--                        <img src="" alt="" class="comic-thumbnail"/>--%>
    <%--                        <div class="introduce">--%>
    <%--                            <div class="introduce__inner">--%>
    <%--                                <div class="title">comic.comicBookTitle</div>--%>
    <%--                                <div class="author">comic.comicBookAuthor</div></div>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>
    <%--                </c:forEach>--%>
    <%--                <c:forEach var="comic" items="${mainComicBookVOMap.get('2010')}">--%>
    <%--                    <div class="comic-book-section">--%>
    <%--                        <img src="" alt="" class="comic-thumbnail"/>--%>
    <%--                        <div class="introduce">--%>
    <%--                            <div class="introduce__inner">--%>
    <%--                                <div class="title">comic.comicBookTitle</div>--%>
    <%--                                <div class="author">comic.comicBookAuthor</div></div>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>
    <%--                </c:forEach>--%>
    <%--            </div>--%>
    <%--        </div>--%>


    <!-- 메인 끝 -->

    <!-- footer 시작 -->
    <%@ include file="footer.jsp" %>
    <!-- footer 끝 -->
</body>
</html>
