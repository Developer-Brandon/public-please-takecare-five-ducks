<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/drama/register.css">
    <script src="${pageContext.request.contextPath}/resources/js/drama/register.js"></script>
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
            <p class="title">✍🏼드라마 등록하기</p>
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
                <input type="text" class="content title-input"
                       onkeypress="if(window.event.keyCode===13) enterInputValue()">
                <button class="find-thumbnail-button">썸네일 찾기</button>
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
                <p class="title">작가이름<span class="pdcfd-required-symbol">*</span></p>
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
                    <c:forEach var="broadcastStateVO" items="${broadcastStateList}">
                        <li onclick="selectBroadcastState('${broadcastStateVO.broadcastStateEnum.toString()}')">
                            <c:choose>
                                <c:when test="${broadcastStateVO.broadcastStateEnum.toString() == 'end'}">
                                    <a class="end" style="color:#000AFF;">종영🔚</a>
                                </c:when>
                                <c:when test="${broadcastStateVO.broadcastStateEnum.toString() == 'yet'}">
                                    <a class="yet" style="color:#FF0000;">미종영🔄</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="early_end" style="color:#04CF00;">조기종영⛱</a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">방영일자</p>
            </div>
            <div class="item__right">
                <input class="content drama-reg-dt" type='number' placeholder="19800922">
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
