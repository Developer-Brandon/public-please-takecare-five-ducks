<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="page_header.jsp" %>
    <!-- -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>
<body class="ptcfd-body ptcfd-block-drag">
<!-- 메뉴 시작 -->
<%@ include file="menu.jsp" %>
<!-- 메뉴 끝 -->

<!-- 메인 시작 -->
<div class="image-box">
    <%--  메인 배너 사진 --%>
    <img class="evangalion"
         src="${pageContext.request.contextPath}/resources/images/evangalion.png"
         alt="에반게리온 초호기">
</div>

<%-- 메인 시작 --%>
<div class="ptcfd-body__inner">

    <!-- 가장 핫한 애니 시작 -->
    <div class="wrap-hot-anime-list">
        <div class="wrap-hot-anime-list__inner">
            <!--    -->
            <div class="hot-anime-list">
                <div class="title">
                    <p class="main-title">가장 핫한🔥애니 추천</p>
                    <p class="sub-title">장르 불문 인기 애니를 만나보세요~</p>
                </div>
                <div class="anime-thumbnail-list">
                    <c:forEach var="anime" items="${mainAnimeVOList}">
                        <c:choose>
                            <c:when test="${mainAnimeVOList.size() != 0}">
                                <div class="thumbnail-box">
                                    <div class="thumbnail-box__inner"
                                         style="background-image: url('<c:out value="${anime.webThumbnailUrl}"/>')"
                                         onclick="goNotionPage('${anime.link}')">
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <p class="thumbnail-box__default">등록된 애니가 없습니다</p>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </div>
                <div class="wrap-more-anime-button">
                    <button class="see-more-anime-button" onclick="goAnimePage()">애니 더 보기👉🏻</button>
                </div>
            </div>
            <!--  -->
        </div>
    </div>
    <!-- 가장 핫한 애니 끝 -->

    <!-- 각종 도서(책) 모음 시작 -->
    <div class="wrap-some-book-list">
        <div class="wrap-some-book-list__inner">
            <!--  -->
            <div class="some-book-list">
                <div class="title">
                    <p class="main-title">각종 도서(책)📖 모음</p>
                    <p class="sub-title">강의 요약 스크립트 / 기술서적 / 인터넷 소설 등등</p>
                </div>
                <div class="some-book-list">
                    <div class="some-book-list__inner">
                        <c:forEach var="book" items="${mainBookVOList}">
                            <c:choose>
                                <c:when test="${mainBookVOList.size() != 0}">
                                    <div class="book-item" onclick="goNotionPage('${book.link}')">
                                        <div class="book-item__left">
                                            <img src="${pageContext.request.contextPath}/resources/images/book-icon.png"
                                                 alt="책 아이콘"
                                                 class="book-icon">
                                        </div>
                                        <div class="book-item__right">
                                            <div class="top-title">
                                                <p class="book-title"><c:out value="${book.bookTitle}"/></p>
                                                <p id="book-type" class="
                                            <c:choose>
                                                <c:when test="${book.bookTypeEnglish == 'SCRIPT'}">book-type_script</c:when>
                                                <c:when test="${book.bookTypeEnglish == 'STUDY_SUMMARY'}">book-type_study-summary</c:when>
                                                <c:when test="${book.bookTypeEnglish == 'BOOK_SUMMARY'}">book-type_book-summary</c:when>
                                                <c:when test="${book.bookTypeEnglish == 'LECTURE_SUMMARY'}">book-type_lecture_summary</c:when>
                                                <c:when test="${book.bookTypeEnglish == 'NOVEL'}">book-type_novel</c:when>
                                                <c:otherwise>book-type_default</c:otherwise>
                                            </c:choose>
                                            ">
                                                    <span style="font-weight:800 !important;"></span>
                                                    <c:out value="${book.bookTypeEnglish}"/>
                                                    <span style="font-weight:800 !important;"></span>
                                                </p>
                                                <p id="book-reg-dt"><c:out value="${book.bookRegDt}"/></p>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <p class="some-book-list__inner__default">등록된 책이 없습니다</p>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                    <div class="wrap-more-book-button">
                        <button class="see-more-book-button" onclick="goBookPage()">도서(책) 더보기👉🏻</button>
                    </div>
                </div>
            </div>
            <!--  -->
        </div>
    </div>
    <!-- 각종 도서(책) 모음 끝 -->

    <!-- 내가 찾던 바로 그 만화책 시작 -->
    <div class="wrap-comic-book-list">
        <div class="wrap-comic-book-list__inner">
            <!--  -->
            <div class="comic-book-list">
                <div class="title">
                    <p class="main-title">내가 찾던 바로 그 만화책📚!</p>
                    <p class="sub-title">옛날 고전 만화부터, 최신 만화까지 다양하게 즐겨보세요~</p>
                </div>
                <div class="comic-book-cover">
                    <div class="comic-book-cover__inner">
                        <c:choose>
                            <c:when test="${ainComicBookVOMap.size().get(0).size() > 1}">
                                <c:forEach var="comic" items="${mainComicBookVOMap}">
                                    <%-- 1990 시작 --%>
                                    <c:if test="${comic.key == '1990'}">
                                        <div class="comic-book-section">
                                            <p class="gen-title">📆&nbsp;1990~</p>
                                            <c:forEach var="innerComic" items="${comic.value}">
                                                <div class="wrap-thumbnail-item">
                                                    <c:choose>
                                                        <c:when test="${innerComic.webThumbnailUrl != ''}">
                                                            <div class="thumbnail__default"></div>
                                                            <!-- 추후 썸네일 준비 끝나면 개발 예정 -->
                                                            <!-- <img src="${innerComic.webThumbnailUrl}" alt="" class="thumbnail"> -->
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="thumbnail__default"></div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <div class="introduce">
                                                        <div class="introduce__inner">
                                                            <div class="title">${innerComic.comicBookTitle}</div>
                                                            <div class="author">${innerComic.comicBookAuthor}</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <%-- 2000 시작 --%>
                                    <c:if test="${comic.key == '2000'}">
                                        <div class="comic-book-section">
                                            <p class="gen-title">📆&nbsp;2000~</p>
                                            <img src="" alt="" class="comic-thumbnail"/>
                                            <c:forEach var="innerComic" items="${comic.value}">
                                                <div class="introduce">
                                                    <div class="introduce__inner">
                                                        <div class="title">${innerComic.comicBookTitle}</div>
                                                        <div class="author">${innerComic.comicBookAuthor}</div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <%-- 2010 시작 --%>
                                    <c:if test="${comic.key == '2010'}">
                                        <div class="comic-book-section">
                                            <p class="gen-title">📆&nbsp;2010~</p>
                                            <img src="" alt="" class="comic-thumbnail"/>
                                            <c:forEach var="innerComic" items="${comic.value}">
                                                <div class="introduce">
                                                    <div class="introduce__inner">
                                                        <div class="title">innerComic.comicBookTitle</div>
                                                        <div class="author">innerComic.comicBookAuthor</div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p class="comic-book-cover__inner__default">등록된 만화책이 없습니다</p>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
                <div class="wrap-more-comic-book-button">
                    <div class="see-more-comic-book-button" onclick="goComicPage()">만화책 더보기👉🏻</div>
                </div>
            </div>
            <!--  -->
        </div>
    </div>
    <!-- 내가 찾던 바로 그 만화책 모음 끝 -->

    <!-- 베스트 드라마/영화 모음 시작 -->
    <div class="wrap-drama-and-movie-list">
        <div class="wrap-drama-and-movie-list__inner">
            <!--  -->
            <div class="drama-and-movie-list">
                <div class="title">
                    <p class="main-title">베스트 드라마📺/영화📽 모음</p>
                    <p class="sub-title">인기 드라마와 영화를 보러 출바알~!</p>
                </div>
                <div class="drama-and-movie-list__inner">
                    <c:choose>
                        <c:when test="${mainEntertainVOList.size() > 0}">
                            <c:forEach var="entertain" items="${mainEntertainVOList}">
                                <div class="drama-item" onclick="goNotionPage('${entertain.link}')">
                                    <img src="${entertain.webThumbnailUrl}"
                                         alt="영화, 드라마썸네일입니다."
                                         class="thumbnail"/>
                                    <p class="title">${entertain.title}</p>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p class="drama-and-movie-list__inner__default">등록된 드라마, 영화가 없습니다</p>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="wrap-dam-buttons">
                    <button class="dam-button" onclick="goDramaPage()">드라마 더보기👉🏻</button>
                    <button class="dam-button" onclick="goMoviePage()">영화 더보기👉🏻</button>
                </div>
            </div>
            <!--  -->
        </div>
    </div>
    <!-- 베스트 드라마/영화 모음 끝 -->
</div>
<!-- footer 시작 -->
<%@ include file="footer.jsp" %>
<!-- footer 끝 -->
</body>
<!-- 메인 끝 -->
</html>
