<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <!-- -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/util/component/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/book/register.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
    <script type="text/javascript">
    </script>
</head>
<body class="custom-body">
<!-- 메뉴 시작 -->
<%@ include file="../menu.jsp" %>
<!-- 메뉴 끝 -->

<%-- 메인 시작 --%>
<div class="book-banner">
    <div class="book-banner__inner"></div>
</div>
<div class="body__inner">
    <%-- 상단의 제목 시작 --%>
    <div class="top">
        <div class="top__left">
            <p class="title">책 등록하기</p>
        </div>
        <div class="top__right">
        </div>
    </div>

    <div class="item-section">
        <div class="item">
            <div class="item__left">
                <p class="title">제목</p>
            </div>
            <div class="item__right">
                <input type="text" class="content">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">책 종류</p>
            </div>
            <div class="item__right">
                <input type="text" class="content">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">컨텐츠제작자</p>
            </div>
            <div class="item__right">
                <input type="text" class="content">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">컨텐츠제작일자</p>
            </div>
            <div class="item__right">
                <input type="text" class="content">
            </div>
        </div>
        <div class="item">

            <div class="item__left">
                <p class="title">제작국가</p>
            </div>
            <div class="item__right">
                <input type="text" class="content">
            </div>
        </div>
        <div class="item">
            <div class="item__left">
                <p class="title">importLink</p>
            </div>
            <div class="item__right">
                <input type="text" class="content">
            </div>
        </div>

        <div class="register-bottom">
            <div class="register-bottom__inner">
                <p class="register-text">등록하기</p>
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
