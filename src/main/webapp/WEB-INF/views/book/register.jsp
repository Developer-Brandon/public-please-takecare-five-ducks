<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/book/register.css">
    <script src="${pageContext.request.contextPath}/resources/js/book/register.js"></script>
</head>
<body class="ptcfd-body ptcfd-block-drag">
<!-- 메뉴 시작 -->
<%@ include file="../menu.jsp" %>
<!-- 메뉴 끝 -->

<%-- 메인 시작 --%>
<div class="ptcfd-body__inner">
    <%-- 상단의 제목 시작 --%>
    <div class="top">
        <div class="top__left">
            <p class="title">✍🏼책 등록하기</p>
        </div>
        <div class="top__right">
        </div>
    </div>

    <%-- 각각 요소들의 섹션 --%>
    <%--    <form onsubmit="return false;">--%>
    <div class="item-section">
        <div class="item">
            <div class="item__left">
                <p class="title">제목<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content title-input">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">책 종류<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <ul>
                    <c:forEach var="bookType" items="${bookTypeList}">
                        <li onclick="selectBookType('${bookType.bookTypeEnglish}','${bookType.bookTypeNo}')">
                            <c:choose>
                                <c:when test="${bookType.bookTypeEnglish == 'SCRIPT'}">
                                    <p class="content book-type-text ${bookType.bookTypeEnglish}" style="color:${bookType.hexCode};">${bookType.bookTypeKorean}</p>
                                </c:when>
                                <c:when test="${bookType.bookTypeEnglish == 'BOOK_SUMMARY'}">
                                    <p class="content book-type-text ${bookType.bookTypeEnglish}" style="color:${bookType.hexCode};">${bookType.bookTypeKorean}</p>
                                </c:when>
                                <c:when test="${bookType.bookTypeEnglish == 'BOOK_PDF'}">
                                    <p class="content book-type-text ${bookType.bookTypeEnglish}" style="color:${bookType.hexCode};">${bookType.bookTypeKorean}</p>
                                </c:when>
                                <c:when test="${bookType.bookTypeEnglish == 'STUDY_SUMMARY'}">
                                    <p class="content book-type-text ${bookType.bookTypeEnglish}" style="color:${bookType.hexCode};">${bookType.bookTypeKorean}</p>
                                </c:when>
                                <c:when test="${bookType.bookTypeEnglish == 'LECTURE_SUMMARY'}">
                                    <p class="content book-type-text ${bookType.bookTypeEnglish}" style="color:${bookType.hexCode};">${bookType.bookTypeKorean}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="content book-type-text NOVEL" style="color:${bookType.hexCode};">
                                            ${bookType.bookTypeKorean}
                                    </p>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">컨텐츠제작자<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content author-input">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">컨텐츠제작일자</p>
            </div>
            <div class="item__right">
                <input class="content book-reg-dt" type='number' placeholder="19800922">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">제작국가<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <ul>
                    <c:forEach var="contentsMadeNatureInfoVO" items="${contentsMadeNatureInfoList}">
                        <li onclick="selectMadeNature('${contentsMadeNatureInfoVO.madeNatureNo}', '${contentsMadeNatureInfoList.size()}')">
                            <p class="content
                                made-nature-no-text
                                made-nature-no-text${contentsMadeNatureInfoVO.madeNatureNo}">
                                    ${contentsMadeNatureInfoVO.koreanName}
                            </p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">importLink<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content import-link">
            </div>
        </div>
    </div>

    <%-- 하단의 섹션 --%>
    <div class="register-bottom">
        <div class="register-bottom__inner">
            <p class="register-text">등록하기</p>
        </div>
    </div>
    <%-- </form> --%>
</div>
<%-- 메인 끝 --%>

<!-- footer 시작 -->
<%@ include file="../footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
