<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/anime/register.css">
    <script src="${pageContext.request.contextPath}/resources/js/anime/register.js"></script>
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
            <p class="title">✍🏼애니 등록하기</p>
        </div>
        <div class="top__right">
        </div>
    </div>

    <%-- 썸네일 자동찾기 섹션 --%>
    <div class="thumbnail-section">
        <div class="thumbnail-section__inner">
            <!-- 썸네일이 없다면 이곳의 dom으로... -->
            <div class="ptcfd-default-thumbnail">
                <div>
                    <p>❌</p>
                    <p>검색된 썸네일이 없습니다</p>
                </div>
            </div>
            <%-- 썸네일이 있다면 이곳의 dom으로... --%>
            <div class="thumbnail-preview-list"></div>
        </div>
    </div>

    <%-- 각각 요소들의 섹션 --%>
    <%--    <form onsubmit="return false;">--%>
    <div class="item-section">
        <div class="item">
            <div class="item__left">
                <p class="thumbnail">썸네일 주소</p>
            </div>
            <div class="item__right">
                <input type="text" class="content thumbnail-input" readonly>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">제목<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content title-input" onkeypress="if(window.event.keyCode===13) enterInputValue()">
                <button class="find-thumbnail-button">썸네일 찾기</button>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">작가<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input type="text" class="content author-input">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">방영상태<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <ul>
                    <c:forEach var="finalizedYnEnum" items="${animeFinalizedList}">
                        <li onclick="selectFinalizedState('${finalizedYnEnum}')">
                            <c:choose>
                                <c:when test="${finalizedYnEnum == 'y'}">
                                    <p class="content finalized-text finalized-text-y" style="color:#FF0000;">완결</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="content finalized-text finalized-text-n" style="color:#2400FF;">
                                        방영중</p>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">방영회수<span class="pdcfd-required-symbol">*</span></p>
            </div>
            <div class="item__right">
                <input class="content board-cast-cnt-input" type='number'>
                <span class="board-cast-cnt-text">&nbsp;회</span>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">최초방영일자</p>
            </div>
            <div class="item__right">
                <input class="content anime-reg-dt" type='number' placeholder="19800922">
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
    <%--    </form>--%>
</div>

<%-- 메인 끝 --%>

<!-- footer 시작 -->
<%@ include file="../footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
