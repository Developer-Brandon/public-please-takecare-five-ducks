<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <!-- -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/util/component/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/anime/modifier.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
    <script type="text/javascript">
    </script>
</head>
<body class="custom-body">
<!-- 메뉴 시작 -->
<%@ include file="../menu.jsp" %>
<!-- 메뉴 끝 -->

<%-- 메인 시작 --%>
<%--<div class="anime-banner">--%>
<%--    <div class="anime-banner__inner"></div>--%>
<%--</div>--%>
<div class="body__inner">
    <%-- 상단의 제목 시작 --%>
    <div class="top">
        <div class="top__left">
            <p class="title">✍🏼애니 수정, 삭제하기</p>
        </div>
        <div class="top__right">
        </div>
    </div>

    <%-- 썸네일 자동찾기 섹션 --%>
    <div class="thumbnail-section">
        <div class="thumbnail-section__inner">
            <%-- <img src="" alt="" class="thumbnail"> --%>
            <%-- img 태그가 없을 시 기본적으로 사진 지정 --%>
            <div class="default-thumbnail"></div>
        </div>
    </div>

    <%-- 각각 요소들의 섹션 --%>
    <div class="item-section">
        <div class="item">
            <div class="item__left">
                <p class="title">제목</p>
            </div>
            <div class="item__right">
                <input type="text" class="content title-input">
                <button class="find-thumbnail-button">썸네일 찾기</button>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">방영상태</p>
            </div>
            <div class="item__right">
                <p class="content finalized-text">완결</p>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">방영회수</p>
            </div>
            <div class="item__right">
                <input type="text" class="content board-cast-cnt-input">
                <span class="board-cast-cnt-text">&nbsp;회</span>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">최초방영일자</p>
            </div>
            <div class="item__right">
                <input type="text" class="content anime-reg-dt">
            </div>
        </div>
        <div class="item">

            <div class="item__left">
                <p class="title">제작국가</p>
            </div>
            <div class="item__right">
                <p class="content made-nature-no-text">일본</p>
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">importLink</p>
            </div>
            <div class="item__right">
                <input type="text" class="content import-link">
            </div>
        </div>
    </div>

    <%-- 하단의 버튼 섹션 --%>
    <div class="modifier-bottom">
        <div class="modifier-bottom__inner">
            <div class="modifier-bottom__inner__left">
                <p class="delete-text">삭제하기</p>
            </div>
            <div class="modifier-bottom__inner__right">
                <p class="modify-text">수정하기</p>
            </div>
        </div>
    </div>
</div>

<%-- 메인 끝 --%>

<!-- footer 시작 -->
<%@ include file="../footer.jsp" %>
<!-- footer 끝 -->
</body>
</html>
