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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/drama/main.css">
    <script src="${pageContext.request.contextPath}/resources/js/drama/main.js"></script>
</head>
<body class="ptcfd-body ptcfd-block-drag">
<!-- 메뉴 시작 -->
<%@ include file="../menu.jsp" %>
<!-- 메뉴 끝 -->

<div class="ptcfd-body__inner">
    <%-- 상단의 제목 시작 --%>
    <div class="top">
        <div class="top__left">
            <p class="title">드라마 모음</p>
        </div>
        <div class="top__right">
            <p class="register-text" onclick="goDramaRegisterPage()">등록하기</p>
        </div>
    </div>

    <%-- list section 시작 --%>
    <div class="list-section">
        <c:choose>
            <c:when test="${dramaListResultVO.dramaVOList.size() != 0}">
                <ul>
                    <c:forEach var="dramaVO" items="${dramaListResultVO.dramaVOList}">
                        <li class="item" onclick="goDramaDetailPage('${dramaVO.dramaNo}', '${dramaVO.link}')">
                            <a class="drama-no"><c:out value="${dramaVO.dramaNo}"/></a>
                            <a class="drama-title">
                                <c:out value="${dramaVO.dramaTitle}"/>
                                <span style="display:inline-block;"
                                      onclick="goDramaModifierPage('${dramaVO.dramaNo}')">&nbsp;✍🏻
                        </span>
                            </a>
                            <c:choose>
                                <c:when test="${dramaVO.broadcastStateEnum.toString() == 'end'}">
                                    <a class="drama-board-state being" style="color:#000AFF;">종영🔚</a>
                                </c:when>
                                <c:when test="${dramaVO.broadcastStateEnum.toString() == 'yet'}">
                                    <a class="drama-board-state finished" style="color:#FF0000;">미종영🔄</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="drama-board-state vacation" style="color:#04CF00;">조기종영⛱</a>
                                </c:otherwise>
                            </c:choose>
                            <a class="drama-contents-made-nature"><c:out value="${dramaVO.madeKoreanName}"/></a>
                            <a class="drama-author"><c:out value="${dramaVO.dramaAuthor}"/></a>
                            <a class="drama-reg-dt"><c:out value="${dramaVO.dramaRegDt}"/></a>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <div class="wrap-drama-default">
                    <p class="drama-default-word">❌<br><br>등록된 드라마가 없습니다</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <%-- search section 시작 --%>
    <c:if test="${dramaListResultVO.dramaVOList.size()} != 0">
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
            <c:if test="${dramaListResultVO.pageHandler.totalCnt != null && dramaListResultVO.pageHandler.totalCnt != 0}">
                <c:if test="${dramaListResultVO.pageHandler.showPrev}">
                    <a class="first-left-side"
                       href="<c:url value="/drama/main${dramaListResultVO.pageHandler.sc.getQueryString(dramaListResultVO.pageHandler.beginPage)}"/>">
                        맨 앞으로
                    </a>
                </c:if>
                <ul>
                    <c:forEach var="i"
                               begin="${dramaListResultVO.pageHandler.beginPage}"
                               end="${dramaListResultVO.pageHandler.endPage}">
                        <li class="item ${i == dramaListResultVO.pageHandler.sc.page? "active" : ""}">
                            <a href="<c:url value="/drama/main${dramaListResultVO.pageHandler.sc.getQueryString(i)}"/>">
                                    ${i}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
                <c:if test="${dramaListResultVO.pageHandler.showNext}">
                    <a class="last-right-side"
                       href="<c:url value="/drama/main${dramaListResultVO.pageHandler.sc.getQueryString(dramaListResultVO.pageHandler.endPage)}"/>">
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
