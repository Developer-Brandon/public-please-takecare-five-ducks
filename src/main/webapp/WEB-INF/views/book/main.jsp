<%@ page import="java.net.URLDecoder" %>
<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/book/main.css">
    <script src="${pageContext.request.contextPath}/resources/js/book/main.js"></script>
</head>
<body class="ptcfd-body">
<!-- 메뉴 시작 -->
<%@ include file="../menu.jsp" %>
<!-- 메뉴 끝 -->

<div class="ptcfd-body__inner">

    <%-- 상단의 제목 시작 --%>
    <div class="top">
        <div class="top__left">
            <p class="title">책 모음</p>
        </div>
        <div class="top__right">
            <p class="register-text" onclick="goBookRegisterPage()">등록하기</p>
        </div>
    </div>

    <%-- list section 시작 --%>
    <div class="list-section">
        <ul>
            <c:forEach var="bookVO" items="${bookListResultVO.bookVOList}">
                <li class="item" onclick="goBookDetailPage('${bookVO.bookNo}', '${bookVO.link}')">
                    <a class="book-no"><c:out value="${bookVO.bookNo}"/></a>
                    <a class="book-title">
                        <c:out value="${bookVO.bookTitle}"/>
                        <span onclick="goBookModifierPage('${bookVO.bookNo}')">&nbsp;✍🏻</span>
                    </a>
                    <c:choose>
                        <c:when test="${bookVO.bookTypeEnglish == 'SCRIPT'}">
                            <a class="book-type script">${bookVO.bookTypeKorean}📖</a>
                        </c:when>
                        <c:when test="${bookVO.bookTypeEnglish == 'BOOK_SUMMARY'}">
                            <a class="book-type book-summary">${bookVO.bookTypeKorean}📖</a>
                        </c:when>
                        <c:when test="${bookVO.bookTypeEnglish == 'BOOK_PDF'}">
                            <a class="book-type book-pdf">${bookVO.bookTypeKorean}📖</a>
                        </c:when>
                        <c:when test="${bookVO.bookTypeEnglish == 'STUDY_SUMMARY'}">
                            <a class="book-type study-summary">${bookVO.bookTypeKorean}📖</a>
                        </c:when>
                        <c:when test="${bookVO.bookTypeEnglish == 'LECTURE_SUMMARY'}">
                            <a class="book-type lecture-summary">${bookVO.bookTypeKorean}📖</a>
                        </c:when>
                        <c:otherwise>
                            <a class="book-type novel">${bookVO.bookTypeKorean}📖</a>
                        </c:otherwise>
                    </c:choose>
                    <a class="book-author"><c:out value="${bookVO.bookAuthor}"/></a>
                    <a class="book-reg-dt"><c:out value="${bookVO.regDt}"/></a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <%-- search section 시작 --%>
    <div class="search-section">
        <div class="wrap-search-input">
            <p class="search-text">👆🏼제목</p>
            <input type="text"
                   class="search-input"
                   onkeypress="if(window.event.keyCode===13) enterSearchInputValue()"/>

        </div>
    </div>

    <%-- pagination section 시작 --%>
    <div class="pagination-section">
        <div class="pagination-section__inner">
            <c:if test="${bookListResultVO.pageHandler.totalCnt != null && bookListResultVO.pageHandler.totalCnt != 0}">
                <c:if test="${bookListResultVO.pageHandler.showPrev}">
                    <a class="first-left-side"
                       href="<c:url value="/book/main${bookListResultVO.pageHandler.sc.getQueryString(bookListResultVO.pageHandler.beginPage)}"/>">
                        맨 앞으로
                    </a>
                </c:if>
                <ul>
                    <c:forEach var="i"
                               begin="${bookListResultVO.pageHandler.beginPage}"
                               end="${bookListResultVO.pageHandler.endPage}">
                        <li class="item ${i == bookListResultVO.pageHandler.sc.page? "active" : ""}">
                            <a href="<c:url value="/book/main${bookListResultVO.pageHandler.sc.getQueryString(i)}"/>">
                                    ${i}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
                <c:if test="${bookListResultVO.pageHandler.showNext}">
                    <a class="last-right-side"
                       href="<c:url value="/book/main${bookListResultVO.pageHandler.sc.getQueryString(bookListResultVO.pageHandler.endPage)}"/>">
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
