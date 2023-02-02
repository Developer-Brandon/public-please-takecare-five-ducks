<%@ page import="java.net.URLDecoder" %>
<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <%-- TODO: 추후 게시판 검색창에 쿠키개념 도입 예정 --%>
    <%--        request.setCharacterEncoding("UTF-8");--%>

    <%--        Cookie[] cookies = request.getCookies();--%>

    <%--        String oldSearchTitle = "";--%>

    <%--        // 만약 쿠키가 비어있지 않다면?--%>
    <%--        if(cookies != null){--%>

    <%--            for(Cookie cookie:cookies){--%>

    <%--                if(cookie.getName().equals("title")){--%>

    <%--                    oldSearchTitle = cookie.getValue();--%>

    <%--                    oldSearchTitle = URLDecoder.decode(oldSearchTitle, "UTF-8");--%>
    <%--                }--%>
    <%--            }--%>
    <%--        }--%>
    <%--        System.out.println(">>클라이언트가 전송한 쿠키정보:"+oldSearchTitle+"["+request.getRemoteAddr()+"]");--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/comic/main.css">
    <script src="${pageContext.request.contextPath}/resources/js/comic/main.js"></script>
</head>
<body class="ptcfd-body ptcfd-block-drag">
<!-- 메뉴 시작 -->
<%@ include file="../menu.jsp" %>
<!-- 메뉴 끝 -->

<div class="ptcfd-body__inner">

    <%-- 상단의 제목 시작 --%>
    <div class="top">
        <div class="top__left">
            <p class="title">만화책 모음</p>
        </div>
        <div class="top__right">
            <p class="register-text" onclick="goComicBookRegisterPage()">등록하기</p>
        </div>
    </div>

    <%-- list section 시작 --%>
    <div class="list-section">
        <c:choose>
            <c:when test="${comicBookListResultVO.comicBookVOList.size() != 0}">
                <ul>
                    <c:forEach var="comicVO" items="${comicBookListResultVO.comicBookVOList}">
                        <li class="item" onclick="goComicBookDetailPage('${comicVO.bookNo}', '${comicVO.link}')">
                            <a class="comic-no"><c:out value="${comicVO.bookNo}"/></a>
                            <a class="comic-title">
                                <c:out value="${comicVO.comicBookTitle}"/>
                                <span style="display:inline-block;"
                                      onclick="goComicBookModifierPage('${comicVO.bookNo}')">&nbsp;✍🏻
                        </span>
                            </a>
                            <c:choose>
                                <c:when test="${comicVO.comicBookSerialStateEnum.toString() == 'being'}">
                                    <a class="being" style="color:#000AFF;">연재중🔄</a>
                                </c:when>
                                <c:when test="${comicVO.comicBookSerialStateEnum.toString() == 'finished'}">
                                    <a class="finished" style="color:#FF0000;">연재완료🔚</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="vacation" style="color:#04CF00;">휴재중⛱</a>
                                </c:otherwise>
                            </c:choose>
                            <a class="comic-author"><c:out value="${comicVO.comicBookAuthor}"/></a>
                            <a class="comic-reg-dt"><c:out value="${comicVO.comicBookRegDt}"/></a>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <div class="wrap-comic-default">
                    <p class="comic-default-word">❌<br><br>등록된 만화책이 없습니다</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <%-- search section 시작 --%>
    <c:if test="${comicBookListResultVO.comicBookVOList.size() != 0}">
        <div class="search-section">
            <div class="wrap-search-input">
                <p class="search-text">👆🏼제목</p>
                <input type="text"
                       class="search-input"
                       onkeypress="if(window.event.keyCode===13) enterSearchInputValue()"
                       value="${oldSearchTitle}"/>

            </div>
        </div>
    </c:if>

    <%-- pagination section 시작 --%>
    <div class="pagination-section">
        <div class="pagination-section__inner">
            <c:if test="${comicBookListResultVO.pageHandler.totalCnt != null && comicBookListResultVO.pageHandler.totalCnt != 0}">
                <c:if test="${comicBookListResultVO.pageHandler.showPrev}">
                    <a class="first-left-side"
                       href="<c:url value="/comic/main${comicBookListResultVO.pageHandler.sc.getQueryString(comicBookListResultVO.pageHandler.beginPage)}"/>">
                        맨 앞으로
                    </a>
                </c:if>
                <ul>
                    <c:forEach var="i"
                               begin="${comicBookListResultVO.pageHandler.beginPage}"
                               end="${comicBookListResultVO.pageHandler.endPage}">
                        <li class="item ${i == comicBookListResultVO.pageHandler.sc.page? "active" : ""}">
                            <a href="<c:url value="/comic/main${comicBookListResultVO.pageHandler.sc.getQueryString(i)}"/>">
                                    ${i}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
                <c:if test="${comicBookListResultVO.pageHandler.showNext}">
                    <a class="last-right-side"
                       href="<c:url value="/comic/main${comicBookListResultVO.pageHandler.sc.getQueryString(comicBookListResultVO.pageHandler.endPage)}"/>">
                        맨 뒤로
                    </a>
                </c:if>
            </c:if>
        </div>
    </div>
    <%-- pagination section 끝 --%>
</div>

<!-- footer 시작 -->
<%@ include file="../footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
